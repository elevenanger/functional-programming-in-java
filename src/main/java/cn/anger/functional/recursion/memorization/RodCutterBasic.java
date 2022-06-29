package cn.anger.functional.recursion.memorization;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * author : liuanglin
 * date : 2022/6/29 08:31
 * description : 演示 memorization 机制
 * 找到获取最大收益的木材切割方式
 */
public class RodCutterBasic {

    /*
    存储不同长度木材的价格
     */
    final List<Integer> prices;

    public RodCutterBasic(final List<Integer> prices) {
        this.prices = prices;
    }

    /**
     * 计算最大的收益
     * @param length 木材的长度
     * @return 最大收益
     */
    public int maxProfit(final int length) {
        // 计算不切割的收益
        int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
        for (int i = 1; i < length; i++) {
            /*
            递归调用 maxProfit()
            计算各种切割方式的最大收益
             */
            int priceWhenCut = maxProfit(i) + maxProfit(length - i);
            if (profit < priceWhenCut)
                profit = priceWhenCut;
        }
        return profit;
    }

    public int maxProfitWithFunction(final int length) {
        BiFunction<Function<Integer,Integer>, Integer, Integer> compute =
            (func, len) -> {
            int profit = (len <= prices.size()) ? prices.get(len - 1) : 0;
                for (int i = 1; i < len; i++) {
                    int priceWhenCut = func.apply(i) + func.apply(len - i);
                    if (profit < priceWhenCut) profit = priceWhenCut;
                }
                return profit;
            };
        return Memorizer.callMemorized(compute, length);
    }

    public static void main(String[] args) {
        final List<Integer> priceValues =
            Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);
        final RodCutterBasic rodCutterBasic = new RodCutterBasic(priceValues);
        System.out.println(rodCutterBasic.maxProfit(16));
        System.out.println(rodCutterBasic.maxProfitWithFunction(100));
    }
}
