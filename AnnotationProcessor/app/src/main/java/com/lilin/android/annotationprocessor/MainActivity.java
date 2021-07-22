package com.lilin.android.annotationprocessor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lilin.android.ServiceManager;

/**
 * Android AnnotationProcessor
 * https://blog.csdn.net/qq_15827013/article/details/103722462
 * 注意点：
 * 1、注解类生成位置必须在项目的包名下，否则会报找不到
 * 2、这行依赖必不可少，会根据@AutoService注解执行生成类的代码 否则不会生成注解类
 * annotationProcessor 'com.google.auto.service:auto-service:1.0-rc6'
 * 3、不要使用Diagnostic.Kind.ERROR 会被当成错误，阻碍正常注解生成流程
 * messager.printMessage(Diagnostic.Kind.NOTE, "=======处理结束=======");
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceManager.getService(IService1.class).doFun();
        ServiceManager.getService(IService2.class).doTest();

    }
}