package com.uptoser.java.javase.other.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Java 7对原有的NIO进行了重大改进，改进主要包括如下两方面的内容。
 * ➢ 提供了全面的文件IO和文件系统访问支持。
 * ➢ 基于异步Channel的IO。
 * 第一个改进表现为Java 7新增的java.nio.file包及各个子包；
 * 第二个改进表现为Java 7在java.nio.channels包下增加了多个以Asynchronous开头的Channel接口和类。
 *
 * Java 7把这种改进称为NIO.2
 */
public class NIO2Main {
    /**
     * Path接口代表一个平台无关的平台路径
     * NIO.2还提供了Paths工具类
     * Paths包含了两个返回Path的静态工厂方法
     */
    public void paths(){
        // 以当前路径来创建Path对象
        Path path = Paths.get(".");
        System.out.println("path里包含的路径数量：" + path.getNameCount());
        System.out.println("path的根路径：" + path.getRoot());
        // 获取path对应的绝对路径。
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        // 获取绝对路径的根路径
        System.out.println("absolutePath的跟路径：" + absolutePath.getRoot());
        // 获取绝对路径所包含的路径数量
        System.out.println("absolutePath里包含的路径数量：" + absolutePath.getNameCount());
        System.out.println(absolutePath.getName(3));
        // 以多个String来构建Path对象
        Path path2 = Paths.get("g:" , "publish" , "codes");
        System.out.println(path2);

    }

    /**
     * NIO.2还提供了Files工具类
     * Files包含了大量静态的工具方法来操作文件
     */
    public void files() throws IOException {
        System.out.println("------------------------");
        final String CLASS_PATH = this.getClass().getClassLoader().getResource("").getPath().substring(1);
//        /*----------不重要的代码start----------- */
//        String[] classPath = CLASS_PATH.split("/");
//        //获取盘符
//        String diskLetter = classPath[0];
//        //获取不包括盘符的目录数组
//        String[] dir = Arrays.copyOfRange(classPath, 1, classPath.length);
//        //定义文件名
//        String fileName = "read.txt";
//        //向数组的最后加入一个元素（文件名） 返回一个新的数组
//        String[] filePath = pushElement(dir,fileName);
//        /*----------不重要的代码end----------- */
        Path path = Paths.get(CLASS_PATH+"read.txt");
        // 复制文件
        Files.copy(path , new FileOutputStream(CLASS_PATH+"a.txt"));
        // 判断文件是否为隐藏文件
        System.out.println("是否为隐藏文件：" + Files.isHidden(path));
        // 一次性读取文件的所有行
        List<String> lines = Files.readAllLines(path, Charset.forName("utf-8"));
        System.out.println(lines);
        //读取文件
        Files.lines(path).forEach(System.out::println);
        // 判断指定文件的大小
        System.out.println("read.txt的大小为：" + Files.size(path));
        List<String> poem = new ArrayList<>();
        poem.add("水晶潭底银鱼跃");
        poem.add("清徐风中碧竿横");
        // 直接将多个字符串内容写入指定文件中
        Files.write(Paths.get(CLASS_PATH+"out.txt") , poem , Charset.forName("utf-8"));
        FileStore cStore = Files.getFileStore(Paths.get("C:/"));
        // 判断C盘的总空间，可用空间
        System.out.println("C:共有空间：" + cStore.getTotalSpace());
        System.out.println("C:可用空间：" + cStore.getUsableSpace());
        //列出所有文件和子目录
        Files.list(Paths.get(".")).forEach(System.out::println);
        System.out.println("---------------------------------------------");
    }

    /**
     * Files类提供了如下两个方法来遍历文件和子目录。
     * ➢ walkFileTree(Path start, FileVisitor<?super Path>visitor)：遍历start路径下的所有文件和子目录。
     * ➢ walkFileTree(Path start, Set<FileVisitOption> options, int maxDepth, FileVisitor<?super Path>visitor)：
     * 与上一个方法的功能类似。该方法最多遍历maxDepth深度的文件。
     *
     * 上面两个方法都需要FileVisitor参数，FileVisitor代表一个文件访问器，
     * walkFileTree()方法会自动遍历start路径下的所有文件和子目录，遍历文件和子目录都会“触发”FileVisitor中相应的方法。
     * FileVisitor中定义了如下4个方法。
     * ➢ FileVisitResult postVisitDirectory(T dir, IOException exc)：访问子目录之后触发该方法。
     * ➢ FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs)：访问子目录之前触发该方法。
     * ➢ FileVisitResult visitFile(T file, BasicFileAttributes attrs)：访问file文件时触发该方法。
     * ➢ FileVisitResult visitFileFailed(T file, IOException exc)：访问file文件失败时触发该方法。
     *
     * FileVisitResult定义了如下几种后续行为。
     * ➢ CONTINUE：代表“继续访问”的后续行为。
     * ➢ SKIP_SIBLINGS：代表“继续访问”的后续行为，但不访问该文件或目录的兄弟文件或目录。
     * ➢ SKIP_SUBTREE：代表“继续访问”的后续行为，但不访问该文件或目录的子目录树。
     * ➢ TERMINATE：代表“中止访问”的后续行为。
     *
     * 实际编程时没必要为FileVisitor的4个方法都提供实现，
     * 可以通过继承SimpleFileVisitor（FileVisitor的实现类）来实现自己的“文件访问器”，
     * 这样就根据需要、选择性地重写指定方法了。
     */
    public void fileVisitorTest() throws IOException {
        Files.walkFileTree(Paths.get(".") , new SimpleFileVisitor<Path>(){
            // 访问文件时候触发该方法
            @Override
            public FileVisitResult visitFile(Path file , BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问" + file + "文件");
                // 找到了read.txt文件
                if (file.endsWith("read.txt")){
                    System.out.println("--已经找到目标文件--");
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }
            // 开始访问目录时触发该方法
            @Override
            public FileVisitResult preVisitDirectory(Path dir , BasicFileAttributes attrs) throws IOException{
                System.out.println("正在访问：" + dir + " 路径");
                return FileVisitResult.CONTINUE;
            }
        });
        System.out.println("-------------------------------");
    }

    /**
     * NIO.2的Path类提供了如下一个方法来监听文件系统的变化。
     * ➢ register(WatchService watcher, WatchEvent.Kind<?>...events)：
     * 用watcher监听该path代表的目录下的文件变化。events参数指定要监听哪些类型的事件。
     *
     * 在这个方法中WatchService代表一个文件系统监听服务，它负责监听path代表的目录下的文件变化。
     * 一旦使用register()方法完成注册之后，接下来就可调用WatchService的如下三个方法来获取被监听目录的文件变化事件。
     * ➢ WatchKey poll()：获取下一个WatchKey，如果没有WatchKey发生就立即返回null。
     * ➢ WatchKey poll(long timeout, TimeUnit unit)：尝试等待timeout时间去获取下一个WatchKey。
     * ➢ WatchKey take()：获取下一个WatchKey，如果没有WatchKey发生就一直等待。
     *
     * 果程序需要一直监控，则应该选择使用take()方法；如果程序只需要监控指定时间，则可考虑使用poll()方法
     */
    public void watchServiceTest() throws IOException, InterruptedException {
        // 获取文件系统的WatchService对象
        WatchService watchService = FileSystems.getDefault().newWatchService();
        // 为C:盘根路径注册监听
        Paths.get("C:/").register(watchService
                , StandardWatchEventKinds.ENTRY_CREATE
                , StandardWatchEventKinds.ENTRY_MODIFY
                , StandardWatchEventKinds.ENTRY_DELETE);
        while(true){
            // 获取下一个文件改动事件
            WatchKey key = watchService.take();    //①
            for (WatchEvent<?> event : key.pollEvents()){
                System.out.println(event.context() +" 文件发生了 "
                        + event.kind()+ "事件！");
            }
            // 重设WatchKey
            boolean valid = key.reset();
            // 如果重设失败，退出监听
            if (!valid) break;
        }
    }

    /**
     * Java 7的NIO.2在java.nio.file.attribute包下提供了大量的工具类，通过这些工具类，开发者可以非常简单地读取、修改文件属性
     * 这些工具类主要分为如下两类。
     * ➢ XxxAttributeView：代表某种文件属性的“视图”。
     * ➢ XxxAttributes：代表某种文件属性的“集合”，程序一般通过XxxAttributeView对象来获取XxxAttributes。
     *
     * 在这些工具类中，FileAttributeView是其他XxxAttributeView的父接口
     * ➢ AclFileAttributeView：通过AclFileAttributeView，开发者可以为特定文件设置ACL（Access Control List）及文件所有者属性。
     * 它的getAcl()方法返回List<AclEntry>对象，该返回值代表了该文件的权限集。通过setAcl（List）方法可以修改该文件的ACL。
     * ➢ BasicFileAttributeView：它可以获取或修改文件的基本属性，
     * 包括文件的最后修改时间、最后访问时间、创建时间、大小、是否为目录、是否为符号链接等。
     * 它的readAttributes()方法返回一个BasicFileAttributes对象，
     * 对文件夹基本属性的修改是通过BasicFileAttributes对象完成的。
     * ➢ DosFileAttributeView：它主要用于获取或修改文件DOS相关属性，比如文件是否只读、是否隐藏、是否为系统文件、是否是存档文件等。
     * 它的readAttributes()方法返回一个DosFileAttributes对象，对这些属性的修改其实是由DosFileAttributes对象来完成的。
     * ➢ FileOwnerAttributeView：它主要用于获取或修改文件的所有者。它的getOwner()方法返回一个UserPrincipal对象来代表文件所有者；
     * 也可调用setOwner（UserPrincipal owner）方法来改变文件的所有者。
     * ➢ PosixFileAttributeView：它主要用于获取或修改POSIX（Portable Operating System Interface of INIX）属性，
     * 它的readAttributes()方法返回一个PosixFileAttributes对象，
     * 该对象可用于获取或修改文件的所有者、组所有者、访问权限信息（就是UNIX的chmod命令负责干的事情）。
     * 这个View只在UNIX、Linux等系统上有用。
     * ➢ UserDefinedFileAttributeView：它可以让开发者为文件设置一些自定义属性。
     */
    public void attributeViewTest() throws IOException {
        String path = ClassLoader.getSystemResource("").getPath().substring(1)+"attribute";
        // 获取将要操作的文件
        Path testPath = Paths.get(path);
        // 获取访问基本属性的BasicFileAttributeView
        BasicFileAttributeView basicView = Files.getFileAttributeView(
                testPath , BasicFileAttributeView.class);
        // 获取访问基本属性的BasicFileAttributes
        BasicFileAttributes basicAttribs = basicView.readAttributes();
        // 访问文件的基本属性
        System.out.println("创建时间：" + new Date(basicAttribs
                .creationTime().toMillis()));
        System.out.println("最后访问时间：" + new Date(basicAttribs
                .lastAccessTime().toMillis()));
        System.out.println("最后修改时间：" + new Date(basicAttribs
                .lastModifiedTime().toMillis()));
        System.out.println("文件大小：" + basicAttribs.size());
        // 获取访问文件属主信息的FileOwnerAttributeView
        FileOwnerAttributeView ownerView = Files.getFileAttributeView(
                testPath, FileOwnerAttributeView.class);
        // 获取该文件所属的用户
        System.out.println(ownerView.getOwner());
        // 获取系统中guest对应的用户
        UserPrincipal user = FileSystems.getDefault()
                .getUserPrincipalLookupService()
                .lookupPrincipalByName("guest");
        // 修改用户
//        ownerView.setOwner(user);
        // 获取访问自定义属性的FileOwnerAttributeView
        UserDefinedFileAttributeView userView = Files.getFileAttributeView(
                testPath, UserDefinedFileAttributeView.class);
        List<String> attrNames = userView.list();
        // 遍历所有的自定义属性
        for (String name : attrNames)
        {
            ByteBuffer buf = ByteBuffer.allocate(userView.size(name));
            userView.read(name, buf);
            buf.flip();
            String value = Charset.defaultCharset().decode(buf).toString();
            System.out.println(name + "--->" + value) ;
        }
        // 添加一个自定义属性
        userView.write("Name1", Charset.defaultCharset()
                .encode("String1"));
        // 获取访问DOS属性的DosFileAttributeView
        DosFileAttributeView dosView = Files.getFileAttributeView(testPath
                , DosFileAttributeView.class);
        // 将文件设置隐藏、只读
        dosView.setHidden(true);
        dosView.setReadOnly(true);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NIO2Main n = new NIO2Main();
        n.paths();
        n.files();
//        n.fileVisitorTest();
//        n.watchServiceTest();
        n.attributeViewTest();
    }
    /**
     * 向数组的最后加入一个元素（文件名） 返回一个新的数组
     * @param dir  String数组
     * @param fileName  元素
     * @return  新数组
     */
    private String[] pushElement(String[] dir, String fileName) {
        String[] filePath = new String[dir.length+1];
        System.arraycopy(dir,0,filePath,0,dir.length);
        filePath[filePath.length-1] = fileName;
//        Arrays.stream(filePath).forEach(System.out::println);
        return filePath;
    }
}
