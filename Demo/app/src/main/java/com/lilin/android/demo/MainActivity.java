package com.lilin.android.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 安卓AOP三剑客:APT,AspectJ,Javassist
 * APT：通过注解处理器基于源代码加料后生产新的java文件
 * AspectJ:
 * 通过注解修改原class文件
 * 使用场景:Dagger，butterKnife，组件化方案等等
 * 使用场景：主要用于性能监控，日志埋点等
 * Javassist：
 * 修改原dex文件
 * 使用场景:热更新（可以在编译后，打包Dex之前干事情，可以突破一下限制）
 *
 * 参考资料：
 * https://www.jianshu.com/p/e737c187e0c2
 * https://www.jianshu.com/p/5c9f1e8894ec
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG,"onCreate");
        testAOP();
        AnnotationTest();
    }

    @AspectAnnotation
    private void AnnotationTest() {
        Log.e(TAG, "AnnotationTest-invoke");
    }

    /**
     *测试自定义Pointcuts
     */
    @DebugTool
    private void testCustomPointcut() {
        Log.e(TAG, "执行原始方法testCustomPointcut");
    }

    public void testPointcut(View view) {
        testCustomPointcut();
    }

    /**
     * 测试JointPoint Around
     */
    private void testAOP() {
        Log.e(TAG,"调用原始方法testAOP");
    }

}