package study.java_study.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDoneExample {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        DoneTask doneTask = new DoneTask();

        Future<String> future = executorService.submit(doneTask);
        executorService.shutdown();

        while (!future.isDone()) {
            System.out.println("아직 작업이 진행 중입니다....");
            Thread.sleep(1000);
        }

        System.out.println(future.get());
    }

    public static class DoneTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(10000);
            return "작업완료";
        }
    }
}
