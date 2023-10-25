package com.ntr.ds.queue;

import java.util.logging.Logger;

public class Buffer<E> extends RingBuffer<E> {

    private static final Logger log = Logger.getLogger(Buffer.class.getName());

    public Buffer(int capacity) {
        super(capacity);
    }

    @Override
    public void offer(E data) {
        if (size == capacity) {
            log.info("Queue is full. New element discarded");
            return;
        }

        var newNode = Node.of(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            front.next = newNode;
            front = newNode;
            front.next = rear;
        }
        size++;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            log.info("Queue is empty. Returning null value.");
            return null;
        }
        E data = rear.data;
        if (rear == front) {
            rear = front = null;
        } else {
            front.next = rear.next;
            rear = rear.next;
        }

        size--;
        return data;
    }
}
