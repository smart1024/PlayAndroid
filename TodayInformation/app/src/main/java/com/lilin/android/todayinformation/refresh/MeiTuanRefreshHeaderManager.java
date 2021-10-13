package com.lilin.android.todayinformation.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lilin.android.todayinformation.R;
import com.lilin.android.todayinformation.databinding.MeituanHeaderRefreshLayoutBinding;

/**
 * 创建日期：2021/10/12 17:49
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.todayinformation.refresh
 * 类说明：
 */

public class MeiTuanRefreshHeaderManager extends BaseRefreshHeaderManager{
    private ImageView imageView;
    public MeiTuanRefreshHeaderManager(Context context) {
        super(context);
    }

    @Override
    View getHeaderView() {
        View view = mLayoutInflater.inflate(R.layout.meituan_header_refresh_layout, null);
        MeituanHeaderRefreshLayoutBinding bind = MeituanHeaderRefreshLayoutBinding.bind(view);
        imageView = bind.loading;
        return view;
    }

    @Override
    public void idle() {
        imageView.clearAnimation();
        imageView.setImageResource(R.mipmap.pull_image);
        imageView.setScaleX(0);
        imageView.setScaleY(0);
    }

    @Override
    public void pullingDown() {

    }

    @Override
    public void release() {
        imageView.setImageResource(R.drawable.mei_tuan_loading_pre);
        AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
        drawable.start();
    }

    @Override
    public void refreshing() {
        imageView.setImageResource(R.drawable.mei_tuan_loading);
        AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
        drawable.start();
    }

    @Override
    public void pullDownPercent(float percent) { //从0到1变化
        Log.e("pullDownPercent",percent+"");
        imageView.setScaleX(percent);
        imageView.setScaleY(percent);
    }
}
