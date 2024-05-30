package com.ntr.hrank;

import com.ntr.cache.DoublyLinkedList;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import static com.ntr.hrank.Algorithms.*;
import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

    // Top node
    private SinglyLinkedListNode n1;
    private SinglyLinkedListNode n2;
    private SinglyLinkedListNode n3;
    private SinglyLinkedListNode n4;

    @BeforeEach
    public void setup() {
        n1 = new SinglyLinkedListNode(1);
        n2 = new SinglyLinkedListNode(2);
        n3 = new SinglyLinkedListNode(3);
        n4 = new SinglyLinkedListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
    }

    @Test
    public void addNodeAtPosTest() {

        SinglyLinkedListNode result = insertNodeAtPosition(n1, 4, 2);
    }

    @Test
    public void deleteNodeAtPosTest() {
    }

    @Test
    public void reverseTest() {
        SinglyLinkedListNode n1 = new SinglyLinkedListNode(1);
        SinglyLinkedListNode n2 = new SinglyLinkedListNode(2);
        SinglyLinkedListNode n3 = new SinglyLinkedListNode(3);
        SinglyLinkedListNode n4 = new SinglyLinkedListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        SinglyLinkedListNode result = reverse(n1);
    }

    @Test
    public void threeXTest() {
        // random number from 1 to 10
        int initialNumber = 598271;
        int x = initialNumber;
        int count = 0;
        while (x != 1) {
            ++count;
            if (x % 2 == 0) {
                System.out.printf("Iteration %s: %s / 2", count, x);
                x /= 2;
            } else {
                System.out.printf("Iteration %s: %s * 3 + 1", count, x);
                x = x * 3 + 1;
            }
            System.out.printf(" = %s\n", x);
        }

        System.out.printf("Initial number: %s, Iterations: %s", initialNumber, count);

    }

    @Test
    public void addNodeAtTailTest() {
        SinglyLinkedListNode temp = n1;
        int data = 5;

        while (temp.next != null) temp = temp.next;
        temp.next = new SinglyLinkedListNode(data);

        int answer = 1;
        // test n1
        while (n1.next != null) {
            Assertions.assertNotNull(n1);
            Assertions.assertEquals(answer, n1.data);
            n1 = n1.next;
            answer++;
        }
    }

    @Test
    public void addNodeAtStartTest() {
        SinglyLinkedListNode newStart = new SinglyLinkedListNode(5);
        newStart.next = n1;
    }

    @Test
    public void mergeListsTest() {
        SinglyLinkedListNode h1 = new SinglyLinkedListNode(1);
        SinglyLinkedListNode h2 = new SinglyLinkedListNode(3);
        SinglyLinkedListNode h3 = new SinglyLinkedListNode(7);
        h1.next = h2;
        h2.next = h3;

        SinglyLinkedListNode l1 = new SinglyLinkedListNode(1);
        SinglyLinkedListNode l2 = new SinglyLinkedListNode(2);
        l1.next = l2;

        SinglyLinkedListNode mergedLists = mergeListsWithArray(h1, l1);
    }

    @Test
    public void rotateArrayTest() {
        List<Integer> rotatedStep2 = rotateLeft(2, Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expectedStep2 = Arrays.asList(3, 4, 5, 1, 2);
        Assertions.assertNotNull(rotatedStep2);
        Assertions.assertEquals(rotatedStep2, expectedStep2);

        List<Integer> rotatedStep3 = rotateLeft(3, Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expectedStep3 = Arrays.asList(4, 5, 1, 2, 3);
        Assertions.assertNotNull(rotatedStep3);
        Assertions.assertEquals(rotatedStep3, expectedStep3);

        List<Integer> rotatedStep4 = rotateLeft(4, Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expectedStep4 = Arrays.asList(5, 1, 2, 3, 4);
        Assertions.assertNotNull(rotatedStep4);
        Assertions.assertEquals(rotatedStep4, expectedStep4);
    }

    @Test
    public void hasCycleTest() {
        boolean actual = hasCycle(n1);

        Assertions.assertFalse(actual);
    }

    @Test
    public void findMergePointTest() {
        SinglyLinkedListNode h1 = new SinglyLinkedListNode(10);
        SinglyLinkedListNode h2 = new SinglyLinkedListNode(20);

        h1.next = h2;
        h2.next = n1.next.next; // n3

        int actual = findMergeNode(n1, h1);

        Assertions.assertEquals(3, actual);
    }

    @Test
    public void removeDuplicatesTest() {
        SinglyLinkedListNode dup = new SinglyLinkedListNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = dup;
        dup.next = n4;
        SinglyLinkedListNode shit = removeDuplicates(n1);
    }

    @Test
    public void dynamicArrayTest() {
        List<List<Integer>> queries = new ArrayList<>();
        queries.add(Arrays.asList(1, 0, 5));
        queries.add(Arrays.asList(1, 1, 7));
        queries.add(Arrays.asList(1, 0, 3));
        queries.add(Arrays.asList(2, 1, 0));
        queries.add(Arrays.asList(2, 1, 1));

        List<Integer> result = dynamicArray(2, queries);
        Assertions.assertNotNull(result);
    }

    @Test
    public void quicksortTest() {
        int[] array = new int[]{1, 5, 3, 8, 6, 11, 2};

        // index range for the sorting algorithm to work upon
        int low = 0;
        int high = array.length - 1;

        Algorithms.quicksort(array, low, high);

        for (int j : array) {
            System.out.println(j);
        }
    }

    /**
     * maxSpan([1, 2, 1, 1, 3]) → 4
     * maxSpan([1, 4, 2, 1, 4, 1, 4]) → 6
     * maxSpan([1, 4, 2, 1, 4, 4, 4]) → 6
     * maxSpan([1, 1, 1, 2, 1, 4, 1, 5, 6, 4, 6, 6, 9, 3, 9, 5, 3]) → 9
     */
    @Test
    public void maxSpanTest() {
        int actual1 = maxSpan(new int[]{1, 2, 1, 1, 3});
        assertEquals(4, actual1);
        int actual2 = maxSpan(new int[]{1, 4, 2, 1, 4, 1, 4});
        assertEquals(6, actual2);
        int actual3 = maxSpan(new int[]{1, 4, 2, 1, 4, 4, 4});
        assertEquals(6, actual3);
        int actual4 = maxSpan(new int[]{1, 1, 1, 2, 1, 4, 1, 5, 6, 4, 6, 5, 9, 3, 9, 5, 3});
        assertEquals(9, actual4);
    }


    @Test
    public void linearInTest() {
        boolean actual = linearIn(new int[]{1, 2, 4, 6}, new int[]{2, 4});
        Assertions.assertFalse(actual);
    }

    @Test
    public void squareUpTest() {
        int[] arr = squareUp(5);
    }

    @Test
    public void mirrorTest() {
        int x = maxMirror(new int[]{1, 2, 3, 8, 9, 3, 2, 1});
        assertEquals(3, x);
    }

    @Test
    public void clumpsTest() {
        int s = countClumps(new int[]{1, 2, 2, 3, 4, 4});
    }

    @Test
    public void doubleCharTest() {
        String res = doubleChar("The");
        assertEquals("TThhee", res);
    }

    @Test
    public void countHiTest() {
        int res = countHi("abc hi ho");
        assertEquals(1, res);
    }

    @Test
    public void mergeListsV2Test() {
        SinglyLinkedListNode h1 = new SinglyLinkedListNode(1);
        SinglyLinkedListNode h2 = new SinglyLinkedListNode(3);
        SinglyLinkedListNode h3 = new SinglyLinkedListNode(7);
        h1.next = h2;
        h2.next = h3;

        SinglyLinkedListNode l1 = new SinglyLinkedListNode(1);
        l1.next = new SinglyLinkedListNode(2);

        SinglyLinkedListNode mergedLists = mergeLists(h1, l1);

    }

    @Test
    public void getNodeTest() {
        SinglyLinkedListNode h1 = new SinglyLinkedListNode(3);
        SinglyLinkedListNode h2 = new SinglyLinkedListNode(2);
        SinglyLinkedListNode h3 = new SinglyLinkedListNode(1);
        h1.next = h2;
        h2.next = h3;

        int n = getNode(h1, 2);
        Assertions.assertEquals(n, 3);
    }

    @Test
    public void sortedInsertTest() {
        DoublyLinkedListNode d1 = new DoublyLinkedListNode(1);
        DoublyLinkedListNode d2 = new DoublyLinkedListNode(2);
        DoublyLinkedListNode d3 = new DoublyLinkedListNode(3);
        d1.next = d2;
        d2.prev = d1;
        d2.next = d3;
        d3.prev = d2;

        DoublyLinkedListNode res = sortedInsert(d1, 0);
    }

    @Test
    public void reverseListTest() {
        DoublyLinkedListNode d1 = new DoublyLinkedListNode(1);
        DoublyLinkedListNode d2 = new DoublyLinkedListNode(2);
        DoublyLinkedListNode d3 = new DoublyLinkedListNode(3);
        DoublyLinkedListNode d4 = new DoublyLinkedListNode(4);
        d1.next = d2;
        d2.prev = d1;
        d2.next = d3;
        d3.prev = d2;
        d3.next = d4;
        d4.prev = d3;

        DoublyLinkedListNode reversed = reverse(d1);
    }

    @Test
    public void arrayManipulationTest() {
        List<List<Integer>> queries =
                Arrays.asList(Arrays.asList(1, 5, 3),
                        Arrays.asList(4, 8, 7),
                        Arrays.asList(6, 9, 1));

        int n = 10;
        long max = arrayManipulationV2(n, queries);
        Assertions.assertEquals(10, max);
    }

    @Test
    public void firstSwapTest() {
        String[] arr = firstSwap(new String[]{"ax", "bx", "cx", "cy", "by", "ay", "aaa", "azz"});
        Assertions.assertArrayEquals(new String[]{"ay", "by", "cy", "cx", "bx", "ax", "aaa", "azz"}, arr);
    }

    @Test
    public void countClumpsTest() {
        int res = countClumps(new int[]{1, 2, 2, 2, 2, 2, 2, 3, 4, 4});
        Assertions.assertEquals(2, res);
    }

    @Test
    public void fibonacciTest() {
        long res = fibonacci(50, new HashMap<>());
        Assertions.assertEquals(12586269025L, res);
    }

    @Test
    public void fizzBuzzTest() {
        String[] arr = fizzBuzz(1, 11);
        Assertions.assertArrayEquals(new String[]{"1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz"}, arr);
    }

    @Test
    public void noNegativeTest() {
        List<Integer> res = noNegative(Arrays.asList(-3, -3, 3, 3));
        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }

        Assertions.assertArrayEquals(new int[]{3, 3}, arr);
    }

    @Test
    public void denseRankingTest() {
        List<Integer> res = denseRanking(new int[]{100, 90, 90, 80, 80, 80, 75, 70, 70, 70, 70, 70, 60});
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
            System.out.println(arr[i]);
        }
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 3, 3, 3, 4, 5, 5, 5, 5, 5, 6}, arr);
    }

    @Test
    public void determineRankingTest() {
        List<Integer> scores = new ArrayList<>();
        scores.add(70);
        scores.add(80);
        scores.add(105);
        List<Integer> res = determineRanking(List.of(100, 90, 90, 80), scores);

    }

    @Test
    public void knapsackTest() {
        int res = maxKnapsackValue(3, new int[]{10, 20, 30, 40}, new int[]{60, 100, 120}, 50);
        Assertions.assertEquals(220, res);

        res = maxKnapsackValue(4, new int[]{1, 3, 4, 5}, new int[]{1, 4, 5, 7}, 7);
        Assertions.assertEquals(9, res);
    }

    @Test
    public void maxPeakTest() {
        int res = maxPeak(new int[]{0, 1, 1, 3, 2, 4, 5, 6});
        Assertions.assertEquals(3, res);

        res = maxPeak(new int[]{0, 1, 2, 3, 4, 4, 6});
        Assertions.assertEquals(0, res);

        res = maxPeak(new int[]{1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3});
        Assertions.assertEquals(6, res);
    }

    @Test
    public void maxSubarrayAverageTest() {
        double res = maxSubarrayAverage(new int[]{1, 12, -5, -6, 50, 3}, 4);
        Assertions.assertEquals(12.75, res);
    }

    @Test
    public void mergeIntervalsTest() {
        int[][] res = mergeIntervals(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});

        res = mergeIntervals(new int[][]{{6, 8}, {1, 9}, {2, 4}, {4, 7}});
    }

    @Test
    public void rotateImageTest() {
        int[][] image1 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] res = rotateImage(image1);
        System.out.println("Test 1:\nRotating image:");
        printMatrix(image1);
        System.out.println("\nResulting image:");
        printMatrix(res);

        int[][] expected1 = new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        for (int i = 0; i < res.length; i++) {
            Assertions.assertArrayEquals(expected1[i], res[i]);
        }

        int[][] image2 = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        res = rotateImage(image2);
        System.out.println("\nTest 2:\nRotating image:");
        printMatrix(image2);
        System.out.println("\nResulting image:");
        printMatrix(res);
        int[][] expected2 = new int[][]{{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}};
        for (int i = 0; i < res.length; i++) {
            Assertions.assertArrayEquals(expected2[i], res[i]);
        }
    }

    @Test
    public void spiralMatrixTest() {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29, 30}
        };
        List<Integer> res = spiralMatrix(matrix);
        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }
        Assertions.assertArrayEquals(new int[]{
                1, 2, 3, 4, 5, 6,
                12, 18, 24, 30, 29, 28,
                27, 26, 25, 19, 13, 7,
                8, 9, 10, 11, 17, 23,
                22, 21, 20, 14, 15, 16}, arr);
    }

    @Test
    public void encodeTest() {
        String[] words = new String[]{"Hello", "World"};
        String res = encode(words);
        Assertions.assertEquals("5#Hello5#World", res);
    }

    @Test
    public void decodeTest() {
        String word = "5#Hello5#World12#a12re33fcvd12#vv";
        System.out.println("Result of " + word + ":");
        String[] res = decode(word);
        Stream.of(res).forEach(x -> System.out.println(x + " "));
        Assertions.assertArrayEquals(new String[]{"Hello", "World", "a12re33fcvd1", "vv"}, res);

        word = "1#a";// single short word
        System.out.println("\nResult of " + word + ":");
        res = decode(word);
        Stream.of(res).forEach(x -> System.out.println(x + " "));
        Assertions.assertArrayEquals(new String[]{"a"}, res);

        word = "1#a1#b"; // consecutive delimiters
        System.out.println("\nResult of " + word + ":");
        res = decode(word);
        Stream.of(res).forEach(x -> System.out.println(x + " "));
        Assertions.assertArrayEquals(new String[]{"a", "b"}, res);

        word = "3#1235#abc12"; // words with only numbers/ending with numbers
        System.out.println("\nResult of " + word + ":");
        res = decode(word);
        Stream.of(res).forEach(x -> System.out.println(x + " "));
        Assertions.assertArrayEquals(new String[]{"123", "abc12"}, res);

        word = "5#33ab1"; // words with only numbers/ending with numbers
        System.out.println("\nResult of " + word + ":");
        res = decode(word);
        Stream.of(res).forEach(x -> System.out.println(x + " "));
        Assertions.assertArrayEquals(new String[]{"33ab1"}, res);
    }

    @Test
    public void longestConsecutiveSequenceTest() {
//        int res = longestConsecutiveSequence(new int[] {100, 4, 200, 1, 3, 2});
        int res = longestConsecutiveSequence(new int[]{4, 1, 2, 7, 8, 9, 5, 10, 3});

    }

    @Test
    public void subarraySumTest() {
        int res = subarraySum(new int[]{1, 2, 3}, 3);
        Assertions.assertEquals(2, res);

        res = subarraySum(new int[]{1, 1, 1, 1}, 2);
        Assertions.assertEquals(3, res);

        res = subarraySum(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7);
        Assertions.assertEquals(4, res);
    }

    @Test
    public void isAnagramTest() {
        boolean res = isAnagram("nat", "bat");
        Assertions.assertFalse(res);

        res = isAnagram("eat", "tea");
        Assertions.assertTrue(res);
    }

    @Test
    public void groupAnagramsTest() {
        List<List<String>> res = groupAnagrams(new String[]{"foo", "bar", "enlist", "inlets", "listen", "ofo", "silent", "bra"});
    }

    @Test
    public void topFrequentTest() {
        List<Integer> res = topFrequent(new int[]{1, 5, 1, 9, 2, 9, 3, 8, 3, 6, 5, 9}, 2);
    }

    @Test
    public void mergeSortedListsTest() {
        List<Integer> res = mergeLists(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}});
        Assertions.assertArrayEquals(new Integer[]{1, 1, 2, 3, 4, 4, 5, 6}, res.toArray(new Integer[0]));
    }

    @Test
    public void longestUniqueSubstringTest() {
        int res = longestUniqueSubstring("abcabcbb");
        Assertions.assertEquals(3, res);

        res = longestUniqueSubstring("pwwkew");
        Assertions.assertEquals(3, res);
    }

    @Test
    public void rotateArrayRightTest() {
        int[] res = rotateArray(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        Assertions.assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, res);

        res = rotateArray(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        Assertions.assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, res);

        res = rotateArray(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        Assertions.assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, res);
    }

    @Test
    public void set0Test() {
        int[][] res = set0(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
    }

    @Test
    public void prodTest() {
        List<Integer> res = prod(new int[]{1, 2, 3, 4});
    }

    @Test
    public void findDuplicateTest() {
        int res = findDuplicate(new int[]{1, 3, 4, 2, 2});
        Assertions.assertEquals(2, res);

        res = findDuplicate(new int[]{3, 1, 3, 4, 2});
        Assertions.assertEquals(3, res);
    }

    /**
     * 9 = 0
     * 8 = 1
     * 1 = 8
     * 3 = 10
     * 2 = 11
     * 5 = 12
     * 4 = 13
     * 7 = 14
     * 6 = 15
     */
    @Test
    public void xorTest() {
        System.out.println(1 ^ 9);
    }

    @Test
    public void longestCommonPrefixTest() {
        String res = longestCommonPrefix(new String[]{"flower", "flow", "flight"});
        Assertions.assertEquals("fl", res);

        res = longestCommonPrefix(new String[]{"dog", "racecar", "car"});
        Assertions.assertEquals("", res);
    }

    @Test
    public void canJumpTest() {
        boolean res = canJump(new int[]{2, 3, 1, 1, 4});
        assertTrue(res);

        res = canJump(new int[]{2, 3, 1, 0, 4});
        assertTrue(res);

        res = canJump(new int[]{3, 2, 1, 0, 4});
        assertFalse(res);
    }

    @Test
    public void firstMissingPositiveTest() {
        int res = firstMissingPositive(new int[]{7, 8, 9, 11, 12});
        Assertions.assertEquals(1, res);

        res = firstMissingPositive(new int[]{3, 4, -1, 1});
        Assertions.assertEquals(2, res);

        res = firstMissingPositive(new int[]{1, 2, 3});
        Assertions.assertEquals(3, res);
    }

    @Test
    public void binarySearchTest() {
        int[] values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int res = binarySearchRecursive(values, 17, 0, values.length);

        Assertions.assertEquals(16, res);
    }

    @Test
    public void isBalancedTest() {
        boolean res = isBalanced("(()())");
        Assertions.assertTrue(res);

        res = isBalanced("(()())((()))(((())))()(()((())())");
        Assertions.assertFalse(res);

        res = isBalanced("(()())((()))(((())))()(()(())())");
        Assertions.assertTrue(res);
    }

    @Test
    public void mergeRangesTest() {
        List<List<Integer>> ranges = Arrays.asList(
//                Arrays.asList(1, 3),
//                Arrays.asList(2, 6),
//                Arrays.asList(8, 10),
//                Arrays.asList(15, 18)
                Arrays.asList(1, 4),
                Arrays.asList(0, 4)
        );

        List<List<Integer>> res = getRanges(ranges);
        System.out.println(res);
    }

    @Test
    public void timeConversionTest() {
        String res = timeConversion("12:01:00PM");
        Assertions.assertEquals("12:01:00", res);

        res = timeConversion("12:01:00AM");
        Assertions.assertEquals("00:01:00", res);

        res = timeConversion("07:05:45PM");
        Assertions.assertEquals("19:05:45", res);
    }

    @Test
    public void matchingStringsTest() {
        List<Integer> res = matchingStrings(Arrays.asList("ab", "ab", "abc"), Arrays.asList("ab", "abc", "bc"));
        int [] arr = new int[3];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        Assertions.assertArrayEquals(new int[]{2, 1, 0}, arr);
    }

    @Test
    public void pangramTest() {
        String res = pangrams("We promptly judged antique ivory buckles for the next prize");
        Assertions.assertEquals("pangram", res);
    }

    @Test
    public void valTest() {
        Val v = new Val(UUID.randomUUID().toString());
        Assertions.assertNotNull(v);
    }

    private void printMatrix(int[][] matrix) {
        Stream.of(matrix).forEach(x -> {
            Arrays.stream(x).forEach(y -> System.out.print(y + " "));
            System.out.println();
        });
    }
}
