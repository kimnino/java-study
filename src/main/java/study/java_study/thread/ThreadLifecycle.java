package study.java_study.thread;

public class ThreadLifecycle {

    private static final Object lock = new Object();

    public void exec() {
        Thread thread = new Thread(() -> {
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

        System.out.println("start() 호출 전 상태 : " + thread.getState());

        System.out.println("start() 호출");
        thread.start();
    }

}
