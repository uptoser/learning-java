package com.uptoser.java.design_patterns.behavioral_patterns.memento;

import com.uptoser.java.design_patterns.behavioral_patterns.memento.origin.Memento;

import java.io.*;

/**
 * 负责人 (Caretaker) ：
 * 负责管理保存备忘录的对象。
 * 负责人如果不和原发者在同一个包中，就不能对备忘录中的内容进行修改或读取。
 * 如果需要将备忘录保存到磁盘，负责人可以使用对象流将备忘录写人文件。
 */
public class CareTaker {
    final String CLASS_PATH = ClassLoader.getSystemResource("").getPath();
    File file;
    private Memento memento = null;

    CareTaker() {
        file = new File(CLASS_PATH+"savePoint");
    }

    public Memento getMemento() {
        if (file.exists()) {
            try {
                FileInputStream in = new FileInputStream(CLASS_PATH+"savePoint");
                ObjectInputStream inObject = new ObjectInputStream(in);
                memento = (Memento) inObject.readObject();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }else{
            System.out.println("存档点不存在");
        }
        return memento;
    }

    public void saveMemento(Memento memento) {
        try {
            FileOutputStream out = new FileOutputStream(CLASS_PATH+"savePoint");
            ObjectOutputStream outObject = new ObjectOutputStream(out);
            outObject.writeObject(memento);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
