package com.ntr.concurrent.usethreads.tproblems;

import java.util.concurrent.Semaphore;

public class RoundRobin {

  private int cycles;
  private int currentTurn;
  private boolean done = false;
  private final int N; // total threads
  private final int K; // total cycles

  private final Object lock;

  public RoundRobin(int n, int k) {
    N = n;
    K = k;
    cycles = 1;
    currentTurn = 0;
    lock = new Object();
  }


  void start() {
    Thread[] threads = new Thread[N];
    for (int i = 0; i < N; i++) {
      final int threadId = i;
      threads[i] = new Thread(() -> run(threadId), "" + i);
    }

    for (var t : threads) {
      t.start();
    }

    for (var t : threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    System.out.printf("From main thread: Round-Robin finished.%n");

  }


  private void run(int threadId) {
    synchronized (lock) {
      // start of cycle
      while (!done) {
        // wait to get its turn
        // any waking thread should see the current state of the cycle(done)
        while (currentTurn != threadId && !done) {
          try {
            lock.wait();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
          }
        }
        // another check before modifying shared state to see if the control flag was changed after waking up
        if (done) {
          return;
        }

        // while its turn: count, reset if counter hits limit, notify
        System.out.printf("%s%n", currentTurn);
        currentTurn++;

        // reset wait queue if on last turn N
        if (currentTurn == N) {
          System.out.printf("Cycle %s done. %n%n", cycles);
          currentTurn = 0;
          cycles++;
          // signal to exit control loop on last cycle
          if (cycles > K) {
            done = true;
          }
        }

        // notify all others waiting
        lock.notifyAll();
      }
    }
  }


  private void runV2(int myTurn) {
    synchronized (lock) {
      // start of cycle
      while (!done) {
        // while its turn: count, reset if counter hits limit, notify
        if (currentTurn == myTurn) {
          System.out.printf("%s%n", currentTurn);
          currentTurn++;

          // notify all waiting threads that the lock will be released
          lock.notifyAll();

          // reset wait queue if on turn N
          if (currentTurn == N) {
            System.out.printf("Cycle %s done. %n%n", cycles);
            // break on last cycle and notify all others waiting
            if (cycles >= K) {
              done = true;
              lock.notifyAll();
              break;
            } else {
              cycles++;
              currentTurn = 0;
            }
          }
        }
        // wait to get its turn.
        // any thread that wakes up should see the current state of the cycle.
        while (currentTurn != myTurn && !done) {
          try {
            lock.wait();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
          }
        }
      }
    }
  }

  public static void main(String[] args) {

    RoundRobin roundRobin = new RoundRobin(10, 10000);
    long startDef = System.nanoTime();
    roundRobin.start();
    long endDef = System.nanoTime();

//    RoundRobinFast fast = new RoundRobinFast(10, 50000);
//    long startF = System.nanoTime();
//    fast.start();
//    long endF = System.nanoTime();

    System.out.printf("Default Calculation took %.3f seconds%n",
        (endDef - startDef) / 1_000_000_000.0);
//    System.out.printf("Fast Calculation took %.3f seconds%n", (endF - startF) / 1_000_000_000.0);
  }

  private static final class RoundRobinFast {

    private final int N, K;
    private final Semaphore[] sems;
    private volatile boolean done;

    public RoundRobinFast(int n, int k) {
      this.N = n;
      this.K = k;
      this.sems = new Semaphore[N];
      for (int i = 0; i < N; i++) {
        sems[i] = new Semaphore(0);
      }
      sems[0].release(); // thread 0 starts
    }

    void start() {
      Thread[] threads = new Thread[N];
      for (int i = 0; i < N; i++) {
        final int id = i;
        threads[i] = new Thread(() -> run(id), "T-" + i);
        threads[i].start();
      }
      for (var t : threads) {
        try {
          t.join();
        } catch (InterruptedException ignored) {
        }
      }
      System.out.println("From main thread: Round-Robin finished.");
    }

    private void run(int id) {
      // optional: buffer output to avoid println cost
      StringBuilder sb = new StringBuilder(1024);

      for (int cycle = 1; cycle <= K && !done; cycle++) {
        // each thread participates once per cycle
        sems[id].acquireUninterruptibly();

        // do work
        sb.append(id).append('\n');

        // last thread prints cycle marker (optional)
        if (id == N - 1) {
          sb.append("Cycle ").append(cycle).append(" done.\n\n");
        }

        // pass baton
        int next = (id + 1) % N;
        sems[next].release();
      }

      // make sure everyone can exit cleanly after K cycles
      if (id == N - 1) {
        done = true;
        // release the whole ring once so no one is stuck
        for (int i = 0; i < N; i++) {
          sems[i].release();
        }
      }

      // emit buffered output once (MUCH faster than println per step)
      System.out.print(sb);
    }
  }

}
