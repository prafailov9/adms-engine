package com.ntr.cache;

import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

public class DoublyLinkedList<E> {

    private final ReentrantLock lock = new ReentrantLock();

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyLinkedList() {

    }

    public void add(final E data) {
        Node<E> newNode = Node.of(data);
        synchronized (lock) {
            if (isEmpty()) {
                head = tail = newNode;
            } else {
                newNode.prev = head;
                head.next = newNode;
                head = head.next;
            }
            size++;
        }
    }


    /**
     * removes last element in the list
     *
     * @return
     */
    public E remove() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty!");
        }
        E data = tail.data;
        synchronized (lock) {
            tail = tail.next;
            if (tail == null) {
                head = null;
            } else {
                tail.prev = null;
            }
            size--;
        }
        return data;
    }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    static class Node<E> {
        Node<E> next;
        Node<E> prev;
        E data;

        private Node(E data) {
            this.data = data;
        }

        static <E> Node<E> of(E data) {
            Node<E> node = new Node<>(data);
            node.next = null;
            node.prev = null;

            return node;
        }

    }

}
