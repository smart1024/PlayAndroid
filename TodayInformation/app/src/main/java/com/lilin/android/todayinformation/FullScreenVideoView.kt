package com.lilin.android.todayinformation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 创建日期：2021/10/9 10:38
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.todayinformation
 * 类说明：
 */

public class FullScreenVideoView extends VideoView {
    public FullScreenVideoView(Context context) {
        super(context);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * super.onMeasure(widthMeasureSpec, heightMeasureSpec)
     * 系统默认根据宽高比调整视频VideoView大小，导致不能全屏
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = getDefaultSize(0, widthMeasureSpec);
        int measureHeight = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(measureWidth,measureHeight);
    }
}
