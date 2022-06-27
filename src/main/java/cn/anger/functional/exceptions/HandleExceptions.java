package cn.anger.functional.exceptions;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * author : liuanglin
 * date : 2022/6/27 10:23
 * description : 处理 Stream 中的异常
 */
public class HandleExceptions {
    public static void main(String[] args) {
        /*
        Java 会严格校验已检查异常的处理
        对于可能产生异常的 lambda 表达式
        由于 map() 方法不会抛出异常
        所以 lambda 表达式也不能抛出任何已检查的异常
        可以在 lambda 表达式中处理异常
        或者捕获异常重新抛出未检查的异常
         */
        Stream.of("./source","./target")
            .map(path -> {
                try {
                    return new File(path).getCanonicalPath();
                } catch (IOException e) {
                    // 如果有异常则返回异常的信息
                    return e.getMessage();
                }
            })
            .forEach(System.out::println);
    }
}
