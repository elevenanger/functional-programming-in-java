package cn.anger.functional.design;

import java.awt.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * author : liuanglin
 * date : 2022/6/26 17:11
 * description : 使用相机演示装饰器模式
 * 往相机添加滤镜可以改变相机的行为（成像效果）
 * 滤镜可以添加一个或者多个
 */
@SuppressWarnings("unchecked")
public class Camera {

    /*
    滤镜：改变相机的成像效果
    改变颜色
     */
    private Function<Color, Color> filter = color -> color;

    /**
     * 使用 filter 接收颜色然后返回处理后的颜色
     * @param inputColor 原始颜色
     * @return 处理后的颜色
     */
    public Color capture(final Color inputColor) {
        final Color processedColor;
        processedColor = filter.apply(inputColor);
        return processedColor;
    }

    /**
     * 设置滤镜
     * 使用 Function.compose() 方法组合多个 Function
     * Function::compose 是其方法引用，原本为
     * (f1,f2) -> f1.compose(f2)
     * 该方法将返回由两个function组合而成的 Function
     * 通过 reduce() 方法
     * 递归调用 compose() 方法将任意数量的 filter 组合到一起
     * @param filters 任意数量的滤镜
     * 使用函数编程实现传入任意数量的 filter 实现装饰器模式
     * 只需要传入 lambda 表达式或者方法引用
     * 而不需要创建对象继承体系
     */
    public void setFilters(final Function<Color, Color> ... filters) {
        filter =
            Stream.of(filters)
                // 组合 filter
                .reduce(Function::compose)
                /*
                Function::identity 输入等于输出
                如果 filter 数量为 0 则原样返回
                 */
                .orElseGet(Function::identity);
    }
}
