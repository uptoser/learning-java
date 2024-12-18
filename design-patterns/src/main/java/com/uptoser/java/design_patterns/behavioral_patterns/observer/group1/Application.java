package com.uptoser.java.design_patterns.behavioral_patterns.observer.group1;

/**
 * @author Uptoser 2024年11月30日
 *
 * Observer Pattern(Another Name: Dependents, Publish-Subscribe)
 * Define a one-to-many dependency between objects so that when one object changes
 * state, all its dependents are notified and updated automatically.
 *
 * 观察者模式（别名：依赖，发布一订阅）
 * 定义对象间的一种一对多的依赖关系，当一个对象的状态发生变化时，所有依它的对象都得到通知并被自动更新。
 */
public class Application{
   public static void main(String args[]){
      final String CLASS_PATH = ClassLoader.getSystemResource("").getPath();
      SeekJobCenter center=new SeekJobCenter();//具体主题 center
      UniverStudent zhangLin=new UniverStudent(center,CLASS_PATH+"A.txt");//具体观察者 zhangLin
      HaiGui wangHao=new HaiGui(center,CLASS_PATH+"B.txt");//具体观察者 wangHao
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
