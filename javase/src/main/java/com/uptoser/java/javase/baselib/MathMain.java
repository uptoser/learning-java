package com.uptoser.java.javase.baselib;

import java.util.Arrays;
import java.util.Random;

/**
 * Math类除提供了大量静态方法之外，还提供了两个类变量：PI和E，正如它们名字所暗示的，它们的值分别等于π和e
 */
public class MathMain {
    public static void main(String[] args) {
        /*
        Math类的用法几乎覆盖了Math类的所有数学计算功能
         */
        /*---------下面是三角运算---------*/
        //将弧度转换角度
        System.out.println("Math.toDegrees(1.57)："
                + Math.toDegrees(1.57));
        //将角度转换为弧度
        System.out.println("Math.toRadians(90)："
                + Math.toRadians(90));
        //计算反余弦，返回的角度范围在 0.0 到 pi 之间。
        System.out.println("Math.acos(1.2)：" + Math.acos(1.2));
        //计算反正弦；返回的角度范围在 -pi/2 到 pi/2 之间。
        System.out.println("Math.asin(0.8)：" + Math.asin(0.8));
        //计算反正切；返回的角度范围在 -pi/2 到 pi/2 之间。
        System.out.println("Math.atan(2.3)：" + Math.atan(2.3));
        //计算三角余弦。
        System.out.println("Math.cos(1.57)：" + Math.cos(1.57));
        //计算值的双曲余弦。
        System.out.println("Math.cosh(1.2 )：" + Math.cosh(1.2 ));
        //计算正弦
        System.out.println("Math.sin(1.57 )：" + Math.sin(1.57 ));
        //计算双曲正弦
        System.out.println("Math.sinh(1.2 )：" + Math.sinh(1.2 ));
        //计算三角正切
        System.out.println("Math.tan(0.8 )：" + Math.tan(0.8 ));
        //计算双曲正切
        System.out.println("Math.tanh(2.1 )：" + Math.tanh(2.1 ));
        //将矩形坐标 (x, y) 转换成极坐标 (r, thet));
        System.out.println("Math.atan2(0.1, 0.2)：" + Math.atan2(0.1, 0.2));
        /*---------下面是取整运算---------*/
        //取整，返回小于目标数的最大整数。
        System.out.println("Math.floor(-1.2 )：" + Math.floor(-1.2 ));
        //取整，返回大于目标数的最小整数。
        System.out.println("Math.ceil(1.2)：" + Math.ceil(1.2));
        //四舍五入取整
        System.out.println("Math.round(2.3 )：" + Math.round(2.3 ));
        /*---------下面是乘方、开方、指数运算---------*/
        //计算平方根。
        System.out.println("Math.sqrt(2.3 )：" + Math.sqrt(2.3 ));
        //计算立方根。
        System.out.println("Math.cbrt(9)：" + Math.cbrt(9));
        //返回欧拉数 e 的n次幂。
        System.out.println("Math.exp(2)：" + Math.exp(2));
        //返回 sqrt(x2 +y2)，没有中间溢出或下溢。
        System.out.println("Math.hypot(4 , 4)：" + Math.hypot(4 , 4));
        // 按照 IEEE 754 标准的规定，对两个参数进行余数运算。
        System.out.println("Math.IEEEremainder(5 , 2)："
                + Math.IEEEremainder(5 , 2));
        //计算乘方
        System.out.println("Math.pow(3, 2)：" + Math.pow(3, 2));
        //计算自然对数
        System.out.println("Math.log(12)：" + Math.log(12));
        //计算底数为 10 的对数。
        System.out.println("Math.log10(9)：" + Math.log10(9));
        //返回参数与 1 之和的自然对数。
        System.out.println("Math.log1p(9)：" + Math.log1p(9));
        /*---------下面是符号相关的运算---------*/
        //计算绝对值。
        System.out.println("Math.abs(-4.5)：" + Math.abs(-4.5));
        //符号赋值，返回带有第二个浮点数符号的第一个浮点参数。
        System.out.println("Math.copySign(1.2, -1.0)："
                + Math.copySign(1.2, -1.0));
        //符号函数；如果参数为 0，则返回 0；如果参数大于 0，
        // 则返回 1.0；如果参数小于 0，则返回 -1.0。
        System.out.println("Math.signum(2.3)：" + Math.signum(2.3));
        /*---------下面是大小相关的运算---------*/
        //找出最大值
        System.out.println("Math.max(2.3 , 4.5)：" + Math.max(2.3 , 4.5));
        //计算最小值
        System.out.println("Math.min(1.2 , 3.4)：" + Math.min(1.2 , 3.4));
        //返回第一个参数和第二个参数之间与第一个参数相邻的浮点数。
        System.out.println("Math.nextAfter(1.2, 1.0)："
                + Math.nextAfter(1.2, 1.0));
        //返回比目标数略大的浮点数
        System.out.println("Math.nextUp(1.2 )：" + Math.nextUp(1.2 ));
        //返回一个伪随机数，该值大于等于 0.0 且小于 1.0。
        System.out.println("Math.random()：" + Math.random());

        /*
        ThreadLocalRandom与Random
        Random类专门用于生成一个伪随机数，它有两个构造器：一个构造器使用默认的种子（以当前时间作为种子），
        另一个构造器需要程序员显式传入一个long型整数的种子。
        ThreadLocalRandom类是Java 7新增的一个类，它是Random的增强版。在并发访问的环境下，
        使用ThreadLocalRandom来代替Random可以减少多线程资源竞争，最终保证系统具有更好的线程安全性。

        ThreadLocalRandom与Random都比Math的random()方法提供了更多的方式来生成各种伪随机数，可以生成浮点类型的伪随机数，也可以生成整数类型的伪随机数
         */
        Random rand = new Random();
        System.out.println("rand.nextBoolean()："
                + rand.nextBoolean());
        byte[] buffer = new byte[16];
        rand.nextBytes(buffer);
        System.out.println(Arrays.toString(buffer));
        //生成0.0~1.0之间的伪随机double数
        System.out.println("rand.nextDouble()："
                + rand.nextDouble());
        //生成0.0~1.0之间的伪随机float数
        System.out.println("rand.nextFloat()："
                + rand.nextFloat());
        //生成平均值是 0.0，标准差是 1.0的伪高斯数
        System.out.println("rand.nextGaussian()："
                + rand.nextGaussian());
        //生成一个处于int整数取值范围的伪随机整数
        System.out.println("rand.nextInt()：" + rand.nextInt());
        //生成0~26之间的伪随机整数
        System.out.println("rand.nextInt(26)：" + rand.nextInt(26));
        //生成一个处于long整数取值范围的伪随机整数
        System.out.println("rand.nextLong()：" +  rand.nextLong());

        /*
        Random使用一个48位的种子，如果这个类的两个实例是用同一个种子创建的，对它们以同样的顺序调用方法，则它们会产生相同的数字序列。
         */
        Random r1 = new Random(50);
        System.out.println("第一个种子为50的Random对象");
        System.out.println("r1.nextBoolean():\t" + r1.nextBoolean());
        System.out.println("r1.nextInt():\t\t" + r1.nextInt());
        System.out.println("r1.nextDouble():\t" + r1.nextDouble());
        System.out.println("r1.nextGaussian():\t" + r1.nextGaussian());
        System.out.println("---------------------------");
        Random r2 = new Random(50);
        System.out.println("第二个种子为50的Random对象");
        System.out.println("r2.nextBoolean():\t" + r2.nextBoolean());
        System.out.println("r2.nextInt():\t\t" + r2.nextInt());
        System.out.println("r2.nextDouble():\t" + r2.nextDouble());
        System.out.println("r2.nextGaussian():\t" + r2.nextGaussian());
        System.out.println("---------------------------");
        Random r3 = new Random(100);
        System.out.println("种子为100的Random对象");
        System.out.println("r3.nextBoolean():\t" + r3.nextBoolean());
        System.out.println("r3.nextInt():\t\t" + r3.nextInt());
        System.out.println("r3.nextDouble():\t" + r3.nextDouble());
        System.out.println("r3.nextGaussian():\t" + r3.nextGaussian());
    }
}
