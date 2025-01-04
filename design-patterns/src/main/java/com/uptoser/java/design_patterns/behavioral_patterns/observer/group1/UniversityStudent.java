package com.uptoser.java.design_patterns.behavioral_patterns.observer.group1;

import java.io.*;
/**
 * 具体观察者：大学生求职者
 */
public class UniversityStudent implements Observer{
    Subject subject;
    File myFile;
    UniversityStudent(Subject subject, String fileName){
        this.subject=subject;
        subject.addObserver(this);     //使当前实例成为subject所引用的具体主题的观察者
        myFile=new File(fileName);
    }
    public void hearTelephone(String heardMessage){
        try{
            RandomAccessFile out=new RandomAccessFile(myFile,"rw");
            out.seek(out.length());
            byte [] b=heardMessage.getBytes();
            out.write(b);                               //更新文件中的内容
            System.out.print("我是一个大学生,");
            System.out.println("我向文件"+myFile.getName()+"写入如下内容:");
            System.out.println(heardMessage);
        }catch(IOException e){
            System.out.println(e);
        }
    }
}

