package cn.anger.functional.collections;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author : liuanglin
 * @date : 2022/6/22 08:42
 * @description : 使用 lambda 表达式让迭代的过程更简洁
 */
public class Iteration {

    public static void main(String[] args) {
        List<String> foods =
            Arrays.asList("potato","eggplant","tomato","beef","pork");

        System.out.println("for i 循环 ：");
        for (int i = 0; i < foods.size(); i++) {
            System.out.print(foods.get(i) + " ");
        }

        System.out.println("\nfor 迭代 : ");
        for (String food: foods) {
            System.out.print(food + " ");
        }
        /*
        for i 和 for 迭代都属于外部迭代
        将需要达成的目标以及如何达成目标混合在一起
        显示控制迭代的过程
        两种迭代方式都是命令式的迭代

        将代码风格转变为函数式风格的原因：
        1、for 循环本质上是顺序的，而且很难并行化
        2、这种循环是非多态的，将集合传递给 for 循环，而不是调用集合的方法（多态方法）来执行任务
        3、在设计层面，这违反了"告诉，而不是命令"的原则，这种操作要求执行特性的迭代，而不是将迭代的细节给底层的库实现
         */

        System.out.println("\nIterable.forEach() 迭代，使用匿名内部类作为参数 : ");
        /*
        Iterable.forEach() 方法接收 Consumer 类型的参数
        通过匿名内部类的方式声明 Consumer 以及 accept() 方法执行相应的逻辑
        在 foods 集合上调用了 forEach() 方法
        将匿名 Consumer 内部类实例传递给 forEach() 方法
        forEach() 方法将会为集合中的每个元素调用 Consumer 的 accept() 方法
        执行 accept() 方法中相应的逻辑

        这种方式为内部迭代
        不需要声明如何进行迭代
        只需要聚焦于对集合中元素的操作
        但是缺点是代码看起来会更加冗长
         */
        foods.forEach(new Consumer<String>() {
            @Override
            public void accept(String food) {
                System.out.print(food + " ");
            }
        });

        /*
        forEach 方法接收 lambda 表达式作为参数
        取代匿名内部类
        使得代码看起来更简洁
        forEach 是一个高阶函数
        接收 lambda 表达式或者函数代码块作为参数在每个元素的上下文进行执行
        food 变量在 forEach 方法调用的过程中绑定每个集合中的元素
        底层的库控制如何评估 lambda 表达式
        它可以控制惰性执行、执行顺序或者并行执行这些表达式
         */
        System.out.println("\n使用 lambda 表达式作为 forEach() 参数 ： ");
        foods.forEach((final String food) -> System.out.print(food + " "));

        /*
        Java 基于上下文自动对 lambda 表达式的参数类型进行推断
        不需要显示声明参数类型
        值得注意的是，类型推断的参数是非 final 的
         */
        System.out.println("\n使用类型推断 lambda 表达式作为 forEach() 参数 ： ");
        foods.forEach(name -> System.out.print(name + " "));

        System.out.println("\n使用方法引用作为 forEach() 参数 ： ");
        foods.forEach(System.out::print);
    }

}
