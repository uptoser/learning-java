package com.uptoser.java.design_patterns.behavioral_patterns.command;

import java.util.ArrayList;

/**
 * 宏命令接口（批处理）
 */
public class MacroCommand implements Command {
	ArrayList<Command> commandList;
	
	public MacroCommand(ArrayList<Command> commandList) {
		this.commandList = commandList;
	}

	@Override
	public void execute() {
		if(commandList.size()!=0){
			for(Command command:commandList)
				command.execute();
		}
	}

	@Override
	public void undo() {
		if(commandList.size()!=0){
			for(Command command:commandList)
				command.undo();
		}
	}

}
