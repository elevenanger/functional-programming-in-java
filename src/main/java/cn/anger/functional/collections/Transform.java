package cn.anger.functional.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : liuanglin
 * @date : 2022/6/22 09:48
 * @description : 对集合进行转换操作
 */
public class Transform {

    public static void main(String[] args) {
        List<String> foods =
            Arrays.asList("potato","eggplant","tomato","beef","pork");

        /*
        因为 String 是不可变的
        Arrays.list() 方法创建的 list 也是不可变的
        不能直接操作原来的集合
        需要定义一个新的集合
        将转换后的元素添加到新集合中
        for 循环是外部迭代
        这种操作方式还是命令式的
         */
        System.out.println("for 循环进行集合转换操作 ： ");
        final List<String> upperFoods = new ArrayList<>();
        for (String food : foods) {
            upperFoods.add(food.toUpperCase());
        }
        System.out.println(upperFoods);

        /*
        使用 stream 的 map() 方法可以避免可变性并使代码更简洁
        stream() 方法将集合封装为 Stream 对象
        map 方法将 lambda 表达式应用于集合中的每个元素
        map 方法和 forEach 方法不同
        后者只是在上下文中运行给定的函数
        map 还会收集函数执行的结果并返回结果集合
        map 方法返回的集合大小和输入的集合大小相同

        java 接收 lambda 表达式或者方法引用作为函数接口抽象方法的实现
        如果 lambda 表达式仅仅是传递参数，则可以使用方法引用作为替代
        但是如果需要在传递参数之前对参数进行操作
        或者在返回结果之前执行转换动作
        则无法使用方法引用
         */
        System.out.println("\n使用 stream 方法进行集合转换操作 ：");
        foods.stream()
            // 仅将参数传递给方法，逻辑都封装在方法内可以使用方法引用
            .map(String::toUpperCase)
            // 需要在传递给方法之前对参数进行操作（food + " " ），则需要使用 lambda 表达式
            .forEach(food -> System.out.print(food + " "));

        System.out.println("\n使用 stream map 方法转换集合元素类型 ：");
        foods.stream()
            .map(String::length)
            .forEach(l -> System.out.print(l+ " "));
    }
}
