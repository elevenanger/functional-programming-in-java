package cn.anger.functional.resource.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static cn.anger.functional.resource.lock.Locker.*;

/**
 * author : liuanglin
 * date : 2022/6/27 18:03
 * description : 用完对象立即释放 常规锁的方式实现
 */
public class Locking {
    Lock lock = new ReentrantLock();

    public void setLock(final Lock lock) {
        this.lock = lock;
    }

    public void doOp1() {
        lock.lock();
        try {
            // 将业务逻辑写在方法中
            System.out.println("Locking.doOp1");
        }finally {
            lock.unlock();
        }
    }

    public void doOp2() {
        runLocked(lock,() -> System.out.println("Locking.doOp2"));
    }

    public static void main(String[] args) {
        Locking locking = new Locking();
        locking.doOp1();
        locking.doOp2();
    }
}
