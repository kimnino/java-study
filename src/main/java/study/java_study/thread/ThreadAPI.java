package study.java_study.thread;

public class ThreadAPI {

    public void createAndStartThread() {

        Runnable runnable = () -> {
            System.out.println("김민혁의 스레드가 실행 중 ... ");
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void currentThread() {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("김민혁의 스레드가 실행 중 ... ");
        };

        Thread thread = new Thread(runnable);
        thread.setName("Minhyuk");
        thread.start();

        System.out.println(Thread.currentThread().getName());
    }

    public void getState() throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println("김민혁의 스레드가 실행 중 ... ");
        };

        Thread minhyuk = new Thread(runnable);
        minhyuk.start();

        Thread.sleep(500);
        Thread.State state = minhyuk.getState();
        System.out.println(state.name());
    }

    public void isAlive() throws InterruptedException {
        Thread task1 = new Thread(() -> {
            try {
                System.out.println("task1 작업 시작...");
                Thread.sleep(3000);
                System.out.println("task1 작업 종료...");
            } catch (InterruptedException e) {
            }
        });
        task1.start();

        while (task1.isAlive()) {
            System.out.println("task1 스레드가 아직 작업 중입니다.");
            Thread.sleep(500);
        }

        System.out.println("task1 작업이 종료되었습니다.");
    }
}
