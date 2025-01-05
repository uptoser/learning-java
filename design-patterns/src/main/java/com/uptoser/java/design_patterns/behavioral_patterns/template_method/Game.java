package com.uptoser.java.design_patterns.behavioral_patterns.template_method;

/**
 * 抽象模板 (Abstract Template) ：
 * 抽象模板是一个抽象类。抽象模板定义了若干个方法以表示一个算法的各个步骤，
 * 这些方法中有抽象方法也有非抽象方法，其中的抽象方法称作原语操作（ Primitive Operation) 。
 * 重要的一点是，抽象模板中还定义了一个称作模板方法的方法，该方法不仅包含有抽象模板中表示算法步骤的方法调用，
 * 而且也可以包含有定义在抽象模板中的其他对象的方法调用，即模板方法定义了算法的骨架。
 */
public abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    /**
     * 钩子方法
     */
    public void hookMethod(){
        System.out.println("默认钩子方法");
    }

    public boolean isHook(){
        return true;
    }

    //模板
    public final void play(){
        //挂钩处
        if(isHook())
            hookMethod();

        //初始化游戏
        initialize();

        //开始游戏
        startPlay();

        //结束游戏
        endPlay();
    }
}