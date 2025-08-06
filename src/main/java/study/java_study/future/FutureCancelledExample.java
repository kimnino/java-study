package study.java_study.future;

import java.util.concurrent.*;

public class FutureCancelledExample {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CancelledTask doneTask = new CancelledTask();

        Future<String> future = executorService.submit(doneTask);
        executorService.shutdown();

        while (!future.isDone()) {
            System.out.println("아직 작업이 진행 중입니다....");
            Thread.sleep(1000);
            future.cancel(true);
        }

        if (future.isCancelled()) {
            System.out.println("작업이 취소되었습니다.");
        } else {
            try {
                System.out.println(future.get());
            } catch (CancellationException e) {
                System.out.println("작업 취소로 인해 데이터를 가져올 수 없습니다.");
            }
        }
    }

    public static class CancelledTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(10000);
            return "작업완료";
        }
    }
}
