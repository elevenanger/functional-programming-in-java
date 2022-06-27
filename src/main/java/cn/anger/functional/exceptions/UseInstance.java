package cn.anger.functional.exceptions;

/**
 * author : liuanglin
 * date : 2022/6/27 10:43
 * description : 声明符合特定需求的函数接口来处理异常
 */
@FunctionalInterface
public interface UseInstance<T, X extends Throwable> {
    void accept(T t) throws X;
}
