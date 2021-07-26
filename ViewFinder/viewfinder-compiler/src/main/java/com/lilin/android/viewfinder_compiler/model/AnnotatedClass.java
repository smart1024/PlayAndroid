package com.lilin.android.viewfinder_compiler.model;

import com.lilin.android.viewfinder_compiler.TypeUtil;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * 创建日期：2021/7/23 14:23
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.viewfinder_compiler.model
 * 类说明：被注解的类
 */

public class AnnotatedClass {
    /*被注解的类*/
    public TypeElement mClassElement;
    /**
     * 类中被注解的元素列表
     */
    public List<BindViewField> mFields;
    /**
     * 类中被注解的方法列表
     */
    public List<OnClickMethod> mMethods;
    public Elements mElementUtils;

    public AnnotatedClass(TypeElement classElement, Elements elementUtils){
        mClassElement = classElement;
        mFields = new ArrayList<>();
        mMethods = new ArrayList<>();
        mElementUtils = elementUtils;
    }

    public String getFullClassName(){
        return mClassElement.getQualifiedName().toString();
    }

    public void addField(BindViewField field){
        mFields.add(field);
    }

    public void addMethod(OnClickMethod method){
        mMethods.add(method);
    }

    public JavaFile generateFinder() {
        /**
         * @Override
         *  public void inject(final T host, Object source, Provider provider)
          */
        MethodSpec.Builder injectMethodBuilder = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                //参数类型 参数名host 参数修饰符 final
                .addParameter(TypeName.get(mClassElement.asType()),"host",Modifier.FINAL)
                .addParameter(TypeName.OBJECT,"source")
                .addParameter(TypeUtil.PROVIDER,"provider");
        for (BindViewField field : mFields){
            //   host.mTextView = (TextView)(provider.findView(source, 2131165279));
            injectMethodBuilder.addStatement("host.$N = ($T)(provider.findView(source,$L))", field.getFieldName(), ClassName.get(field.getFieldType()),field.getResId());
        }

        //View.OnClickListener listener;
        if (mMethods.size() > 0) {
            injectMethodBuilder.addStatement("$T listener", TypeUtil.ANDROID_ON_CLICK_LISTENER);
        }

        //声明匿名类并赋值给listener变量
        for (OnClickMethod method: mMethods){
            TypeSpec listener = TypeSpec.anonymousClassBuilder("" )
                    .addSuperinterface(TypeUtil.ANDROID_ON_CLICK_LISTENER)
                    .addMethod(MethodSpec.methodBuilder("onClick")
                            .addAnnotation(Override.class)
                            .addModifiers(Modifier.PUBLIC)
                            .returns(TypeName.VOID)
                            .addParameter(TypeUtil.ANDROID_VIEW,"view")
                            .addStatement("host.$N()", method.getMethodName())
                            .build())
                    .build();
            injectMethodBuilder.addStatement("listener = $L",listener);
            for(int id : method.ids){
                injectMethodBuilder.addStatement("provider.findView(source,$L).setOnClickListener(listener)",id);
            }
        }
        // generate whole class
        TypeSpec finderClass = TypeSpec.classBuilder(mClassElement.getSimpleName()+"$$Finder")
                .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
//                implements Finder<MainActivity> 添加实现接口和泛型
                .addSuperinterface(ParameterizedTypeName.get(TypeUtil.FINDER,TypeName.get(mClassElement.asType())))
                .addMethod(injectMethodBuilder.build())
                .build();
        String packageName = mElementUtils.getPackageOf(mClassElement).getQualifiedName().toString();
        return JavaFile.builder(packageName,finderClass).build();
    }

}
