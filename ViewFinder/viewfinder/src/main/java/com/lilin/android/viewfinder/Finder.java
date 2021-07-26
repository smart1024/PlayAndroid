package com.lilin.android.viewfinder;

import com.lilin.android.viewfinder.provider.Provider;

/**
 * 创建日期：2021/7/23 17:42
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.viewfinder.provider
 * 类说明：
 */

public interface Finder<T> {
    void inject(final T host, Object source, Provider provider);
}
