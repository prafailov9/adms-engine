package com.ntr.concurrent.usethreads.tproblems;

public class BoundedBlockingQueue<T> {

  private Node<T> start;
  private Node<T> end;

  private final int capacity;
  private int size;
  private final Object lock = new Object();

  public BoundedBlockingQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity les than 0");
    }
    this.capacity = capacity;
    this.size = 0;
  }

  public void put(T data) {
    synchronized (lock) {
      while (size == capacity) {
        try {
          lock.wait();
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
          return;
        }
      }
      Node<T> n = new Node<>(data);
      // for first element, init pointers
      if (size == 0) {
        start = end = n;
      } else {
        // attach new node to queue
        end.next = n;
        // advance moving pointer(end) to the new node
        end = n;
      }
      size++;
      System.out.printf("put %s (size=%s)%n", n.element, size);
      lock.notifyAll();
    }

  }

  public T take() {
    T e;
    synchronized (lock) {
      while (isEmpty()) {
        try {
          lock.wait();
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
          return null;
        }
      }
      e = start.element;
      start = start.next;
      size--;
      if (size == 0) {
        start = end = null; // reset control pointers
      }
      System.out.printf("take %s (size = %s)%n", e, size);
      lock.notifyAll();
      return e;

    }
  }

  public T peek() {
    synchronized (lock) {
      if (start != null) {
        return start.element;
      }
      return null;
    }
  }

  public boolean isEmpty() {
    synchronized (lock) {
      return size == 0;
    }
  }

  public int size() {
    synchronized (lock) {
      return size;
    }
  }


  private static final class Node<T> {

    T element;
    Node<T> next;

    Node(T element) {
      this.element = element;
    }
  }
}
