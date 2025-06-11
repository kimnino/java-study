package study.java_study.thread;

public class ThreadLifecycle {

    private static final Object lock = new Object();

    public void exec() throws InterruptedException {
        Thread minhyukThread = new Thread(() -> {
            try {
                System.out.println("스레드 내부 : 3초간 잠에 든다.... ( TIMED_WAITING )");
                Thread.sleep(3000);

                System.out.println("스레드 내부 : synchronized 블록.... ( BLOCKED )");
                synchronized (lock) {
                    System.out.println("스레드 내부 : lock을 획득! ( RUNNABLE )");
                }

                System.out.println("스레드 내부 : 작업 완료!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("start() 호출 전 상태 : " + minhyukThread.getState());

        System.out.println("start() 호출");
        minhyukThread.start();

        synchronized (lock) {
            System.out.println("메인 스레드 : lock을 획득");

            Thread.sleep(5000);
            System.out.println("minhyukThread가 락을 기다릴때 상태 : " + minhyukThread.getState());

            System.out.println("메인 스레드: lock 해제");
        }

        minhyukThread.join();
        System.out.println("스레드 작업 완료 후 상태 : " + minhyukThread.getState());
    }

}
