package cn.anger.functional.recursion;

/**
 * author : liuanglin
 * date : 2022/6/28 19:46
 * description : TailCall 工具类
 */
public class TailCalls {

    public static <T> TailCall<T> call(final TailCall<T> nextCall) {
        return nextCall;
    }

    public static <T> TailCall<T> done(final T value) {
        return new TailCall<T>() {
            @Override
            public boolean isComplete() {
                return true;
            }

            @Override
            public T result() {
                return value;
            }

            @Override
            public TailCall<T> apply() {
                throw new Error("未完成");
            }
        };
    }
}
