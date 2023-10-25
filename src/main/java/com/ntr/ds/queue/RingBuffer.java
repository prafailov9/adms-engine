package com.ntr.ds.queue;

import java.util.logging.Logger;

/**
 * Circular queue - the front and rear nodes are connected. It has a limited capacity.
 * When the buffer is full and the add() method is called it will overwrite the rear node.
 *
 * @param <E>
 */
public class RingBuffer<E> extends AbstractQueue<E> {

    private static final Logger log = Logger.getLogger(RingBuffer.class.getName());
    protected final int capacity;

    public RingBuffer(final int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void offer(E data) {
        var newNode = Node.of(data);
        if (size == capacity) {
            log.info("Queue is full. Overwriting last element");
            overwrite(newNode);
            rear.data = newNode.data;
            return;
        }
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

    public int capacity() { return capacity; }

    /**
     * will overwrite the rear node with the new node and attach it to the front
     *
     * @param newNode - the node to add
     */
    private void overwrite(final Node<E> newNode) {
        rear.data = newNode.data;
        newNode.next = rear.next;
        rear = newNode;
        front.next = rear;
    }

}
