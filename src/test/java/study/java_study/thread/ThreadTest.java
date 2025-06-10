package study.java_study.thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    public void test1() {
        MinhyukTread minhyukTread = new MinhyukTread();
        minhyukTread.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("메인 스레드 실행");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void test2() {
        MinhyukRunnable minhyukRunnable = new MinhyukRunnable();
        Thread thread = new Thread(minhyukRunnable);
        thread.run();

        for (int i = 0; i < 5; i++) {
            System.out.println("메인 스레드 실행");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
