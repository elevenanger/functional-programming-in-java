package cn.anger.functional.recursion;

import java.util.stream.Stream;

/**
 * author : liuanglin
 * date : 2022/6/28 19:39
 * description :
 */
@FunctionalInterface
public interface TailCall<T> {

    TailCall<T> apply();

    default boolean isComplete() {return false;}

    default T result() { throw new Error("未完成");}

    default T invoke() {
        return Stream.iterate(this, TailCall::apply)
            .filter(TailCall::isComplete)
            .findFirst()
            .get()
            .result();
    }
}
