package com.uptoser.java.javase.other.io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * File类是java.io包下代表与平台无关的文件和目录
 */
public class FileMain {
    /**
     * 访问文件名相关方法
     * ➢ String getName()：返回此File对象所表示的文件名或路径名（如果是路径，则返回最后一级子路径名）。
     * ➢ String getPath()：返回此File对象所对应的路径名。
     * ➢ File getAbsoluteFile()：返回此File对象的绝对路径。
     * ➢ String getAbsolutePath()：返回此File对象所对应的绝对路径名。
     * ➢ String getParent()：返回此File对象所对应目录（最后一级子目录）的父目录名。
     * ➢ boolean renameTo(File newName)：重命名此File对象所对应的文件或目录，如果重命名成功，则返回true；否则返回false。
     *
     * 文件检测相关方法
     * ➢ boolean exists()：判断File对象所对应的文件或目录是否存在。
     * ➢ boolean canWrite()：判断File对象所对应的文件和目录是否可写。
     * ➢ boolean canRead()：判断File对象所对应的文件和目录是否可读。
     * ➢ boolean isFile()：判断File对象所对应的是否是文件，而不是目录。
     * ➢ boolean isDirectory()：判断File对象所对应的是否是目录，而不是文件。
     * ➢ boolean isAbsolute()：判断File对象所对应的文件或目录是否是绝对路径。该方法消除了不同平台的差异，
     * 可以直接判断File对象是否为绝对路径。在UNIX/Linux/BSD等系统上，如果路径名开头是一条斜线（/），则表明该File对象对应一个绝对路径；
     * 在Windows等系统上，如果路径开头是盘符，则说明它是一个绝对路径。
     *
     * 获取常规文件信息
     * ➢ long lastModified()：返回文件的最后修改时间。
     * ➢ long length()：返回文件内容的长度。
     *
     * 文件操作相关方法
     * ➢ boolean createNewFile()：当此File对象所对应的文件不存在时，该方法将新建一个该File对象所指定的新文件，如果创建成功则返回true；否则返回false。
     * ➢ boolean delete()：删除File对象所对应的文件或路径。
     * ➢ static File createTempFile(String prefix, String suffix)：在默认的临时文件目录中创建一个临时的空文件，
     * 使用给定前缀、系统生成的随机数和给定后缀作为文件名。这是一个静态方法，可以直接通过File类来调用。
     * prefix参数必须至少是3字节长。建议前缀使用一个短的、有意义的字符串，比如 "hjb" 或 "mail"。suffix参数可以为null，
     * 在这种情况下，将使用默认的后缀“.tmp”。
     * ➢ static File createTempFile(String prefix, String suffix, File directory)：
     * 在directory所指定的目录中创建一个临时的空文件，使用给定前缀、系统生成的随机数和给定后缀作为文件名。
     * 这是一个静态方法，可以直接通过File类来调用。
     * ➢ void deleteOnExit()：注册一个删除钩子，指定当Java虚拟机退出时，删除File对象所对应的文件和目录。
     *
     *目录操作相关方法
     * ➢ boolean mkdir()：试图创建一个File对象所对应的目录，如果创建成功，则返回true；否则返回false。
     * 调用该方法时File对象必须对应一个路径，而不是一个文件。
     * ➢ String[] list()：列出File对象的所有子文件名和路径名，返回String数组。
     * ➢ File[] listFiles()：列出File对象的所有子文件和路径，返回File数组。
     * ➢ static File[] listRoots()：列出系统所有的根路径。这是一个静态方法，可以直接通过File类来调用。
     *
     */
    public static void main(String[] args) throws IOException {
        // 以当前路径来创建一个File对象
        /*
        在默认情况下，系统总是依据用户的工作路径来解释相对路径，这个路径由系统属性“user.dir”指定，通常也就是运行Java虚拟机时所在的路径
         */
        File file = new File(".");
        // 直接获取文件名，输出一点
        System.out.println(file.getName());
        // 获取相对路径的父路径可能出错，下面代码输出null
        System.out.println(file.getParent());
        // 获取绝对路径
        System.out.println(file.getAbsoluteFile());
        // 获取上一级路径
        System.out.println(file.getAbsoluteFile().getParent());
        // 在当前路径下创建一个临时文件
        File tmpFile = File.createTempFile("aaa", ".txt", file);
        // 指定当JVM退出时删除该文件
        tmpFile.deleteOnExit();
        // 以系统当前时间作为新文件名来创建新文件
        File newFile = new File(System.currentTimeMillis() + "");
        System.out.println("newFile对象是否存在：" + newFile.exists());
        // 以指定newFile对象来创建一个文件
        newFile.createNewFile();
        // 以newFile对象来创建一个目录，因为newFile已经存在，
        // 所以下面方法返回false，即无法创建该目录
        newFile.mkdir();
        //删除文件
        newFile.delete();
        // 使用list()方法来列出当前路径下的所有文件和路径
        String[] fileList = file.list();
        System.out.println("====当前路径下所有文件和路径如下====");
        for (String fileName : fileList)
        {
            System.out.println(fileName);
        }
        // listRoots()静态方法列出所有的磁盘根路径。
        File[] roots = File.listRoots();
        System.out.println("====系统所有根路径如下====");
        for (File root : roots)
        {
            System.out.println(root);
        }
        FileMain.fileNameFilter();
    }

    /**
     * 在File类的list()方法中可以接收一个FilenameFilter参数，通过该参数可以只列出符合条件的文件
     * FilenameFilter接口里包含了一个accept(File dir, String name)方法，该方法将依次对指定File的所有子目录或者文件进行迭代，
     * 如果该方法返回true，则list()方法会列出该子目录或者文件
     */
    public static void fileNameFilter(){
        System.out.println("----------------------------");
        //如果文件是java结尾或是一个目录则返回true
        String[] files = new File(".").list((dir,name)->name.endsWith(".java")
                || new File(name).isDirectory());
        Arrays.stream(files).forEach(System.out::println);

    }
}
