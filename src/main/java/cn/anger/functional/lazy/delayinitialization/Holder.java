package cn.anger.functional.lazy.delayinitialization;

import java.util.function.Supplier;

/**
 * author : liuanglin
 * date : 2022/6/28 08:38
 * description : 使用 Supplier 实例化并缓存对象
 */
public class Holder {
    private Supplier<Heavy> heavy = this::createAndCacheHeavy;

    public Holder() {
        System.out.println("Holder.Holder");
    }

    public Heavy getHeavy() {
        return heavy.get();
    }

    /**
     * synchronized 关键字修饰该方法
     * 这个方法是线程安全的
     * @return heavy 对象
     */
    private synchronized Heavy createAndCacheHeavy() {
        class HeavyFactory implements Supplier<Heavy> {
            private final Heavy heavyInstance = new Heavy();
            @Override
            public Heavy get() {
                System.out.println("HeavyFactory.get");
                return heavyInstance;
            }
        }

        /*
        多线程并发场景下
        第一个线程首次调用该方法时检查 heavy 是不是 HeavyFactory 的实例
        如果这不是默认的 Supplier
        将 heavy 替换为 HeavyFactory 实例
        最终 get() 方法返回 HeavyFactory 持有的 Heavy 实例

        其它的线程将会因为 synchronized 关键字会等待第一个线程执行完毕
        第二个并发线程进入该方法又会检查 heavy 是不是 HeavyFactory 的一个实例
        因为第一个线程已经完成实例化的操作
        第二个并发线程会直接绕过该操作直接执行 get() 返回当前持有的 Heavy 实例

        后续的调用
        因为 heavy 已经实例化为 HeavyFactory 对象
        直接执行 HeavyFactory get() 方法
        将不会额外产生 synchronized 的开销
        通过这种方法实现了对象的懒加载、线程安全并且避免了每次调用 synchronized 开销
         */
        if (!(heavy instanceof HeavyFactory)) {
            System.out.println("HeavyFactory initialization");
            heavy = new HeavyFactory();
        }
        return heavy.get();
    }

    public static void main(String[] args) {
        Holder holder = new Holder();
        holder.getHeavy();
        holder.getHeavy();
        holder.getHeavy();
    }
}
