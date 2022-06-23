package cn.anger.functional.strings;

/**
 * author : liuanglin
 * date : 2022/6/23 14:39
 * description : 遍历字符串
 */
public class IterateString {
    public static void main(String[] args) {
        final String str = "abc123ABC";
        /*
        chars() 方法返回一个 Stream
        使用 forEach() 方法进行迭代
        chars() 方法返回一个以整数表示的字符序列而非原字符
         */
        System.out.println("打印基于整数表示的字符：");
        str.chars().forEach(c -> System.out.print(c + " "));
        System.out.println("\n打印原字符：");
        str.chars()
            .mapToObj(c -> (char)c)
            .forEach(c -> System.out.print(c + " "));
        System.out.println("\n只打印数字：");
        str.chars()
            .filter(Character::isDigit)
            .mapToObj(c -> (char) c)
            .forEach(c -> System.out.print(c + " "));
        System.out.println("\n只打印字母：");
        /*
        使用方法引用
        Java 编译器检查方法是实例方法或者 static 方法
        如果是实例方法，合成的方法成为元素调用的目标(c.getC())
        如果是 static 方法，合成的方法将元素作为方法的参数(Character.isAlphabetic(c))
         */
        str.chars()
            .filter(Character::isAlphabetic)
            .mapToObj(TransformChar::new)
            .map(TransformChar::getC)
            .forEach(c -> System.out.print(c + " "));
    }
}

class TransformChar {
    private final int c;

    public char getC() {
        return (char) c;
    }

    public TransformChar(int c) {
        this.c = c;
    }
}