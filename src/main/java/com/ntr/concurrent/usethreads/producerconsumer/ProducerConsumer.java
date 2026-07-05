package com.ntr.concurrent.usethreads.producerconsumer;

import com.ntr.ds.queue.LinkedQueue;
import com.ntr.ds.queue.Queue;

public class ProducerConsumer {

  private final Queue<Message> messageBuffer = new LinkedQueue<>();
  private final Object lock = new Object();
  private final int capacity;

  public ProducerConsumer(int initialCapacity) {
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException("initialCapacity <= 0");
    }
    capacity = initialCapacity;
  }

  public void produce() {
    int value = 0;
    synchronized (lock) {
      while (!Thread.currentThread().isInterrupted()) {
        while (messageBuffer.size() == capacity) {
          try {
            lock.wait();
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            break;
          }
        }
        if (Thread.currentThread().isInterrupted()) {
          break;
        }

        messageBuffer.offer(Message.of(++value));
        System.out.printf("producer added: %s%n", value);

        lock.notifyAll();
      }
      System.out.println("Producer finished work. Sending cancel signal to consumer...");
      messageBuffer.offer(Message.ofPoison());
      lock.notifyAll();
    }
  }

  public void consume() {
    synchronized (lock) {
      while (true) {
        while (messageBuffer.isEmpty()) {
          try {
            lock.wait();
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return;
          }
        }
        var message = messageBuffer.take();
        if (message.cancel) {
          System.out.println("Cancel signal received. Consumer exiting...");
          return;
        }
        System.out.printf("consumer took: %s%n", message.value);
        lock.notifyAll();
      }
    }
  }

  private record Message(int value, boolean cancel) {

    static Message of(int value) {
      return new Message(value, false);
    }

    static Message ofPoison() {
      return new Message(-1, true);
    }
  }

}
