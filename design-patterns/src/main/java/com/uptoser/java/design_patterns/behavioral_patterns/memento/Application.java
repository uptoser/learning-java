package com.uptoser.java.design_patterns.behavioral_patterns.memento;

import com.uptoser.java.design_patterns.behavioral_patterns.memento.origin.Memento;
import com.uptoser.java.design_patterns.behavioral_patterns.memento.origin.Originator;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * 备忘录模式（Memento Pattern）别名：标记
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样以后就可将该对象恢复到原先保存的状态。
 * <p>
 * Memento Pattern (Another Name: Token)
 * Without violating encapsulation, capture and externalize an object' original state
 * so that the object can be restored to this state later.
 * <p>
 * 应用实例： 1、后悔药。 2、打游戏时的存档。 3、Windows 里的 ctrl + z。 4、IE 中的后退。 5、数据库的事务管理。
 */
public class Application {
    public static void main(String[] args) {
        final String CLASS_PATH = ClassLoader.getSystemResource("").getPath();
        Scanner reader = new Scanner(System.in);
        Originator originator = new Originator(new File(CLASS_PATH+"phrase.txt"));
        File favorPhrase = new File(CLASS_PATH+"favorPhrase.txt");
        RandomAccessFile out = null;
        try {
            out = new RandomAccessFile(favorPhrase, "rw");
        } catch (IOException exp) {
        }
        System.out.println("是否从上次读取的位置继续读取成语（输入y或n）");
        String answer = reader.nextLine();
        if (answer.startsWith("y") || answer.startsWith("Y")) {
            CareTaker caretaker = new CareTaker();
            Memento memento = caretaker.getMemento();     //得到备忘录
            if (memento != null)
                originator.restoreFromMemento(memento);  //让Originator恢复到备忘录中所存储的状态
        }
        String phrase = null;
        while ((phrase = originator.readLine()) != null) {
            System.out.println(phrase);
            System.out.println("是否将该成语保存到" + favorPhrase.getName() + "(输入y或n)");
            answer = reader.nextLine();
            if (answer.startsWith("y") || answer.startsWith("Y")) {
                try {
                    out.seek(favorPhrase.length());
                    byte[] b = phrase.getBytes();
                    out.write(b);
                    out.writeChar('\n');
                } catch (IOException exp) {
                }
            }
            System.out.println("是否继续读取成语？(输入y或n)");
            answer = reader.nextLine();
            if (answer.startsWith("y") || answer.startsWith("Y"))
                continue;
            else {
                originator.closeRead();
                CareTaker caretaker = new CareTaker();
                caretaker.saveMemento(originator.createMemento());   //将备忘录保存到文件中
                try {
                    out.close();
                } catch (IOException exp) {
                }
                System.exit(0);
            }
        }
        System.out.println("读完全部成语");
    }
}
