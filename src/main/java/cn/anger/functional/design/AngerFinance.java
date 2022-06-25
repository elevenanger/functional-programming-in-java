package cn.anger.functional.design;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * author : liuanglin
 * date : 2022/6/25 16:18
 * description : Anger 金融情况
 */
public class AngerFinance {
    static String financeInfoPath = "./src/main/java/resources/";

    /**
     * 根据股票代码读取对应路径下的文件
     * 获取股价
     * 此方法符合 Function<String,BigDecimal>
     * 可以作为方法引用参数传入 calculateNAV.computeStockWorth() 方法
     * 与其在 lambda 函数中处理异常
     * 应该找到更好的地方来处理它们
     * 重新抛出异常以便上游能够处理该异常
     * lambda 表达式或者方法引用只能抛出在函数接口抽象方法使用 throws 语句声明的异常
     * @param ticker 股票代码
     * @return 股价
     */
    public static BigDecimal getPrice(final String ticker) {
        try (Stream<String> sharesStream =
            Files.lines(Paths.get(financeInfoPath,ticker + ".dat"))){
            return
                sharesStream.map(BigDecimal::new)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
