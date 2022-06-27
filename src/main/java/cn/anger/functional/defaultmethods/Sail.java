package cn.anger.functional.defaultmethods;

/**
 * author : liuanglin
 * date : 2022/6/27 08:22
 * description :
 */
public interface Sail {
    default void cruise() {
        System.out.println("Sail.cruise");
    }

    default void turn() {
        System.out.println("Sail.turn");
    }

}
