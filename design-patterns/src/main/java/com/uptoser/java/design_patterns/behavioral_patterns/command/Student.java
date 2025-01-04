package com.uptoser.java.design_patterns.behavioral_patterns.command;

/**
 * 学生类：相关于命令模式中的Receiver接收者
 */
public class Student {
	private String name;
	private boolean state;//true为已上交作业

	public Student(String name) {
		this.name = name;
		state = false;
	}

	public void handup() {
		if(!state){
			state = !state;
			System.out.println(name+"上交了作业");
		}else{
			System.out.println(name+"已经交过了作业");
		}
	}

	public void takeback() {
		if(state){
			state = !state;
			System.out.println(name+"取回了作业");
		}else{
			System.out.println(name+"还没有交过作业");
		}
	}
	
	public void printState(){
		System.out.println(name);
	}

	public void setState(boolean state) {
		this.state = state;
	}
	

}
