package cn.anger.functional.lazy.evaluation;


import java.util.function.Supplier;

/**
 * author : liuanglin
 * date : 2022/6/28 09:54
 * description : 惰性计算
 */
public class Evaluation {

    /*
    比较耗时的计算方法
     */
    public static boolean evaluate(final int value) {
        System.out.println("Evaluation.evaluate : " + value);
        simulateTimeConsumeOp(1000);
        return value > 100;
    }

    private static void simulateTimeConsumeOp(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 传入两个 Boolean 值
     * 使用 && 操作符进行计算
     * 传入 evaluate() 方法的返回值
     * 虽然 && 操作符是断路操作
     * 因为参数需要在方法执行前确定
     * 所以 evaluate() 方法需要先执行完毕
     * 这个方法是积极运算的
     * 无法进行惰性计算
     */
    public static void eagerEvaluate(final boolean in1, final boolean in2) {
        System.out.println("积极计算：");
        System.out.println("结果：" + (in1 && in2));
    }

    /**
     * 如果已知某个参数将不会在执行方法的过程中使用
     * 可以设计方法使部分或者所有参数延迟计算
     * 使用 Supplier<Boolean> 作为参数 ，传入 evaluate() 方法
     * 参数按需进行计算, evaluate() 方法不会立即被执行
     * 利用 && 的断路机制
     * 如果 in1.get() 的结果为 false
     * in2.get() 将不会被执行
     */
    public static void lazyEvaluation(
        final Supplier<Boolean> in1, final Supplier<Boolean> in2) {
        System.out.println("惰性计算：");
        System.out.println("结果：" + (in1.get() && in2.get()));
    }

    public static void main(String[] args) {
        eagerEvaluate(evaluate(1),evaluate(2));
        lazyEvaluation(() -> evaluate(1),() -> evaluate(1));
    }
}
