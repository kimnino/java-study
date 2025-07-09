package study.java_study.locks;

import java.util.concurrent.locks.StampedLock;

public class StampedLockOptimisticReadExample {

    private final StampedLock lock = new StampedLock();
    private int count = 0;

    public static void main(String[] args) throws InterruptedException {
        StampedLockOptimisticReadExample example = new StampedLockOptimisticReadExample();

        Thread writeThread = new Thread(() -> {
            example.writeCount(1);
        }, "WriteThread");

        Thread readerThread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": count는 " + example.readCount());
        }, "ReaderThread-1");

        Thread readerThread2 = new Thread(() -> {
            try {
                Thread.sleep(150);
                System.out.println(Thread.currentThread().getName() + ": count는 " + example.readCount());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "ReaderThread-2");

        Thread readerThread3 = new Thread(() -> {
            try {
                Thread.sleep(150);
                System.out.println(Thread.currentThread().getName() + ": count는 " + example.readCount());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "ReaderThread-3");

        readerThread1.start();
        writeThread.start();
        readerThread2.start();
        readerThread3.start();

        writeThread.join();
        readerThread1.join();
        readerThread2.join();
        readerThread3.join();
    }

    public void writeCount(int value) {
        long stamp = lock.writeLock();
        try {
            System.out.println(Thread.currentThread().getName() + ": 쓰기 잠금 획득. 값 더하기");
            count = count + value;
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + ": 값 변경 완료: (" + count + ")");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlockWrite(stamp);
            System.out.println(Thread.currentThread().getName() + ": 쓰기 잠금 해제.");
        }
    }

    public int readCount() {
        long stamp = lock.tryOptimisticRead();
        int currentCount = count;
        System.out.println(Thread.currentThread().getName() + ": 낙관적 읽기 시도 중... (" + currentCount + ")");

        if (!lock.validate(stamp)) {
            System.out.println(Thread.currentThread().getName() + ": 낙관적 읽기 실패! 쓰기 충돌 감지. 일반 읽기 잠금으로 재시도.");
            stamp = lock.readLock();
            try {
                currentCount = count;
                System.out.println(Thread.currentThread().getName() + ": 일반 읽기 잠금으로 성공: (" + currentCount + ")");
            } finally {
                lock.unlockRead(stamp);
            }
        } else {
            System.out.println(Thread.currentThread().getName() + ": 낙관적 읽기 성공! 충돌 없음.");
        }
        return currentCount;
    }
}
