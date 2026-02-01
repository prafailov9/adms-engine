package com.ntr.hrank.ds;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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

    // 1) Count frequency of each character
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      freq.put(c, freq.getOrDefault(c, 0) + 1);
    }

    // 2) Find first character with frequency 1
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (freq.get(c) == 1) {
        return c;
      }
    }

    return '\0';
  }

}
