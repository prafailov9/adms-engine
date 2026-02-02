package com.ntr.hrank.threads;


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ProdConsTest {

  List<Integer> queue = new ArrayList<>();
  private final Object lock = new Object();
  private static final int CAPACITY = 10;
  private boolean terminate = false;

  @Test
  public void prodConsTest() {
    // when creating a threat, we are submitting a task to run at a later time(when we call .start())
    Thread producer = new Thread(this::produce, "producer");
    Thread consumer = new Thread(this::consume, "consumer");

    producer.start();
    consumer.start();

    try {
      producer.join();
      consumer.join();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

  }

  void produce() {
    int i = 1;
    int cycles = 100;
    System.out.println("PRODUCER tries to get in...");
    synchronized (lock) {
      System.out.println("PRODUCER: in!");
      while (!terminate) {
        System.out.println("PRODUCER: QUEUE size: " + queue.size());
        while (queue.size() == CAPACITY) {
          try {
            System.out.println("PRODUCER waiting...");
            lock.wait();
          } catch (InterruptedException ex) {
            System.out.println("Interrupted");
            Thread.currentThread().interrupt();
          }
        }
        queue.add(i);
        System.out.println("[" + i + "]* -> " + Thread.currentThread().getName() + ": " + i);
        if (cycles == i) {
          terminate = true;
        }
        i++;
        lock.notifyAll();
      }
    }

  }

  void consume() {
    System.out.println("CONSUMER tries to get in...");
    synchronized (lock) {
      System.out.println("CONSUMER: in!");
      while (!terminate || !queue.isEmpty()) {
        System.out.println("CONSUMER: QUEUE size: " + queue.size());
        while (queue.isEmpty()) {
          try {
            System.out.println("consumer waiting...");
            lock.wait();// consumer thread stops and waits for the producer to call it
          } catch (InterruptedException ex) {
            System.out.println("Interrupted");
            Thread.currentThread().interrupt();
          }
        }
        int i = queue.removeFirst();
        System.out.println("[" + i + "]& -> " + Thread.currentThread().getName() + ": " + i);
        lock.notifyAll();
      }
    }
  }


}