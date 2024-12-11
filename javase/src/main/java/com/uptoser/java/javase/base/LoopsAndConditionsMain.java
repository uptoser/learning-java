package com.uptoser.java.javase.base;

/**
 * Java 条件语句 和 循环语句
 */
public class LoopsAndConditionsMain {
    /**
     * 条件语句 Java Condition Statements
     * @return
     */
    public LoopsAndConditionsMain conditionStatements(){
        /*
        if条件语句 If Statements
        */
        int age = 17;
        if(age>1){
            System.out.println("if之后括号里的只能是一个逻辑表达式");
            System.out.println("花括号里的语句是一个整体，需要一起执行");
        }
        //if else 语句
        if(age >= 18)
            System.out.println("你已经成年了");
        else {
            System.out.println("你还没成年");
            System.out.println("只有一行语句时，可以省略花括号");
        }
        //if else if
        age = 32;
        if(age<18){
            System.out.println("你是青年");
        }else if(age < 50){
            System.out.println("你是中年");
        }else{
            System.out.println("你是老年");
        }

        /*
        switch 条件语句 Switch Statements
        */
        Season season = Season.FALL;
        switch (season){
            case SPRING:
                System.out.println("春暖花开");
                break;
            case SUMMER:
                System.out.println("夏日炎炎");
                break;
            case FALL:
                System.out.println("秋高气爽");
                break;
            case WINTER:
                System.out.println("冬雪皑皑");
                break;
            default:
                System.out.println("季节输入错误");
        }
        return this;
    }

    /**
     * 循环语句 Loop Statements
     */
    public void loopStatements(){
        //while 循环语句
        int i = 1;
        while (i<=10){
            System.out.printf("当前的数值为：%d\n",i++);
        }
        // do while 循环语句
        do {
            System.out.printf("当前的数值为：%d\n",i++);
        }while (i<=20);
        //for循环语句
//        for (int i=0;i<10;i++){
        for (;i<31;i++){
            System.out.printf("当前的数值为：%d\n",i);
        }
        //嵌套循环
        i = 0;
        while (i<5){
            if(3==i){
                i += 1;
                continue;//使用continue忽略本次循环剩下语句
            }
            for (int j=0;j<5;j++){
                if(i==4 && j==3)break;//使用break结束循环
                System.out.printf("当前的坐标为：[%d,%d]\n",i,j);
            }
            i++;
        }

    }

    enum Season {
        SPRING,SUMMER,FALL,WINTER
    }

    public static void main(String[] args) {
        new LoopsAndConditionsMain().conditionStatements().loopStatements();
    }
}
