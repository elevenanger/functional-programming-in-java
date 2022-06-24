package cn.anger.functional.collect;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * author : liuanglin
 * date : 2022/6/24 09:17
 * description : 使用 Files 工具类
 */
public class ListFiles {

    public static void main(String[] args) {
        try (Stream<Path> pathStream =
            Files.list(Paths.get("."))){
            pathStream
                // 过滤只保留文件夹
                .filter(Files::isDirectory)
                .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        筛选文件夹下的文件
        传统的方式 File.list() 传入 FilenameFilter 接口对象
        定义内部类实现 FilenameFilter 接口
         */
        File pathFiles = new File(".");
        String[] fileNames =
            pathFiles.list(
                new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith("xml");
                    }
                }
            );
        System.out.println(Arrays.toString(fileNames));

        fileNames =
            pathFiles.list(
                // 优化代码，将匿名内部类实现接口转为使用 lambda 函数实现接口函数方法
                (dir, name) -> name.endsWith("xml"));
        System.out.println(Arrays.toString(fileNames));

        /*
        通过文件的属性查找文件
        File 类中包括各种不同的文件属性
        以此来筛选文件
         */
        File[] files =
            pathFiles.listFiles(File::isHidden);
        System.out.println(Arrays.toString(files));
    }
}
