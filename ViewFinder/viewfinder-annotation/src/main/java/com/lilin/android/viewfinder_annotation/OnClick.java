package com.lilin.android.viewfinder_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *使用起来是这样的:
 * @OnClick(R.id.btn)或者@OnClick({R.id.btn,R.id.tv})
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface OnClick {
    int[] value();
}
