package com.lilin.android.viewfinder.provider;

import android.content.Context;
import android.view.View;

/**
 * 创建日期：2021/7/23 15:30
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.viewfinder.provider
 * 类说明：
 */

public interface Provider {
    Context getContext(Object source);
    View findView(Object source, int id);
}
