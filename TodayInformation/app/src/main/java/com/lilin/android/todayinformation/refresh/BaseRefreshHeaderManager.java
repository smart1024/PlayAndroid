package com.lilin.android.todayinformation.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 创建日期：2021/10/12 14:07
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.todayinformation.refresh
 * 类说明：
 */

/**
 * 负责管理HeaderView
 */
public abstract class BaseRefreshHeaderManager {
    protected LayoutInflater mLayoutInflater;
    public BaseRefreshHeaderManager(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }
    abstract View getHeaderView();

    public abstract void idle();

    public abstract void pullingDown();

    public abstract void release();

    public abstract void refreshing();

    public void pullDownPercent(float percent){}
}
