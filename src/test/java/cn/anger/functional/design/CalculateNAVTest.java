package cn.anger.functional.design;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : liuanglin
 * date : 2022/6/25 16:51
 * description : CalculateNAV 测试类
 */
class CalculateNAVTest {

    @Test
    void computeStockWorthByMethodReference() {
        // 委派方法引用实现 priceFinder
        final CalculateNAV calculateNAV = new CalculateNAV(AngerFinance::getPrice);
        assertEquals(
            calculateNAV.computeStockWorth("anger",10),
            new BigDecimal("1000.00"));
    }

    @Test
    void computeStockWorthByLambda() {
        // 委派 lambda 表达式实现 priceFinder
        final CalculateNAV calculateNAV =
            new CalculateNAV(ticker -> new BigDecimal("6.00"));
        BigDecimal expected = new BigDecimal("6000.00");
        assertEquals(0,
            calculateNAV.computeStockWorth("G00G", 1000)
                .compareTo(expected), 0.001);
    }
}