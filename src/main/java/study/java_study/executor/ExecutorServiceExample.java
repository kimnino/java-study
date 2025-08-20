package study.java_study.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 1; i < 4; i++) {
            final int taskId = i;
            executorService.submit(() -> {
                String name = Thread.currentThread().getName();
                System.out.println("Task " + taskId + " 시작 :: " + name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Task " + taskId + " InterruptedException");
                }
                System.out.println("Task " + taskId + " 종료 :: " + name);
            });
        }

        executorService.shutdown();

    }
}
