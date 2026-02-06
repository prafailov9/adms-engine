package com.ntr.hrank.ds;

import static com.ntr.hrank.ds.DSAlgorithms.isAnagram;
import static com.ntr.hrank.ds.DSAlgorithms.lengthOfLongestSubstring;
import static com.ntr.hrank.ds.DSAlgorithms.maxSumSubarray;
import static com.ntr.hrank.ds.DSAlgorithms.minSubarrayLen;
import static com.ntr.hrank.ds.DSAlgorithms.subarrayAverages;
import static com.ntr.hrank.ds.DSAlgorithms.targetSum;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DSAlgorithmsTest {

  @Test
  public void isAnagramTest() {
    boolean res;
    res = isAnagram("anagram", "nagaram");
    assertTrue(res);
  }

  @Test
  public void targetSumTest() {
    int[] res = targetSum(new int[]{2, 1, 1, 5, 7}, 9);
    assertArrayEquals(new int[]{0, 4}, res);
  }

  @Test
  public void lengthOfLongestSubstringTest() {
    int res;
    res = lengthOfLongestSubstring("abcabcbb");
    assertEquals(3, res);

    res = lengthOfLongestSubstring("abcaabqwertyu");
    assertEquals(9, res);

    res = lengthOfLongestSubstring("abcbc");
    assertEquals(3, res);
  }

  @Test
  public void maxSumSubarrayTest() {
    int res;
    res = maxSumSubarray(new int[]{2, 1, 5, 1, 3, 2}, 3);
    assertEquals(9, res);

    res = maxSumSubarray(
        new int[]{2, 1, 5, 1, 3, 2, 2, 5, 4, 3, 1, 2, 72, 5, 12, 23, 1, 3}, 2);
    assertEquals(77, res);

    res = maxSumSubarray(
        new int[]{2, 1, 5, 1, 3}, 5);
    assertEquals(12, res);
  }

  @Test
  public void subarrayAveragesTest() {
    double[] res;
    res = subarrayAverages(new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2}, 5);
    assertArrayEquals(new double[]{2.2, 2.8, 2.4, 3.6, 2.8}, res);
  }

  @Test
  public void minSubarrayLenTest() {
    int res;
    res = minSubarrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
    assertEquals(2, res);

    res = minSubarrayLen(7, new int[]{2, 3, 1, 2, 4, 3, 2, 5, 50, 1, 2, 3, 3, 1});
    assertEquals(1, res);
  }

}