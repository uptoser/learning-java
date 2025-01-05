package com.uptoser.java.design_patterns.behavioral_patterns.memento.origin;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 原发者 (Originator) ：
 * 需要在某个时刻保存其状态的对象。
 * 原发者负责创建备忘录，当原发者需要恢复某个时刻的状态时，它通过获得相应备忘录中的数据来恢复那个时刻的状态，
 */
public class Originator {

    long readPosition;     //在文件中的读取的位置
    File file;
    RandomAccessFile in;
    String phrase = null;

    public Originator(File file) {
        this.file = file;
        try {
            in = new RandomAccessFile(file, "r");
        } catch (IOException exp) {
        }
    }

    public Memento createMemento() {
        Memento mem = new Memento();
        mem.setPositionState(readPosition);
        return mem;
    }

    public void restoreFromMemento(Memento mem) {
        readPosition = mem.getPositionState();
    }

    public String readLine() {
        try {
            in.seek(readPosition);
            phrase = in.readLine();
            if (phrase != null) {
                byte b[] = phrase.getBytes("iso-8859-1");
                phrase = new String(b);
            }
            readPosition = in.getFilePointer();    //获取当前的读取位置
        } catch (IOException exp) {
        }
        return phrase;
    }

    public void closeRead() {
        try {
            in.close();
        } catch (IOException exp) {
        }
    }
}
