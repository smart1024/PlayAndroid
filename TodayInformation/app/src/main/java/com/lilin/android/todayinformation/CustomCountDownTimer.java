package com.lilin.android.todayinformation;

import android.os.CountDownTimer;
import android.os.Handler;

/**
 * 创建日期：2021/10/9 11:23
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.todayinformation
 * 类说明：
 */

public class CustomCountDownTimer extends CountDownTimer implements Runnable{
    private long millisInFuture;
    private long countDownInterval;
    private ICountDownHandler iCountDownHandler;
    private Handler handler;
    private boolean isRun;
    public CustomCountDownTimer(long millisInFuture, long countDownInterval,ICountDownHandler countDownHandler) {
        super(millisInFuture, countDownInterval);
        this.countDownInterval = countDownInterval;
        this.millisInFuture = millisInFuture;
        iCountDownHandler = countDownHandler;
        handler = new Handler();
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {

    }

    public void startCountDown(){
        isRun = true;
        handler.post(this);
    }

    public void cancelCountDown(){
        isRun = false;
        handler.removeCallbacks(this);
    }

    @Override
    public void run() {
        if (!isRun) return;
        if (iCountDownHandler == null) return;
        iCountDownHandler.onTick(millisInFuture);
        if (millisInFuture == 0){
            iCountDownHandler.onFinish();
            cancelCountDown();
        }else {
            millisInFuture--;
            handler.postDelayed(this,countDownInterval);
        }
    }

    public interface ICountDownHandler{
       void onTick(long time);
       void onFinish();
    }
}
