package cn.anger.functional.resource.exceptiontests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : liuanglin
 * date : 2022/6/27 18:26
 * description : RodCutter 测试类
 */
class RodCutterTest {

    @Test
    void VerboseExceptionTest() {
        RodCutter rodCutter = new RodCutter();
        try {
            rodCutter.maxProfit(0);
            fail("参数为 0 会产生异常");
        }catch (RodCutterException e) {
            assertTrue(true, "参数为0");
        }
    }

    @Test()
    void TerseExceptionTest() {
        RodCutter rodCutter = new RodCutter();
        /*
        使用 assertThrows() 方法断言异常
        第二个 Executable 参数使用 lambda 表达式执行代码
        代码更为精简
        准确表达需要测试的意图
         */
        assertThrows(RodCutterException.class,
            () -> rodCutter.maxProfit(0));
    }
}