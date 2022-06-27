package cn.anger.functional.defaultmethods;

/**
 * author : liuanglin
 * date : 2022/6/27 08:23
 * description : 默认方法的优先级
 */
public class SeaPlane extends Vehicle implements FastFly,Sail{
    private int altitude;

    @Override
    public void cruise() {
        System.out.println("SeaPlane.cruise : ");
        if (altitude > 0) {
            FastFly.super.cruise();
        }else {
            Sail.super.cruise();
        }
    }

    public static void main(String[] args) {
        SeaPlane seaPlane = new SeaPlane();
        seaPlane.takeOff();
        seaPlane.land();
        seaPlane.cruise();
        seaPlane.turn();
        /* output:
        默认方法的优先级：
        1、子类自动拥有超类的默认方法
        2、接口中的默认方法，子类实现优先于超类
        3、在类中的实现，包括抽象方法声明，优先于所有接口的默认方法
        4、如果存在多个默认方法实现之间的冲突，继承类需要消除歧义

        takeOff() 方法在 Fly 中定义为默认方法
        FastFly 重新声明了 takeOff() 方法
        FastFly.takeOff 方法优先
        FastFly.takeOff

        land() 方法为 Fly() 的默认方法
        子类自动拥有该方法
        Fly.land

        cruise() 方法在 FastFly 和 Sail 接口中都声明为默认方法
        SeaPlane 继承这两个接口，需要消除方法的歧义
        SeaPlane.cruise :
        Sail.cruise

        turn() 方法在 Fly 和 Sail 接口中声明为默认方法
        Vehicle 类中也有定义 public 方法
        Vehicle 类中该方法的实现优先级最高
        Vehicle.turn
         */
    }
}
