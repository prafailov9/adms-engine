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
      log.info("Buffer is full. New element discarded");
      return;
    }

    var n = Node.of(data);
    if (isEmpty()) {
      tail = head = n;
    } else {
      tail.next = n;
      tail = n;
      tail.next = head;
    }
    size++;
  }

  @Override
  public E take() {
    if (isEmpty()) {
      log.info("Buffer is empty.");
      return null;
    }
    E data = head.value;
    if (head == tail) {
      head = tail = null;
    } else {
      tail.next = head.next;
      head = head.next;
    }

    size--;
    return data;
  }
}
