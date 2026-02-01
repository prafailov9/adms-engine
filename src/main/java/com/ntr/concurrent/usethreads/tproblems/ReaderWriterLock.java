package com.ntr.concurrent.usethreads.tproblems;

/**
 * Writer-preferred
 */
public class ReaderWriterLock {

  private final Object lock = new Object();

  private int activeReaders;
  private int waitingWriters;
  private boolean activeWriter;

  public void readLock() {
    synchronized (lock) {
      while (activeWriter || waitingWriters > 0) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          return;
        }
      }
      activeReaders++;
      System.out.printf("%s start%n", Thread.currentThread().getName());
    }
  }

  public void readUnlock() {
    synchronized (lock) {
      activeReaders--;
      if (activeReaders == 0) {
        lock.notifyAll();
        System.out.printf("%s start%n", Thread.currentThread().getName());
      }
    }
  }

  public void writeLock() {
    synchronized (lock) {
      waitingWriters++;
      while (activeWriter || activeReaders > 0) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          return;
        }
      }
      waitingWriters--;
      activeWriter = true;
      System.out.printf("%s start%n", Thread.currentThread().getName());
    }
  }

  public void writeUnlock() {
    synchronized (lock) {
      activeWriter = false;
      lock.notifyAll();
      System.out.printf("%s wait%n", Thread.currentThread().getName());
    }
  }


}
