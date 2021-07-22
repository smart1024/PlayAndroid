package com.lilin.android.annotationprocessor;

import android.util.Log;

import com.lilin.android.lib_anotations.ByteService;

/*Service2Impl有两个接口，指定注解的clazz接口为IService2*/
@ByteService(clazz = IService2.class)
public class IService2Impl implements ITest,IService2{

    @Override
    public void doTest() {
        Log.e(IService2Impl.class.getSimpleName(),"doTest");
    }

    @Override
    public void test() {
        Log.e(IService2Impl.class.getSimpleName(),"test");
    }
}
