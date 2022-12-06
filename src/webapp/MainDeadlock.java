package webapp;

public class MainDeadlock {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (object1) {
                System.out.println(Thread.currentThread().getName() + " start");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }, "Thread1");
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (object2) {
                synchronized (object1) {
                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }, "Thread2");

        thread1.start();
        thread2.start();
    }
}
