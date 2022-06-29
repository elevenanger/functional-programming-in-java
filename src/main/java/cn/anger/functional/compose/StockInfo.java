package cn.anger.functional.compose;

import java.math.BigDecimal;

/**
 * author : liuanglin
 * date : 2022/6/29 12:26
 * description : 股票信息
 */
public class StockInfo {
    private final String ticker;
    private final BigDecimal price;

    public StockInfo(final String ticker, final BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
            "ticker='" + ticker + '\'' +
            ", price=" + price +
            '}';
    }
}
