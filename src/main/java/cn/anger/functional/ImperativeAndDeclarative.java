package cn.anger.functional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : liuanglin
 * @date : 2022/6/21 14:03
 * @description : 命令式编程和声明式编程
 */
class City {
    String name;

    public City(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class FindCity{
    static List<City> cities;
    static {
        cities=
            Stream.of("Chang Sha","Wu Han","Dong Guan")
                .map(City::new)
                .collect(Collectors.toList());
    }

    /**
     * 使用命令式的方式遍历 cities 查找是否存在匹配的城市
     * 命令式编程需要声明每一步执行的方式
     * 然后程序按照声明的方式去执行
     * 这种方式更为底层
     * 而且代码量也会比较多
     */
    static boolean findCityInImperativeWay(String cityName) {
        // 1 定义一个bBoolean 变量标识
        boolean found = false;
        // 2 遍历集合中的元素与我们需要查找的城市进行对比
        for (City city:cities) {
            if (city.name.equals(cityName)){
                // 3 存在符合条件的元素将标识设置为 true 结束循环
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * 使用声明式的方式查找城市
     * 声明式的代码对比命令式代码有以下几点提升：
     * 没有误用可变的变量
     * 封装迭代的过程
     * 代码更精简
     * 意图更清晰
     * 代码与业务意图密切相关
     * 不容易出错
     * 容易理解和维护
     */
    static boolean findCityInDeclarativeWay(String cityName) {
        return cities.contains(new City(cityName));
    }

    public static void main(String[] args) {
        String cityName = "Chang Sha";
        System.out.println("Imperative found : " + findCityInImperativeWay(cityName));
        System.out.println("Declarative found : " + findCityInDeclarativeWay(cityName));

        /* output:
        Imperative found : true
        Declarative found : true
        命令式和声明式的代码都能实现目标

        声明式的风格有以下好处：
        每个步骤之间组合更直观，不混乱
        免于低层次操作
        容易改变逻辑和优化
        方法库控制迭代
        效率更高，循环的惰性求职
        需要时更容易进行并行处理
         */
    }
}

/*
计算折扣
首先定义一个价格 BigDecimal 类型对象的集合
如果价格超过 20 ，则有 10% 的折扣
统计打折的金额的总和
 */
class CalPriceDiscount {
    static final BigDecimal START = BigDecimal.valueOf(20);
    static final BigDecimal DISCOUNT = BigDecimal.valueOf(0.9);
    static final Random RANDOM = new Random();
    static final List<BigDecimal> PRICES =
        IntStream
            .range(0,10)
            .mapToObj(i -> BigDecimal.valueOf(RANDOM.nextDouble() * 100 +1))
            .collect(Collectors.toList());

    static void imperativeCal() {
        BigDecimal totalOfDiscount = BigDecimal.ZERO;
        for (BigDecimal price : PRICES) {
            if (price.compareTo(START) > 0)
                totalOfDiscount = totalOfDiscount.add(price.multiply(DISCOUNT));
        }
        System.out.println("imperative total discount : " + totalOfDiscount);
    }

    static void declarativeCal() {
        BigDecimal totalOfDiscountPrices =
            PRICES.stream()
                .filter(price -> price.compareTo(START) > 0)
                .map(price -> price.multiply(DISCOUNT))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println("declarative total discount : " + totalOfDiscountPrices);
    }

    public static void main(String[] args) {
        imperativeCal();
        declarativeCal();
    }
}