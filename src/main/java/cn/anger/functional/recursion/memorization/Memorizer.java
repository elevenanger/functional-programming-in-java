package cn.anger.functional.recursion.memorization;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * author : liuanglin
 * date : 2022/6/29 09:16
 * description : 记录器
 */
public class Memorizer {

    public static <T, R> R callMemorized(
        final BiFunction<Function<T, R>, T, R> function, final T input) {
        Function<T, R> memorized = new Function<T, R>() {
            private final Map<T, R> store = new HashMap<>();
            @Override
            public R apply(final T input) {
                return store.computeIfAbsent(input, key ->
                    function.apply(this, key));
            }
        };
        return memorized.apply(input);
    }
}
