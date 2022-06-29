package cn.anger.functional.compose;

import java.util.Optional;

/**
 * author : liuanglin
 * date : 2022/6/29 14:08
 * description : 使用函数式的代码范式选择股票
 */
public class PickStockFunctional {

    public static void main(String[] args) {
        Optional<StockInfo> targetStock =
            Tickers.listSymbols()
                .stream()
                .map(StockUtils::getStock)
                .filter(StockUtils.isPriceLessThan(100))
                .reduce(StockUtils::pickHigher);
        targetStock.ifPresent(System.out::println);
    }
}
