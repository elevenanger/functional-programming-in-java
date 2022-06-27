package cn.anger.functional.defaultmethods;

/**
 * author : liuanglin
 * date : 2022/6/27 08:21
 */
public interface FastFly extends Fly{
    @Override
    default void takeOff() {
        System.out.println("FastFly.takeOff");
    }
}
