package com.uptoser.java.javase.base;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 * Java 数组
 */
public class ArrayMain {
    public static void main(String[] args) {
        /*
        一旦数组的初始化完成，数组在内存中所占的空间将被固定下来，因此数组的长度将不可改变。
        即使把某个数组元素的数据清空，但它所占的空间依然被保留，依然属于该数组，数组的长度依然不变。
         */
        String[] strArrayName;//定义数组 推荐
        int intArrayName[];//定义数组
        /*
        Java语言中数组必须先初始化，然后才可以使用。所谓初始化，就是为数组的数组元素分配内存空间，并为每个数组元素赋初始值。
         */
        //静态初始化数组
        intArrayName = new int[]{1,2,3,4,5,6,7,8,9};
        String[] seasonArrays = {"SPRING","SUMMER","AUTUMN","WINTER"};
        //动态初始化数组
        int[] prices = new int[5];
        //使用数组
        String spring = seasonArrays[0];
        /*
        如果访问数组元素时指定的索引值小于0，或者大于等于数组的长度，编译程序不会出现任何错误，
        但运行时出现异常：java.lang.ArrayIndexOutOfBoundsException：N（数组索引越界异常）

        spring = seasonArrays[4];
        */
        prices[0] = 10;//数组元素进行赋值
        for (int i=0;i<prices.length;i++){
//            System.out.println(prices[i]);
        }

        /*
        数组引用变量只是一个引用，这个引用变量可以指向任何有效的内存，只有当该引用指向有效内存后，才可通过该数组变量来访问数组元素。
        如果希望在程序中访问数组对象本身，则只能通过这个数组的引用变量来访问它。
        实际的数组对象被存储在堆（heap）内存中；如果引用该数组对象的数组引用变量是一个局部变量，那么它被存储在栈（stack）内存中
         */

        //操作数组的工具类：Arrays
        ArrayMain.arraysTools();
        ArrayMain.jdk8ArraysTools();
    }



    /**
     * 操作数组的工具类：Arrays
     */
    public static void arraysTools(){
        int[] a = {1, 3, 5, 8, 9, 11, 14, 17, 15, 20};
        int[] a2 = {1, 3, 5, 8, 9, 11, 14, 17, 15, 20};
        //如果a数组和a2数组的长度相等，而且a数组和a2数组的数组元素也一一相同，该方法将返回true
        boolean equals = Arrays.equals(a, a2);
        System.out.println(equals);
        //这个方法将会把a2数组复制成一个新数组，其中length是新数组的长度
        /*
        type[] copyOfRange(type[] original, int from, int to)：这个方法与前面方法相似，
        但这个方法只复制original数组的from索引到to索引的元素。
         */
        int[] a3 = Arrays.copyOf(a2, 11);
        equals = Arrays.equals(a, a3);//a和a3不相等，因为长度不同、值不同
        System.out.println(equals);
        //把数组转成字符转
        System.out.println("a3="+Arrays.toString(a3));
        //使用二分法查询key元素值在a数组中出现的索引；如果a数组不包含key元素值，则返回负数。调用该方法时要求数组中元素已经按升序排列，这样才能得到正确结果。
        int result = Arrays.binarySearch(a, 15);
        System.out.println(result);
        //将a3数组第2个元素(包括)到第4个元素(不包括)赋值为88
        Arrays.fill(a3,1,3,88);
        System.out.println("a3="+Arrays.toString(a3));
        //对数组进行排序
        Arrays.sort(a);
        System.out.println("a="+Arrays.toString(a));
    }

    /**
     * jdk8新增Arrays工具类方法
     */
    private static void jdk8ArraysTools() {
        int[] arrays = {5,-8,3,7,99,0};
        //对数组进行并发排序
        Arrays.parallelSort(arrays);
        System.out.println(Arrays.toString(arrays));
        /*
        该方法使用op参数指定的计算公式计算得到的结果作为新的数组元素。
        新数组的第一个元素无须计算，直接等于array数组的第一个元素。
         */
        Arrays.parallelPrefix(arrays,new IntBinaryOperator(){
            //left代表新数组中前一个索引处的元素，right代表array数组中当前索引处的元素。
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(Arrays.toString(arrays));
        /*
        该方法使用指定的生成器（generator）为所有数组元素设置值，该生成器控制数组元素的值的生成算法。
         */
        arrays = new int[5];
        Arrays.setAll(arrays, new IntUnaryOperator() {
            //operand代表正在计算处的索引
            @Override
            public int applyAsInt(int operand) {
                return operand * 5 + 1;
            }
        });
        System.out.println(Arrays.toString(arrays));
        //流式编程
        Arrays.stream(arrays).filter(x->x>3).limit(3).forEach(System.out::println);
    }


}
