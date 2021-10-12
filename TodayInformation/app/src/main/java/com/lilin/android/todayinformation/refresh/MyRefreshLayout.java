package com.lilin.android.todayinformation.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建日期：2021/10/12 13:57
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.todayinformation
 * 类说明：
 */

public class MyRefreshLayout extends LinearLayout {
    private int mHeaderViewHeight; //headerView的测量高度

    //topMargin从隐藏时的minHeaderViewHeight,到最大值完全显示并且包含1/3，高度的topMargin
    /**
     * 最大topMargin是headerView高度0.3，
     */
    private int maxHeaderViewHeight;

    /**
     * 最小topMargin是headerView刚好隐藏
     */
    private int minHeaderViewHeight;
    private View mHeaderView;

    private BaseRefreshHeaderManager mRefreshManager;
    private RefreshState mCurrentRefreshState = RefreshState.IDLE;
    private onRefreshListener mOnRefreshListener;
    private float downY;

    public MyRefreshLayout(Context context) {
        this(context,null);
    }

    public MyRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    /**
     * 设置HeaderView
     * 设置topMargin为HeaderView高度的负数，实现默认屏幕内不可见
     */
    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        if (mRefreshManager == null){
            mRefreshManager = new DefaultRefreshHeaderManager(getContext());
        }
        mHeaderView = mRefreshManager.getHeaderView();
        mHeaderView.measure(0,0);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        minHeaderViewHeight = -mHeaderViewHeight;
        maxHeaderViewHeight = (int) (mHeaderViewHeight*0.3);
        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,mHeaderViewHeight);
        params.topMargin = minHeaderViewHeight;
        //将headerview添加最顶部
        addView(mHeaderView,0,params);
    }

    /**
     * 设置自定义
     * @param manager
     */
    public void setRefreshManager(BaseRefreshHeaderManager manager){
        mRefreshManager = manager;
    }

    float interceptDownY;
    float interceptDownX;
    /**
     * 处理触摸事件被子控件消费，导致MyRefreshLayout无法获得触摸事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                interceptDownY = ev.getY();
                interceptDownX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = ev.getY() - interceptDownY;
                float dx = ev.getX() - interceptDownX;
                if (dy > 0 && dy > Math.abs(dx)){ //竖向滑动
                    if (handleChildViewTop()){ //判断子view是否滚动到顶部
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean handleChildViewTop() {
        View view = getChildAt(1);
        Log.e("handleChildViewTop",view+"");
        if (view instanceof ScrollView){
            return RefreshScrollingUtil.isScrollViewOrWebViewToTop(view);
        }

        if (view instanceof RecyclerView){
            return RefreshScrollingUtil.isRecyclerViewToTop((RecyclerView) view);
        }

        if (view instanceof ListView){
            return RefreshScrollingUtil.isAbsListViewToTop((ListView)view);
        }

        if (view instanceof GridView){
            return RefreshScrollingUtil.isAbsListViewToTop((GridView)view);
        }
        return false;
    }

//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        View view = getChildAt(1);
//        Log.e("onFinishInflate",view+"");
//    }

    /**
     * 处理触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();
                if (downY == 0){
                    downY = interceptDownY;
                }
                Log.e("moveY===",moveY+"");
                Log.e("downY===",downY+"");

                float dy = moveY - downY;
                Log.e("dy==",dy+"");
                if (dy > 0){ //改变HeaderView的布局
                    int topMargin = (int) Math.min(dy / 2.0f + minHeaderViewHeight, maxHeaderViewHeight);

                    if (topMargin < 0 && mCurrentRefreshState == RefreshState.IDLE){//下拉过程中headerView还没完全显示
                        mCurrentRefreshState = RefreshState.PULLING_DOWN;
                        handleRefreshState();
                    }else if (topMargin >=0 && mCurrentRefreshState == RefreshState.PULLING_DOWN){//headerView完全显示时
                        mCurrentRefreshState = RefreshState.RELEASE;
                        handleRefreshState();
                    }
                    Log.e("topMargin===",topMargin+"");
                    Log.e("onTouchEvent===",mCurrentRefreshState+"");

                    LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
                    //除以1.8实现阻尼效果，默认是线性变化
                    params.topMargin = topMargin;
                    mHeaderView.setLayoutParams(params);
                }
                return true;
            case MotionEvent.ACTION_UP:
                if(handleEventUp()){
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void handleRefreshState() {
        switch (mCurrentRefreshState){
            case IDLE:
                mRefreshManager.idle();
                break;
            case PULLING_DOWN:
                mRefreshManager.pullingDown();
                break;
            case RELEASE:
                mRefreshManager.release();
                break;
            case REFRESHING:
                mRefreshManager.refreshing();
                break;
        }
    }

    /**
     * 处理手抬起逻辑
     * @return
     */
    private boolean handleEventUp() {
        Log.e("handleEventUp",mCurrentRefreshState+"");
        LinearLayout.LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
        if (mCurrentRefreshState == RefreshState.PULLING_DOWN){//下拉过程中抬起，则隐藏header
            hideHeaderView(params);
        }else if (mCurrentRefreshState == RefreshState.RELEASE){ //释放状态抬起，执行刷新动作
            params.topMargin = 0;
            mHeaderView.setLayoutParams(params);
            mCurrentRefreshState = RefreshState.REFRESHING;
            if (mOnRefreshListener != null){
                mOnRefreshListener.onRefresh();
            }
        }
        return true;
    }

    public void hideHeaderView(LayoutParams params) {
        ValueAnimator animator = ValueAnimator.ofInt(params.topMargin,minHeaderViewHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int)animator.getAnimatedValue();
                params.topMargin = animatedValue;
                mHeaderView.setLayoutParams(params);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentRefreshState = RefreshState.IDLE;
                handleRefreshState();
            }
        });
        animator.setDuration(800);
        animator.start();
    }

    public void finishRefresh(){
        hideHeaderView((LayoutParams) mHeaderView.getLayoutParams());
    }

    /**
     * 空闲状态
     * 正在下拉状态
     * 等待释放状态
     * 正在刷新状态
     */
    private enum RefreshState{
        IDLE,PULLING_DOWN,RELEASE,REFRESHING
    }

    public interface onRefreshListener{
        void onRefresh();
    }

    public void setOnRefreshListener(onRefreshListener onRefreshListener){
        mOnRefreshListener = onRefreshListener;
    }
}
