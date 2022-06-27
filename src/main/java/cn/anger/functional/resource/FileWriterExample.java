package cn.anger.functional.resource;

import java.io.FileWriter;
import java.io.IOException;

/**
 * author : liuanglin
 * date : 2022/6/27 11:08
 * description : 演示对象回收
 */
public class FileWriterExample {

    private final FileWriter fileWriter;

    public FileWriterExample(final String fileName) throws IOException {
        fileWriter = new FileWriter(fileName);
    }

    public void writerStuff(final String message) throws IOException {
        fileWriter.write(message);
    }

    public void finalize() throws IOException {
        fileWriter.close();
    }

    public static void main(String[] args) throws Exception{
        /*
        example.dat 文件被创建了但是是空文件
        因为如果不调用 finalize() 方法
        JVM 认为还有足够的内存空间
        不需要执行 gc 操作
        finalizer 将永远不会执行
        文件不会被关闭，内容无法从内存中写入文件
         */
        final FileWriterExample fileWriterExample =
            new FileWriterExample("example.dat");
        fileWriterExample.writerStuff("something");
        // 调用 finalize() 方法关闭文件，将内容写入文件中
        fileWriterExample.finalize();
    }
}
