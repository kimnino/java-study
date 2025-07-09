package study.java_study.locks;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AbstractQueuedSynchronizerExample {

    private final AQS aqs = new AQS();

    public static void main(String[] args) throws InterruptedException {
        AbstractQueuedSynchronizerExample example = new AbstractQueuedSynchronizerExample();

        // 여러 스레드가 동시에 락을 획득 시도
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(example::doSomethingCritical, "Thread-" + i);
            thread.start();
            Thread.sleep(10);
        }

        Thread.sleep(5000);
        System.out.println("\n--- 모든 스레드 작업 시뮬레이션 완료 ---");
    }

    public void lock() {
        System.out.println(Thread.currentThread().getName() + " -> 락 획득을 시도합니다. (줄 서기)");
        aqs.acquire(1);
        System.out.println(Thread.currentThread().getName() + " -> 락 획득 성공!");
    }

    public void unlock() {
        aqs.release(1);
        System.out.println(Thread.currentThread().getName() + " -> 락을 해제했습니다. (줄 다음 사람 입장)");
    }

    public void doSomethingCritical() {
        this.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " --- 공유 자원 사용 중 ---");
            Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1500));
            System.out.println(Thread.currentThread().getName() + " --- 공유 자원 사용 완료 ---");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(Thread.currentThread().getName() + " 인터럽트 발생!");
        } finally {
            this.unlock();
        }
    }

    private static class AQS extends AbstractQueuedSynchronizer {

        public AQS() {
            setState(0);
        }

        // 락 획득 시도
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) { // 0이면 락을 획득하고, 1로 상태를 변경
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false; // 대기열 큐에 넣음
        }

        // 락 해제 시도
        @Override
        protected boolean tryRelease(int arg) {
            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalMonitorStateException("현재 락을 소유하고 있지 않습니다.");
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true; //  큐의 다음 스레드를 깨움
        }

        // 락이 현재 획득되어 있는지 여부
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }
}
