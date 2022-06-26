package cn.anger.functional.design;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.function.Consumer;

/**
 * author : liuanglin
 * date : 2022/6/26 17:46
 */
class CameraTest {

    static final Camera CAMERA = new Camera();
    static Consumer<String> printCaptured;

    @BeforeAll
    static void beforeAll() {
        printCaptured =
            filterInfo ->
                System.out.printf("滤镜 ：%s: %s%n", filterInfo,
                    // 原始照片颜色
                    CAMERA.capture(new Color(200, 100, 100)));
    }

    @Test
    void testCaptureWithNoFilter() {
        // 无滤镜输出
        printCaptured.accept("无滤镜");
    }


    /*
    使用滤镜
     */
    @Test
    @SuppressWarnings("unchecked")
    void testCaptureWithFilter() {
        CAMERA.setFilters(Color::darker);
        printCaptured.accept("darker");
        CAMERA.setFilters(Color::brighter);
        printCaptured.accept("brighter");
        CAMERA.setFilters(Color::brighter,Color::darker);
        printCaptured.accept("brighter and darker");
    }

}