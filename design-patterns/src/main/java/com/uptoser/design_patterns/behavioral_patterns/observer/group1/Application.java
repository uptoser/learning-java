package com.uptoser.design_patterns.behavioral_patterns.observer.group1;

/**
 * @author Uptoser 2024年11月30日
 */
public class Application{
   public static void main(String args[]){
      SeekJobCenter center=new SeekJobCenter();//具体主题 center
      UniverStudent zhangLin=new UniverStudent(center,"A.txt");//具体观察者 zhangLin
      HaiGui wangHao=new HaiGui(center,"B.txt");//具体观察者 wangHao
      center.giveNewMess("腾辉公司需要10个java程序员。");//具体主题给出新信息
      center.notifyObservers();//具体主题通知信息
      center.giveNewMess("海景公司需要8个动画设计师。");
      center.notifyObservers();
      center.giveNewMess("仁海公司需要9个电工。");
      center.notifyObservers();
      center.giveNewMess("仁海公司需要9个电工。");//信息不是新的
      center.notifyObservers();//观察者不会执行更新操作
   }
}
