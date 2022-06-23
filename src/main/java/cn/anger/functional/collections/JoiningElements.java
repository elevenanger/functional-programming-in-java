package cn.anger.functional.collections;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author : liuanglin
 * date : 2022/6/23 14:04
 * description : 连接集合中的元素
 */
public class JoiningElements {

    public static void main(String[] args) {
        final List<String> vegetables =
            Arrays.asList("cabbage","potato","eggplant","tomato","pepper");

        /*
        使用增强 for 循环连接集合中的元素
        但是输出的结果最终会多一个 ,
         */
        System.out.println("增强 for 循环连接集合元素：");
        for (String vegetable : vegetables) {
            System.out.print(vegetable + ", ");
        }

        /*
        使用传统 for i 循环可以解决这个问题
        但是代码比较冗长
         */
        System.out.println("\nfor i 连接集合元素：");
        for (int i = 0; i < vegetables.size() - 1; i++) {
            System.out.print(vegetables.get(i) + ", ");
        }
        System.out.print(vegetables.get(vegetables.size() - 1));

        /*
        使用 java 8 新增的 String.join() 方法进行集合元素的连接
         */
        System.out.printf("%n使用 String.join() 方法连接集合的元素：%n%s",
            String.join(", ",vegetables));

        /*
        collect() 方法进行规约
        将实际的实现或者目标委托给收集器进行处理(Collector)
        Collectors 工具类提供了一系列的收集器
        收集器充当了收集对象接收 collect() 方法传递的元素并将其转换为需要的格式
         */
        System.out.printf("%n使用 Stream collect() 方法连接集合中的元素：%n%s",
            vegetables.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(", ")));
    }
}
