package com.atmianshi.jmm;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.concurrent.CountDownLatch;

/**
 * @program: atguigutwo
 * @description: 减法计数器
 * @author: mxk
 * @create: 2020-06-07 23:12
 **/
public class CountDownLatchDemo {
    static  final Integer num=6;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for(int i = 1; i < 7; i++) {   
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t被灭");
                countDownLatch.countDown();
            
            },Country.fore_enum(i).getCountry_message()).start();
            
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("秦滅六國，一統中原");
        }

}

