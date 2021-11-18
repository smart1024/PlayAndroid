package com.lilin.android.practicedraw.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class Practice2DrawCircleView extends View {
    private Paint paint = new Paint();
    public Practice2DrawCircleView(Context context) {
        super(context);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("","");
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
//        canvas.drawCircle();
        //默认实心圆
        canvas.drawCircle(300,300,200,paint);

        //设置画空心圆
        paint.setStyle(Paint.Style.STROKE);
        //设置画笔粗细
        paint.setStrokeWidth(4);
        //设置抗锯齿
        paint.setAntiAlias(true);
        canvas.drawCircle(750,300,200,paint);


        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(300,750,200,paint);

        //paint 后面设置的属性会覆盖前面的
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(750,750,200,paint);
    }
}
