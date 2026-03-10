package com.ntr.hrank.rec;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.ntr.hrank.rec.BackTrackingAlgorithms.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BackTrackingAlgorithmsTest {

    @Test
    public void subsetsTest() {
        List<List<Integer>> res = subsets(new int[]{1, 2});
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(List.of(1, 2));
        expected.add(List.of(1));
        expected.add(List.of(2));
        expected.add(Collections.emptyList());

        assertEquals(expected, res);
    }

    @Test
    public void permutationsTest() throws InterruptedException {
        List<List<Integer>> res = permutations(new int[]{1, 2, 3});

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(List.of(1, 2, 3));
        expected.add(List.of(1, 3, 2));
        expected.add(List.of(2, 1, 3));
        expected.add(List.of(2, 3, 1));
        expected.add(List.of(3, 1, 2));
        expected.add(List.of(3, 2, 1));

        assertEquals(expected.size(), res.size());
        assertTrue(res.containsAll(expected));
        int i = 1;
        while (i <= 10_000) {
            System.out.println(getRandom(1, 1000000));
            i++;
        }

    }


}