package com.uptoser.java.design_patterns.structural_patterns.decorator;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Share 2017.9.10
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
		Decorator rw1 = new WordDecorator(rw, file2);
		ArrayList<String> wordList = rw1.readWord(file);
		for(String s : wordList)
			System.out.println(s);
		
		
		
	}

}
