package com.ntr.hrank.rec;

import static com.ntr.hrank.rec.RecursionAlgorithms.arraySum;
import static com.ntr.hrank.rec.RecursionAlgorithms.binarySearch;
import static com.ntr.hrank.rec.RecursionAlgorithms.countDigits;
import static com.ntr.hrank.rec.RecursionAlgorithms.countOccurrences;
import static com.ntr.hrank.rec.RecursionAlgorithms.factorial;
import static com.ntr.hrank.rec.RecursionAlgorithms.findMax;
import static com.ntr.hrank.rec.RecursionAlgorithms.findMin;
import static com.ntr.hrank.rec.RecursionAlgorithms.firstIndex;
import static com.ntr.hrank.rec.RecursionAlgorithms.isPalindrome;
import static com.ntr.hrank.rec.RecursionAlgorithms.isSorted;
import static com.ntr.hrank.rec.RecursionAlgorithms.mergeSort;
import static com.ntr.hrank.rec.RecursionAlgorithms.power;
import static com.ntr.hrank.rec.RecursionAlgorithms.printNumbers;
import static com.ntr.hrank.rec.RecursionAlgorithms.reverse;
import static com.ntr.hrank.rec.RecursionAlgorithms.sumDigits;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class RecursionAlgorithmsTest {

  @Test
  public void factorialTest() {
    int res = factorial(3);
    assertEquals(6, res);
    System.out.println(res);

    res = factorial(1);
    assertEquals(1, res);
    System.out.println(res);

    res = factorial(2);
    assertEquals(2, res);
    System.out.println(res);

    res = factorial(10);
    assertEquals(3628800, res);
    System.out.println(res);

    res = factorial(12);
    assertEquals(479001600, res);
    System.out.println(res);
  }

  @Test
  public void arraySumTest() {
    int res = arraySum(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 0);
    assertEquals(36, res);
    System.out.println("res = " + res);

    res = arraySum(new int[]{1, 2, 2}, 0);
    assertEquals(5, res);
    System.out.println("res = " + res);

    res = arraySum(new int[]{1, 2}, 0);
    assertEquals(3, res);
    System.out.println("res = " + res);

  }

  @Test
  public void reverseStrTest() {
    String res = new String(reverse(new char[]{'a', 'b', 'c'}, 0, 2));
    assertEquals("cba", res);
    System.out.println(res);

    res = new String(reverse(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j'}, 0, 8));
    assertEquals("jhgfedcba", res);
    System.out.println(res);

    res = new String(reverse(new char[]{'p', 'r', 'i', 'n', 't', 'e', 'r'}, 0, 6));
    assertEquals("retnirp", res);
    System.out.println(res);
  }

  @Test
  public void findMaxTest() {
    long res = findMax(new long[]{1, 3, 2, -1, 2}, 0);
    assertEquals(3, res);
    System.out.println(res);

    res = findMax(new long[]{1, 3, 1382983, -1, 2}, 0);
    assertEquals(1382983, res);
    System.out.println(res);

    res = findMax(new long[]{123123, 3}, 0);
    assertEquals(123123, res);
    System.out.println(res);

  }

  @Test
  public void printNumbersTest() {
    printNumbers(new int[]{1, 2, 3}, 0);
  }

  @Test
  public void countOccurrencesTest() {
    int res = countOccurrences(new int[]{1, 2, 3, 2, 2}, 0, 2);
    assertEquals(3, res);
    System.out.println(res);

    res = countOccurrences(new int[]{}, 0, 2);
    assertEquals(0, res);
    System.out.println(res);

    res = countOccurrences(new int[]{1, 2, 3, 4, -1}, 0, 20);
    assertEquals(0, res);
    System.out.println(res);

    res = countOccurrences(new int[]{1, 77, 2, 3, 4, 77, 5, 6, 77, 7, 8, 77, 77}, 0, 77);
    assertEquals(5, res);
    System.out.println(res);

    res = countOccurrences(new int[]{1, 3, 1}, 0, 3);
    assertEquals(1, res);
    System.out.println(res);
  }

  @Test
  public void isSortedTest() {
    assertTrue(isSorted(new int[]{1, 2, 2, 7}, 1));
    assertTrue(isSorted(new int[]{0}, 1));
    assertFalse(isSorted(new int[]{1, 7, 2}, 1));
    assertTrue(isSorted(new int[]{}, 1));
    assertFalse(isSorted(new int[]{-9, -7, 5, -3, 1}, 1));
  }

  @Test
  public void firstIndexTest() {
    int res = firstIndex(new int[]{5, 1, 2, 1}, 0, 1);
    assertEquals(1, res);
    res = firstIndex(new int[]{5, 1, 2, 1}, 0, 5);
    assertEquals(0, res);
    res = firstIndex(new int[]{0}, 0, 1);
    assertEquals(-1, res);

  }

  @Test
  public void countDigitsTest() {
    int res = countDigits(13579);
    assertEquals(5, res);

    res = countDigits(0);
    assertEquals(0, res);

    res = countDigits(-1231723012);
    assertEquals(10, res);
  }

  @Test
  public void powerTest() {
    long res = power(2, 3);
    assertEquals(8, res);
  }

  @Test
  public void sumDigitsTest() {
    int res = sumDigits(12);
    assertEquals(3, res);

    res = sumDigits(12345);
    assertEquals(15, res);

    res = sumDigits(3333333);
    assertEquals(21, res);

    int n = 12345;
    // formula to get a digit from N at specified position, where the power exponent:
    //k = 0 → units
    //k = 1 → tens
    //k = 2 → hundreds
    // so to get the third digit from n = 12345: 10^2
    long thirdDigit = (n / power(10, 2)) % 10;
    System.out.println(thirdDigit);
  }

  @Test
  public void isPalindromeTest() {
    boolean res = isPalindrome("racecar", 0, 6);
    assertTrue(res);

    res = isPalindrome("abca", 0, 3);
    assertFalse(res);
  }

  @Test
  public void findMinTest() {
    long res = findMin(new long[]{1, 3, 2, -1, 2}, 0);
    assertEquals(-1, res);
    System.out.println(res);

    res = findMin(new long[]{1, 3, 1382983, -1, 2}, 0);
    assertEquals(-1, res);
    System.out.println(res);

    res = findMin(new long[]{123123, 3}, 0);
    assertEquals(3, res);
    System.out.println(res);

  }

  @Test
  public void reverseBuildNewStringTest() {
    String res;
    res = reverse("emily");
    assertEquals("ylime", res);

    res = reverse("banana");
    assertEquals("ananab", res);
  }

  @Test
  public void binarySearchTest() {
//    assertEquals(-1, binarySearch(new int[]{}, 5));
//    assertEquals(0, binarySearch(new int[]{7}, 7));
//    assertEquals(-1, binarySearch(new int[]{7}, 3));
//    assertEquals(3, binarySearch(new int[]{1, 3, 5, 7, 9, 11}, 7));
//    assertEquals(2, binarySearch(new int[]{5, 10, 25, 50, 81, 92, 99}, 25));

    List<Integer> list = new ArrayList<>();
    for (int i = 1; i <= 290_000_000; i++) {
      list.add(i);
    }
    long start = System.nanoTime();
    assertEquals(100_111_110,
        binarySearch(list.stream().mapToInt(Integer::intValue).toArray(), 100_111_111));
    long end = System.nanoTime();
    System.out.println((end - start) / 1_000_000_000.0);
  }

  @Test
  public void mergeSortTest() {
    int[] res = mergeSort(new int[]{1, 5, -2, 3, 12, -3, 2, 9, 11, 7});
    assertArrayEquals(new int[]{-3, -2, 1, 2, 3, 5, 7, 9, 11, 12}, res);

    res = mergeSort(new int[]{5, 2, 4, 1, 3});
    assertArrayEquals(new int[]{1, 2, 3, 4, 5}, res);

    res = mergeSort(new int[]{1});
    assertArrayEquals(new int[]{1}, res);

    res = mergeSort(new int[]{});
    assertArrayEquals(new int[]{}, res);

    res = mergeSort(new int[]{9, 7, 5, 3, 1});
    assertArrayEquals(new int[]{1, 3, 5, 7, 9}, res);
  }

}