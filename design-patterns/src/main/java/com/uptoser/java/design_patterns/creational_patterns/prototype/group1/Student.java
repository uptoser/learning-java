package com.uptoser.java.design_patterns.creational_patterns.prototype.group1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;

@Data
@AllArgsConstructor
public class Student implements Cloneable, Serializable {
    private Books book;
    private String name;
    private int age;

    @Override
    protected Student clone() throws CloneNotSupportedException {
        return (Student)super.clone();
    }

    public Student clone2() throws CloneNotSupportedException {
        Student clone = (Student) super.clone();
        clone.setBook(clone.getBook().clone());
        return clone;
    }
    //通过序列化复制对象
    public Student clone3() throws IOException, ClassNotFoundException {
        Student clone;
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteOut);
        oos.writeObject(this);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(byteIn);
        clone = (Student)ois.readObject();
        return clone;
    }
}


