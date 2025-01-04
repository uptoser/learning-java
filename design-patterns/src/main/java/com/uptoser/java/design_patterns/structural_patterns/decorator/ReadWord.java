package com.uptoser.java.design_patterns.structural_patterns.decorator;

import java.io.File;
import java.util.ArrayList;

/**
 * 原本的抽象组件
 */
public abstract class ReadWord {
	public abstract ArrayList<String> readWord(File file);
}
