package com.ntr.concurrent.usethreads.tproblems;

public class ReusableBarrier {


  private final int totalCount;
  private int arrived;
  private int phase = 0;
  private final Object lock = new Object();
  private boolean done;

  public ReusableBarrier(int count) {
    totalCount = count;
  }

  public void await() throws InterruptedException {
    synchronized (lock) {
      int myPhase = phase;
      arrived++;
      if (arrived == totalCount) {
        // on last arrival advance phase and release all
        arrived = 0;
        phase++;
        lock.notifyAll();
        return;
      }
      // wait for phase to advance
      while (phase == myPhase) {
        lock.wait();
      }
      // released
    }
  }
}
