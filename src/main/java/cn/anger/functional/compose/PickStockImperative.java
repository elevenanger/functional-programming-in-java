package cn.anger.functional.compose;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * author : liuanglin
 * date : 2022/6/29 12:38
 * description : 使用命令式代码从股票列表中选择股票
 */
public class PickStockImperative {

    public static void main(String[] args) {

        // 创建一个 List 存储全部的 StockInfo 对象
        final List<StockInfo> stocks = new ArrayList<>();
        for (String symbol : Tickers.listSymbols()) {
            stocks.add(StockUtils.getStock(symbol));
        }

        // 遍历 StockInfo 筛选出股价低于 100 的股票
        final List<StockInfo> stockPriceUnder100 = new ArrayList<>();
        final Predicate<StockInfo> isPriceLessThan100 = StockUtils.isPriceLessThan(100);
        for (StockInfo stock : stocks) {
            if (isPriceLessThan100.test(stock))
                stockPriceUnder100.add(stock);
        }

        // 规约股价低于 100 的股票，选出其中股价最高的股票
        StockInfo highestPrice =  new StockInfo("", BigDecimal.ZERO);
        for (StockInfo stockInfo : stockPriceUnder100) {
            highestPrice = StockUtils.pickHigher(highestPrice,stockInfo);
        }

        System.out.printf("低于 100 股价最高的股票是：%s", highestPrice);
    }
}
