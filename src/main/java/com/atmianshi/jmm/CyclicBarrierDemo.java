package com.atmianshi.jmm;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: atguigutwo
 * @description: 屏障计数器
 * @author: mxk
 * @create: 2020-06-07 23:44
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("集齐七颗龙珠召唤神龙");
        });
        for(int i = 1; i < 8; i++) {
            final  int temp=i;
                new Thread(() -> {

                    try {
                        System.out.println("集齐第"+Thread.currentThread().getName()+"颗龙珠");
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                },String.valueOf(i)).start();

        }
    }
}
