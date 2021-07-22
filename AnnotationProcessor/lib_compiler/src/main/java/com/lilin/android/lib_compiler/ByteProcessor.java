package com.lilin.android.lib_compiler;

import com.google.auto.service.AutoService;
import com.lilin.android.lib_anotations.ByteService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

/**
 *
 *     1、获取指定注解的所有元素
 *     2、拿到元素的类名
 *     3、拿到元素标注的接口的类名
 *     4、存入map，输出log
 *     5、开始生成代码
 *
 */
@AutoService(Processor.class)
public class ByteProcessor extends AbstractProcessor {
    /*用于生成新的java文件的对象*/
    private Filer filer;

    /*用于输出log的对象*/
    private Messager messager;
    private Map<String, String> mapper = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    /**
     * getSupportedAnnotationTypes()方法用于返回该Processor想要接收处理的注解，要返回全路径类名，通常使用getCanonicalName()方法
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> res = new HashSet<>();
        res.add(ByteService.class.getCanonicalName());
        return res;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.NOTE, "=======开始处理=======");
        Set<? extends Element> sets = roundEnvironment.getElementsAnnotatedWith(ByteService.class);
        if (sets!=null && sets.size() > 0){
            for (Element element : sets){
                //每一个元素由于只能是类，所以都是TypeElement类型
                if (element instanceof TypeElement){
                    //获取定义你该注解的元素(这里是类)的全路径名称
                    String implName = TypeName.get(element.asType()).toString();
                    //对应的接口全路径类名
                    String interName;
                    try {
                        //通过注解的clazz对象直接获取
                        interName = element.getAnnotation(ByteService.class).clazz().getCanonicalName();
                    }catch (MirroredTypeException mte){
                        //由于调用clazz对象时，可能因为Class对象还没有被加载，所以抛异常
                        //异常中有相关class对象的信息，直接拿到类名即可
                        interName = TypeName.get(mte.getTypeMirror()).toString();
                    }

                    //如果没有定义你clazz(默认为Object)，则取该类默认实现的接口
                    if (Object.class.getCanonicalName().equals(interName)){
                        List<? extends TypeMirror> typeMirrors = ((TypeElement) element).getInterfaces();
                        interName = TypeName.get(typeMirrors.get(0)).toString();
                    }
                    //放入map中后续生成代码
                    mapper.put(interName,implName);
                    //messager输出log
                    messager.printMessage(Diagnostic.Kind.NOTE, "Interface: " + interName + " Impl: " + implName);
                }
            }
            generate();
        }
        //生成代码
        messager.printMessage(Diagnostic.Kind.NOTE, "=======处理结束=======");
        return true;
    }


    /**
     * 1、MethodSpec生成方法和构造器
     * 2、FieldSpec生成字段
     * 3、TypeVariableName生成泛型定义
     * 4、ParameterizedTypeName生成类型，可以包含泛型
     * 5、TypeSpec生成类
     * 6、JavaFile生成文件
     * 7、Filer提供写入文件功能
     */
    private void generate() {
        //private constructor
        MethodSpec cons = MethodSpec.constructorBuilder().addModifiers(Modifier.PRIVATE).build();
        // static map
        ParameterizedTypeName mapType = ParameterizedTypeName.get(ClassName.get(Map.class),ClassName.get(Class.class),ClassName.get(Object.class));
        FieldSpec map = FieldSpec.builder(mapType,"services",Modifier.PRIVATE,Modifier.STATIC,Modifier.FINAL).initializer("new java.util.HashMap<>()").build();
        //static init
        FieldSpec init = FieldSpec.builder(Boolean.class,"isInit",Modifier.PRIVATE,Modifier.STATIC).initializer("false").build();
        //static getService
        MethodSpec.Builder getServiceBuilder = MethodSpec.methodBuilder("getService").addModifiers(Modifier.PUBLIC,Modifier.STATIC);
        TypeVariableName t = TypeVariableName.get("T");
        TypeVariableName b = TypeVariableName.get("B").withBounds(t);
        getServiceBuilder.addTypeVariable(t).addTypeVariable(b);
        getServiceBuilder.addParameter(ParameterizedTypeName.get(ClassName.get(Class.class),t),"clazz");
        getServiceBuilder.returns(b);
        //statement
        getServiceBuilder.beginControlFlow("if(!isInit)");
        generateInitStatement(getServiceBuilder).addStatement("isInit=true").endControlFlow();
        getServiceBuilder.addStatement("return (B) services.get(clazz)");

        //class
        TypeSpec typeSpec = TypeSpec.classBuilder("ServiceManager")
                .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
                .addField(init)
                .addField(map)
                .addMethod(cons)
                .addMethod(getServiceBuilder.build())
                .build();

        //file
        JavaFile javaFile = JavaFile.builder("com.lilin.android",typeSpec).build();
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MethodSpec.Builder generateInitStatement(MethodSpec.Builder getServiceBuilder) {
        for (Map.Entry<String,String> entry:mapper.entrySet()){
            getServiceBuilder.addStatement(String.format("services.put(%s.class,new %s())",entry.getKey(),entry.getValue()));
        }
        return getServiceBuilder;
    }
}