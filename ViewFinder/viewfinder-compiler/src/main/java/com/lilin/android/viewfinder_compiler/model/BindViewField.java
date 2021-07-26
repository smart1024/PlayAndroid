package com.lilin.android.viewfinder_compiler.model;

import com.lilin.android.viewfinder_annotation.BindView;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * 创建日期：2021/7/23 14:25
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.viewfinder_compiler.model
 * 类说明：被BindView注解的控件
 */

public class BindViewField {
    /*成员变量类型*/
    private VariableElement mFieldElement;
    private int mResId;
    public BindViewField(Element element) throws IllegalArgumentException{
        if (element.getKind() != ElementKind.FIELD){ //判断是不是成员变量
            throw  new IllegalArgumentException(String.format("Only fields can be annotated with @%s",BindView.class.getSimpleName()));
        }
        //获取被注解的控件
        mFieldElement = (VariableElement) element;
        //获取控件的注解
        BindView bindView = mFieldElement.getAnnotation(BindView.class);
        mResId = bindView.value();

        if (mResId < 0) {
            throw new IllegalArgumentException(
                    String.format("value() in %s for field %s is not valid !", BindView.class.getSimpleName(),
                            mFieldElement.getSimpleName()));
        }
    }

    /**
     * 被注解的控件名
     * @return
     */
    public Name getFieldName(){
       return mFieldElement.getSimpleName();
    }

    /**
     * 被注解控件ID
     * @return
     */
    public int getResId() {
        return mResId;
    }

    /**
     * 返回控件类型
     * @return
     */
    public TypeMirror getFieldType(){
        return mFieldElement.asType();
    }
}
