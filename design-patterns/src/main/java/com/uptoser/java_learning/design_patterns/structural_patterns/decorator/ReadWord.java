package com.uptoser.java_learning.design_patterns.structural_patterns.decorator;

import java.io.File;
import java.util.ArrayList;

public abstract class ReadWord {
	public abstract ArrayList<String> readWord(File file);
}
