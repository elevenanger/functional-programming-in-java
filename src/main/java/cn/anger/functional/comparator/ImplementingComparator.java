package cn.anger.functional.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author : liuanglin
 * date : 2022/6/23 15:52
 * description : 实现比较器接口
 */
public class ImplementingComparator {

    public static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public int ageDifference(final Person other) {
            return age - other.getAge();
        }

        @Override
        public String toString() {
            return String.format("%s-%d", name, age);
        }
    }
    public static void main(String[] args) {
        final List<Person> persons = Arrays.asList(
            new Person("Wong", 5),
            new Person("Zhang", 3),
            new Person("Li", 4)
        );

        Comparator<Person> ascendComparator = Person::ageDifference;
        List<Person> ascendAge =
            persons.stream()
                /*
                sorted() 方法可以传入符合 Comparator 接口的 lambda 表达式
                遍历集合中的元素
                并应用给定的 Comparator 来决定元素的逻辑顺序
                sorted((p1,p2) -> p1.ageDifference(p2)) 表达式可以简写为方法引用
                编译器将自动将 ageDifference 方法作为第一个表达式参数 p1 的调用目标
                第二个表达式参数 p2 作为 ageDifference 方法的参数
                使用这种简写时
                必须确保第一个参数作为引用方法的预期目标
                剩余的参数作为引用方法的参数
                 */
                .sorted(ascendComparator)
                .collect(Collectors.toList());
        System.out.printf("年龄升序排序：%s%n",ascendAge);

        /*
        sorted((p1,p2) -> p2.ageDifference(p1))
        重新定义 lambda 表达式
        对年龄降序排序
        重复定义了逻辑
         */
        List<Person> descendAge =
            persons.stream()
                .sorted((p1,p2) -> p2.ageDifference(p1))
                .collect(Collectors.toList());
        System.out.printf("年龄降序排序：%s%n",descendAge);

        /*
        如果只是需要翻转原来的比较规则
        使用 reversed() 方法即可
        reversed() 方法是一个高阶方法
        它创建并返回一个函数表达式
         */
        Comparator<Person> descendComparator = ascendComparator.reversed();
        descendAge =
            persons.stream()
                .sorted(descendComparator)
                .collect(Collectors.toList());
        System.out.printf("年龄降序排序：%s%n",descendAge);

        System.out.printf("姓名升序排序：%s%n",
            persons.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList()));

        persons.stream()
            .min(ascendComparator)
            .ifPresent(p -> System.out.println("年龄最小：" + p));

        persons.stream()
            .max(ascendComparator)
            .ifPresent(p -> System.out.println("年龄最大：" + p));
    }
}
