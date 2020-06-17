package com.atmianshi.jmm;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @program: atguigutwo
 * @description: 信号量计数
 * @author: mxk
 * @create: 2020-06-07 23:52
 **/
public class SemaPhoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for(int i = 0; i < 6; i++) {
            final  int temp=i;
                new Thread(() -> {
                    try {

                        semaphore.acquire();
                        System.out.println("第"+Thread.currentThread().getName()+"抢到车位");
                        TimeUnit.MILLISECONDS.sleep(3);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    semaphore.release();
                    System.out.println("第"+Thread.currentThread().getName()+"释放车位");
                },String.valueOf(temp)).start();

        }
    }
}
