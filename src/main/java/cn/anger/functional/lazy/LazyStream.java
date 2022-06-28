package cn.anger.functional.lazy;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * author : liuanglin
 * date : 2022/6/28 11:36
 * description : 演示 Stream 的惰性计算机制
 */
public class LazyStream {

    private static int length(final String name) {
        System.out.printf("计算 %s 长度%n", name);
        return name.length();
    }

    private static String toUpper(final String name) {
        System.out.printf("转换 %s 成大写%n", name);
        return name.toUpperCase();
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList(
            "carl", "kevin", "mike", "lawson", "bene", "jeffery", "jackson",
            "bowen", "joe", "susan");

        /*
        Stream 调用链中的每一步只做确保终止操作能够完成的操作
        首先执行 filter() 方法
        知道查找到集合中第一个满足给定 lambda 表达式的元素
        执行 filter() 方法后传递给 map() 方法
        map() 方法正常执行后传递给终止方法完成计算
         */
        Optional<String> firstNameWith3Letters =
            names.stream()
                .filter(name -> length(name) == 3)
                .map(LazyStream::toUpper)
                .findFirst();
        firstNameWith3Letters.ifPresent(System.out::println);

        /*
        即使将中间操作和终止操作拆分开来
        中间操作也会延迟执行
        知道终止操作被调用才开始执行
         */
        System.out.println("拆分中间操作和终止操作：");
        final Stream<String> nameWith3Letters =
            names.stream()
                .filter(name -> length(name) == 3)
                .map(LazyStream::toUpper);
        System.out.println("调用终止操作:");
        firstNameWith3Letters = nameWith3Letters.findFirst();
        firstNameWith3Letters.ifPresent(System.out::println);
    }
}
