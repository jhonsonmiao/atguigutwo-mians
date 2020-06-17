package com.atmianshi.jmm;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: atguigutwo
 * @description: 可重入锁，递归锁代码
 * @author: mxk
 * @create: 2020-06-07 18:00
 **/
public class ReentrantLockDemo {

    public static void main(String[] args)  {
        phone phone = new phone();
        new Thread(() -> {
            phone.sendMsn();
        },"AA").start();

        new Thread(() -> {
            phone.sendMsn();
        },"BB").start();

        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phoneTwo phoneTwo = new phoneTwo();
        Thread cc = new Thread(phoneTwo, "CC");
        Thread dd = new Thread(phoneTwo, "DD");
        cc.start();
        dd.start();


    }

}
class phone{

    public synchronized  void sendMsn(){
        System.out.println(Thread.currentThread().getName()+"invoke sendMsn");
        sendEmile();
    }

    public synchronized void sendEmile(){
        System.out.println(Thread.currentThread().getName()+"invoke sendEmile");
    }



}


class phoneTwo implements Runnable{

    public synchronized  void sendMsn(){
        System.out.println(Thread.currentThread().getName()+"invoke sendMsn");
        sendEmile();
    }

    public synchronized void sendEmile(){
        System.out.println(Thread.currentThread().getName()+"invoke sendEmile");
    }
    ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {

        get();
    }
    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"invoke get");
           set();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"invoke set");
        }finally {
            lock.unlock();
        }
    }

}

