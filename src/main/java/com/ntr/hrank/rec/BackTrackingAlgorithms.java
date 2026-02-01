package com.ntr.hrank.rec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BackTrackingAlgorithms {

  /**
   * Generate All Subsets
   * You are given an integer array nums containing unique elements.
   * Return all possible subsets (the power set) of nums.
   * The solution set must not contain duplicate subsets.
   * You may return the subsets in any order.
   */
  static List<List<Integer>> subsets(int[] arr) {
    List<List<Integer>> all = new ArrayList<>();
    writeSubsets(arr, 0, new ArrayList<>(), all);
    return all;
  }


  static void writeSubsets(int[] arr, int i, List<Integer> current, List<List<Integer>> all) {
    if (i == arr.length) {
      // write end branch
      all.add(new ArrayList<>(current));
      return;
    }
    // write into and Integer as compiler gets confused by different the add()
    // methods at runtime(add(Integer value), add(int index))
    // and throws IndexOOB
    Integer v = arr[i];
    // yes
    current.add(v);
    writeSubsets(arr, i + 1, current, all);
    // no
    current.remove(v);
    writeSubsets(arr, i + 1, current, all);
  }

  public static List<List<Integer>> permutations(int[] arr) {
    List<List<Integer>> perms = new ArrayList<>();

    // write all arr items to unused
    var unused = new ArrayList<>(Arrays.stream(arr).boxed().toList());
    writePermutations(new ArrayList<>(), unused, perms);
    return perms;
  }

  /**
   * All permutations that can be formed using the elements already in current,
   * plus any remaining unused elements.
   */
  private static void writePermutations(List<Integer> current,
      List<Integer> unused,
      List<List<Integer>> perms) {

    if (unused.isEmpty()) {
      perms.add(new ArrayList<>(current));
      return;
    }

    //choose val
    //make val unavailable
    //recurse
    //undo both changes
    for (int i = 0; i < unused.size(); i++) {
      Integer v = unused.remove(i);
      current.add(v);

      writePermutations(current, unused, perms);

      current.removeLast();
      unused.add(i, v);
    }

  }

}
