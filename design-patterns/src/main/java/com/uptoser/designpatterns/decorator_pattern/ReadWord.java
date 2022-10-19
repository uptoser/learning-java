package com.uptoser.designpatterns.decorator_pattern;

import java.io.File;
import java.util.ArrayList;

public abstract class ReadWord {
	public abstract ArrayList<String> readWord(File file);
}
