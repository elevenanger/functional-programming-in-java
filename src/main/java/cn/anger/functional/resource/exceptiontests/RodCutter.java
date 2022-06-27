package cn.anger.functional.resource.exceptiontests;

/**
 * author : liuanglin
 * date : 2022/6/27 18:22
 * description : 异常测试基类
 */
public class RodCutter {

    public void maxProfit(final int profit) throws RodCutterException {
        if (profit == 0)
            throw new RodCutterException();
        else System.out.println("RodCutter.maxProfit " + profit);
    }

}
