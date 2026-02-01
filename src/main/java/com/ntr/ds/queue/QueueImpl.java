package com.ntr.ds.queue;

public class QueueImpl<E> extends AbstractQueue<E> {

  @Override
  public void offer(E data) {
    var newNode = Node.of(data);

    if (isEmpty()) {
      front = rear = newNode;
    } else {
      front.next = newNode;
      front = newNode;
    }
    size++;
  }

  @Override
  public E poll() {
    if (isEmpty()) {
      throw new RuntimeException("Queue is empty!");
    }
    E data = rear.data;
    rear = rear.next;
    if (rear == null) {
      front = null;
    }
    size--;
    return data;
  }

  @Override
  public E peek() {
    return rear.data;
  }

}
