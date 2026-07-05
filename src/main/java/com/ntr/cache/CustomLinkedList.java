package com.ntr.cache;

import java.util.Objects;

public class CustomLinkedList<E> implements LinkedList<E> {

  private Node<E> start;
  private Node<E> end;
  private int size;

  @Override
  public void addFirst(E elem) {
    var n = new Node<>(elem);
    if (isEmpty()) {
      start = end = n;
    } else {
      n.next = start;
      start.prev = n;
      start = n;
    }

    size++;
  }

  @Override
  public void addLast(E elem) {
    var n = new Node<>(elem);
    if (isEmpty()) {
      start = end = n;
    } else {
      n.prev = end;
      end.next = n;
      end = n;
    }

    size++;
  }

  @Override
  public E remove(E elem) {
    // if no elems
    if (isEmpty()) {
      return null;
    }

    var t = start;
    while (t != null) {
      if (Objects.equals(t.elem, elem)) {
        E e = t.elem;
        detach(t);
        return e;
      }
      t = t.next;
    }
    return null;
  }

  @Override
  public E removeFirst() {
    if (isEmpty()) {
      return null;
    }
    E e = start.elem;
    start = start.next;
    if (start == null) {
      end = null;
    } else {
      start.prev = null;
    }
    size--;
    return e;
  }

  @Override
  public E removeLast() {
    if (isEmpty()) {
      return null;
    }
    E e = end.elem;
    end = end.prev;
    if (end == null) {
      start = null;
    } else {
      end.next = null;
    }
    size--;
    return e;
  }

  @Override
  public E peekLast() {
    return end == null ? null : end.elem;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  private void detach(Node<E> node) {
    if (node == start && node == end) {
      start = end = null;
    } else if (node == start) {
      start = start.next;
      start.prev = null;
    } else if (node == end) {
      end = end.prev;
      end.next = null;
    } else {
      // reattach node's neighbors
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }
    size--;
  }

  private static final class Node<E> {

    E elem;
    Node<E> prev;
    Node<E> next;

    Node(E e) {
      elem = e;
    }

  }

}
