package com.uptoser.java.design_patterns.structural_patterns.adapter;

/**
 * 适配器
 * 适配器的适配程度
 * 1.完全适配
 * 2.不完全适配
 * 如果目标接口中的方法数目少于被适配者接口的方法数目，那么适配器只能将被适配者接口（抽象类）与目标接口进行部分适配。
 * 3.剩余适配
 * 如果目标接口中的方法数目大于被适配者接口的方法数目，那么适配器可将被适配者接口（抽象类）与目标接口进行完全适配，但必须将目标多余的方法给出用户允许的默认实现。
 */
public class ElectricAdapter implements ThreeElectricOutlet, TwoElectricOutlet {
    TwoElectricOutlet outlet2;
    ThreeElectricOutlet outlet3;

    ElectricAdapter(TwoElectricOutlet outlet2, ThreeElectricOutlet outlet3) {
        this.outlet2 = outlet2;
        this.outlet3 = outlet3;
    }
    @Override
    public void connectElectricCurrent() {
        if(this instanceof TwoElectricOutlet && outlet3!=null)
            outlet3.connectElectricCurrent();
        if(this instanceof ThreeElectricOutlet && outlet2!=null)
            outlet2.connectElectricCurrent();
    }
}