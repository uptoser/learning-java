package com.uptoser.java.design_patterns.behavioral_patterns.mediator;

/**
 * 中介者模式（Mediator Pattern）
 * 用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显示地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
 * 中介者模式属于行为型模式。
 *
 * Mediator Pattern
 * Define an object that encapsulates how a set of objects interact. Mediator promotes
 * loose coupling by keeping objects from referring to each other explicitly, and it lets you
 * vary their interaction independently.
 *
 */
public class Application {
    public static void main(String[] args) {
        ConcreteMediator mediator=new ConcreteMediator();
        ColleagueA colleagueA=new ColleagueA(mediator);
        ColleagueB colleagueB=new ColleagueB(mediator);
        ColleagueC colleagueC=new ColleagueC(mediator);
        colleagueA.setName("A国");
        colleagueB.setName("B国");
        colleagueC.setName("C国");

        String [] messA={"要求归还曾抢夺的100斤土豆","要求归还曾抢夺的20头牛"};
        colleagueA.giveMess(messA);
        String [] messB={"要求归还曾抢夺的10只公鸡","要求归还曾抢夺的15匹马"};
        colleagueB.giveMess(messB);
        String [] messC={"要求归还曾抢夺的300斤小麦","要求归还曾抢夺的50头驴"};
        colleagueC.giveMess(messC);
    }
}
