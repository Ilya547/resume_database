package webapp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainDeadlock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        deadlockMethod(lock1, lock2, "Thread1").start();
        deadlockMethod(lock2, lock1, "Thread2").start();
    }
    private Lock lock = new ReentrantLock();

    private static void threadState(Thread thread) {
        System.out.println(thread.getName() + " is " + thread.getState());
    }

    private static Thread deadlockMethod(Object lock1, Object lock2, String name) {
        return new Thread(() -> {
            synchronized (lock1) {
                threadState(Thread.currentThread());
                try {
                    Thread.sleep(111);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    threadState(Thread.currentThread());
                }
            }
        }, name);
    }
}
