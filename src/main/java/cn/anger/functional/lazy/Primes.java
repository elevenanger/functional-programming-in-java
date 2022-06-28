package cn.anger.functional.lazy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * author : liuanglin
 * date : 2022/6/28 18:33
 * description : 无限 Stream
 */
public class Primes {

    public static boolean isPrime(final int number) {
        return number > 1 &&
            IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(divisor -> number % divisor == 0);
    }

    private static int primeAfter(final int number) {
        if (isPrime(number + 1))
            return number + 1;
        else
            return primeAfter(number + 1);
    }

    public static List<Integer> primes(final int fromNumber, final int count) {
        /*
        iterate() 方法创建顺序无限 Stream
        传入一个 seed 和函数
        将会将函数作用于 seed 产生后续的元素
        f(seed),f(f(seed)) ...
         */
        return Stream.iterate(primeAfter(fromNumber -1), Primes::primeAfter)
            .limit(count)
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        primes(10,10).forEach(System.out::println);
    }
}
