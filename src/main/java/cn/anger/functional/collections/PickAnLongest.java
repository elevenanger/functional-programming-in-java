package cn.anger.functional.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * @author : liuanglin
 * @date : 2022/6/23 08:33
 * @description : 从集合中找出最长的元素
 */
public class PickAnLongest {
    public static void main(String[] args) {
        final List<String> vegetables =
            Arrays.asList("cabbage","potato","eggplant","tomato","pepper");

        // MapReduce 模式
        int totalNumber =
            vegetables
                .stream()
                .mapToInt(String::length)
                .sum();

        System.out.println("元素总长度 ： " + totalNumber);

        /*
        查找最长的元素
        如果多个元素长度一样
        则取最前的一个
        reduce() 方法接收二元表达式作为参数
        方法遍历整个集合
        此外，它还会继承 lambda 表达式计算的结果用于后续的计算
        最终的结果可能为空，reduce() 方法返回 Optional 对象
         */
        final BinaryOperator<String> findTheFirstLongest =
            (s1, s2) -> s1.length() >= s2.length()?s1:s2;
        final Optional<String> aLongVeg =
            vegetables.stream()
                .reduce(findTheFirstLongest);

        aLongVeg.ifPresent(f -> System.out.printf("单词最长的蔬菜 ：%s%n",f));

        /*
        如果集合只有一个元素
        reduce() 方法会直接返回这个元素，不执行lambda表达式
         */
        List<String> singletonList = Collections.singletonList("single");
        final Optional<String> singleString =
            singletonList.stream().reduce(
                (s1,s2) -> {
                    System.out.println("Single String Compare");
                    return String.valueOf(findTheFirstLongest);
                }
            );
        singleString.ifPresent(System.out::println);

        /*
        如果需要设置默认值或者基础值
        可以将这个值作为参数传递给 reduce() 方法
        这个值将会参与 lambda 表达式的计算
        所以不会有获取不到结果的情况
        直接使用 T 参数类型接收方法返回的结果
         */
        String longest =
            vegetables.stream()
                .reduce("cucumber",findTheFirstLongest);
        System.out.println(longest);
    }
}
