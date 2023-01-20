package webapp.main;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static int counter;
//    private static final Object LOCK = new Object();
    private static final Lock lock = new ReentrantLock();
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " is " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + " is " +
                Thread.currentThread().getState())).start();
        System.out.println(thread0.getState());
        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
//      List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService cs = new ExecutorCompletionService(executorService);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() -> {
//            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                latch.countDown();
                return 5;
            });
            cs.poll();

//            System.out.println(future.isDone());
//            try {
//                System.out.println(future.get(16, TimeUnit.MICROSECONDS));
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            } catch (TimeoutException e) {
//                throw new RuntimeException(e);
//            }

//            thread.start();
//            threads.add(thread);
        }

//        threads.forEach(T -> {
//            try {
//                T.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
        latch.await(10, TimeUnit.MICROSECONDS);
        executorService.shutdownNow();
        System.out.println("counter = " + mainConcurrency.atomicInteger.get());
    }

    private void inc() {
//        lock.lock();
//        try {
        atomicInteger.incrementAndGet();
//            counter++;
//        } finally {
//            lock.unlock();
//        }
    }
}
