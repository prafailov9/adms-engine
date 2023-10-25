package com.ntr.ds.queue;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;

public class QueueImplTest {

    private Queue<Integer> queue = new QueueImpl<>();


    @Test
    public void addTest() {
        for (int i = 1; i <= 3; i++) {
            queue.offer(i);
        }
        Assertions.assertEquals(queue.size(), 3);
    }

    @Test
    public void removeTest() {
        for (int i = 1; i <= 5; i++) {
            queue.offer(i);
        }

        for (int i = 1; i <= 5; i++) {
            queue.poll();
        }

        Assertions.assertEquals(queue.size(), 0);
    }

    @Test
    public void iterateTest() {
        // give me the 3rd element
        for (int i = 1; i <= 5; i++) {
            queue.offer(i);
        }
        Iterator<Integer> it = queue.iterator();
        int expected = 0;
        for (Integer i: queue) {
            if (i == 3) expected = i;
        }

        Assertions.assertEquals(expected ,3);
    }

}