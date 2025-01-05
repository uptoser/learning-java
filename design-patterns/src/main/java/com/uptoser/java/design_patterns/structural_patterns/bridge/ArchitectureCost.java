package com.uptoser.java.design_patterns.structural_patterns.bridge;

/**
 * 抽象 (Abstraction) ：
 * 是一个抽象类，该抽象类含有声明的变量，即维护一个 Implementor(实施者) 类型对象。
 */
public abstract class  ArchitectureCost{
      BuildingDesign design;
      double unitPrice;
      public  abstract double giveCost() ;
}
