package cn.anger.functional.resource.lock;

import java.util.concurrent.locks.Lock;

/**
 * author : liuanglin
 * date : 2022/6/27 18:09
 * description : 对象使用完立即释放 lambda 表达式实现
 */
public class Locker {

    /*
    支持传入实现 Runnable 的 lambda 表达式
     */
    public static void runLocked(Lock lock,Runnable block) {
        lock.lock();
        try {
            block.run();
        } finally {
            lock.unlock();
        }
    }

}
