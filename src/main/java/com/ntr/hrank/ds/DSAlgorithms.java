package com.ntr.hrank.ds;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DSAlgorithms {

  /**
   * Given an integer array nums, return true if
   * any value appears at least twice, otherwise return false.
   */
  public static boolean containsDuplicate(int[] nums) {

    var s = new HashSet<>();
    for (int num : nums) {
      if (s.contains(num)) {
        return false;
      }
      s.add(num);
    }
    return true;
  }

  /**
   * Given a string s, return the first character that does not repeat.
   * If none exists, return '\0'.
   */
  public static char firstUniqueChar(String s) {
    Map<Character, Integer> freq = new HashMap<>();

    // count frequency of each character
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      freq.put(c, freq.getOrDefault(c, 0) + 1);
    }

    // find first character with frequency 1
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (freq.get(c) == 1) {
        return c;
      }
    }

    return '\0';
  }

  /**
   * s = "anagram"
   * t = "nagaram"
   */
  public static boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    Map<Character, Integer> freq = new HashMap<>();
    int n = s.length();
    // for every char in "s" increment count at s[i]
    // for every char in "t" decrement count at t[i]
    for (int i = 0; i < n; i++) {
      freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);
      freq.put(t.charAt(i), freq.getOrDefault(t.charAt(i), 0) - 1);
    }

    // if anagram, map should be filled with 0s
    for (var v : freq.values()) {
      if (v != 0) {
        return false;
      }
    }

    return true;
  }

  /**
   * [2, 7, 1, 1, 5], target = 9
   * assume only one solution -> return first found
   */
  public static int[] targetSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      if (map.containsKey(target - nums[i])) {
        return new int[]{map.get(target - nums[i]), i};
      }
      map.put(nums[i], i);
    }

    return new int[]{};
  }

  /*** _____________________ SLIDING WINDOW PRACTICE
   /**
   * str = "abcabcbb" -> 3
   * longest substrings are: abc[0, 2], abc[3, 5]. this alg takes the last one
   */
  public static int lengthOfLongestSubstring(String str) {
    Set<Character> set = new HashSet<>();
    int max = 0;
    // all chars inside the window must be unique
    int left = 0;
    int right = 0;

    // extend window by adding a unique char into the set
    // on a duplicate -> flush all before the duplicate, including.
    // then restart the process until end of string.
    while (right < str.length()) {
      if (!set.contains(str.charAt(right))) {
        set.add(str.charAt(right));
        right++;
        max = Math.max(right - left, max);
      } else {
        while (set.contains(str.charAt(right))) {
          set.remove(str.charAt(left));
          left++;
        }
      }
    }
    return max;
  }

  /**
   * Given an integer array nums and an integer k, return the maximum sum of any contiguous subarray
   * of size k.
   */
  public static int maxSumSubarray(int[] nums, int k) {
    if (k > nums.length) {
      return 0;
    }
    int left = 0;
    int sum = 0;
    int max = nums[0];
    for (int right = 0; right < nums.length; right++) {

      sum += nums[right];
      if (right - left + 1 == k) {
        if (max < sum) {
          max = sum;
        }
        sum -= nums[left];
        left++;
      }
    }
    return max;
  }


  /**
   * Return an array of averages of all contiguous subarrays of size k.
   */
  public static double[] subarrayAverages(int[] nums, int k) {
    double[] avg = new double[(nums.length - k) + 1];
    int left = 0;
    double sum = 0.0;

    for (int right = 0; right < nums.length; right++) {
      sum += nums[right];

      if (right - left + 1 == k) {
        avg[left] = sum / k;
        sum -= nums[left];
        left++;
      }
    }
    return avg;
  }

  /**
   * Smallest Subarray with Sum ≥ Target
   * Return the length of the smallest contiguous subarray whose sum is ≥ target.
   * Return 0 if no such subarray exists.
   * Input: target = 7, nums = [2,3,1,2,4,3]
   * Output: 2   // [4,3]
   * <p>
   * <p>
   * relies on all numbers being positive
   */
  public static int minSubarrayLen(int target, int[] arr) {
    if (arr.length == 0) {
      return 0;
    }
    // min subarray len
    int min = Integer.MAX_VALUE;
    int sum = 0;
    int left = 0;

    for (int right = 0; right < arr.length; right++) {
      sum += arr[right];
      while (sum >= target) {
        min = Math.min(min, right - left + 1);
        // exclude invalid item from sum, move window
        sum -= arr[left];
        left++;
      }
    }

    return min == Integer.MAX_VALUE ? 0 : min;

  }

  /**
   * Given strings s and t, return the smallest substring of s that contains all characters of t.
   * Input: s = "ADOBECODEBANC", t = "ABC"
   * Output: "BANC"
   */
  public static String minWindow(String s, String t) {
    //Two frequency maps
    //Track how many characters are “fully satisfied”
    //Shrink aggressively once valid

    return "";
  }

  /**
   * Given a binary array, return the length of the longest subarray containing only 1s after
   * replacing at most k zeros.
   * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
   * Output: 6
   */
  public static int longestOnes(int[] nums, int k) {
    //Count zeros
    //Shrink when zeros > k
    return 1;
  }

  /**
   * I: ["eat","tea","tan","ate","nat","bat"]
   * O: [
   * ["eat","tea","ate"],
   * ["tan","nat"],
   * ["bat"]
   * ]
   */
  public static List<List<String>> groupAnagrams(String[] strs) {
    return null;
  }

  /**
   * I: nums1 = [1,2,2,1]
   * nums2 = [2,2]
   * O: [2]
   */
  public static int[] intersection(int[] nums1, int[] nums2) {
    return new int[]{};
  }


}
