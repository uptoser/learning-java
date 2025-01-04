package com.uptoser.java.design_patterns.behavioral_patterns.observer.group1;

import java.util.ArrayList;

/**
 * 具体主题：求职中心
 *
 * 主题接口规定了具体主题需要实现的通知观察者更新数据的notifyObservers()方法，
 * 具体主题通过实现notifyObservers()方法来通知具体观察者，实现的方式是遍历具体主题中用来存放观察者引用的集合，
 * 并让集合中的每个具体观察者执行观察者接口(Observer)规定更新数据的方法，比如 hearTelephone() 方法。
 * 对于某些问题，具体主题应当保证数据确实发生了变化再遍历存放观察者引用的集合。在本问题中，具体主题维护着一个 string
 */
public class SeekJobCenter implements Subject{
    String message;
    boolean changed;//true是最新消息
    ArrayList<Observer> personList;             
    SeekJobCenter(){
       personList=new ArrayList<>();
       message="";
       changed=false; 
    }
    public void addObserver(Observer o){
       if(!(personList.contains(o)))
         personList.add(o);                    
    }
    public void deleteObserver(Observer o){
       if(personList.contains(o))
         personList.remove(o); 
    }
    public void notifyObservers(){ 
       if(changed){             
          for(Observer observer : personList){
            observer.hearTelephone(message);
          }
          changed=false; 
       }
    }
    public void giveNewMess(String str){
       if(str.equals(message)){
           changed=false;
       }else{
           message=str;
           changed=true;
       }
    }
}
