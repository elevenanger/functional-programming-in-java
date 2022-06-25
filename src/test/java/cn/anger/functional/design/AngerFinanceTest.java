package cn.anger.functional.design;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * author : liuanglin
 * date : 2022/6/25 16:37
 * description : AngerFinance 测试类
 */
class AngerFinanceTest {

    @Test
    void getPrice() {
        assertEquals(new BigDecimal("100.00"), AngerFinance.getPrice("anger"));
        assertEquals(new BigDecimal("1993.08"), AngerFinance.getPrice("eleven"));
    }
}
