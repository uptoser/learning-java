package com.uptoser.java.design_patterns.structural_patterns.decorator;

import java.io.File;
import java.util.ArrayList;

/**
 * 装饰模式(包装器，扩展)
 * 动态地给对象添加一些额外的职责。就功能来说装饰模式相比生成子类更为灵活
 *
 * Decorator Pattern(Another Name: Wrapper)
 * Attach additional responsibilities to an object dynamically. Decorators provide a
 * flexible alternative to subclassing for extending functionality.
 *
 * 装饰模式中非常重要的一点就是“具体组件"和“装饰"都是“抽象组件"的子类
 */
public class Application {
	/*
	 * 装饰模式(包装器，扩展)：Component ConcreteComponent Decorator ConcreteDecotator
	 * 内容：一些客户想要读取句子的时候也会得到翻译已有ReadWord.java和ReadEnglishWord.java不允许修改现有系统的代码进行扩展
	 */
	public static void main(String[] args) {
		final String CLASS_PATH = ClassLoader.getSystemResource("").getPath();
		File file = new File(CLASS_PATH+"article.txt");
		File file2 = new File(CLASS_PATH+"article2.txt");
		ReadWord rw = new ReadEnglishWord();
		ReadWord rw1 = new WordDecorator(rw, file2);
		ArrayList<String> wordList = rw1.readWord(file);
		for(String s : wordList)
			System.out.println(s);
	}

}
