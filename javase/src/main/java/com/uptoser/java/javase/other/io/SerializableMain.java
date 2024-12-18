package com.uptoser.java.javase.other.io;

import java.io.*;

/**
 * 序列化机制允许将实现序列化的Java对象转换成字节序列，这些字节序列可以保存在磁盘上，或通过网络传输，以备以后重新恢复成原来的对象
 * Java 9增强了对象序列化机制，它允许对读入的序列化数据进行过滤，这种过滤可在反序列化之前对数据执行校验，从而提高安全性和健壮性。
 * 如果需要让某个对象支持序列化机制，则必须让它的类是可序列化的（serializable）。为了让某个类是可序列化的，该类必须实现如下两个接口之一。
 * ➢ Serializable
 * ➢ Externalizable
 * 该接口是一个标记接口，实现该接口无须实现任何方法，它只是表明该类的实例是可序列化的。
 */
public class SerializableMain {

    /**
     * 使用对象流实现序列化
     * ObjectOutputStream
     * ObjectInputStream
     */
     /*
    如果某个类的成员变量的类型不是基本类型或String类型，而是另一个引用类型，那么这个引用类必须是可序列化的，
    否则拥有该类型成员变量的类也是不可序列化的
     */
    public void serializableByObjectStream() throws IOException {
        File tmp = File.createTempFile("object","tmp");
        tmp.deleteOnExit();
        try(
            FileOutputStream fos = new FileOutputStream(tmp);
            FileInputStream fis = new FileInputStream(tmp);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            Person p = new Person("刘备", 56);
            oos.writeObject(p);
            Person np = (Person)ois.readObject();
            System.out.println("反序列化的人名："+np.getName());
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //在ObjectInputStream输入流中的readObject()方法声明抛出了ClassNotFoundException异常
            throw new RuntimeException(e);
        }
        /*
        当一个可序列化类有多个父类时（包括直接父类和间接父类），这些父类要么有无参数的构造器，
        要么也是可序列化的——否则反序列化时将抛出InvalidClassException异常
         */
        /*
        当使用Java序列化机制序列化可变对象时一定要注意，只有第一次调用wirteObject()方法来输出对象时才会将对象转换成字节序列，
        并写入到ObjectOutputStream；在后面程序中即使该对象的实例变量发生了改变，
        再次调用writeObject()方法输出该对象时，改变后的实例变量也不会被输出。
         */

    }

    public static void main(String[] args) throws IOException {
        SerializableMain s = new SerializableMain();
        s.serializableByObjectStream();
    }


     static class Person implements Serializable {
         /**
          * 该类变量的值用于标识该Java类的序列化版本，也就是说，如果一个类升级后，
          * 只要它的serialVersionUID类变量值保持不变，序列化机制也会把它们当成同一个序列化版本
          *
          * 可以通过JDK安装路径的bin目录下的serialver.exe工具来获得该类的serialVersionUID类变量的值。命令如下：
          */
        private static final long serialVersionUID = 512L;
        private String name;
        private int age;
         /**
          * 实例变量前面使用transient关键字修饰，可以指定Java序列化时无须理会该实例变量
          * transient关键字只能用于修饰实例变量，不可修饰Java程序中的其他成分。
          */
        public transient String password;
        public Person(String name, int age) {this.name = name;this.age = age;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name; }
        public int getAge() {return age;}
        public void setAge(int age) {this.age = age;}

         /**
          *Java还提供了一种自定义序列化机制，通过这种自定义序列化机制可以让程序控制如何序列化各实例变量
          * 在序列化和反序列化过程中需要特殊处理的类应该提供如下特殊签名的方法，这些特殊的方法用以实现自定义序列化。
          * ➢ private void writeObject(java.io.ObjectOutputStream out) throws IOException
          * ➢ private void readObject(java.io.ObjectInputStream in) throws IOException,ClassNotFoundException;
          * ➢ private void readObjectNoData() throws ObjectStreamException;
          */
         private void writeObject(java.io.ObjectOutputStream out)
                 throws IOException
         {
             // 将name Field的值反转后写入二进制流
             out.writeObject(new StringBuffer(name).reverse());
             out.writeInt(age);
         }
         private void readObject(java.io.ObjectInputStream in)
                 throws IOException, ClassNotFoundException
         {
             // 将读取的字符串反转后赋给name Field
             this.name = ((StringBuffer)in.readObject()).reverse()
                     .toString();
             this.age = in.readInt();
         }

         /**
          * Java还提供了另一种序列化机制，这种序列化方式完全由程序员决定存储和恢复对象数据。
          * 要实现该目标，Java类必须实现Externalizable接口，该接口里定义了如下两个方法。
          * ➢ void readExternal(ObjectInput in)：需要序列化的类实现readExternal()方法来实现反序列化。
          *     该方法调用DataInput（它是ObjectInput的父接口）的方法来恢复基本类型的实例变量值，
          *     调用ObjectInput的readObject()方法来恢复引用类型的实例变量值。
          * ➢ void writeExternal(ObjectOutput out)：需要序列化的类实现writeExternal()方法来保存对象的状态。
          *     该方法调用DataOutput（它是ObjectOutput的父接口）的方法来保存基本类型的实例变量值，
          *     调用ObjectOutput的writeObject()方法来保存引用类型的实例变量值。
          */
//         @Override
//         public void writeExternal(ObjectOutput out) throws IOException {
//             // 将name Field的值反转后写入二进制流
//             out.writeObject(new StringBuffer(name).reverse());
//             out.writeInt(age);
//         }
//
//         @Override
//         public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//             // 将读取的字符串反转后赋给name Field
//             this.name = ((StringBuffer)in.readObject()).reverse().toString();
//             this.age = in.readInt();
//         }
         /**
          * 关于对象序列化，还有如下几点需要注意。
          * ➢ 对象的类名、实例变量（包括基本类型、数组、对其他对象的引用）都会被序列化；
          * 方法、类变量（即static修饰的成员变量）、transient实例变量（也被称为瞬态实例变量）都不会被序列化。
          * ➢ 实现Serializable接口的类如果需要让某个实例变量不被序列化，则可在该实例变量前加transient修饰符，
          * 而不是加static关键字。虽然static关键字也可达到这个效果，但static关键字不能这样用。
          * ➢ 保证序列化对象的实例变量类型也是可序列化的，否则需要使用transient关键字来修饰该实例变量，要不然，该类是不可序列化的。
          * ➢ 反序列化对象时必须有序列化对象的class文件。
          * ➢ 当通过文件、网络来读取序列化后的对象时，必须按实际写入的顺序读取。
          */
    }
}
