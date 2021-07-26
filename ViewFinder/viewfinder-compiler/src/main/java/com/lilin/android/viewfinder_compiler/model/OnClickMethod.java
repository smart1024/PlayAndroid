package com.lilin.android.viewfinder_compiler.model;

import com.lilin.android.viewfinder_annotation.OnClick;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;

/**
 * 创建日期：2021/7/23 14:25
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.viewfinder_compiler.model
 * 类说明：被OnClick注解的方法
 */

public class OnClickMethod {
    /*被注解的为方法类型*/
    private ExecutableElement methodElement;
    /*注解的id*/
    public int[] ids;
    public OnClickMethod(Element element){
        if (element.getKind() != ElementKind.METHOD){
            throw new IllegalArgumentException(String.format("Only methods can be annotated with @%s", OnClick.class.getSimpleName()));
        }
        methodElement = (ExecutableElement) element;
        OnClick onClick = element.getAnnotation(OnClick.class);
        ids = onClick.value();
        if (ids.length == 0){
            throw new IllegalArgumentException(String.format("Must set valid ids for @%s", OnClick.class.getSimpleName()));
        }else {
            for (int id : ids) {
                if (id < 0) {
                    throw new IllegalArgumentException(String.format("Must set valid id for @%s", OnClick.class.getSimpleName()));
                }
            }
        }

        List<? extends VariableElement> parameters = methodElement.getParameters();
        //被注解的方法参数必须为空，暂时这么定
        if (parameters.size() > 0) {
            throw new IllegalArgumentException(
                    String.format("The method annotated with @%s must have no parameters", OnClick.class.getSimpleName()));
        }
    }

    /*被注解的方法名*/
    public Name getMethodName() {
        return methodElement.getSimpleName();
    }
}
