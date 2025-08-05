package study.java_study.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CancelTask cancelTask = new CancelTask();

        Future<String> future = executorService.submit(cancelTask);
        executorService.shutdown();
        System.out.println("작업 제출.");
        Thread.sleep(5000);

        boolean result = future.cancel(false);
        if (result) {
            System.out.println("작업을 성공적으로 취소");
        } else {
            System.out.println("작업을 취소할 수 없다...");
        }


    }

    public static class CancelTask implements Callable<String> {

        @Override
        public String call() {
            try {
                System.out.println("작업시작");
                Thread.sleep(8000);
                System.out.println("작업완료");
                return "작업 완료";
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
                return "";
            }
        }
    }

}
