package com.atmianshi.jmm;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: atguigutwo
 * @description: lock版的生产者消费者案例
 * @author: mxk
 * @create: 2020-06-10 22:58
 * 口诀：线程操作资源类 判斷干活通知    严防多线程导致的虚假通知
 **/
class ShareData {

    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increament() throws Exception {
        lock.lock();
        try {


            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void decreament() throws Exception {
        lock.lock();
        try {

            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }


}


public class LockConditionProvideConsumeDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    shareData.increament();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {

                    shareData.decreament();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();


    }
}
