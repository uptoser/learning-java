package com.uptoser.java.design_patterns.behavioral_patterns.command;

/**
 * 命令接口
 */
public interface Command {

	public void execute();
	public void undo();
}
