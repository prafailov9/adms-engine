package com.ntr.concurrent.usethreads.deadlock;

import static java.lang.Thread.currentThread;

public class DeadlockMain {

    String text = "text";

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();

        Runnable task = () -> {
            System.out.printf("IN %s, processor name is %s\n", currentThread().getName(), processor.name);
            processor.name = "new-name";
            System.out.printf("Thread %s, changed processor name to %s\n", currentThread().getName(), processor.name);
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);


//        Processor processor = new Processor();
//
//        Runnable task1 = processor::operation1;
//        Runnable task2 = processor::operation2;
//
//        Thread t1 = new Thread(task1);
//        Thread t2 = new Thread(task2);
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();

    }

}
