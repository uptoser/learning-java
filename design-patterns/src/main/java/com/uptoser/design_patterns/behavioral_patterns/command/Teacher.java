package com.uptoser.design_patterns.behavioral_patterns.command;

public class Teacher {

	Command command;

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
