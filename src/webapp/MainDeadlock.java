package webapp;

public class MainDeadlock {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();

        Thread thread1 = new Thread(() -> {
            ThreadState(Thread.currentThread());
            synchronized (object1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                }
            }
            ThreadState(Thread.currentThread());
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            ThreadState(Thread.currentThread());
            synchronized (object2) {
                synchronized (object1) {
                }
            }
            ThreadState(Thread.currentThread());
        }, "Thread2");
        thread1.start();
        thread2.start();
    }

    private static void ThreadState(Thread thread) {
        System.out.println(thread.getName() + " is " + thread.getState());
    }
}
