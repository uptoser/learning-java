package com.uptoser.designpatterns.decorator_pattern;


public abstract class Decorator extends ReadWord {

	protected ReadWord readWord;
	
	public Decorator(ReadWord readWord){
		this.readWord = readWord;
	}
}
