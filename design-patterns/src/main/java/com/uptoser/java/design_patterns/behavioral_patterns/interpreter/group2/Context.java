package com.uptoser.java.design_patterns.behavioral_patterns.interpreter.group2;

import java.util.StringTokenizer;

/**
 * 上下文（Context）：包含解释器之外的一些全局信息，在解释过程中提供给解释器使用，通常用于存储变量的值、保存解释器的状态等。
 */
public class Context {
    StringTokenizer tokenizer;
    String token;

    public Context(String text) {
        setContext(text);
    }

    public void setContext(String text) {
        tokenizer = new StringTokenizer(text);
    }

    String nextToken() {
        if (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
        } else
            token = "";
        return token;
    }
}