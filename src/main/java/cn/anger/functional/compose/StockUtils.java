package cn.anger.functional.compose;

import cn.anger.functional.design.AngerFinance;

import java.math.BigDecimal;
import java.util.function.Predicate;

/**
 * author : liuanglin
 * date : 2022/6/29 12:29
 * description : 股票工具类
 */
public class StockUtils {

    /**
     * 获取股票信息
     * @param ticker 股票代码
     * @return 股票信息对象
     */
    public static StockInfo getStock(final String ticker) {
        return new StockInfo(ticker, AngerFinance.getPrice(ticker));
    }

    /**
     * 与给定股价进行比较
     * 比其小 true
     * 比其大 false
     * @param price 股价
     * @return 比较结果
     */
    public static Predicate<StockInfo> isPriceLessThan(final int price) {
        return
            stockInfo -> stockInfo.getPrice().compareTo(BigDecimal.valueOf(price)) < 0;
    }

    public static StockInfo pickHigher(
        final StockInfo stock1, final StockInfo stock2) {
        return stock1.getPrice().compareTo(stock2.getPrice()) > 0 ? stock1 :stock2;
    }

}
