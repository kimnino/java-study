package study.java_study.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task1 = task1();
        Runnable task2 = task2();
        Runnable task3 = task3();

        System.out.println("작업 제출 시작");
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        System.out.println("모든 작업 제출 완료");

        executor.shutdown();
    }

    private static Runnable task1() {
        return () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("task 1 start :: " + threadName);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("task 1 interrupted");
            }
            System.out.println("task 1 end :: " + threadName);
        };
    }

    private static Runnable task2() {
        return () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("task 2 start :: " + threadName);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("task 2 interrupted");
            }
            System.out.println("task 2 end :: " + threadName);
        };
    }

    private static Runnable task3() {
        return () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("task 3 start :: " + threadName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("task 3 interrupted");
            }
            System.out.println("task 3 end :: " + threadName);
        };
    }
}
