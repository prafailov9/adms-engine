package com.ntr.concurrent.usethreads.tproblems;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinV2 {

  private int cycle;
  private int turn;
  private final int N; // total threads
  private final int K; // total cycles
  private final Object lock = new Object();

  private final List<Runner> runners = new ArrayList<>();

  RoundRobinV2(int N, int K) {
    this.N = N;
    this.K = K;
    cycle = 1;
    for (int i = 0; i < N; i++) {
      runners.add(new Runner(i + 1));
    }
  }

  void start() {
    for (var r : runners) {
      r.start();
    }
  }

  void stop() throws InterruptedException {
    for (var r : runners) {
      r.awaitTermination();
    }
  }

  private class Runner implements Runnable {

    private final int runnerId;
    private final Thread thread;

    Runner(int runnerId) {
      this.runnerId = runnerId;
      thread = new Thread(this, "runner-" + runnerId);
    }

    void start() {
      thread.start();
    }

    void awaitTermination() throws InterruptedException {
      thread.join();
    }

    private boolean maxCycles() {
      return cycle > K;
    }

    @Override
    public void run() {
      synchronized (lock) {
        while (!maxCycles()) {
          while (turn != runnerId - 1 && !maxCycles()) {
            try {
              lock.wait();
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
              return;
            }
          }
          if (maxCycles()) {
            return;
          }
          // while its turn: count, reset if counter hits limit, notify
          System.out.printf("%s%n", runnerId);
          turn++;

          // reset wait queue if on last turn N
          if (turn == N) {
            System.out.printf("Cycle %s done. %n%n", cycle);
            turn = 0;
            cycle++;
          }

          // notify all others waiting
          lock.notifyAll();

        }
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {

    RoundRobinV2 obj = new RoundRobinV2(5, 10);
    obj.start();
    obj.stop();


  }

}
