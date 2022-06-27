package cn.anger.functional.resource;

import java.io.FileWriter;
import java.io.IOException;

/**
 * author : liuanglin
 * date : 2022/6/27 11:31
 * description : 演示自动对象管理特性
 */
public class FileWriterARM implements AutoCloseable{

    private final FileWriter writer;

    FileWriterARM(String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }

    public void writeStuff(final String message) throws IOException {
        writer.write(message);
    }

    @Override
    public void close() throws Exception {
        System.out.println("FileWriterARM.close");
        writer.close();
    }

    public static void main(String[] args) {
        /*
        使用 ARM 自动管理对象机制
        实现 Autocloseable 接口
        声明 close() 方法
        try-with-resource 语句执行会自动回收对象
        try 语句执行完成后 调用 close() 方法
        对象将会变成不可访问的对象
        最终被 JVM GC 策略回收
         */
        try (FileWriterARM fileWriterARM =
            new FileWriterARM("armExample.dat")){
            fileWriterARM.writeStuff("arm auto closable");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
