package com.uptoser.java.design_patterns.creational_patterns.builder.group1;

import javax.swing.*;

/**
 * 抽象生成器 (Builder) ：抽象生成器是一个接口，
 * 该接口除了为创建一个 Product 对象的各个组件定义了若干个方法外，还要定义返回 Product 对象的方法。
 */
public interface Builder{
    public abstract void buildButton();
    public abstract void buildLabel();
    public abstract void buildTextField();
    public abstract JPanel getPanel();
}
