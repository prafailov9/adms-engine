package com.ntr.hrank.rec;

import java.util.HashSet;
import java.util.Set;

public class SlidingWindowAlgorithms {

  public static int maxSubarraySum(int[] arr, int k) {
    if (arr.length == 0) {
      return 0;
    }
    int sum = 0;
    int left = 0;
    int max = arr[0];

    for (int right = 0; right < arr.length; right++) {
      sum += arr[right];
      if (k == right - left + 1) {
        if (max < sum) {
          max = sum;
        }
        sum -= arr[left];
        left++;
      }
    }
    return max;
  }

  static double[] averageSubarrays(int[] arr, int k) {
    if (arr.length < k) {
      return new double[0];
    }

    double sum = 0.0;
    int left = 0;
    double[] averages = new double[arr.length - k + 1];
    for (int right = 0; right < arr.length; right++) {
      sum += arr[right];

      if (k == right - left + 1) {
        averages[left] = sum / k;
        sum -= arr[left];
        left++;
      }
    }

    return averages;
  }

  static boolean containsNearbyDuplicate(int[] arr, int k) {
    if (arr.length == 0) {
      return false;
    }

    Set<Integer> set = new HashSet<>();
    int left = 0;

    for (int right = 0; right < arr.length; right++) {
      if (set.contains(arr[right])) {
        return true;
      }

      set.add(arr[right]);

      if (right - left + 1 > k) {
        set.remove(arr[left]);
        left++;
      }
    }

    return false;
  }

  // _____________________ VARIABLE WINDOWS _____________________


  /**
   * Longest Substring Without Repeating Characters
   * Return length of longest substring without duplicate chars.
   * Example:
   * "abcabcbb" → 3
   * Core idea:
   * Expand right
   * Shrink left when invalid
   */
  static int longestSubstringNoRepeat(String s) {
    if (s.length() <= 1) {
      return s.length();
    }
    int max = Integer.MIN_VALUE;
    int len = 0;
    Set<Character> set = new HashSet<>();
    int left = 0;
    for (int right = 0; right < s.length(); right++) {
      set.add(s.charAt(right));
      len++;

      while (set.contains(s.charAt(left))) {
        len--;
        left++;
      }

    }

    return max;

  }

}
