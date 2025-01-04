package com.uptoser.java.design_patterns.behavioral_patterns.mediator;

/**
 * 中介者：中介者是一个接口，该接口定义了用于同事 (Colleague) 对象之间进行通信的方法。
 *
 * 具体中介者 (ConcreteMediator) ：具体中介者是实现中介者接口的类。
 * 具体中介者需要包含所有具体同事（ ConcreteColleague ）的引用，并通过实现中介者接口中的方法来满足具体同事之间的通信请求。
 */
public class ConcreteMediator{
    ColleagueA colleagueA;
    ColleagueB colleagueB;
    ColleagueC colleagueC;
    public void registerColleagueA(ColleagueA colleagueA){
       this.colleagueA=colleagueA;
    }
    public void registerColleagueB(ColleagueB colleagueB){
       this.colleagueB=colleagueB;
    }
    public void registerColleagueC(ColleagueC colleagueC){
       this.colleagueC=colleagueC;
    }
    public void deliverMess(Colleague colleague,String [] mess){
       if(colleague==colleagueA){
          if(mess.length>=2){
            colleagueB.receiverMess(colleague.getName()+mess[0]);
            colleagueC.receiverMess(colleague.getName()+mess[1]);
          } 
       }
       else if(colleague==colleagueB){
          if(mess.length>=2){
            colleagueA.receiverMess(colleague.getName()+mess[0]);
            colleagueC.receiverMess(colleague.getName()+mess[1]);
          }  
       }
       else if(colleague==colleagueC){
          if(mess.length>=2){
            colleagueA.receiverMess(colleague.getName()+mess[0]);
            colleagueB.receiverMess(colleague.getName()+mess[1]);
          }  
       }    
    }
}