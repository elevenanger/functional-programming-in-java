package cn.anger.functional.collect;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author : liuanglin
 * date : 2022/6/24 10:22
 * description : 列出目录下的子目录
 */
public class ListSubDirs {

    static File filePath = new File(".");

    /**
     * 查找目录以及子目录下的所有文件
     * @return 文件列表
     */
    static List<File> listTheHardWay() {
        // File List 用于存储文件
        List<File> files = new ArrayList<>();
        // 列出目录下的所有文件
        File [] filesInCurrentDir = filePath.listFiles();
        assert filesInCurrentDir != null;
        // 遍历文件列表
        for (File file : filesInCurrentDir) {
            // 如果是文件夹，继续查找文件夹中的文件
            File [] filesInSubDir = file.listFiles();
            if (filesInSubDir != null) {
                // 将文件夹中的文件全部添加到文件列表中
                files.addAll(Arrays.asList(filesInSubDir));
            } else {
                // 如果是文件，直接添加到文件列表中
                files.add(file);
            }
        }
        return files;
    }

    /**
     * 使用 flatMap
     * @return 文件列表
     *
     * flatMap() 方法和 map() 方法一样
     * 映射集合中的元素
     * 但是 map() 方法用 lambda 表达式返回一个元素
     * flatMap() 方法用 lambda 表达式返回一个 Stream
     * flatMap() 方法将多个元素映射为多个 Stream
     * 然后进行扁平化处理，将其转换为一个扁平流
     */
    static List<File> betterWay() {
        return Arrays.stream(filePath.listFiles())
                .flatMap(
                    file -> file.listFiles() == null ?
                        // 将单个 file 元素映射为 Stream
                        Stream.of(file) :
                        // 将文件数组映射为 Stream
                        // 将所有的 Stream 转换为一个扁平流
                        Stream.of(file.listFiles()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(listTheHardWay());
        System.out.println(betterWay());
    }
}
