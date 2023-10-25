package com.ntr.concurrent.usethreads;

import com.ntr.concurrent.Main;

public class CreateThreads {

    public Thread[] createThreads(final int numberOfThreads) {
        Thread[] threads = new Thread[numberOfThreads];

        for(int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(task(), "Thread-" + i);
        }
        return threads;
    }

    public Runnable task() {
        return () -> {
            int c = 0;
            while (c < Main.ITERATIONS) {
                System.out.println(Thread.currentThread().getName() + " exec task number " + c);
                c++;
            }
        };
    }
}
