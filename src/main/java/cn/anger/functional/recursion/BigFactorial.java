package cn.anger.functional.recursion;

import java.math.BigInteger;

/**
 * author : liuanglin
 * date : 2022/6/28 20:02
 * description : 大数阶乘
 */
public class BigFactorial {
    public static BigInteger decrement(final BigInteger number) {
        return number.subtract(BigInteger.ONE);
    }

    public static BigInteger multiply(
        final BigInteger first, BigInteger second) {
        return first.multiply(second);
    }

    final static BigInteger ONE = BigInteger.ONE;
    final static BigInteger FIVE = BigInteger.valueOf(5);
    final static BigInteger TWENTYK = BigInteger.valueOf(200_00);

    private static TailCall<BigInteger> factorialTaiRec(
        final BigInteger factorial, final BigInteger number) {
        if (number.equals(ONE))
            return TailCalls.done(factorial);
        else
            return TailCalls.call(() ->
                factorialTaiRec(multiply(factorial, number), decrement(number)));
    }

    public static BigInteger factorial(final BigInteger number){
        return factorialTaiRec(ONE,number).invoke();
    }

    public static void main(String[] args) {
        System.out.printf("%.20s...", factorial(TWENTYK));
    }
}
