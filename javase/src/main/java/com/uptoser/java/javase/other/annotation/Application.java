package com.uptoser.java.javase.other.annotation;

@AnnotationMain(name = "世界1", config = Application.class)
@AnnotationMain(name = "世界2",age = 19,config = Application.class)
public class Application {
    public static void main(String[] args) {
        //获取重复注解
        Class<Application> clazz = Application.class;
        AnnotationMain[] annotations = clazz.getDeclaredAnnotationsByType(AnnotationMain.class);
        for (AnnotationMain a:annotations) {
            System.out.println(a.age()+":"+a.name());
        }

        /**
         编译时处理注解 TODO
         APT（Annotation Processing Tool）是一种注解处理工具，它对源代码文件进行检测，
         并找出源文件所包含的注解信息，然后针对注解信息进行额外的处理

         列如通过java源码生成mapper映射文件

         Java提供的javac.exe工具有一个-processor选项，该选项可指定一个注解处理器，如果在编译Java源文件时通过该选项指定了注解处理器，
         那么这个注解处理器将会在编译时提取并处理Java源文件中的注解
         */

    }
}
