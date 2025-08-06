package study.java_study.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class CompletableFutureExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("메인 스레드 작업 시작...");

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("비동기 작업 시작 :: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException 발생 :: " + Thread.currentThread().getName());
            }
            int result = ThreadLocalRandom.current().nextInt(100);
            System.out.println("비동기 작업 완료 결과 : " + result);
            return result;
        });

        future.thenApply(result -> {
            System.out.println(result + " 결과에 10을 곱하자 :: " + Thread.currentThread().getName());
            return result * 10;
        }).thenAccept(finalResult -> {
            System.out.println("나는 반환은 없어 :: " + Thread.currentThread().getName());
            System.out.println("최종 결과값 :: " + finalResult);
        });

        System.out.println("위에는 비동기 작업으로 메인 스레드에서 다른 작업 진행 중...");
        Thread.sleep(5000);
        System.out.println("메인 스레드 종료.");
    }
}
