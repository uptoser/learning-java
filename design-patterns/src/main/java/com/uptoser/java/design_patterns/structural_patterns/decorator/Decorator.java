package com.uptoser.java.design_patterns.structural_patterns.decorator;

/**
 * 装饰者：也是抽象组件的子类
 */
public abstract class Decorator extends ReadWord {

	protected ReadWord readWord;
	
	public Decorator(ReadWord readWord){
		this.readWord = readWord;
	}
}
