package com.uptoser.java.design_patterns.behavioral_patterns.command;

/**
 * 老师类：代表命令模式中的Invoker请求者
 */
public class Teacher {

	Command command;

	public Teacher(Command command){
		this.command = command;
	}
	public Teacher(){
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public void execute() {
		command.execute();
	}

	public void undo() {
		command.undo();
	}

}
