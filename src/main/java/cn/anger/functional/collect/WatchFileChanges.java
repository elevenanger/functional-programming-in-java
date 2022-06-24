package cn.anger.functional.collect;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/**
 * author : liuanglin
 * date : 2022/6/24 11:32
 * description : 监听文件的变化
 */
public class WatchFileChanges {
    static final Path path = Paths.get(".");

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        path 获取文件系统实例
        文件系统实例作为 WatchService 工厂
        创建 WatchService 实例
         */
        WatchService watchService =
            path.getFileSystem().newWatchService();
        // 注册 WatchService 接收变更通知
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        /*
        可以轮询 WatchService 获取任意变更
        它将通过 WatchKey 进行通知
         */
        final WatchKey watchKey =
            watchService.poll(1, TimeUnit.MINUTES);

        /*
        获取 WatchKey 中的事件详情
         */
        if (watchKey != null) {
            watchKey.pollEvents()
                .stream()
                .map(WatchEvent::context)
                .forEach(System.out::println);
        }
    }
}
