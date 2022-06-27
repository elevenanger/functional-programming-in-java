package cn.anger.functional.lazy.delayinitialization;

/**
 * author : liuanglin
 * date : 2022/6/27 19:05
 * description : 引用消耗资源对象
 * 懒加载的第一个版本
 */
public class HolderNaive {
    private Heavy heavy;

    public HolderNaive() {
        System.out.println("HolderNaive Initialization");
    }

    /*
    在需要是进行 Heavy 对象的初始化操作
    但是这个操作无法确保线程安全
     */
    public Heavy getHeavy() {
        System.out.println("HolderNaive.getHeavy");
        if (heavy == null)
            heavy = new Heavy();
        return heavy;
    }

    /*
    使用 synchronized 关键字确保线程安全
    但是每次调用该方法都需要额外的锁的开销
    在当前场景下
    只有 heavy 第一次初始化才会有竞争问题
    初始化后将不会变化
    每次都需要进行 synchronized 操作开销太大
     */
    public synchronized Heavy getHeavySync() {
        System.out.println("HolderNaive.getHeavySync");
        if (heavy == null)
            heavy = new Heavy();
        return heavy;
    }

    public static void main(String[] args) {
        HolderNaive holderNaive = new HolderNaive();
        System.out.println(holderNaive.getHeavy());
        System.out.println(holderNaive.getHeavy());

        holderNaive = new HolderNaive();
        System.out.println(holderNaive.getHeavySync());
        System.out.println(holderNaive.getHeavySync());
    }
}
