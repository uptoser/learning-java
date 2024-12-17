## 输入/输出流体系

| 分类    | 字节输入流                    | 字节输出流                     | 字符输入流               | 字符输出流               |
|:-----:|:------------------------:| ------------------------- | ------------------- | ------------------- |
| 抽象基类  | *InputStream*            | *OutputStream*            | *Reader*            | *Writer*            |
| 访问文件  | **FileInputStream**      | **FileOutputStream**      | **FileReader**      | **FileWriter**      |
| 访问数组  | **ByteArrayInputStream** | **ByteArrayOutputStream** | **CharArrayReader** | **CharArrayWriter** |
| 访问管道  | **PipedInputStream**     | **PipedOutputStream**     | **PipedReader**     | **PipedWriter**     |
| 访问字符串 |                          |                           | **StringReader**    | **StringWriter**    |
| 缓冲流   | BufferedInputStream      | BufferedOutputStream      | BufferedReader      | BufferedWriter      |
| 转换流   |                          |                           | InputStreamReader   | OutputStreamWriter  |
| 对象流   | ObjectInputStream        | ObjectOutputStream        |                     |                     |
| 抽象基类  | *FilterInputStream*      | *FilterOutputStream*      | *FilterReader*      | *FilterWriter*      |
| 打印流   |                          | PrintStream               |                     | PrintWriter         |
| 推回输入流 | PushBackInputStream      |                           | PushBackReader      |                     |
| 特殊流   | DataInputStream          | DataOutputStream          |                     |                     |
> * 粗体字标出的类代表节点流，必须直接与指定的物理节点关联
> * 斜体字标出的类代表抽象基类，无法直接创建实例
> * 上面表格仅仅总结了输入/输出流体系中位于java.io包下的流，还有一些诸如AudioInputStream、CipherInputStream、DeflaterInputStream、ZipInputStream等具有访问音频文件、加密/解密、压缩/解压等功能的字节流，它们具有特殊的功能，位于JDK的其他包下

