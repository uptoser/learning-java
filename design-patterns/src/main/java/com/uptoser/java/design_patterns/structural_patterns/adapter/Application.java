package com.uptoser.java.design_patterns.structural_patterns.adapter;


/**
 * 适配器模式：（别名：包装器）
 * 将一个类的接口转换成客户希望的另外一个接口。 Adapter 模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * <p>
 * Adapter Pattern(Another Name: Wrapper)
 * Convert the interface of a class into another interface clients expect. Adapter lets
 * classes work together that couldn't otherwise because of incompatible interfaces.
 */
public class Application {

    public static void main(String[] args) {
        ThreeElectricOutlet outlet;        //目标接口（三相插座）
        TV tv = new TV();                     //电视机
        outlet = tv;                       //电视机插在三相插座上
        System.out.println("使用三相插座接通电流：");
        outlet.connectElectricCurrent();   //接通电流，开始播放电视节目
        Washer washer = new Washer();              //洗衣机
        ElectricAdapter adapter = new ElectricAdapter(washer,null);//把洗衣机插在适配器上
        outlet = adapter;                    //适配器插在三相插座上
        System.out.println("使用三相插座接通电流：");
        outlet.connectElectricCurrent();   //接通电流，开始洗衣服
    }

}
