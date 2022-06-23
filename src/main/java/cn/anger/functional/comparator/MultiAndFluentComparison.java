package cn.anger.functional.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * author : liuanglin
 * date : 2022/6/23 18:58
 * description : 基于多个属性进行比较
 */
public class MultiAndFluentComparison {

    public static void main(String[] args) {

        final List<Person> persons = Arrays.asList(
            new Person("Wong",5),
            new Person("Zhang",3),
            new Person("Liu",3),
            new Person("Li",4)
        );

        /*
        定义 byName byAge Function
        传入 comparing() 方法
        可以更流程的表达业务意图
         */
        final Function<Person,String> byName = Person::getName;
        final Function<Person,Integer> byAge = Person::getAge;
        System.out.println("按照姓名排序：");
        persons.stream()
            .sorted(Comparator.comparing(byName))
            .forEach(System.out::println);
        System.out.println("按照年龄排序：");
        persons.stream()
            .sorted(Comparator.comparing(byAge))
            .forEach(System.out::println);
        System.out.println("按照年龄以及姓名进行排序：");
        persons.stream()
            // thenComparing 与上一个 comparator 创建一个组合 comparator
            .sorted(Comparator.comparing(byAge).thenComparing(byName))
            .forEach(System.out::println);
    }
}
