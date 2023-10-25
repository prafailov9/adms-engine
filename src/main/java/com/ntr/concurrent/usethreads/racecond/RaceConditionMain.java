package com.ntr.concurrent.usethreads.racecond;

public class RaceConditionMain {

    public static final Integer ITERATIONS = 100_0;

    public static void main(String[] args) throws InterruptedException {

        LongWrapper longWrapper = new LongWrapper(0L);

        Runnable task = () -> {
            for (int i = 0; i < ITERATIONS; i++) {
                longWrapper.increment();
            }
        };

        Thread[] threads = new Thread[ITERATIONS];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task, "Thread-" + i);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        // longWrapper is incremented 1000 times by 1000 diffeent threads. The result should be 1 Million.
        // The problem happenes in the increment() method
        System.out.printf("Value = %s", longWrapper.get());
    }

}
