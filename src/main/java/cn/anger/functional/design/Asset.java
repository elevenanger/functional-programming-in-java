package cn.anger.functional.design;

/**
 * author : liuanglin
 * date : 2022/6/25 14:22
 * description : 资产
 * 以一个对资产求和的例子来说明关注点分离的设计思想
 *
 * Asset 是一个常规的 java bean
 * 有两个属性 type 和 value
 */
public class Asset {
    public enum AssetType{ BOND, STOCK }
    private final AssetType type;
    private final int value;

    public Asset(AssetType type, int value) {
        this.type = type;
        this.value = value;
    }
    public AssetType getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
}
