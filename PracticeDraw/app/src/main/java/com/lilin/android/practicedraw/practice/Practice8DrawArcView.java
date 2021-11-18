package com.lilin.android.practicedraw.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice8DrawArcView extends View {
    private Paint paint = new Paint();
    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形

        /**
         * left,top,right,bottom 表示圆弧所在矩形
         * useCenter表示是否连接到圆心
         * startAngle:起始角度，顺时针方向与x轴夹角为正，反之为负
         * sweepAngle:扫过的角度
         */
//        canvas.drawArc(200, 100, 800, 500,250);
        paint.setAntiAlias(true);
        //
        canvas.drawArc(200, 100, 800, 500, 250, 100, true, paint); // 绘制扇形

        //填充，不连接圆心
        canvas.drawArc(200, 100, 800, 500, 20, 140, false, paint); // 绘制弧形

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(200, 100, 800, 500, 180, 60, false, paint); // 绘制不封口的弧形
    }
}
