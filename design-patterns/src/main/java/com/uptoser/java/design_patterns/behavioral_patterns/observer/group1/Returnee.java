package com.uptoser.java.design_patterns.behavioral_patterns.observer.group1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 具体观察者：海归求职者
 */
public class Returnee implements Observer{
    Subject subject;
    File myFile;
    Returnee(Subject subject, String fileName){
        this.subject=subject;
        subject.addObserver(this);
        myFile=new File(fileName);
    }
    public void hearTelephone(String heardMessage){
        try{
            boolean boo=heardMessage.contains("java程序员")||heardMessage.contains("软件");
            if(boo){
                RandomAccessFile out=new RandomAccessFile(myFile,"rw");
                out.seek(out.length());
                byte [] b=heardMessage.getBytes();
                out.write(b);
                System.out.print("我是一个海归,");
                System.out.println("我向文件"+myFile.getName()+"写入如下内容:");
                System.out.println(heardMessage);
            }else{
                System.out.println("我是海归,这次的信息中没有我需要的信息");
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}

