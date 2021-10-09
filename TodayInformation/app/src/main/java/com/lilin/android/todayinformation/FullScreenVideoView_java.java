package com.lilin.android.todayinformation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 创建日期：2021/10/9 11:03
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.todayinformation
 * 类说明：全屏VideoView java 实现
 */

public class FullScreenVideoView_java extends VideoView {
    public FullScreenVideoView_java(Context context) {
        super(context);
    }

    public FullScreenVideoView_java(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenVideoView_java(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = getDefaultSize(0, widthMeasureSpec);
        int heightSize = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(widthSize,heightSize);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
