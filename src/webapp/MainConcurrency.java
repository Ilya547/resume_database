package webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int TREADS_NUMBER = 10000;
    private static int counter;
    private static final Object LOCK = new Object();

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
        List<Thread> threads = new ArrayList<>(TREADS_NUMBER);
        for (int i = 0; i < TREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);


        }

        threads.forEach(T -> {
            try {
                T.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("counter = " + counter);
    }

    private void inc() {
        counter++;

    }
}
