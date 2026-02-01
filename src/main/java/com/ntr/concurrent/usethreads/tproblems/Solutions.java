package com.ntr.concurrent.usethreads.tproblems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solutions {

  private volatile int sharedCounter = 0;
  private final Object counterLock = new Object();

  void helloFromThreads() {
    var threads = IntStream.range(0, 3)
        .mapToObj(i -> new Thread(
            () -> System.out.printf("Hello from %s (id=%d)%n",
                Thread.currentThread().getName(), Thread.currentThread().threadId()),
            "worker-" + i))
        .toList();

    threads.forEach(Thread::start);
    threads.forEach(this::join);
    System.out.println("Main done");
  }

  void sharedCounter() {
    Runnable sharedTask = () -> {
      for (int i = 0; i < 1000; i++) {
        synchronized (counterLock) {
          sharedCounter++;
        }
      }
    };
    List<Thread> threads = Stream.of(new Thread[5]).map(t -> new Thread(sharedTask)).toList();
    threads.forEach(Thread::start);
    threads.forEach(this::join);
    System.out.printf("Counter: %s", sharedCounter);
  }

  private final List<Integer> buffer = new ArrayList<>(1);
  private final Object lock = new Object();
  private static final int CAP = 10;
  private int nextVal = 1;
  private boolean done = false;

  void producerConsumer() throws InterruptedException {

    // producer chooses when to stop.

    Thread producer = new Thread(() -> {
      synchronized (lock) {
        while (!done) {
          while (buffer.size() == 1) {
            // producer will wait while the buffer is ful
            wait(lock);
          }

          if (nextVal > CAP) {
            done = true;
            // wake any waiting consumer so it can exit
            lock.notifyAll();
            break;
          }
          System.out.printf("Producer. Buffer: %s...%n", buffer);
          buffer.add(++nextVal);

          if (nextVal == 10) {
            done = true;
          }
          lock.notifyAll();
        }
      }
    });

    Thread consumer = new Thread(() -> {
      while (!done) {
        synchronized (lock) {
          while (buffer.isEmpty() && !done) {
            wait(lock);
          }
          System.out.printf("Consumer. Buffer: %s...%n", buffer);
          buffer.removeFirst();
          lock.notifyAll();
        }
      }
    });

    producer.start();
    consumer.start();

    join(producer);
    join(consumer);

    System.out.println(buffer);
  }

  private void wait(final Object lock) {
    try {
      lock.wait();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void join(final Thread t) {
    try {
      t.join();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Solutions s = new Solutions();
//    s.helloFromThreads();
//    s.sharedCounter();
    s.producerConsumer();
  }

}
