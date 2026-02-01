package com.ntr.concurrent.usethreads.tproblems;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReaderWriterLockTest {

  private final ReaderWriterLock readerWriterLock = new ReaderWriterLock();

  @Test
  public void basicOrdering() {
    Thread[] readers = new Thread[3];
    Thread writer = new Thread(() -> {
      readerWriterLock.writeLock();
      readerWriterLock.writeUnlock();
    }, "W1");

    for (int i = 0; i < 3; i++) {
      readers[i] = new Thread(() -> {
        readerWriterLock.readLock();
        readerWriterLock.readUnlock();
      }, "R" + (i + 1));
    }

    writer.start();
    for (var t : readers) {
      t.start();
    }

    try {
      writer.join();
      for (var t : readers) {
        t.join();
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

}