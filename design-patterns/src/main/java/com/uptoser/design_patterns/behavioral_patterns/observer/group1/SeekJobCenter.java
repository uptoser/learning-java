package com.uptoser.design_patterns.behavioral_patterns.observer.group1;

import java.util.ArrayList;

/**
 * 主题接口规定了具体主题需要实现的通知观察者更新数据的notifyObservers()方法，
 * 具体主题通过实现notifyObservers()方法来通知具体观察者，实现的方式是遍历具体主题中用来存放观察者引用的集合，
 * 并让集合中的每个具体观察者执行观察者接口(Observer)规定更新数据的方法，比如 hearTelephone() 方法。
 * 对于某些问题，具体主题应当保证数据确实发生了变化再遍历存放观察者引用的集合。在本问题中，具体主题维护着一个 string
 */
public class SeekJobCenter implements Subject{
    String mess;
    boolean changed;
    ArrayList<Observer> personList;             
    SeekJobCenter(){
       personList=new ArrayList<Observer>();
       mess=""; 
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
          for(int i=0;i<personList.size();i++){
            Observer observer=personList.get(i);
            observer.hearTelephone(mess);         
          }
          changed=false; 
       }
    }
    public void giveNewMess(String str){
       if(str.equals(mess))
             changed=false;
       else{
             mess=str;
             changed=true;
       }
    }
}
