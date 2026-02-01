package com.ntr.hrank.rec;

import static com.ntr.hrank.rec.BackTrackingAlgorithms.permutations;
import static com.ntr.hrank.rec.BackTrackingAlgorithms.subsets;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

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
  public void permutationsTest() {
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
  }


}