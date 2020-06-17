package com.atmianshi.jmm;

import java.util.Date;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: atguigutwo
 * @description: 同步队列，不存储元素
 * @author: mxk
 * @create: 2020-06-10 22:13
 **/
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> syncQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                syncQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                syncQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                syncQueue.put("3");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
            } catch (InterruptedException e) {
                System.out.println(e);
                e.printStackTrace();
            }

        },"AAA").start();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName()+"\t"+syncQueue.take());
                System.out.println(Thread.currentThread().getName()+"\t"+syncQueue.take());
                System.out.println(Thread.currentThread().getName()+"\t"+syncQueue.take());
            } catch (InterruptedException e) {
                System.out.println(e);
                e.printStackTrace();
            }
           ;

        },"BB").start();

    }
}
