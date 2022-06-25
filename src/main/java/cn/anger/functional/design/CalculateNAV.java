package cn.anger.functional.design;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * author : liuanglin
 * date : 2022/6/25 15:26
 * description : 对数据进行计算
 * 使用 lambda 表达式以及策略模式可以将关注点与方法分离
 * 也可以使用 lambda 表达式将关注点与类分离
 * 从重用的角度来看
 * 委派设计模式是比继承更好的设计工具
 * 它可以轻易区分类锁依赖的实现
 * 并且可以动态插入不同的行为
 * 这种做法可以区独立于类的行为和类所依赖的行为
 * 使得设计更为灵活二不需要强制使用深层的类的继承结构
 * 相较于将部分职责委派给另一个类
 * 可以将其委派给 lambda 表达式和方法引用
 */
public class CalculateNAV {

    /**
     * 输入股票代码
     * 返回股票的单位净值
     * 使用其它方法实现具体查找和计算逻辑
     * 所以将 Function 声明为一个 private 域
     * 而不是方法的参数
     * 将查找单位净值这部分职责委派给 lambda 表达式或者方法引用
     */
    private Function<String, BigDecimal> priceFinder;

    public CalculateNAV(final Function<String, BigDecimal> priceFinder) {
        /*
        在构造函数中初始化 priceFinder 域
        而不是将其耦合到该类中某个具体的实现方法
        这使用了依赖注入以及依赖倒置原则
        将关注点分离并依赖于抽象
        而不是嵌入到实现中
        使得代码的可扩展性更强
         */
        this.priceFinder = priceFinder;
    }

    /**
     * 根据股份以及股票代码计算股票的价值
     * @param ticker 股票代码
     * @param shares 股份
     * @return 股票价值
     */
    public BigDecimal computeStockWorth(
        final String ticker, final int shares) {
        // 价值=单位净值*股份
        return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
    }

}
