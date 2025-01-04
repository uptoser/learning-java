package com.uptoser.java.design_patterns.creational_patterns.builder.group1;

import javax.swing.*;
/**
 * 指挥者 (Director) ：指挥者是一个类，该类需含有 Builder 接口声明的变量。
 * 指挥者的职责是负责向用户提供具体生成器，即指挥者将请求具体生成器来构造用户所需要的 Product 对象，
 * 如果所请求的具体生成器成功地构造出 Product 对象，指挥者就可以让该具体生成器返回所构造的 Product 对象。
 */
public class Director{
    private Builder builder;
    Director(Builder builder){
       this.builder=builder;
    }
    public JPanel constructProduct(){
       builder.buildButton();
       builder.buildLabel();
       builder.buildTextField();
       JPanel product=builder.getPanel();
       return product;
   }
}
