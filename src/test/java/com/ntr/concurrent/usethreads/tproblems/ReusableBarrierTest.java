package com.ntr.concurrent.usethreads.tproblems;

import com.ntr.ds.res.Resource;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ReusableBarrierTest {


  private List<Integer> buffer = new ArrayList<>();
  private boolean done = false;
  private final Object lock = new Object();
  private final int capacity = 10;
  private int totalCycles = 10000;

  @Test
  public void dicks () {

    Resource r = Resource.get();
    System.out.println(r);

  }

  @Test
  public void producerConsumerTest() {

    Thread p = new Thread(this::produce, "producer");
    Thread c = new Thread(this::consume, "consumer");

    p.start();
    c.start();

    try {
      p.join(); // MT blocks
      c.join();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    System.out.println("Both threads joined. Main Thread exiting...");
  }

  void produce() {
    int item = 0;
    int cycles = 1;
    while (!done) {
      synchronized (lock) {
        while (buffer.size() == capacity && !done) {
          try {
            item = 0;
            lock.wait();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
          }
        }

        if (done) {
          return;
        }

        buffer.add(++item);
        System.out.printf("%s added %s%n", Thread.currentThread().getName(), item);
        cycles++;
        if (cycles == totalCycles) {
          done = true;
        }
        lock.notifyAll();
      }
    }
  }

  void consume() {
    while (true) {
      synchronized (lock) {
        while (buffer.isEmpty() && !done) {
          try {
            lock.wait();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
          }
        }
        if (done) {
          return;
        }

        int item = buffer.removeFirst();
        System.out.printf("%s took %s%n", Thread.currentThread().getName(), item);
        lock.notifyAll();
      }
    }
  }

  @Test
  public void t1() {

    int N = 5, PHASES = 3;
    ReusableBarrier barrier = new ReusableBarrier(
        N);

    for (int i = 0; i < N; i++) {
      final String name = "t-" + i;
      new Thread(() -> {
        for (int p = 1; p <= PHASES; p++) {
          System.out.printf("%s before %d%n", name, p);
          try {
            barrier.await();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          System.out.printf("%s after %d%n", name, p);
        }
      }, name).start();
    }
  }
}