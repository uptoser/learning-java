package com.uptoser.java.javase.other.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 创建并使用自定义的类加载器
 * ClassLoader中包含了大量的protected方法——这些方法都可被子类重写。
 * ClassLoader类有如下两个关键方法。
 * ➢ loadClass(String name, boolean resolve)：该方法为ClassLoader的入口点，根据指定名称来加载类，系统就是调用ClassLoader的该方法来获取指定类对应的Class对象。
 * ➢ findClass(String name)：根据指定名称来查找类。
 * 如果需要实现自定义的ClassLoader，则可以通过重写以上两个方法来实现，通常推荐重写findClass()方法，而不是重写loadClass()方法。loadClass()方法的执行步骤如下。
 * ① 用findLoadedClass(String) 来检查是否已经加载类，如果已经加载则直接返回。
 * ② 在父类加载器上调用loadClass()方法。如果父类加载器为null，则使用根类加载器来加载。
 * ③ 调用findClass(String)方法查找类。
 * ClassLoader里还包含如下一些普通方法。
 * ➢ findSystemClass(String name)：从本地文件系统装入文件。它在本地文件系统中寻找类文件，如果存在，就使用defineClass()方法将原始字节转换成Class对象，以将该文件转换成类。
 * ➢ static getSystemClassLoader()：这是一个静态方法，用于返回系统类加载器。
 * ➢ getParent()：获取该类加载器的父类加载器。
 * ➢ resolveClass(Class<?> c)：链接指定的类。类加载器可以使用此方法来链接类c。读者无须理会关于此方法的太多细节。
 * ➢ findLoadedClass(String name)：如果此Java虚拟机已加载了名为name的类，则直接返回该类对应的Class实例，否则返回null。该方法是Java类加载缓存机制的体现。
 */
public class CompileClassLoader extends ClassLoader{
    // 读取一个文件的内容
    private byte[] getBytes(String filename) throws IOException {
        File file = new File(filename);
        long len = file.length();
        byte[] raw = new byte[(int)len];
        try(
                FileInputStream fin = new FileInputStream(file))
        {
            // 一次读取class文件的全部二进制数据
            int r = fin.read(raw);
            if(r != len)
                throw new IOException("无法读取全部文件：" + r + " != " + len);
            return raw;
        }
    }
    // 定义编译指定Java文件的方法
    private boolean compile(String javaFile) throws IOException {
        System.out.println("CompileClassLoader:正在编译 " + javaFile + "...");
        // 调用系统的javac命令
        Process p = Runtime.getRuntime().exec("javac " + javaFile);
        try {
            // 其他线程都等待这个线程完成
            p.waitFor();
        }
        catch(InterruptedException ie){
            System.out.println(ie);
        }
        // 获取javac线程的退出值
        int ret = p.exitValue();
        // 返回编译是否成功
        return ret == 0;
    }
    // 重写ClassLoader的findClass方法
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        Class clazz = null;
        // 将包路径中的点（.）替换成斜线（/）。
        String fileStub = name.replace("." , "/");
        String javaFilename = fileStub + ".java";
        String classFilename = fileStub + ".class";
        File javaFile = new File(javaFilename);
        File classFile = new File(classFilename);
        // 当指定Java源文件存在，且class文件不存在、或者Java源文件
        // 的修改时间比class文件修改时间更晚，重新编译
        if(javaFile.exists() && (!classFile.exists() || javaFile.lastModified() > classFile.lastModified())){
            try{
                // 如果编译失败，或者该Class文件不存在
                if(!compile(javaFilename) || !classFile.exists())
                {
                    throw new ClassNotFoundException(
                            "ClassNotFoundExcetpion:" + javaFilename);
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        // 如果class文件存在，系统负责将该文件转换成Class对象
        if (classFile.exists())
        {
            try
            {
                // 将class文件的二进制数据读入数组
                byte[] raw = getBytes(classFilename);
                // 调用ClassLoader的defineClass方法将二进制数据转换成Class对象
                clazz = defineClass(name,raw,0,raw.length);
            }
            catch(IOException ie)
            {
                ie.printStackTrace();
            }
        }
        // 如果clazz为null，表明加载失败，则抛出异常
        if(clazz == null)
        {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }
    // 定义一个主方法
    public static void main(String[] args) throws Exception
    {
        // 如果运行该程序时没有参数，即没有目标类
        if (args.length < 1)
        {
            System.out.println("缺少目标类，请按如下格式运行Java源文件：");
            System.out.println("java CompileClassLoader ClassName");
        }
        // 第一个参数是需要运行的类
        String progClass = args[0];
        // 剩下的参数将作为运行目标类时的参数，
        // 将这些参数复制到一个新数组中
        String[] progArgs = new String[args.length-1];
        System.arraycopy(args , 1 , progArgs
                , 0 , progArgs.length);
        CompileClassLoader ccl = new CompileClassLoader();
        // 加载需要运行的类
        Class<?> clazz = ccl.loadClass(progClass);
        // 获取需要运行的类的主方法
        Method main = clazz.getMethod("main" , (new String[0]).getClass());
        Object[] argsArray = {progArgs};
        main.invoke(null,argsArray);
    }
}
