package study.java_study.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        System.out.println("5초 뒤에 '지연 작업'을 실행...");
        scheduledExecutorService.schedule(() -> {
            System.out.println("--> 지연 작업 완료 : 5초 지났습니다. ");
        }, 5, TimeUnit.SECONDS);

        System.out.println("1초 뒤에 첫 '반복 작업'을 실행하고, 이후 2초마다 반복합니다.");
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("--> 작업 시간이 0초인 반복 작업 실행");
            System.out.println("--> 작업 시간이 0초인 반복 작업 종료");
        }, 1, 2, TimeUnit.SECONDS);

        Thread.sleep(20000); // 10초간 대기

        System.out.println("스케줄러 종료 중...");
        scheduledExecutorService.shutdown();
    }
}
