package com.lilin.android.viewfinder_compiler;

import com.google.auto.service.AutoService;
import com.lilin.android.viewfinder_annotation.BindView;
import com.lilin.android.viewfinder_annotation.OnClick;
import com.lilin.android.viewfinder_compiler.model.AnnotatedClass;
import com.lilin.android.viewfinder_compiler.model.BindViewField;
import com.lilin.android.viewfinder_compiler.model.OnClickMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * 创建日期：2021/7/23 13:58
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.viewfinder_compiler
 * 类说明：
 */

@AutoService(Processor.class)
public class ViewFinderProcesser extends AbstractProcessor {
    /*文件类*/
    private Filer mFiler;
    /*元素类*/
    private Elements mElementUtils;
    /*打印日志类*/
    private Messager mMessager;

    private Map<String, AnnotatedClass> mAnnotatedClassMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
       mFiler = processingEnv.getFiler();
       mMessager = processingEnv.getMessager();
       mElementUtils = processingEnv.getElementUtils();
    }

    /**
     * @return 指定使用的 Java 版本。通常返回 SourceVersion.latestSupported()
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 指定哪些注解需要被注解器处理
     * @return types
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(BindView.class.getCanonicalName());
        types.add(OnClick.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mAnnotatedClassMap.clear();
        try {
            processBindView(roundEnvironment);
            processOnClick(roundEnvironment);
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
            return true; // stop process
        }

        //生成使用自定义注解的类文件
        for (AnnotatedClass annotatedClass:mAnnotatedClassMap.values()){
            try {
                info("Generating file for %s", annotatedClass.getFullClassName());
                annotatedClass.generateFinder().writeTo(mFiler);
            } catch (IOException e) {
                error("Generate file failed, reason: %s", e.getMessage());
                return true;
            }
        }

        return true;
    }

    /**
     * 处理OnClick注解
     * @param roundEnvironment
     */
    private void processOnClick(RoundEnvironment roundEnvironment) {
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(OnClick.class);
        for (Element element:elementSet){
            AnnotatedClass annotatedClass = getAnnotatedClass(element);
            OnClickMethod onClickMethod = new OnClickMethod(element);
            annotatedClass.addMethod(onClickMethod);
        }
    }

    /**
     * 处理BindView注解
     * @param roundEnvironment
     */
    private void processBindView(RoundEnvironment roundEnvironment) {
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        for (Element element:elementSet){
            AnnotatedClass annotatedClass = getAnnotatedClass(element);
            BindViewField bindViewField = new BindViewField(element);
            annotatedClass.addField(bindViewField);
        }
    }

    private AnnotatedClass getAnnotatedClass(Element element) {
        TypeElement classElement = (TypeElement)element.getEnclosingElement();
        String fullClassName = classElement.getQualifiedName().toString();
        AnnotatedClass annotatedClass = mAnnotatedClassMap.get(fullClassName);
        if (annotatedClass == null){
            annotatedClass = new AnnotatedClass(classElement,mElementUtils);
            mAnnotatedClassMap.put(fullClassName,annotatedClass);
        }
        return annotatedClass;
    }

    private void error(String msg,Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
    }

    private void info(String msg,Object... args){
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
    }
}
