package com.lilin.android.todayinformation

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

/**
 * 创建日期：2021/10/9 10:38
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.todayinformation
 * 类说明：
 */
class FullScreenVideoView : VideoView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * super.onMeasure(widthMeasureSpec, heightMeasureSpec)
     * 系统默认根据宽高比调整视频VideoView大小，导致不能全屏
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        val measureWidth = getDefaultSize(0, widthMeasureSpec)
        val measureHeight = getDefaultSize(0, heightMeasureSpec)
        setMeasuredDimension(measureWidth, measureHeight)
    }
}