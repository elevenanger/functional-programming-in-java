package cn.anger.functional.defaultmethods;

/**
 * author : liuanglin
 * date : 2022/6/27 08:20
 * description : 演示默认方法
 */
public interface Fly {
    default void takeOff() {
        System.out.println("Fly.takeOff");
    }

    default void land() {
        System.out.println("Fly.land");
    }

    default void turn() {
        System.out.println("Fly.turn");
    }

    default void cruise() {
        System.out.println("Fly.cruise");
    }
}
