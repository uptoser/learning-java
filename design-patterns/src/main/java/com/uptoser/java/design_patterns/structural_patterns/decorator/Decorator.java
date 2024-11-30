package com.uptoser.java.design_patterns.structural_patterns.decorator;


public abstract class Decorator extends ReadWord {

	protected ReadWord readWord;
	
	public Decorator(ReadWord readWord){
		this.readWord = readWord;
	}
}
