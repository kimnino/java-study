package study.java_study.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private final Lock lock = new ReentrantLock();
    private int count = 0;

    public static void main(String[] args) throws InterruptedException {
        LockExample example = new LockExample();
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                example.increment();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread one = new Thread(task, "ThreadOne");
        Thread two = new Thread(task, "ThreadTwo");

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println("count = " + example.count);
    }

    public void increment() {
        lock.lock();
        count++;
        System.out.println(Thread.currentThread().getName() + " : " + count);
    }
}
