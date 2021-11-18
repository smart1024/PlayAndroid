package com.lilin.android.practicedraw.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice3DrawRectView extends View {
    private Paint paint = new Paint();
    public Practice3DrawRectView(Context context) {
        super(context);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawRect() 方法画矩形

        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);

        int rectW = 360;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenW = dm.widthPixels;
        int screenH = dm.heightPixels;
        int left = (screenW - rectW)/2;
        int top  = 200;

        paint.setColor(Color.BLACK);
        Rect rect = new Rect(left, top, left+rectW, top+rectW);
        canvas.drawRect(rect,paint);
    }
}
