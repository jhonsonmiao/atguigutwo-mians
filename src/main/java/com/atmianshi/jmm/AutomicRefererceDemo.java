package com.atmianshi.jmm;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: atguigutwo
 * @description: 原子引用
 * @author: mxk
 * @create: 2020-06-03 22:05
 **/
public class AutomicRefererceDemo {

  public static void main(String[] args) {
 /*         Person zansan = new Person(3, "张三");
        Person lisi = new Person(4, "李四");
        AtomicReference<Person> personAtomicReference = new AtomicReference<>();
        personAtomicReference.set(zansan);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始执行");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"睡醒了");
            personAtomicReference.compareAndSet( zansan,lisi);
            personAtomicReference.compareAndSet(lisi,zansan);
            System.out.println(personAtomicReference.get().toString());
            System.out.println(Thread.currentThread().getName()+"结束执行");
        },"AAA").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始执行");
            Person person1 = personAtomicReference.get();
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"睡醒了");
            personAtomicReference.compareAndSet( person1,lisi);
            System.out.println(personAtomicReference.get().toString());
            System.out.println(Thread.currentThread().getName()+"结束执行");
        },"BBB").start();*/
        Person zansan = new Person(3, "张三");
        Person lisi = new Person(4, "李四");
        AtomicStampedReference<Person> personAtomicReference = new AtomicStampedReference<>(zansan,1);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始执行");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"睡醒了");
            personAtomicReference.compareAndSet(zansan,lisi,personAtomicReference.getStamp(),personAtomicReference.getStamp()+1);
            personAtomicReference.compareAndSet(lisi,zansan,personAtomicReference.getStamp(),personAtomicReference.getStamp()+1);
            System.out.println(personAtomicReference.getReference().toString());
            System.out.println(Thread.currentThread().getName()+"结束执行");
        },"CCC").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始执行");
            Person person1 = personAtomicReference.getReference();
            int stamp = personAtomicReference.getStamp();
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"睡醒了");
            personAtomicReference.compareAndSet( person1,new Person(4, "李四"),stamp,personAtomicReference.getStamp()+1);
            System.out.println(personAtomicReference.getReference().toString());
            System.out.println(Thread.currentThread().getName()+"结束执行");
        },"DDD").start();

    }
}

class Person{

    private Integer age;

    private String name;

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
