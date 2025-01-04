package com.uptoser.java.design_patterns.structural_patterns.facade;

public class Charge{
    public final int basicCharge=12;
    CheckWord checkWord;
    Charge(CheckWord checkWord){
       this.checkWord=checkWord;
    }
    public void giveCharge(){
        int charge=checkWord.getAmount()*basicCharge;
        System.out.println("广告费用:"+charge+"元");
    } 
}