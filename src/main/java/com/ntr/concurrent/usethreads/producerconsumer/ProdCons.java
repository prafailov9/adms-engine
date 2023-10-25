package com.ntr.concurrent.usethreads.producerconsumer;

import com.ntr.ds.queue.Buffer;

import java.util.logging.Logger;

public class ProdCons {

    private static final Logger log = Logger.getLogger(ProdCons.class.getName());

    private Buffer<Integer> workQueue;

    public ProdCons(final int queueCapacity) {
        workQueue = new Buffer<>(queueCapacity);
    }

    public void produce() {
        int data = 0;
        while (workQueue.capacity() < workQueue.size()) {
            workQueue.offer(data);
            log.info(String.format("Added data to queue: %s", data));
            data++;
        }
    }

    public void consume() {
        while (workQueue.capacity() == workQueue.size()) {
            int data = workQueue.poll();
            log.info(String.format("Removed data from queue: %s", data));
        }
    }
}
