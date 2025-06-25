package study.java_study.synchronization;


import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Synchronization {

    public static void main(String[] args) {
        MonitorCouponService couponService = new MonitorCouponService();

        Runnable customerTask = () -> couponService.applyCoupon(Thread.currentThread().getName());

        Thread customer1 = new Thread(customerTask, "김민혁");
        Thread customer2 = new Thread(customerTask, "조은지");

        customer1.start();
        customer2.start();

        try {
            customer1.join();
            customer2.join();
        } catch (InterruptedException e) {
            customer1.interrupt();
            customer2.interrupt();
        }

        System.out.println("남은 쿠폰: " + couponService.couponCount + "장");
    }

    static class NoSyncCouponService {
        private int couponCount = 1;

        public void applyCoupon(String userName) {
            if (couponCount > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                couponCount--;
                System.out.println(userName + "님에게 쿠폰을 발급했습니다.");
            } else {
                System.out.println(userName + "님, 이벤트 쿠폰이 모두 소진되었습니다.");
            }
        }
    }

    static class SynchronizedCouponService {
        private int couponCount = 1;

        public synchronized void applyCoupon(String userName) {
            if (couponCount > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                couponCount--;
                System.out.println(userName + "님에게 쿠폰을 발급했습니다.");
            } else {
                System.out.println(userName + "님, 이벤트 쿠폰이 모두 소진되었습니다.");
            }
        }
    }

    static class MutexCouponService {
        private final ReentrantLock lock = new ReentrantLock();
        private int couponCount = 1;

        public void applyCoupon(String userName) {
            lock.lock();
            try {
                if (couponCount > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    couponCount--;
                    System.out.println(userName + "님에게 쿠폰을 발급했습니다.");
                } else {
                    System.out.println(userName + "님, 이벤트 쿠폰이 모두 소진되었습니다.");
                }
            } finally {
                lock.unlock();
            }
        }
    }

    static class SemaphoreCouponService {
        private final Semaphore semaphore = new Semaphore(1);
        private int couponCount = 1;

        public void applyCoupon(String userName) {
            try {
                semaphore.acquire();
                if (couponCount > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    couponCount--;
                    System.out.println(userName + "님에게 쿠폰을 발급했습니다.");
                } else {
                    System.out.println(userName + "님, 이벤트 쿠폰이 모두 소진되었습니다.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(userName + "에게 쿠폰 발급중 인터럽트 발생");
            } finally {
                semaphore.release();
            }
        }
    }

    static class MonitorCouponService {
        private int couponCount = 1;

        public void applyCoupon(String userName) {
            synchronized (this) {
                while (couponCount <= 0) {
                    System.out.println(userName + "님 현재 쿠폰이 모두 소진되었습니다. 기다리십쇼....");
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println(userName + ": 쿠폰 발급 대기 중 인터럽트 발생.");
                        return;
                    }
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                couponCount--;
                System.out.println(userName + "님 쿠폰을 발급했습니다. 남은 쿠폰: " + couponCount + "장");

                this.notifyAll();
            }
        }
    }
}
