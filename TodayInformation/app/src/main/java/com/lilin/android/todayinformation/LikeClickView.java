package com.lilin.android.todayinformation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 创建日期：2021/10/11 15:22
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.todayinformation
 * 类说明：
 */

public class LikeClickView extends View {
    private boolean isLike;
    private Bitmap likeBitmap;
    private Bitmap unlikeBitmap;
    private Bitmap shineBitmap;
    private Paint bitmapPanit;
    private int left;
    private int top;
    /**
     * 默认比例必须设置初值大于0才，view才可见
     */
    private float handleScale = 1.0f;
    private int centerX;
    private int centerY;

    public LikeClickView(Context context) {
        this(context,null);
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.jkLikeView);
        isLike = typedArray.getBoolean(R.styleable.jkLikeView_isLike, false);
        typedArray.recycle();
        init();
    }

    private void init() {
        Resources resources = getResources();
        likeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like);
        unlikeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_unlike);
        shineBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like_shining);
        bitmapPanit = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = 0;
        int measureHeight = 0;
        int minHeight = likeBitmap.getHeight() + SystemUtil.dp2px(getContext(),20);
        int minWidth = likeBitmap.getWidth() + SystemUtil.dp2px(getContext(),30);
        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int withSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (withMode != MeasureSpec.EXACTLY){ //未指定控件宽度
            int suggestedMinimumWidth = getSuggestedMinimumWidth();
            int suggestedMinimumHeight = getSuggestedMinimumHeight();
            Log.e("suggestedMinimumWidth",suggestedMinimumWidth+"");
            Log.e("suggestedMinimumHeight",suggestedMinimumHeight+"");
            if (suggestedMinimumWidth == 0){
                measureWidth = minWidth;
            }else {
                measureWidth = Math.max(minWidth,suggestedMinimumWidth);
            }

            if (suggestedMinimumHeight == 0){
                measureHeight = minHeight;
            }else {
                measureHeight = Math.max(minHeight,suggestedMinimumHeight);
            }
        }else {
            measureWidth = Math.max(minWidth,withSize);
            measureHeight = Math.max(minHeight,heightSize);
        }

        setMeasuredDimension(measureWidth,measureHeight);

        //根据测量结果，设置bitmap的位置
        calculateBitmapPosition(measureWidth,measureHeight);
        centerX = measureWidth/2;
        centerY = measureHeight/2;
    }

    /**
     * 由于onDraw会执行多次，可以把bitmap位置计算完后，使用全局变量保存起来见calculateBitmapPosition
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap handBitmap = isLike? likeBitmap : unlikeBitmap;
        //实际上是画布执行动画
        canvas.save(); //保存画布，添加变换，绘制
        canvas.scale(handleScale, handleScale,centerX,centerY);
        canvas.drawBitmap(handBitmap,left,top,bitmapPanit);
        //复位画布的操作，之前变换将被忘记
        canvas.restore();
        if (isLike){
            canvas.drawBitmap(shineBitmap,5+left,0,bitmapPanit);
        }
    }

    /**
     * 根据最后测量结果，计算bitmap绘制位置
     * @param measureWidth
     * @param measureHeight
     */
    private void calculateBitmapPosition(int measureWidth, int measureHeight){
        left = (measureWidth - likeBitmap.getWidth())/2;
        top = (measureHeight- likeBitmap.getHeight())/2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                onClick();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * invalidate会刷新整个view
     */
    private void onClick() {
        isLike = !isLike;
//        Invalidate the whole view. If the view is visible, onDraw(Canvas) will be called at some point in the future
//        invalidate();
//        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "handleScale", 1.0f, 0.8f, 1.0f);
//        animator.setDuration(250);
//        animator.start();
        ValueAnimator animator = ValueAnimator.ofFloat(1.0f, 0.8f, 1.0f);
        animator.setDuration(250);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float)animation.getAnimatedValue();
                handleScale = value;
                invalidate();
            }
        });
    }

    /**
     * 使用自定义动画属性handleScale，必须实现该属性set方法
     * @param value
     */
//    private void setHandleScale(float value){
//        this.handleScale = value;
//        invalidate();
//    }

    /**
     * 当View从window移除的时候，回收资源
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        likeBitmap.recycle();
        unlikeBitmap.recycle();
        shineBitmap.recycle();
    }
}
