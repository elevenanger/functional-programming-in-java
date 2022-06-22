package cn.anger.functional.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : liuanglin
 * @date : 2022/6/22 10:30
 * @description : 查找集合中的元素
 */
public class FindingElements {
    public static void main(String[] args) {
        List<String> foods =
            Arrays.asList("potato","eggplant","tomato","beef","pork","egg");

        System.out.println("for 循环查找元素 ： ");
        List<String> startWithE = new ArrayList<>();
        for (String food : foods) {
            if (food.startsWith("e"))
                startWithE.add(food);
        }
        System.out.println(startWithE);

        /*
        filter() 方法接收返回 boolean 值的 lambda 表达式
        如果执行的结果为 true
        相应的元素将会被添加到结果集合中
        false 则忽略该元素
        filter() 方法最终返回 lambda 表达式执行结果为 true 的元素集合
        filter() 和 map() 方法最终都会返回一个结果集合
        区别是 map() 方法返回的结果集大小和输入的结果集大小相等
        filter() 方法返回的结果集大小不一定等于输入的结果集大小
        map() 可以改变集合元素的类型返回一个新的类型集合
        filter() 返回的集合只能是输入集合的子集
         */
        System.out.println("\nstream filter 方法查找元素 ：");
        startWithE =
            foods.stream()
            .filter(f -> f.startsWith("e"))
            .collect(Collectors.toList());
        System.out.println(startWithE);
    }
}
