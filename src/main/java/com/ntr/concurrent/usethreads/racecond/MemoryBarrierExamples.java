package com.ntr.concurrent.usethreads.racecond;

public class MemoryBarrierExamples {

  // ===== STORESTORE BARRIER =====
  // Ensures all writes BEFORE a volatile write are visible before the volatile write itself
  static class StoreStoreExample {

    int data = 0;
    volatile boolean ready = false;

    void writer() {
      data = 42;          // Normal write
      // [StoreStore barrier inserted here by JVM]
      ready = true;       // Volatile write — 'data' is guaranteed to be written first
    }

    void reader() {
      if (ready) {
        System.out.println(data); // Safe: always prints 42
      }
    }
  }


  // ===== STORELOAD BARRIER =====
  // Ensures a volatile write is NOT reordered with a subsequent volatile read
  // This is the most expensive barrier (full fence)
  static class StoreLoadExample {

    volatile int x = 0;
    volatile int y = 0;

    void thread1() {
      x = 1;              // Volatile write
      // [StoreLoad barrier inserted here by JVM]
      int r1 = y;         // Volatile read — cannot be moved before x = 1
      System.out.println("r1=" + r1);
    }

    void thread2() {
      y = 1;              // Volatile write
      // [StoreLoad barrier inserted here by JVM]
      int r2 = x;         // Volatile read — cannot be moved before y = 1
      System.out.println("r2=" + r2);
    }
    // Without StoreLoad: r1=0, r2=0 would be possible (both threads reorder read before write)
    // With StoreLoad: at least one of r1 or r2 must be 1
  }


  // ===== LOADLOAD BARRIER =====
  // Ensures all reads AFTER a volatile read are not reordered before it
  static class LoadLoadExample {

    int data = 0;
    volatile boolean ready = false;

    void writer() {
      data = 100;
      ready = true;
    }

    void reader() {
      if (ready) {            // Volatile read
        // [LoadLoad barrier inserted here by JVM]
        System.out.println(data); // Normal read — guaranteed to see data=100
        // Cannot be reordered to happen before reading 'ready'
      }
    }
  }


  // ===== LOADSTORE BARRIER =====
  // Ensures a volatile read is NOT reordered with a subsequent normal write
  static class LoadStoreExample {

    volatile int flag = 0;
    int result = 0;

    void process() {
      int f = flag;       // Volatile read
      // [LoadStore barrier inserted here by JVM]
      result = f * 10;    // Normal write — cannot be moved before the volatile read
      // Ensures 'result' is computed using the up-to-date value of 'flag'
    }
  }


  public static void main(String[] args) {
    System.out.println("StoreStore: Volatile write flushes prior normal writes");
    StoreStoreExample ss = new StoreStoreExample();
    ss.writer();
    ss.reader();

    System.out.println("StoreLoad: Full fence between volatile write and subsequent read");
    StoreLoadExample sl = new StoreLoadExample();
    sl.thread1();

    System.out.println("LoadLoad: Volatile read prevents subsequent reads from floating up");
    LoadLoadExample ll = new LoadLoadExample();
    ll.writer();
    ll.reader();

    System.out.println("LoadStore: Volatile read prevents subsequent writes from floating up");
    LoadStoreExample ls = new LoadStoreExample();
    ls.process();
  }
}