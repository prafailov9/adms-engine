package com.ntr.ds.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class AbstractQueue<E> implements Queue<E> {

  protected Node<E> head;
  protected Node<E> tail;

  protected int size;

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public abstract void offer(E data);

  @Override
  public abstract E take();

  protected static class Node<E> {

    Node<E> next;
    E value;

    private Node(E value) {
      this.value = value;
    }

    static <E> Node<E> of(E data) {
      var node = new Node<>(data);
      node.next = null;

      return node;
    }

  }

  @Override
  public Iterator<E> iterator() {
    return new QueueIterator();
  }

  @Override
  public void forEach(Consumer<? super E> action) {
    Queue.super.forEach(action);
  }

  @Override
  public Spliterator<E> spliterator() {
    return Queue.super.spliterator();
  }

  /**
   * Implement an Iterator class to handle Iterable
   */

  class QueueIterator implements Iterator<E> {

    Node<E> current = null;

    @Override
    public boolean hasNext() {
      if (current != null) {
        return current.next != null;
      }

      return head != null;

    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      if (current != null) {
        current = current.next;
      } else {
        current = head;
      }

      return current.value;
    }
  }

}
