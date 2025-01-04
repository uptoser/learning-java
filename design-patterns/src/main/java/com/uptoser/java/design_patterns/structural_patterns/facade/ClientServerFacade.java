package com.uptoser.java.design_patterns.structural_patterns.facade;
/**
 * 外观 (Facade)
 * 外观是一个类，该类包含子系统中全部或部分类的实例引用，当用户想要和子系统中的若干个类的实例打交道时，可以代替地和子系统的外观类的实例打交道。
 */
public class ClientServerFacade{
    private CheckWord checkWord;
    private Charge charge;
    private TypeSetting typeSetting;
    String advertisement;
    public ClientServerFacade(String advertisement){
       this.advertisement=advertisement;
       checkWord=new CheckWord(advertisement);
       charge=new Charge(checkWord);
       typeSetting=new TypeSetting(advertisement);
    }
    public void doAdvertisement(){ 
       checkWord.setChargeAmount();
       charge.giveCharge();
       typeSetting.typeSetting();
    } 
}