package com.ntr.concurrent.usethreads.racecond;

public class VisibilityOrderingMain {

  private volatile boolean running = true;  // try removing volatile!

  public static void main(String[] args) throws InterruptedException {
    // 1. Visibility
    var obj = new VisibilityOrderingMain();
    var t = new Thread(obj::visibilityWork, "worker");

    t.start();
    // gives enough time for t to save running into local cache which helps illustrate the problem.
    Thread.sleep(100);

    System.out.println("Main: requesting stop...");
    // Main thread updates the termination flag used by t
    obj.running = false;  // only WRITES the flag
    t.join();
    System.out.println("Main: worker has stopped");

    // 2. Ordering
  }

  private void visibilityWork() {
    int i = 0;
    // if running is not volatile, the JIT compiler may optimize the loop by hoisting
    // the read of `running` outside the loop — since it never changes within this thread,
    // the compiler sees it as an unnecessary repeated read and caches it in a register.
    // This causes the worker to loop forever, never seeing the update from main.

    // if running is volatile, the JIT is forbidden from hoisting the read.
    // Every iteration must re-read `running` from memory, ensuring the worker
    // sees writes made by other threads.
    while (running) {  // only READS the flag
      i++;
    }
    System.out.println("Worker stopped after " + i + " iterations");
  }
}
