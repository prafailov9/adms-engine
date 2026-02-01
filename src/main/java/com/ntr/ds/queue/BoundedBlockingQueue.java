package com.ntr.ds.queue;

public class BoundedBlockingQueue<E> implements BlockingQueue<E> {

  private Node<E> start;
  private Node<E> end;
  private int size = 0;

  private final int capacity;
  private final Object lock = new Object();

  public BoundedBlockingQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity must be > 0");
    }
    this.capacity = capacity;
  }

  @Override
  public void put(E item) throws InterruptedException {
    Node<E> newNode = new Node<>(item);

    synchronized (lock) {
      while (size == capacity) {
        lock.wait();
      }

      if (size == 0) {
        start = end = newNode;
      } else {
        end.next = newNode;
        end = newNode;
      }

      size++;
      lock.notifyAll();
    }
  }

  @Override
  public E take() throws InterruptedException {
    synchronized (lock) {
      while (size == 0) {
        lock.wait();
      }

      E item = start.item;
      start = start.next;
      size--;

      if (size == 0) {
        end = null;
      }

      lock.notifyAll();
      return item;
    }
  }

  @Override
  public boolean isEmpty() {
    synchronized (lock) {
      return size == 0;
    }
  }

  private static final class Node<E> {

    final E item;
    Node<E> next;

    Node(E item) {
      this.item = item;
    }
  }
}
