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
 * Advice类型：
 * @Before
 * @After
 * @Around
 * @Pointcut
 * @AfterThrowing 会给指定包下所有方法加上try catch语句，但是异常仍然会被抛出，已经有try
 *
 * JoinPoint类型：
 * call  调用函数前后
 * execution 函数内部前后
 * withincode：类内部多个方法场景下 结合Pointcut实现切点进准匹配或者过滤某个方法
 * within:多个类场景下精准匹配某个类
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
     * 测试AOP
     * @param view
     */
    public void testAop(View view) {
        testAOP();
    }
    /**
     * 测试JointPoint Around
     */
    public void testAOP() {
        Log.e(TAG,"调用原始方法testAOP");
    }


    public void testCall(View view) {
        testCall();
    }

    /**
     * 测试call调用
     */
    public void testCall() {
        Log.e(TAG,"调用原始方法testCall");
    }


    public void testWithInCode(View view) {
        testWithInCode();
    }

    /**
     * 测试withincode类型
     */
    private void testWithInCode() {
        testWithInCode1();
        testWithInCode2();
    }

    private void testWithInCode2() {
        printWithInCode();
    }

    private void testWithInCode1() {
        printWithInCode();
    }

    private void printWithInCode() {
        Log.e(TAG,"调用原始方法printWithInCode");
    }

    public void testAfterThrowing(View view) {
        testAfterThrowing();
    }

    private void testAfterThrowing() {
        View view = null;
        view.animate();
    }
}