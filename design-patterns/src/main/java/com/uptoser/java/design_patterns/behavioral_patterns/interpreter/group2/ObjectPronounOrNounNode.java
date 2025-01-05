package com.uptoser.java.design_patterns.behavioral_patterns.interpreter.group2;
/**
 * 结符表达式 (TerminalExpression) ：
 * 实现 AbstractExpression 接口的类。
 * 该类将接口中的解释操作实现为与文法中的终结符相关联的操作，
 * 即文法中每个终结符号需要一个 TerminalExpression 类
 */
public class ObjectPronounOrNounNode implements Node{
    String [] word={"Me","Him","Tiger","Apple"};
    String token;
    boolean boo;
    public void parse(Context context){
        token=context.nextToken();
        int i=0;
        for(i=0;i<word.length;i++){
            if(token.equalsIgnoreCase(word[i])){
                boo=true;
                break;
            }
        }
        if(i==word.length)
            boo=false;
    }
    public void execute(){
        if(boo){
            if(token.equalsIgnoreCase(word[0]))
                System.out.print("我");
            if(token.equalsIgnoreCase(word[1]))
                System.out.print("他");
            if(token.equalsIgnoreCase(word[2]))
                System.out.print("老虎");
            if(token.equalsIgnoreCase(word[3]))
                System.out.print("苹果");
        }
        else{
            System.out.print(token+"(不是该语言中的句子)");
        }
    }
}