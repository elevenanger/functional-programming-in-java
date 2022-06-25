package cn.anger.functional.design;

import java.util.Arrays;
import java.util.List;

/**
 * author : liuanglin
 * date : 2022/6/25 14:25
 * description : Asset 工具类
 */
public class AssetUtil {
    /**
     * 计算一批 Asset 的总资产
     * 这种做法看起来非常自然
     * 使用类 stream 以及内部迭代支持不可变性
     * 但是聚焦于方法的设计本身
     * 存在与三个问题纠缠不清
     * 1、如何迭代
     * 2、什么总资产
     * 3、如何计算
     * 这些纠缠不清的逻辑会导致代码很难复用
     */
    public static int totalAssetValue(final List<Asset> assets) {
        return assets.stream()
            .mapToInt(Asset::getValue)
            .sum();
    }

    /**
     * 基于 totalAssetValue 方法
     * 如果只需要计算 Bond 类型资产的总和
     * 复制 totalAssetValue 方法
     * 将其简单调整一下
     * 声明一个新的方法
     * 这两个方法之间的差别只有方法名以及内部逻辑增加了 BOND 类型的过滤条件
     */
    public static int totalBondAssetValue(final List<Asset> assets) {
        return assets.stream()
            .filter(asset -> asset.getType() == Asset.AssetType.BOND)
            .mapToInt(Asset::getValue)
            .sum();
    }

    /**
     * 仅计算资产中 STOCK 资产的总和
     * 仿照上面的做法
     * 声明一个新的方法
     */
    public static int totalStockAssetValue(final List<Asset> assets) {
        return assets.stream()
            .filter(asset -> asset.getType() == Asset.AssetType.STOCK)
            .mapToInt(Asset::getValue)
            .sum();
    }

    public static void main(String[] args) {
        List<Asset> assets = Arrays.asList(
            new Asset(Asset.AssetType.BOND,100),
            new Asset(Asset.AssetType.STOCK,900),
            new Asset(Asset.AssetType.BOND,300),
            new Asset(Asset.AssetType.BOND,600));
        System.out.printf("总资产：%d%n",totalAssetValue(assets));
        System.out.printf("BOND 类型总资产：%d%n",totalBondAssetValue(assets));
        System.out.printf("STOCK 类型总资产：%d%n",totalStockAssetValue(assets));
    }
}
