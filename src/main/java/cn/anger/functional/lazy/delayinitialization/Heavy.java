package cn.anger.functional.lazy.delayinitialization;

/**
 * author : liuanglin
 * date : 2022/6/27 19:02
 * description : 消耗资源的对象初始化
 * 将消耗资源的对象单独出来
 * 在需要该资源的对象中引用消耗资源的对象
 * 在需要是才进行消耗资源对象的初始化操作
 */
public class Heavy {

    public Heavy() {
        System.out.println("Heavy Initialization");
    }

    @Override
    public String toString() {
        return "Heavy{}";
    }
}
