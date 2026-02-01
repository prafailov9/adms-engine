package com.ntr.concurrent.usethreads.tproblems;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoundedBlockingQueueTest {

  private boolean producerDone = false;
  private boolean consumerDone = false;
  private final int cycles = 10;

  @BeforeEach
  public void setUp() {

  }

  @AfterEach
  public void tearDown() {
    producerDone = false;
    consumerDone = false;
  }

  @Test
  public void singleProducerSingleConsumerTest() {
    int max = 2;
    BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(max);

    Thread p = new Thread(() -> {
      int cyclesDone = 0;
      while (!producerDone) {
        if (cyclesDone == cycles) {
          producerDone = true;
          return;
        }
        queue.put(++cyclesDone);
      }
    });

    Thread c = new Thread(() -> {
      int cyclesDone = 0;
      while (!consumerDone) {
        if (cyclesDone == cycles) {
          consumerDone = true;
          return;
        }
        queue.take();
        cyclesDone++;

      }
    });

    p.start();
    c.start();

    try {
      p.join();
      c.join();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

  }

  @Test
  public void delayConsumer_ensureProducerBlocksDuringDelay() {
    // 1 cap buffer. producer should block after done and unblock when consumer starts takin
    int max = 1;
    BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(max);

    Thread p = new Thread(() -> {
      int cyclesDone = 0;
      while (!producerDone) {
        if (cyclesDone == cycles) {
          producerDone = true;
          return;
        }
        long t0 = System.nanoTime();
        queue.put(++cyclesDone);
        long ms = (System.nanoTime() - t0) / 1_000_000;
        System.out.printf("put %s blocked ~%d ms%n", cyclesDone, ms);
      }
    });

    Thread c = new Thread(() -> {
      int cyclesDone = 0;
      // simulate working consumer
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      while (!consumerDone) {
        if (cyclesDone == cycles) {
          consumerDone = true;
          return;
        }
        queue.take();
        cyclesDone++;

      }
    });

    p.start();
    c.start();

    try {
      p.join();
      c.join();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  //Test 3 (Underflow): cap=3; start consumer first, delay producer. Ensure consumer blocks until first put.
  @Test
  public void consumerFirst_delayProducer_ensureConsumerBlocked() {
    int max = 3;
    BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(max);

    int totalCycles = 12;

    Thread p = new Thread(() -> {
      int cyclesDone = 0;
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      while (!producerDone) {
        if (cyclesDone == cycles) {
          producerDone = true;
          return;
        }
        queue.put(++cyclesDone);
      }
    });

    Thread c = new Thread(() -> {
      int cyclesDone = 0;
      // simulate working consumer

      while (!consumerDone) {
        if (cyclesDone == cycles) {
          consumerDone = true;
          return;
        }
        long t0 = System.nanoTime();
        queue.take();
        cyclesDone++;
        long ms = (System.nanoTime() - t0) / 1_000_000;
        System.out.printf("take %s blocked ~%d ms%n", cyclesDone, ms);
      }
    });

    p.start();
    c.start();

    try {
      p.join();
      c.join();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  @Test
  public void twoProducers_twoConsumers_contention() {
    int max = 1;
    BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(max);

    Thread p1 = new Thread(() -> {
      int cyclesDone = 0;
      while (!producerDone) {
        if (cyclesDone == cycles) {
          producerDone = true;
          return;
        }
        queue.put(++cyclesDone);
      }
    });

    Thread p2 = new Thread(() -> {
      int cyclesDone = 0;
      while (!producerDone) {
        if (cyclesDone == cycles) {
          producerDone = true;
          return;
        }
        queue.put(++cyclesDone + 100);
      }
    });

    Thread c1 = new Thread(() -> {
      int cyclesDone = 0;
      // simulate working consumer

      while (!consumerDone) {
        if (cyclesDone == cycles) {
          consumerDone = true;
          return;
        }
        queue.take();
        cyclesDone++;

      }
    });

    Thread c2 = new Thread(() -> {
      int cyclesDone = 0;
      // simulate working consumer

      while (!consumerDone) {
        if (cyclesDone == cycles) {
          consumerDone = true;
          return;
        }
        queue.take();
        cyclesDone++;

      }
    });

    p1.start();
    p2.start();
    c1.start();
    c2.start();

    try {
      p1.join();
      p2.join();
      c1.join();
      c2.join();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  @Test
  public void twoProducers_twoConsumers_contention_poisonPills() {
    final int CAP = 1;
    final int CONSUMERS = 2;
    final int[] P1 = {1, 2, 3, 4, 5, 6};
    final int[] P2 = {101, 102, 103, 104, 105, 106};
    final Integer POISON = Integer.MIN_VALUE; // sentinel; we never produce this as a real value

    BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(CAP);
    List<Integer> consumed = Collections.synchronizedList(new ArrayList<>());

    Thread p1 = new Thread(() -> {
      for (int v : P1) {
        queue.put(v);
      }
    }, "p1");

    Thread p2 = new Thread(() -> {
      for (int v : P2) {
        queue.put(v);
      }
    }, "p2");

    Runnable consumerTask = () -> {
      while (true) {
        Integer v = queue.take();
        if (v.equals(POISON)) {
          break;      // graceful shutdown
        }
        consumed.add(v);                   // record real items only
      }
    };

    Thread c1 = new Thread(consumerTask, "c1");
    Thread c2 = new Thread(consumerTask, "c2");

    // start all
    p1.start();
    p2.start();
    c1.start();
    c2.start();

    // wait for both producers to finish real items
    try {
      p1.join();
      p2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    // enqueue exactly one poison per consumer so nobody is stranded
    for (int i = 0; i < CONSUMERS; i++) {
      queue.put(POISON);
    }

    // join consumers
    try {
      c1.join();
      c2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    // === Assertions / checks ===
    assertEquals(0, queue.size());

    // multiset check equals P1 ∪ P2 (no loss/dups)
    List<Integer> expected = new ArrayList<>();
    for (int v : P1) {
      expected.add(v);
    }
    for (int v : P2) {
      expected.add(v);
    }

    expected.sort(Integer::compareTo);
    List<Integer> actual = new ArrayList<>(consumed);
    actual.sort(Integer::compareTo);

    assertEquals(expected, actual); // same multiset of values
  }


}