package study.java_study.locks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private final int MAX_SIZE = 3; // 큐의 최대 크기
    private final Queue<Integer> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition(); // 큐가 비어있지 않을 때 사용하는 조건
    private final Condition notFull = lock.newCondition();  // 큐가 가득 차지 않았을 때 사용하는 조건

    public static void main(String[] args) {
        ConditionExample example = new ConditionExample();

        // 생산자 스레드
        Runnable creatorTask = () -> {
            for (int i = 1; i < 6; i++) {
                try {
                    example.create(i);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable removerTask = () -> {
            for (int i = 1; i < 6; i++) {
                try {
                    example.remove();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread creator1 = new Thread(creatorTask, "creatorTask-1");
        Thread creator2 = new Thread(creatorTask, "creatorTask-2");
        Thread remover1 = new Thread(removerTask, "removerTask-1");
        Thread remover2 = new Thread(removerTask, "removerTask-2");

        creator1.start();
        creator2.start();
        remover1.start();
        remover2.start();

        try {
            creator1.join();
            creator2.join();
            remover1.join();
            remover2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void create(int item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == MAX_SIZE) {
                System.out.println(Thread.currentThread().getName() + ": 큐가 가득 참 [" + item + " -  대기]");
                notFull.await();
            }
            queue.offer(item);
            System.out.println("생산 - " + item + ", 현재 큐 크기: " + queue.size());
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int remove() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + ": 큐가 비어있음. 제거 대기...");
                notEmpty.await();
            }
            int item = queue.poll();
            System.out.println("제거 - " + item + ", 현재 큐 크기: " + queue.size());
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }
}
