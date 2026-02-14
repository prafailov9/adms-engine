package com.ntr.hrank.rec;

import java.util.*;

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
        subsetsDfs(arr, 0, new ArrayList<>(), all);
        return all;
    }


    static void subsetsDfs(int[] arr, int i, List<Integer> current, List<List<Integer>> all) {
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
        subsetsDfs(arr, i + 1, current, all);
        // no
        current.remove(v);
        subsetsDfs(arr, i + 1, current, all);
    }

    public static List<List<Integer>> permutations(int[] arr) {
        List<List<Integer>> perms = new ArrayList<>();

        // write all arr items to unused
        var unused = new ArrayList<>(Arrays.stream(arr).boxed().toList());
        permsDfs(new ArrayList<>(), unused, perms);
        return perms;
    }

    /**
     * All permutations that can be formed using the elements already in current,
     * plus any remaining unused elements.
     */
    private static void permsDfs(List<Integer> current, List<Integer> unused, List<List<Integer>> perms) {

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

            permsDfs(current, unused, perms);

            current.removeLast();
            unused.add(i, v);
        }

    }

  /**
   * You are given:
   * an array of distinct positive integers nums
   * a target integer target
   * Return all combinations where:
   * numbers can be reused unlimited times
   * sum of chosen numbers == target
   * Order inside a combination does not matter.
   * No duplicate combinations.
   */
  static List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> results = new ArrayList<>();
    dfs(nums, 0, target, new ArrayList<>(), results);
    return results;

  }

  // [1, 2, 3]
  static private void dfs(int[] nums, int i, int target, List<Integer> current,
      List<List<Integer>> results) {

  }
    public static List<List<Integer>> nQueens(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        queensDfs(n, 0, new ArrayList<>(), ans, new HashSet<>(), new HashSet<>(), new HashSet<>());
        return ans;
    }

    private static void queensDfs(int n,
                                  int i,
                                  List<Integer> place,
                                  List<List<Integer>> ans,
                                  Set<Integer> cols,
                                  Set<Integer> dg,
                                  Set<Integer> adg) {
        if (i == n) {
            ans.add(new ArrayList<>(place));
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!cols.contains(j) && !dg.contains(i + j) && !adg.contains(i - j)) {
                place.add(j);
                cols.add(j);
                dg.add(i + j);
                adg.add(i - j);

                queensDfs(n, i + 1, place, ans, cols, dg, adg);

                place.removeLast();
                cols.remove(j);
                dg.remove(i + j);
                adg.remove(i - j);
            }
        }
    }

    /**
     * counting approach
     */
    private static int queensDfs(int n, int i, Set<Integer> cols, Set<Integer> dg, Set<Integer> adg) {
        if (i == n) {
            return 1;
        }
        int count = 0;

        for (int j = 0; j < n; j++) {
            if (!cols.contains(j) && !dg.contains(i + j) && !adg.contains(i - j)) {
                cols.add(j);
                dg.add(i + j);
                adg.add(i - j);

                count += queensDfs(n, i + 1, cols, dg, adg);

                cols.remove(j);
                dg.remove(i + j);
                adg.remove(i - j);
            }
        }
        return count;
    }

    public static int bitsNQueens(int n) {
        return dfs(n, 0, 0, 0);
    }

    private static int dfs(int n, int cols, int diag, int anti) {
        // all columns filled → placed N queens
        if (cols == (1 << n) - 1) {
            return 1;
        }
        int count = 0;

        // positions available this row
        int available = ((1 << n) - 1) & ~(cols | diag | anti);
        while (available != 0) {
            // pick rightmost 1 bit
            int bit = available & -available;
            // remove that bit from available
            available -= bit;
            count += dfs(n, cols | bit, (diag | bit) << 1, (anti | bit) >> 1);
        }
        return count;
    }

    // in-place mod
    public static void solveSudoku(char[][] board) {

    }

    /**
     * [
     * ["5","3",".",".","7",".",".",".","."],
     * ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],
     * <p>
     * ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],
     * ["7",".",".",".","2",".",".",".","6"],
     * <p>
     * [".","6",".",".",".",".","2","8","."],
     * [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"]
     * ]
     */
    private static void sudokuDfs(char[][] board) {

    }
}
