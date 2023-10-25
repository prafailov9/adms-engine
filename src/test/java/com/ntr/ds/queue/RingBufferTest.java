package com.ntr.ds.queue;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class RingBufferTest {

    private Queue<Integer> buffer = new RingBuffer<>(5);

    @Test
    public void addTest() {
        for (int i = 1; i <= 5; i++) {
            buffer.offer(i);
        }
        Assertions.assertEquals(buffer.size(), 5);
    }

    @Test
    public void addMoreThanCapacityTest() {
        for (int i = 1; i <= 10; i++) {
            buffer.offer(i);
        }
        Assertions.assertEquals(buffer.size(), 5);
    }

    @Test
    public void removeTest() {
        for (int i = 1; i <= 5; i++) {
            buffer.offer(i);
        }

        for (int i = 1; i <= 5; i++) {
            buffer.poll();
        }

        Assertions.assertEquals(buffer.size(), 0);
    }


}