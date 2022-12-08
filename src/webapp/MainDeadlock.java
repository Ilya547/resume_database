package webapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainDeadlock {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();
        List <Object> listObjects = new ArrayList<>();
        listObjects.add(object1);
        listObjects.add(object2);
        List <Object> reverseListObjects  = new ArrayList<>();
        reverseListObjects.add(object2);
        reverseListObjects.add(object1);

        createThread(listObjects).start();
        createThread(reverseListObjects).start();
    }

    private static void threadState(Thread thread) {
        System.out.println(thread.getName() + " is " + thread.getState());
    }

    private static Thread createThread(List<Object> list) {
        Thread thread = new Thread(() -> {
            threadState(Thread.currentThread());
//            Modifier.isSynchronized()
            synchronized (list.get(0)) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (list.get(1)) {
                }
            }
            threadState(Thread.currentThread());
        });
        return thread;
    }
    private static List reverseList(List list) {
        System.out.println("revers");
        Collections.reverse(list);
        return list;
    }
}
