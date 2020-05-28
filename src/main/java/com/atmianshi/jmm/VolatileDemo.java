package com.atmianshi.jmm;

import javax.xml.crypto.Data;

/**
 * @program: atguigutwo
 * @description: jvm轻量级同步机制
 * @author: mxk
 * @create: 2020-05-28 23:12
 **/
public class VolatileDemo {




  /*  public static void main(String[] args) {
        MyData myData = new MyData();
        //验证volatile可见性
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            myData.to60Method();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        while(myData.getNum()==0){

        }
        System.out.println("num值已更新");

    }*/
    //验证volitile不是原子性

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i <20 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPulsPuls();
                }
            },"i").start();
        }
        //主线程，gc
        if(Thread.activeCount()>2){
            //礼让线程
            Thread.yield();
        }
        System.out.println("final"+myData.getNum());

    }
}
class MyData{
    volatile  int num=0;

    public void to60Method(){
        num=60;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void addPulsPuls(){
        num++;
    }
}
