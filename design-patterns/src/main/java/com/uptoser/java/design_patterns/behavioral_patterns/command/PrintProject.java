package com.uptoser.java.design_patterns.behavioral_patterns.command;

import java.util.ArrayList;

/**
 * 批作业类：实现了命令接口
 */
public class PrintProject implements Command {
	
	ArrayList<Student> studentList;

	public PrintProject(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}

	/**
	 * 检查学生作业上交情况
	 */
	@Override
	public void execute() {
		if(studentList.size()!=0){
			for(Student s : studentList)
				s.printState();
		}
	}

	@Override
	public void undo() {
		System.out.println("这里是撤销操作！！！");
	}

}
