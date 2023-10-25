package com.ntr.concurrent.usethreads.deadlock;


public class Processor {


    String name = "processor";

    private final Object key1 = new Object();
    private final Object key2 = new Object();

    /***
     * t1 holds the key to op3 which means t2 has to wait for t1 to release it.
     * In order to release the key1 t1 needs to first acquire key2 to enter op2, but key2 is held by t2.
     * Both t1 and t2 are waiting for each other to release the keys and none of them can continue.
     * This is a deadlock
     */
    public void operation1() {
        synchronized (key1) {
            System.out.printf("[%s] execution operation 1", Thread.currentThread().getName());
            operation2();
        }
    }

    public void operation2() {
        synchronized (key2) {
            System.out.printf("[%s] execution operation 2", Thread.currentThread().getName());
            operation3();
        }
    }

    public void operation3() {
        synchronized (key1) {
            System.out.printf("[%s] execution operation 3", Thread.currentThread().getName());
        }
    }

}
