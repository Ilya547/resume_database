package webapp;

public class MainDeadlock {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();
        class MyThread extends Thread {
            @Override
            public void run() {
                synchronized (object1) {
                    threadState(Thread.currentThread());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (object2) {
                        threadState(Thread.currentThread());
                    }
                }
            }
        }

        synchronized (object2) {
            threadState(Thread.currentThread());
            Thread thread = new MyThread();
            thread.start();
            try {
                Thread.sleep(1111);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (object1){
                threadState(Thread.currentThread());

            }
        }
    }

    private static void threadState(Thread thread) {
        System.out.println(thread.getName() + " is " + thread.getState());
    }
}
