package com.lilin.android.annotationprocessor;

import android.util.Log;

import com.lilin.android.lib_anotations.ByteService;

/*Service1Impl由于只有一个接口所以采用默认的注解*/
@ByteService
public class IService1Impl implements IService1{
    @Override
    public void doFun() {
        Log.e(IService1Impl.class.getSimpleName(),"doFun");
    }
}
