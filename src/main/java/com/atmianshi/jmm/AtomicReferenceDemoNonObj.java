package com.atmianshi.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: atguigutwo
 * @description: 非对象
 * @author: mxk
 * @create: 2020-06-07 15:03
 **/
public class AtomicReferenceDemoNonObj {

    static AtomicReference<Integer> atomicInteger= new AtomicReference(100);
   static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<Integer>(100,1);
   public static void main(String[] args) {
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName()+"开始执行");
//            System.out.println(Thread.currentThread().getName()+"睡觉");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"睡醒");
//            atomicInteger.compareAndSet(100,1);
//            atomicInteger.compareAndSet(1,100);
//            System.out.println(Thread.currentThread().getName()+"结束执行，结果值"+atomicInteger.get());
//        },"AAA").start();
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName()+"开始执行");
//            int i = atomicInteger.get();
//            System.out.println(Thread.currentThread().getName()+"睡觉");
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"睡醒");
//            atomicInteger.compareAndSet(i,2019);
//            System.out.println(Thread.currentThread().getName()+"结束执行，结果值"+atomicInteger.get());
//
//
//        },"BBB").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始执行");
            System.out.println(Thread.currentThread().getName()+"睡觉");
            int stamp = atomicStampedReference.getStamp();
            try {
               TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"睡醒");
            atomicStampedReference.compareAndSet(100,1,stamp,atomicStampedReference.getStamp()+1);
            atomicStampedReference.compareAndSet(1,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"结束执行，结果值"+atomicStampedReference.getReference());
        },"CCC").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始执行");
            int i = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"睡觉");
            try {
               TimeUnit.SECONDS.sleep(9);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"睡醒");
            atomicStampedReference.compareAndSet(100,2019,i,atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"结束执行，结果值"+atomicInteger.get());


        },"DDD").start();
    }
}
