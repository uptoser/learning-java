package com.uptoser.java.design_patterns.behavioral_patterns.command;

import java.util.ArrayList;

/**
 * 批作业命令类，实现了命令类
 */
public class OperateProject implements Command {
	ArrayList<Student> studentList;
	private Student student;
	
	public OperateProject(Student student, ArrayList<Student> studentList) {
		this.studentList = studentList;
		this.student = student;
	}

	/**
	 * 让某位同学上交作业
	 */
	@Override
	public void execute() {
		if(!studentList.contains(student)){
			studentList.add(student);
			student.handup();
		}
	}
	/**
	 * 让某位同学取回作业
	 */
	@Override
	public void undo() {
		if(studentList.contains(student)){
			studentList.remove(student);
			student.takeback();
		}
	}
	

}
