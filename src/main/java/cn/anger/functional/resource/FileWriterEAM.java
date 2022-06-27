package cn.anger.functional.resource;

import cn.anger.functional.exceptions.UseInstance;

import java.io.FileWriter;
import java.io.IOException;

/**
 * author : liuanglin
 * date : 2022/6/27 12:17
 * description : 使用 lambda 表达式管理对象回收
 */
public class FileWriterEAM {
    private final FileWriter writer;

    /**
     * 将构造函数和 close() 方法都声明为 private
     * 防止外部调用
     */
    private FileWriterEAM(final String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }

    private void close() throws IOException {
        System.out.println("FileWriterEAM.close");
        writer.close();
    }

    public void writeStuff(String message) throws IOException {
        writer.write(message);
    }

    /**
     * 在 static 方法中实例化 FileWriterEAM 对象
     * 使用自定义的函数接口作为参数
     * 接收 lambda 函数代码块作为参数
     * 同时允许 lambda 函数抛出已检查的异常
     * 在方法内部完成对象的 close() 操作
     * 确保对象及时回收
     * 调用者无需关心对象的生命周期
     * EAM(execute around method) 用完后立即释放对象
     */
    public static void use(final String fileName,
        final UseInstance<FileWriterEAM, IOException> block) throws IOException {
        final FileWriterEAM writerEAM = new FileWriterEAM(fileName);
        try {
            block.accept(writerEAM);
        }finally {
            writerEAM.close();
        }
    }

    public static void main(String[] args) throws IOException {
        FileWriterEAM.use("EAMExample.dat",
            fileWriterEAM ->
            {
                fileWriterEAM.writeStuff("EAM data");
                fileWriterEAM.writeStuff("EAM data1");
                fileWriterEAM.writeStuff("EAM data2");
            });
    }
}