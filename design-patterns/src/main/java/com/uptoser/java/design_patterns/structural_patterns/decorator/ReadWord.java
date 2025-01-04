package com.uptoser.java.design_patterns.structural_patterns.decorator;

import java.io.File;
import java.util.ArrayList;

/**
 * 原本的抽象组件
 */
public interface ReadWord {
	public ArrayList<String> readWord(File file);
}
