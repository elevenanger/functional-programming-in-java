package cn.anger.functional.design;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * author : liuanglin
 * date : 2022/6/25 14:46
 * description : 重构 AssetUtil
 * 对计算资产列表的总资产分离其中的关键问题进行重构
 * 1、如何迭代
 * 2、什么总资产
 * 3、如何计算总资产
 * 回顾 AssetUtil 中的三个方法对其中如何迭代和如何计算是一样的
 * 不一样的是 什么总资产
 * 这一点是从这些方法中分离出来的一个很好的选择
 */
public class AssetUtilRefactored {

    /**
     * 基于将 什么总资产 这一点分离出来的原则
     * 使用策略模式来实现
     * 一般会使用接口或者类来实现
     * 但是使用 lambda 表达式会带来设计上的优势
     * 无需定义单独的接口或者类
     * 将三个方法重构为一个方法接收一个函数接口作为参数
     * 使用 Predicate 匹配特定的资产类型
     * 可以将 lambda 表达式作为参数
     * 经过这个重构
     * 将三个方法转变为一个高阶函数
     * 根据轻量级的策略对象 (Predicate)
     * 处理可配置化的关注点（什么资产）
     * 同时，这次重构也使用了开闭原则
     * 可以轻易改变选择标准不需要修改方法
     */
    public static int totalAssetValues(final List<Asset> assets,
                                       final Predicate<Asset> selector) {
        return assets.stream()
            .filter(selector)
            .mapToInt(Asset::getValue)
            .sum();
    }

    public static void main(String[] args) {
        List<Asset> assets = Arrays.asList(
            new Asset(Asset.AssetType.BOND,100),
            new Asset(Asset.AssetType.STOCK,900),
            new Asset(Asset.AssetType.BOND,300),
            new Asset(Asset.AssetType.BOND,600));
        /*
        计算所有资产的总和
        传入 true 匹配所有资产
         */
        System.out.printf("所有资产的总和：%d%n",totalAssetValues(assets,
            asset -> true));
        System.out.printf("BOND类型资产总和：%d%n",totalAssetValues(assets,
            asset -> asset.getType().equals(Asset.AssetType.BOND)));
        System.out.printf("STOCK类型资产总和：%d%n",totalAssetValues(assets,
            asset -> asset.getType().equals(Asset.AssetType.STOCK)));
    }
}
