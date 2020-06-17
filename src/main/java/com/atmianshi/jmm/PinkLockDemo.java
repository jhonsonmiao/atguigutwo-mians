package com.atmianshi.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: atguigutwo
 * @description: 自旋锁代码
 * @author: mxk
 * @create: 2020-06-07 18:20
 **/
public class PinkLockDemo {

    public static void main(String[] args) {
        PinkLockDemo pinkLockDemo = new PinkLockDemo();
       new Thread(() -> {
           pinkLockDemo.myLock();
           try {
               TimeUnit.SECONDS.sleep(5);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           pinkLockDemo.unLock();
       },"AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            pinkLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pinkLockDemo.unLock();
        },"BB").start();

    }

    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void myLock(){
      System.out.println(Thread.currentThread().getName()+"come in");
        Thread thread = Thread.currentThread();
        while(!threadAtomicReference.compareAndSet(null,thread)){

         }
    }
    public void unLock(){
        Thread thread = Thread.currentThread();
        threadAtomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"get out");
    }
}




