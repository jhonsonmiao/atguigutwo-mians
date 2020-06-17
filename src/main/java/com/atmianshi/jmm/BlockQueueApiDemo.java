package com.atmianshi.jmm;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @program: atguigutwo
 * @description: 阻塞队列Api demo
 * @author: mxk
 * @create: 2020-06-10 21:36
 **/
public class BlockQueueApiDemo {
    public static void main01(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        System.out.println(blockingQueue.element());//a
//        blockingQueue.add("a");//Exception in thread "main" java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());//Exception in thread "main" java.util.NoSuchElementException
       System.out.println(blockingQueue.element());//Exception in thread "main" java.util.NoSuchElementException
    }

    public static void main02(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.offer("d"));//false
        System.out.println(blockingQueue.peek());//a
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.peek());//null

    }

    public static void main03(String[] args) throws InterruptedException {
       BlockingQueue<String> blockingQueues = new ArrayBlockingQueue<>(3);
        blockingQueues.put("a");
        blockingQueues.put("a");
        blockingQueues.put("a");
//        blockingQueues.put("a");//阻塞
        blockingQueues.take();
        blockingQueues.take();
        blockingQueues.take();
//        blockingQueues.take();//阻塞
    }

    public static void main(String[] args) throws InterruptedException {
        //阻塞有界
//        LinkedBlockingDeque<String> strings = new LinkedBlockingDeque<>();默认容量Integer.MAX_VALUE 20亿
        BlockingQueue<String> blockingQueues = new ArrayBlockingQueue<>(3);
        blockingQueues.offer("a");
        blockingQueues.offer("a");
        blockingQueues.offer("a");
        blockingQueues.offer("a",2, TimeUnit.SECONDS);//超时不塞
        System.out.println(blockingQueues.poll());
        System.out.println(blockingQueues.poll());
        System.out.println(blockingQueues.poll());
        System.out.println(blockingQueues.poll(2,TimeUnit.SECONDS));//null
    }
}
