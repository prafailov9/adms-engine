package com.ntr.ds.stack;

import java.util.concurrent.locks.ReentrantLock;

public class StackImpl<E> implements Stack<E> {

    private final ReentrantLock lock = new ReentrantLock();

    private Node<E> top;
    private int size = 0;


    @Override
    public void push(E elem) {
        synchronized (lock) {
            if (isEmpty()) {
                top = Node.of(elem);
            } else {
                Node<E> node = Node.of(elem);
                node.next = top;
                top = node;
            }
            size++;
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        E data = top.data;
        synchronized (lock) {
            top = top.next;
            size--;
        }
        return data;
    }

    @Override
    public E peek() {
        return top.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    static class Node<E> {
        Node<E> next;
        E data;

        private Node(E data) {
            this.data = data;
        }
        static <E> Node<E> of(E data) {
            return new Node<>(data);
        }

    }
}
