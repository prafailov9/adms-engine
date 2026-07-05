package com.ntr.ds.queue;

public class LinkedQueue<E> extends AbstractQueue<E> {

  @Override
  public void offer(E data) {
    var n = Node.of(data);

    if (isEmpty()) {
      tail = head = n;
    } else {
      tail.next = n;
      tail = n;
    }
    size++;
  }

  @Override
  public E take() {
    if (isEmpty()) {
      throw new RuntimeException("Queue is empty!");
    }
    E data = head.value;
    head = head.next;
    if (head == null) {
      tail = null;
    }
    size--;
    return data;
  }

  @Override
  public E peek() {
    return head.value;
  }

}
