package cn.anger.functional.recursion;

/**
 * author : liuanglin
 * date : 2022/6/28 19:18
 * description : 使用尾调用进行递归防止内存溢出
 * 尾调用是一种递归调用，最后一次调用是调用它本身
 * 常规的递归除了调用它本身之外还会进行额外的计算操作
 */
public class Factorial {

    /**
     * 使用递归计算阶乘
     */
    public static int factorialRec(final int number) {
        if (number == 1)
            return number;
        else
            /*
            每次调用都会等待下一次 factorialRec() 方法的结果
            每次调用都会往堆栈上面堆积
            一旦这个数字较大
            最终导致栈内存溢出
            需要一种不持有堆栈的前提下进行递归的方法
             */
            return number * factorialRec(number - 1);
    }

    public static int factorial(int number) {
        return factorialTailRec(1,number).invoke();
    }

    private static TailCall<Integer> factorialTailRec(final int factorial, final int number) {
        if (number == 1)
            return TailCalls.done(factorial);
        else
            return TailCalls.call(
                () -> factorialTailRec(factorial * number, number -1));
    }

    public static void main(String[] args) {
        System.out.println(factorialRec(10));
        try {
            System.out.println(factorialRec(20000));
        }catch (StackOverflowError error) {
            System.out.println(error);
        }
        System.out.println(factorial(20000));
    }
}
