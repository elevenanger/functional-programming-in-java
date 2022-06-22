package cn.anger.functional.collections;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author : liuanglin
 * @date : 2022/6/22 10:59
 * @description : 复用 lambda 表达式
 */
public class ReuseLambdaExpressions {

    public static void main(String[] args) {

        final List<String> vegetables =
            Arrays.asList("cabbage","potato","eggplant","tomato","pepper");
        final List<String> fruits =
            Arrays.asList("apple","pear","peach","kiwi");

        System.out.println("查找 p 开头的元素 ： ");
        long vegetablesCount = vegetables.stream()
            .filter(v -> v.startsWith("p"))
            .count();

        long fruitsCount = fruits.stream()
            .filter(f -> f.startsWith("p"))
            .count();

        System.out.println(fruitsCount+ " " + vegetablesCount);

        /*
        两个 filter(f -> f.startsWith("p")) 方法中都传入了相同涵义的 lambda 表达式
        filter() 方法接收 Predicate 函数接口对象引用作为参数
        java 将 lambda 表达式合成为 Predicate 接口中的抽象方法 test()
        可以显式地声明 Predicate 类型引用为 lambda 表达式
        并将其传递给 filter() 函数
         */
        System.out.println("\n复用 lambda 表达式查找 p 开头的元素 ： ");
        final Predicate<String> startWithP = s -> s.startsWith("p");
        vegetablesCount = vegetables.stream()
            .filter(startWithP)
            .count();

        fruitsCount = fruits.stream()
            .filter(startWithP)
            .count();

        System.out.println(fruitsCount+ " " + vegetablesCount);

        /*
        上面这种做法有个缺陷，如果需要换一个条件
        将以 p 开头改为以别的字符开头则需要定义一个新的 lambda 表达式
        代码重复的问题还是存在
         */
        final Predicate<String> startWithA = s -> s.startsWith("a");
        fruitsCount = fruits.stream()
            .filter(startWithA)
            .count();
        System.out.println("查找 a 开头的元素 ：" + fruitsCount);

        /*
        使用词法作用域移除代码重复的问题
        将字符提取出来作为函数的参数
        将函数作为 filter() 方法的参数
        需要定义一个变量缓存字符 letter
        保存这个变量直到接收到参数 s
        s 很明确是 stream 中的元素
        但是变量 letter 绑定的值是什么？
        Java 从定义 lambda 函数的范围查找 letter 变量
        称之为词法作用域
        词法作用域是一个非常有效的机制
        它可以缓存一个上下文中提供的值并在后来的另一个上下文中使用它
        由于这个 lambda 表达式在其定义的范围中关闭
        这也被称为闭包
         */
        fruitsCount = fruits.stream()
            .filter(checkIfStartWith("a"))
            .count();
        System.out.println("使用词法作用域的机制定义方法接收字母作为参数，返回匹配字母参数的 lambda 函数进行查找 ： " + fruitsCount);

        /*
        使用 checkIfStartWith() 方法可以有效避免代码重复的问题
        但是作为 static 方法，它会缓存未来调用该方法的变量
        需要将函数作用的范围缩小到使用函数的范围
        可以使用 Function 函数
         */
        final Function<String,Predicate<String>> startWithLetter =
            letter -> s -> s.startsWith(letter);
        fruitsCount = fruits.stream()
            .filter(startWithLetter.apply("a"))
            .count();
        System.out.println("定义 Function 函数返回 Predicate 查找 a 开头的元素 ：" + fruitsCount);
    }
    
    /**
     * 字符作为参数
     * 返回 Predicate 函数对象
     * 这个方法返回一个函数
     * 它本身是一个高阶函数
     */
    static Predicate<String> checkIfStartWith(String letter) {
        /*
        在 lambda 表达式内
        只能访问在封闭范围内是 final 或者有效 final 的变量
        lambda 表达式调用的实际是不确定的
        为了避免竞争条件
        在封闭范围内访问的局部变量初始化之后则不允许发生变化
        并不需要显式地声明变量为 final
        Java 会查找两个方面的因素：
        1、在封闭方法中，lambda 表达式定义之前，被访问的变量需要完成初始化
        2、变量的值不会在 lambda 表达式以外其它的地方发生变化
        满足以上两点条件则是有效的 final 变量
         */
        return s -> s.startsWith(letter);
    }
}
