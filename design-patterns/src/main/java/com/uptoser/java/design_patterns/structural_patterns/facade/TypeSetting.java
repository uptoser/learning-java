package com.uptoser.java.design_patterns.structural_patterns.facade;

/**
 * 子系统 (Subsystem)
 * 子系统是若干个类的集合，这些类的实例协同合作为用户提供所需要的功能，子系统中任何类都不包含外观类的实例引用。
 */
public class TypeSetting {
    String advertisement;

    public TypeSetting(String advertisement) {
        this.advertisement = advertisement;
    }

    public void typeSetting() {
        System.out.println("广告排版格式:");
        System.out.println("********");
        System.out.println(advertisement);
        System.out.println("********");
    }
}
