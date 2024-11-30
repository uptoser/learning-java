package com.uptoser.java.design_patterns.behavioral_patterns.chain_of_responsibility.group1;

/**
 * 处理者 (Handler) ：处理者是一个接口，负责规定具体处理者处理用户请求的方法以及具体处理者设置后继对象的方法。
 */
public interface Handler{
   public abstract void handleRequest(String number);
   public abstract void setNextHandler(Handler handler);
}