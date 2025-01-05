package com.uptoser.java.design_patterns.structural_patterns.bridge;

/**
 * 实现者/实施者 (Implementor) ：
 * 实现者角色是一个接口（抽象类），该接口（抽象类）中的方法不一定与 Abstraction 类中的方法一致。
 * Implementor 接口（抽象类）负责定义基本操作，而 Abstraction 类负责定义基于这些基本操作的较高层次的操作。
 */
public interface BuildingDesign{
      public double computerArea();  
}