package com.uptoser.java.design_patterns.behavioral_patterns.chain_of_responsibility.group1;

/**
 * @author Uptoser 2024年11月30日
 *
 * Chain of Responsibility Pattern
 * Avoid coupling the sender of a request to its receiver by giving more than one
 * object a chance to handle the request. Chain the receiving objects and pass the request
 * along the chain until an object handles it.
 *
 * 责任链模式
 * 使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。
 * 将这些对象连成一条链，并沿着这条裢传递该请求，直到有一个对象处理它为止。
 */
public class Application{
    private Handler beijing,shanghai,tianjin;    //责任链上的对象
    public void createChain(){       //建立责任链
        beijing=new Beijing();
        shanghai=new Shanghai();
        tianjin=new Tianjin();
        beijing.setNextHandler(shanghai);
        shanghai.setNextHandler(tianjin);
    }
    public void reponseClient(String number){  //响应用户的请求
        beijing.handleRequest(number);
    }
    public static void main(String args[]){
        Application  application=new  Application();
        application.createChain();
        application.reponseClient("77720810340930632");;
    }
}



