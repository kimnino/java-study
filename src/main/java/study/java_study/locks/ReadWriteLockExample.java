package study.java_study.locks;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Toilet toilet = new Toilet();

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample example = new ReadWriteLockExample();

        Runnable usedTask1 = () -> example.useToilet("래쉬포드");

        Runnable checkTask1 = () -> example.checkToiletStatus("브루노");
        Runnable checkTask2 = () -> example.checkToiletStatus("요로");
        Runnable checkTask3 = () -> example.checkToiletStatus("쿠냐");
        Runnable checkTask4 = () -> example.checkToiletStatus("음뵈모");

        Thread t1 = new Thread(usedTask1);
        Thread c1 = new Thread(checkTask1);
        Thread c2 = new Thread(checkTask2);
        Thread c3 = new Thread(checkTask3);
        Thread c4 = new Thread(checkTask4);

        t1.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();

        t1.join();
        c1.join();
        c2.join();
        c3.join();
        c4.join();

        System.out.println("코드 종료");
    }

    // 화장실을 사용 ( Write 작업 )
    public void useToilet(String name) {
        writeLock.lock();
        try {
            System.out.println(name + " 본인 축구하듯이 똥 싸러 들어왔다...");
            toilet.usedToilet(name);
        } finally {
            writeLock.unlock();
        }
    }

    // 화장실 사용 유무 확인 ( Read 작업 )
    public void checkToiletStatus(String name) {
        readLock.lock();
        try {
            System.out.println(name + "이(가) 화장실 문앞에 왔습니다.");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(name + "이(가) 화장실 문앞에서 인터럽트 발생.");
        } finally {
            readLock.unlock();
        }
    }

    @NoArgsConstructor
    @Getter
    static class Toilet {
        boolean isUse;
        String usedPerson;

        public void usedToilet(String name) {
            this.isUse = true;
            this.usedPerson = name;
        }

        public void reset() {
            this.isUse = false;
            this.usedPerson = null;
        }
    }
}
