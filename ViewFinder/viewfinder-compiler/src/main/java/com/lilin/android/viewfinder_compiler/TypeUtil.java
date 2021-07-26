package com.lilin.android.viewfinder_compiler;

import com.squareup.javapoet.ClassName;

/**
 * 创建日期：2021/7/23 15:12
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.viewfinder_compiler
 * 类说明：
 */

public class TypeUtil {
    public static final ClassName ANDROID_VIEW = ClassName.get("android.view", "View");
    public static final ClassName ANDROID_ON_CLICK_LISTENER = ClassName.get("android.view", "View", "OnClickListener");
    public static final ClassName FINDER = ClassName.get("com.lilin.android.viewfinder", "Finder");
    public static final ClassName PROVIDER = ClassName.get("com.lilin.android.viewfinder.provider", "Provider");
}
