package com.lilin.android.practicedraw.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice5DrawOvalView extends View {
    private Paint paint = new Paint();
    public Practice5DrawOvalView(Context context) {
        super(context);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawOval() 方法画椭圆
        //当宽高相等是等价于drawCircle
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float lw = 500;
        int widthPixels = metrics.widthPixels;

        float left = (widthPixels - lw)/ 2f;

        RectF f = new RectF(left,150,left+lw,350);
        canvas.drawOval(f,paint);
    }
}
