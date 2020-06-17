package com.atmianshi.jmm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: atguigutwo
 * @description: 读写锁示例
 * @author: mxk
 * @create: 2020-06-07 19:36
 **/
public class ReentrantReanWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //写5次
      for(int i = 0; i < 5; i++) {
          final int tempvalue=i;
              new Thread(() -> {
                  myCache.put(tempvalue,tempvalue);
              },i+"").start();

      }
        //读五次
        for(int i = 0; i < 5; i++) {
            final int tempvalue=i;
            new Thread(() -> {
                myCache.get(tempvalue);
            },i+"").start();

        }

    }

}
class MyCache{

    private volatile ReentrantReadWriteLock lock= new ReentrantReadWriteLock();
    private Map cache=new HashMap<Integer,Object>();


    public void put(Integer key,Object value){
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t开始写入");
        try {
            TimeUnit.MILLISECONDS.sleep(30);
            cache.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t"+key+"写入結束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }


    }
    public void get(Integer key){
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t开始读取");
        try {
            TimeUnit.MILLISECONDS.sleep(30);
            Object o = cache.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取"+o+"結束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }


    }


}
