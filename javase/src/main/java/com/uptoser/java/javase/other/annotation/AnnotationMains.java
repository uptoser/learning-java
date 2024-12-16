package com.uptoser.java.javase.other.annotation;

import java.lang.annotation.*;

/**
 * 定义重复注解
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target( ElementType.TYPE_USE)
@Documented//用于指定被该元注解修饰的注解类将被javadoc工具提取成文档。文档中会出现注解
@Inherited//指定被它修饰的注解将具有继承性。子类会自动加上自定义的注解
public @interface AnnotationMains {
    //定义value成员变量，可以接受多个@AnnotationMain注解
    AnnotationMain[] value();
}
