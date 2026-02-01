package com.ntr.concurrent.usethreads.producerconsumer;

import com.ntr.ds.queue.Buffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ProdCons {

  private int totalCycles;
  private int currentCycles = 0;
  private boolean done = false;

  private final Buffer<Integer> buffer;
  private final Object lock = new Object();

  private final int cap;


  public ProdCons(final int queueCapacity) {
    buffer = new Buffer<>(queueCapacity);
    cap = queueCapacity;
  }

  public ProdCons(final int queueCapacity, int cycles) {
    buffer = new Buffer<>(queueCapacity);
    cap = queueCapacity;
    totalCycles = cycles;
  }

  public void produce() {
    int item = 0;
    int c = 0;
    synchronized (lock) {
      while (!done) {
        while (buffer.size() == cap && !done) {
          try {
            lock.wait();
            c = 0;
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return;
          }
        }
        if (done) {
          return;
        }
        item++;
        c++;
        buffer.offer(item);
        System.out.printf("[%s]: Producer added item to queue: %s%n", c, item);
        incrementAndCheckIfDone();
        lock.notifyAll();
      }
    }
  }

  public void consume() {
    int c = 0;
    synchronized (lock) {
      while (!done) {
        while (buffer.isEmpty() && !done) {
          try {
            lock.wait();
            c = 0;
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return;
          }
        }
        if (done) {
          return;
        }
        int item = buffer.poll();
        c++;
        System.out.printf("[%s]: Consumer removed item to from: %s%n", c, item);
        incrementAndCheckIfDone();
        lock.notifyAll();
      }
    }
  }

  private void incrementAndCheckIfDone() {
    currentCycles++;
    if (currentCycles >= totalCycles) {
      done = true;
    }
  }

}
