package cn.anger.functional.collect;
import cn.anger.functional.comparator.ImplementingComparator.*;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;

/**
 * author : liuanglin
 * date : 2022/6/24 08:19
 * description : 从集合中根据特定条件筛选出子集
 */
public class OlderThan {

    public static void main(String[] args) {
        final List<Person> persons = Arrays.asList(
            new Person("Wong", 5),
            new Person("Zhang", 3),
            new Person("Liu", 3),
            new Person("Li", 4)
        );

        /*
        collect() 方法接收元素流
        并将它们收集到指定的结果容器中
        该方法需要明确的三件事：
        1、如何制作结果容器
        2、如何将单个元素添加到结果容器中
        3、如何将一个结果集合并到另一个结果集中
         */
        List<Person> olderThan3 =
            persons.stream()
                .filter(person -> person.getAge() > 3)
                    .collect(
                        // 制作结果容器
                        ArrayList::new,
                        // 将单个元素添加到结果容器中
                        ArrayList::add,
                        /*
                        将一个结果容器合并到另一个结果容器中
                        对于顺序操作来说这一步不是必须的
                        这种设计主要是为了兼容顺序操作和并行操作两种方式
                        在并行场景下
                        collect() 方法可以将 stream 中的元素拆分成不同的子集
                        然后将它们合并成一个集合
                        最后这个参数就是为此而存在的
                         */
                        ArrayList::addAll);
        System.out.println(olderThan3);

        System.out.printf("年龄大于3的人：%s%n",
            persons.stream()
                .filter(person -> person.getAge() > 3)
                /*
                使用 Collector 作为参数
                Collector 将 Supplier Accumulator Consumer 组合到一起
                便于复用
                 */
                .collect(toList()));

        Map<Integer,List<Person>> peopleByAge =
            persons.stream()
                .collect(groupingBy(
                    /*
                    分类器
                    可以传入 lambda 表达式或者方法引用
                    它返回需要进行分组的属性的值
                     */
                    Person::getAge));
        System.out.println(peopleByAge);

        Map<Integer,List<String>> nameOfPersonByAge =
            persons.stream()
                .collect(
                    groupingBy(
                        Person::getAge,
                        /*
                        groupingBy() 收集器将元素流收集到存储桶中
                        它还可以对每个存储桶应用另一个收集器
                        收集到存储桶的下游数据还能进行进一步的分类和收集
                         */
                        mapping(Person::getName,toList())));
        System.out.println(nameOfPersonByAge);

        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonOfEachLetter =
            persons.stream()
                .collect(groupingBy(
                    // 基于 name 首字母进行分类
                    person -> person.getName().charAt(0),
                    // 查找年龄最大的元素
                    reducing(BinaryOperator.maxBy(byAge))));
        System.out.println(oldestPersonOfEachLetter);
    }
}
