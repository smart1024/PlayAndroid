package com.lilin.android.laopdemo;

import android.app.Application;

import com.lilin.android.laop_runtime.XAOP;

/**
 * 创建日期：2021/8/20 10:15
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.laopdemo
 * 类说明：
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XAOP.init(this);
        XAOP.debug(true);
    }
}
