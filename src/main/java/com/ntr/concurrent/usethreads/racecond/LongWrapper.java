package com.ntr.concurrent.usethreads.racecond;

public class LongWrapper {

  private final Object key = new Object();

  private long l;

  public LongWrapper(final long l) {
    this.l = l;
  }

  public long get() {
    return l;
  }

  public void increment() {
    // this code does 3 different operations:
    // 1. read l from memory
    // 2. add l to 1
    // 3. write new value of l to memory
    // when multiple threads are executing this code the data of one thread might change during calculation,
    // because another thread already changed it
    synchronized (key) {
      l = l + 1;
    }
  }

  public long incrementAndGet() {
    increment();
    return l;
  }

}
