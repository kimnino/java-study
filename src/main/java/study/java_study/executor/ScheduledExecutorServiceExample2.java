package study.java_study.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledExecutorServiceExample2 {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        System.out.println("3초마다 반복, 작업 시간은 5초");
        AtomicInteger count = new AtomicInteger(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            scheduledExecutorService.execute(() -> {
                int currentCount = count.getAndIncrement();
                System.out.println("시작 카운트 :: " + currentCount);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
                System.out.println("종료 카운트 ::  " + currentCount);
            });
        }, 0, 1, TimeUnit.SECONDS);

        Thread.sleep(10000);

        System.out.println("스케줄러 종료 중...");
        scheduledExecutorService.shutdown();
    }
}
