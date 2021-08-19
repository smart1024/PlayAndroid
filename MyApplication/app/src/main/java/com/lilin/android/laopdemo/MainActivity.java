package com.lilin.android.laopdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.lilin.android.laop_annotation.DebugLog;
import com.lilin.android.laop_runtime.XAOP;
import com.lilin.android.laop_runtime.logger.XLogger;

/**
 * 1、掌握gradle插件定义  gradle uploadArchives生成gradle插件本地库
 * 2、
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XAOP.debug(true);
    }

    @DebugLog(priority = Log.ERROR)
    public void handleClick(View view) {
        XLogger.e("点击响应！");
//        ToastUtils.toast("点击响应！");
//        hello("xuexiangjys", "666666");
    }


}