package study.java_study.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BasicExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CallableTask callableTask = new CallableTask();

        System.out.println("작업을 ExecutorService 제출하겠습니다.");
        Future<String> future = executorService.submit(callableTask);
        System.out.println("Future 객체를 받았습니다.");

        executorService.shutdown();

        try {
            System.out.println("Future 결과 대기중...");
            Thread.sleep(10000);
            // get()을 선언한 부분에서 작업이 완료될 때까지 블로킹
            String result = future.get();
            System.out.println("결과 : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class CallableTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            for (int i = 1; i < 6; i++) {
                System.out.println("작업이 진행 중 입니다 => " + i + "초...");
                Thread.sleep(1000);
            }
            return "작업이 완료되었습니다.";
        }
    }
}
