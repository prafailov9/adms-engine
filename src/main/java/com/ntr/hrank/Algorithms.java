package com.ntr.hrank;


import java.util.*;
import java.util.stream.Collectors;

public class Algorithms {

    static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
        }
    }

    public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {
        SinglyLinkedListNode temp = llist;
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        while (position > 1) {
            temp = temp.next;
            position--;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        return llist;
    }

    public static SinglyLinkedListNode deleteNode(SinglyLinkedListNode llist, int position) {
        if (position == 0) {
            llist = llist.next;
            return llist;
        }

        SinglyLinkedListNode temp = llist;

        while (position > 1) {
            temp = temp.next;
            position--;
        }

        temp.next = temp.next.next;
        return llist;
    }

    public static SinglyLinkedListNode reverse(SinglyLinkedListNode llist) {
        SinglyLinkedListNode current = llist;
        SinglyLinkedListNode reversed = null;
        while (current != null) {
            SinglyLinkedListNode newNode = current.next;
            current.next = reversed;
            reversed = current;
            current = newNode;
        }

        return reversed;
    }

    public static SinglyLinkedListNode mergeListsWithArray(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if (head1 == null && head2 == null) return null;
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        SinglyLinkedListNode c1 = head1;
        SinglyLinkedListNode c2 = head2;
        List<Integer> list = new ArrayList<>();

        while (c1 != null && c2 != null) {
            if (c1.data == c2.data) {
                if (!list.contains(c1.data)) {
                    list.add(c1.data);
                    list.add(c2.data);
                }
            }
            if (c1.data < c2.data) {
                if (!list.contains(c1.data)) {
                    list.add(c1.data);
                    list.add(c2.data);
                }
            }
            if (c1.data > c2.data) {
                if (!list.contains(c2.data)) {
                    list.add(c2.data);
                    list.add(c1.data);
                }
            }
            c1 = c1.next;
            c2 = c2.next;
        }
        if (c1 != null) {
            while (c1 != null) {
                if (!list.contains(c1.data)) {
                    list.add(c1.data);
                }
                c1 = c1.next;
            }
        }

        if (c2 != null) {
            while (c2 != null) {
                if (!list.contains(c2.data)) {
                    list.add(c2.data);
                }
                c2 = c2.next;
            }
        }

        SinglyLinkedListNode mergeList = new SinglyLinkedListNode(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            addNode(mergeList, list.get(i));
        }
        return mergeList;
    }

//    public static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
//        if (head1 == null && head2 == null) return null;
//        if (head1 == null) return head2;
//        if (head2 == null) return head1;
//
//        SinglyLinkedListNode c1 = head1;
//        SinglyLinkedListNode c2 = head2;
//        SinglyLinkedListNode mergeList = initMergeList(c1, c2);
//        while (c1 != null && c2 != null) {
//            if (mergeList != null) {
//                if (c1.data == c2.data) {
//                    addNode(mergeList, c1);
//                }
//                if (c1.data > c2.data) {
//                    addNode(mergeList, c2);
//                    addNode(mergeList, c1);
//                }
//                if (c1.data < c2.data) {
//                    addNode(mergeList, c1);
//                    addNode(mergeList, c2);
//                }
//            }
//        }
//        return null;
//    }

//    private static SinglyLinkedListNode initMergeList(SinglyLinkedListNode c1, SinglyLinkedListNode c2) {
//        SinglyLinkedListNode mergeList = null;
//        if (c1.data == c2.data) {
//            mergeList = new SinglyLinkedListNode(c1.data);
//        }
//        if (c1.data > c2.data) {
//            mergeList = new SinglyLinkedListNode(c2.data);
//            addNode(mergeList, c1);
//        }
//        if (c1.data < c2.data) {
//            mergeList = new SinglyLinkedListNode(c1.data);
//            addNode(mergeList, c2);
//        }
//
//        return mergeList;
//    }

    private static void addNode(SinglyLinkedListNode mergeList, int data) {
        SinglyLinkedListNode current = mergeList;
        while (current.next != null) current = current.next;
        current.next = new SinglyLinkedListNode(data);
    }

    public static List<Integer> rotateLeft(int d, List<Integer> arr) {
        List<Integer> rotatedArray = new ArrayList<>(arr);
        for (int i = arr.size() - 1; i >= 0; i--) {
            int current = arr.get(i);
            if (i - d < 0) {
                rotatedArray.set(arr.size() - (d - i), current);
            } else {
                rotatedArray.set(i - d, current);
            }
        }
        return rotatedArray;
    }

    public static boolean hasCycle(SinglyLinkedListNode head) {
        if (head == null) return false;

        Map<SinglyLinkedListNode, Integer> nodeCounterMap = new HashMap<>();
        SinglyLinkedListNode tmp = head;
        while (tmp != null) {
            if (nodeCounterMap.containsKey(tmp)) return true;
            nodeCounterMap.put(tmp, 1);
            tmp = tmp.next;
        }
        return false;
    }


    public static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode h1 = head1;
        SinglyLinkedListNode h2 = head2;

        while (h2 != null) {
            while (h1 != null) {
                if (h1 == h2) return h1.data;
                h1 = h1.next;
            }
            h1 = head1;
            h2 = h2.next;
        }
        return -1;
    }

    public static SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode llist) {
        SinglyLinkedListNode current = llist;
        SinglyLinkedListNode prev = null;
        while (current != null) {
            if (prev != null) {
                if (current.data == prev.data) {
                    prev.next = current.next;
                    current = prev;
                }
            }
            prev = current;
            current = current.next;
        }
        return llist;
    }

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        List<List<Integer>> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }
        int lastAnswer = 0;
        List<Integer> result = new ArrayList<>();

        for (List<Integer> query : queries) {
            int idx = ((query.get(1) ^ lastAnswer) % n);
            if (query.get(0) == 1) {
                arr.get(idx).add(query.get(2));
            } else {
                lastAnswer = arr.get(idx).get(query.get(2) % arr.get(idx).size());
                result.add(lastAnswer);
            }
        }
        return result;
    }

    public static void quicksort(int[] array, int low, int high) {
        if (low > high || low < 0) {
            return;
        }
        int pivot = partition(array, low, high);

        // Sort the two partitions
        quicksort(array, low, pivot - 1); // Left side of pivot
        quicksort(array, pivot + 1, high); // Right side of pivot
    }

    // divides the array into 2 partitions
    private static int partition(int[] array, int low, int high) {
        // choose the last elem as pivot
        int pivot = array[high];

        // temp pivot index
        int i = low - 1;

        for (int j = low; j < high - 1; j++) {
            if (array[j] <= pivot) {
                i++;
                //swap current elem with the elem at the temp pivot index
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        i++;
        int temp = array[high];
        array[high] = array[i];
        array[i] = temp;

        return i;
    }

    public static boolean array123(int[] nums) {
        int seq = 0;
        for (int num : nums) {
            if (seq + 1 == num) {
                seq++;
            }
        }
        return seq == 3;
    }

    public static int stringMatch(String a, String b) {
        // Figure which string is shorter.
        int len = Math.min(a.length(), b.length());
        int count = 0;

        // Look at both substrings starting at i
        for (int i = 0; i < len - 1; i++) {
            String aSub = a.substring(i, i + 2);
            String bSub = b.substring(i, i + 2);
            if (aSub.equals(bSub)) {  // Use .equals() with strings
                count++;
            }
        }
        return count;
    }

    public static String stringX(String str) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (i >= 1 && i < str.length() - 1) {
                if (current != 'x') {
                    sb.append(current);
                }
            } else {
                sb.append(current);
            }
        }
        return sb.toString();
    }


    /**
     * Find the larges span of the same number in an array. A span is the number of elements between the same element, including them:
     * maxSpan([1, 2, 1, 1, 3]) → 4
     * maxSpan([1, 4, 2, 1, 4, 1, 4]) → 6
     * maxSpan([1, 4, 2, 1, 4, 4, 4]) → 6
     */
    public static int maxSpan(int[] numbers) {
        if (numbers.length < 1) {
            return 0;
        }
        int maxSpan = 1;
        Map<Integer, Integer> firstFoundPositions = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int current = numbers[i];
            if (!firstFoundPositions.containsKey(current)) {
                // save every new element and the position where it was found
                firstFoundPositions.put(current, i);
            } else {
                // subtract the current position(i) with the first position where
                // the element was found and add 1 to get the span.
                int currentSpan = i - firstFoundPositions.get(current) + 1;
                if (maxSpan < currentSpan) {
                    maxSpan = currentSpan;
                }
            }
        }
        return maxSpan;
    }

    /**
     * Fuck this shit
     */
    public static int[] fix34(int[] nums) {
        int[] fixed34 = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            fixed34[i] = current;
            if (current == 3) {
                // go forward, check if next elem is not 4
                int k = i + 1;
                while (k < nums.length && nums[k] != 4) {
                    k++;
                }
                if (k < nums.length) {
                    // found 4
                    int temp = nums[k];
                    nums[k] = nums[i + 1];
                    nums[i + 1] = temp;
                } else {
                    // go backwards, find first 4, don't steal 4s from other 3s, that's not cool brah :/
                    int j = i - 1;
                    while (j - 1 >= 0) {
                        if (nums[j] == 4 && nums[j - 1] != 3) {
                            break;
                        }
                        j--;
                    }
                    if (j >= 0) { // swap in both arrays
                        int temp = nums[j];
                        nums[j] = nums[i + 1];
                        fixed34[j] = nums[i + 1];
                        nums[i + 1] = temp;
                    }
                }
            }
        }
        return fixed34;
    }


    public static int[] fix45WithChatGPTSmile(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 4) {
                while (nums[j] != 5 || (j > 0 && nums[j - 1] == 4)) {
                    j++;
                }
                // Swap nums[i+1] and nums[j]
                int temp = nums[i + 1];
                nums[i + 1] = nums[j];
                nums[j] = temp;
            }
        }
        return nums;
    }


    /**
     * canBalance([1, 1, 1, 2, 1]) → true
     * canBalance([2, 1, 1, 2, 1]) → false
     * canBalance([10, 10]) → true
     */
    public static boolean canBalance(int[] nums) {
        // edge cases
        if (nums.length <= 1) {
            return false;
        }
        if (nums.length == 2) {
            return nums[0] == nums[1];
        }
        // real code ;) ;* ;P
        int sumFirst = 0;
        int sumSecond = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length / 2) {
                sumFirst += nums[i];
            } else {
                sumSecond += nums[i];
            }
        }
        // return if equal on first split
        if (sumFirst == sumSecond) {
            return true;
        } else if (sumFirst > sumSecond) {
            int i = nums.length / 2;
            while (sumFirst > sumSecond) {
                sumFirst -= nums[i];
                sumSecond += nums[i];
                i--;
            }
            return sumFirst == sumSecond;
        } else {
            int i = nums.length / 2;
            while (sumFirst < sumSecond) {
                sumFirst += nums[i];
                sumSecond -= nums[i];
                i++;
            }
            return sumFirst == sumSecond;
        }
    }

    public static boolean canBalanceWithChatGPTSmile(int[] nums) {
        int totalSum = 0;
        // gets the total sum
        for (int num : nums) {
            totalSum += num;
        }

        int cumulativeSum = 0;
        // sums each number successively and compares to the total on each step
        for (int num : nums) {
            cumulativeSum += num;
            if (cumulativeSum == totalSum - cumulativeSum) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given two arrays of ints sorted in increasing order, outer and inner,
     * return true if all of the numbers in inner appear in outer.
     * The best solution makes only a single "linear" pass of both arrays,
     * taking advantage of the fact that both arrays are already in sorted order.
     * linearIn([1, 2, 4, 6], [2, 4]) → true
     * linearIn([1, 2, 4, 6], [2, 3, 4]) → false
     * linearIn([1, 2, 4, 4, 6], [2, 4]) → true
     */
    public static boolean linearIn(int[] outer, int[] inner) {
        if (inner.length > outer.length) {
            return false;
        }
        if (inner.length == 0) {
            return true;
        }
        // map to keep track of the inner elements and if they are found in outer array
        Map<Integer, Boolean> innerElements = new HashMap<>();
        boolean containsAll = false;
        for (int i = 0; i < outer.length; i++) {
            // break case
            if (i > inner.length && containsAll) {
                return true;
            }
            if (i < inner.length) {
                innerElements.put(inner[i], false);
            }
            // if elem is found in outer
            if (innerElements.containsKey(outer[i])) {
                innerElements.put(outer[i], true);
            }
            containsAll = innerElements.entrySet().stream().allMatch(Map.Entry::getValue);
        }

        return containsAll;
    }

    public boolean linearInV2(int[] outer, int[] inner) {
        if (inner.length == 0) {
            return true;
        }

        int j = 0;  // pointer for inner array

        for (int i = 0; i < outer.length; i++) {
            if (outer[i] == inner[j]) {
                j++;
                // if the inner pointer has iterated through all elements of inner,
                // then it has found them in outer
                if (j == inner.length) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * create pattern based on the initial size(n)
     * ex: n = 3; {0, 0, 1,    0, 2, 1,    3, 2, 1}
     * n is the number of groups in the array.
     */
    public static int[] squareUp(int n) {
        if (n < 1) {
            return new int[]{};
        }
        // create new array with ^2 length
        int[] squared = new int[n * n];
        int shift = 0;
        int num = 1;
        for (int i = squared.length - 1; i >= 0; i--) {
            squared[i] = num;
            if (num == n) {
                num = 0;
                shift++;
                if (n - (shift - 1) < n) {
                    // rewrite with 0.
                    for (int j = i; j < i + shift - 1; j++) {
                        squared[j] = 0;
                    }
                }
            }
            num++;
        }

        return squared;
    }

    /**
     * Given n>=0, create an array with the pattern
     * {1,    1, 2,    1, 2, 3,   ... 1, 2, 3 .. n} (spaces added to show the grouping).
     * Note that the length of the array will be 1 + 2 + 3 ... + n, which is known to sum to exactly n*(n + 1)/2.
     * seriesUp(3) → [1, 1, 2, 1, 2, 3]
     * seriesUp(4) → [1, 1, 2, 1, 2, 3, 1, 2, 3, 4]
     * seriesUp(2) → [1, 1, 2]
     */
    public static int[] seriesUp(int n) {
        int[] series = new int[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                series[idx++] = j;
            }
        }
        return series;
    }

    /**
     * We'll say that a "mirror" section in an array is a group of
     * contiguous elements such that somewhere in the array, the same group
     * appears in reverse order. For example, the largest mirror
     * section in {1, 2, 3, 8, 9, 3, 2, 1} is length 3 (the {1, 2, 3} part).
     * Return the size of the largest mirror section found in the given array.
     * maxMirror([1, 2, 3, 8, 9, 3, 2, 1]) → 3
     * maxMirror([1, 2, 1, 4]) → 3
     * maxMirror([7, 1, 2, 9, 7, 2, 1]) → 2
     * <p>
     * [1, 2, 3, 2, 5, 4, 5, 3, 2, 1]
     * [1, 2, 3, 2, 5, 4, 3, 2, 1, 5, 6, 7, 8, 9, 8, 7, 6, 5]
     */
    public static int maxMirror(int[] nums) {
        int maxMirrorLength = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j >= 0; j--) {
                int length = 0;
                int x = i;
                int y = j;

                while (x < nums.length && y >= 0 && nums[x] == nums[y]) {
                    length++;
                    x++;
                    y--;

                    maxMirrorLength = Math.max(maxMirrorLength, length);
                }
            }
        }
        return maxMirrorLength;
    }

    /**
     * Say that a "clump" in an array is a series of 2 or more adjacent elements of the same value.
     * Return the number of clumps in the given array.
     * countClumps([1, 2, 2, 3, 4, 4]) → 2
     * countClumps([1, 1, 2, 1, 1]) → 2
     * countClumps([1, 1, 1, 1, 1]) → 1
     */
    public static int countClumps(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int totalClumps = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                totalClumps++;
                // find sequence of the current clump
                int j = i + 1;
                while (j < nums.length && nums[i] == nums[j]) {
                    j++;
                }
                // start at end of sequence
                i = j;
            }
        }
        return totalClumps;
    }

    public static String doubleChar(String str) {

        // The -> TThhee
        StringBuilder doubled = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            doubled.append(str.charAt(i)).append(str.charAt(i));
        }

        return doubled.toString();
    }

    public static int countHi(String str) {
        // "abc hi ho"
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.startsWith("hi", i)) {
                count++;
            }
        }
        return count;
    }

    public static boolean catDog(String str) {
        // catdogdog

        int ccat = 0;
        int cdog = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.startsWith("cat", i)) {
                ccat++;
            } else if (str.startsWith("dog", i)) {
                cdog++;
            }
        }

        return ccat == cdog;
    }

    public static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        SinglyLinkedListNode mergedHead;
        if (head1.data < head2.data) {
            mergedHead = head1;
            head1 = head1.next;
        } else {
            mergedHead = head2;
            head2 = head2.next;
        }

        SinglyLinkedListNode current = mergedHead;
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                current.next = head1;
                head1 = head1.next;
            } else {
                current.next = head2;
                head2 = head2.next;
            }
            current = current.next;
        }

        if (head1 != null) {
            current.next = head1;
        } else {
            current.next = head2;
        }

        return mergedHead;
    }

    public static int getNode(SinglyLinkedListNode llist, int positionFromTail) {
        List<Integer> arr = new ArrayList<>();
        while (llist != null) {
            arr.add(llist.data);
            llist = llist.next;
        }

        return arr.get((arr.size() - 1) - positionFromTail);
    }

    public static int getNodeV2(SinglyLinkedListNode llist, int positionFromTail) {
        SinglyLinkedListNode leading = llist;
        SinglyLinkedListNode trailing = llist;

        // Move the leading pointer forward by positionFromTail steps
        for (int i = 0; i < positionFromTail; i++) {
            leading = leading.next;
        }

        // Move both pointers until the leading pointer reaches the tail
        while (leading.next != null) {
            leading = leading.next;
            trailing = trailing.next;
        }

        // The trailing pointer is now at the desired position
        return trailing.data;

    }

    public static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode llist, int data) {
        DoublyLinkedListNode node = new DoublyLinkedListNode(data);

        // when inserting at the start, attach new node before head node.
        if (llist.data > data) {
            node.next = llist;
            llist.prev = node;
            llist = node;
            return llist;
        }

        DoublyLinkedListNode tmp = llist;
        while (tmp.next != null && tmp.data < data) {
            tmp = tmp.next;
        }
        if (tmp.next != null) {
            node.next = tmp;
            tmp.prev.next = node;
            node.prev = tmp.prev;
        } else {
            if (tmp.data < data) {
                tmp.next = node;
                node.prev = tmp;
            } else {
                node.next = tmp;
                tmp.prev.next = node;
                node.prev = tmp.prev;
            }
        }

        return llist;
    }

    public static DoublyLinkedListNode reverse(DoublyLinkedListNode llist) {
        if (llist == null || llist.next == null) {
            return llist;
        }

        DoublyLinkedListNode reversed = llist;
        while (reversed.next != null) {
            reversed = reversed.next;
        }

        DoublyLinkedListNode tmp = reversed;
        tmp.next = tmp.prev;
        while (tmp != null) {
            tmp.next = tmp.prev;
            tmp = tmp.next;
        }
        reversed.prev = null;
        return reversed;
    }

    /**
     * find the most occurring index ranges and apply the addition.
     * addition starts at 0, for n = 5 -> [0, 0, 0, 0, 0]
     * the queries matrix will always have the following structure: [[a1, b1, k1], [a2, b2, k2], [a3, b3, k3]]
     * the 'a' and 'b' values represent the inclusive start and end of a range, ex: [1, 4]
     * 'k' is the value to be summed to list n.
     * ex: q = [[0, 1, 21], [2, 3, 4], [3, 4, 1]]
     * iteration #1: add 2 to the range 1-3; n -> [21, 21, 2, 2, 0]
     * iteration #2: add 4 to the range 2-3; n -> [21, 21, 6, 6, 0]
     * iteration #3: add 1 to the range 3-4; n -> [21, 21, 6, 7, 1]
     * return the largest value -> 21
     */
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        long[] sumArray = new long[n];
        long max = sumArray[0];

        for (List<Integer> query : queries) {
            int a = query.get(0);
            int b = query.get(1);
            int k = query.get(2);

            for (int i = a - 1; i < b; i++) {
                sumArray[i] += k;
                if (max < sumArray[i]) {
                    max = sumArray[i];
                }
            }
        }
        return max;
    }

    public static long arrayManipulationV2(int n, List<List<Integer>> queries) {
        long[] sumArray = new long[n + 1]; // Using long for large sums

        for (List<Integer> query : queries) {
            int a = query.get(0);
            int b = query.get(1);
            int k = query.get(2);
            // marking the range for k by adding k at the start(a) and subtracting at the end(b-1).
            // subtracting will cancel out the addition when calculating the sum.
            sumArray[a] += k;
            if (b + 1 <= n) {
                sumArray[b + 1] -= k;
            }
        }

        long sum = 0;
        long max = 0;
        // array is marked with k-value ranges,
        // sum all elements and keep track of the max value of sum to get the result
        for (int i = 1; i <= n; i++) {
            sum += sumArray[i];
            if (sum > max) {
                max = sum;
            }
        }

        return max;
    }

    /**
     * We'll say that 2 strings "match" if they are non-empty and their first chars are the same.
     * Loop over and then return the given array of non-empty strings as
     * follows: if a string matches an earlier string in the array, swap the 2 strings in the array.
     * A particular first char can only cause 1 swap, so once a char has caused a swap, its later swaps are disabled.
     * Using a map, this can be solved making just one pass over the array.
     *
     * @param strings - arr of strings
     * @return rearranged array
     */
    public static String[] firstSwap(String[] strings) {
        // store the first char of a string and its index
        Map<Character, Integer> stringMap = new HashMap<>();
        // keep track of duplicate chars
        Set<Character> duplicates = new HashSet<>();

        for (int i = 0; i < strings.length; i++) {
            String current = strings[i];
            // add a unique starting char of each string
            if (!stringMap.containsKey(current.charAt(0))) {
                stringMap.put(current.charAt(0), i);
            } else {
                if (!duplicates.contains(current.charAt(0))) {
                    // swap matching strings
                    int idx = stringMap.get(current.charAt(0));
                    String temp = strings[i];
                    strings[i] = strings[idx];
                    strings[idx] = temp;

                    // add char to duplicates to disallow further swaps
                    duplicates.add(current.charAt(0));
                }
            }
        }

        return strings;
    }

    public static String[] fizzBuzz(int start, int end) {
        String[] strings = new String[end - start];
        for (int i = 0; i < strings.length; i++, start++) {
            boolean mult3 = start % 3 == 0;
            boolean mult5 = start % 5 == 0;
            if (mult3 && mult5) {
                strings[i] = "FizzBuzz";
            } else if (!mult3 && mult5) {
                strings[i] = "Buzz";
            } else if (mult3) {
                strings[i] = "Fizz";
            } else {
                strings[i] = String.valueOf(start);
            }
        }
        return strings;
    }

    public static List<Integer> noNegative(List<Integer> nums) {
        return nums.stream().filter(x -> x >= 0).collect(Collectors.toList());
    }

    public static List<Integer> denseRanking(int[] leaderboard) {
        List<Integer> rankings = new ArrayList<>();
        if (leaderboard.length == 0) {
            return rankings; // return empty rankings if leaderboard is empty
        }

        int rank = 1;
        rankings.add(rank); // first score has rank 1

        for (int i = 1; i < leaderboard.length; i++) {
            if (leaderboard[i] < leaderboard[i - 1]) {
                rank++; // increment rank only if the current score is less than the previous score
            }
            rankings.add(rank);
        }

        return rankings;
    }

    /**
     * Current top ranking scores: [100, 90, 90, 80]
     * current player scores: [70, 80, 105] -> their ranking will be [4, 3, 1]
     * return the player's rank after the new scores
     */
    public static List<Integer> determineRanking(List<Integer> leaderboard, List<Integer> scores) {
        List<Integer> leaderboardRankings = new ArrayList<>();
        int rank = 1;
        leaderboardRankings.add(rank);

        for (int i = 1; i < leaderboard.size(); i++) {
            if (leaderboard.get(i) < leaderboard.get(i - 1)) {
                rank++; // increment rank only if the current score is less than the previous score
            }
            leaderboardRankings.add(rank);
        }

        List<Integer> newRankings = new ArrayList<>();
        int notAddedCounter = 0;
        for (int i = 0; i < scores.size(); i++) {
            boolean added = false;
            for (int j = 0; j < leaderboard.size() && !added; j++) {
                int playerScore = scores.get(i);
                int leaderboardScore = leaderboard.get(j);
                if (playerScore >= leaderboardScore) {
                    newRankings.add(i, leaderboardRankings.get(j));
                    added = true;
                }
            }
            if (!added) {
                notAddedCounter++;
                newRankings.add(leaderboardRankings.get(leaderboardRankings.size() - 1) + notAddedCounter);
            }
        }

        return newRankings;
    }

    /**
     * fibonacci implementation with memoization
     */
    public static long fibonacci(int n, Map<Integer, Long> memo) {
        // will return the nth value if its already calculated
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n <= 2) {
            return 1;
        }
        // store the nth value to skip duplicate calculations
        memo.put(n, fibonacci(n - 1, memo) + fibonacci(n - 2, memo));
        return memo.get(n);
    }

    public static int gridTraveler(int n, int m, Map<Integer, Long> memo) {
        if (n == 1 && m == 1) {
            return 1;
        }
        if (n == 0 || m == 0) {
            return 0;
        }
        return gridTraveler(m - 1, n, memo) + gridTraveler(n, m - 1, memo);
    }


    /**
     * n = 3
     * weights = [10, 20, 30]
     * values = [60, 100, 120]
     * W = 50
     */

    public static int maxKnapsackValue(int n, int[] weights, int[] values, int W) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }

    /**
     * [1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3]
     */
    public static int maxPeak(int[] values) {
        int max = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[i - 1]) {
                Pair<Integer, Integer> res = getPeakSequence(i - 1, values, max);
                max = res.second;
                if (res.first > 0) {
                    // -1 to account for the index
                    i = res.first - 1;
                } else {
                    // we have not found a peak until the end of the array
                    // so we set i to values.length to exit gracefully.
                    i = values.length;
                }
            }
        }
        return max;
    }

    private static Pair<Integer, Integer> getPeakSequence(int startPos, int[] values, int max) {
        int pos = startPos + 1;

        // find climbing peak
        while (pos < values.length && values[pos] > values[pos - 1]) {
            pos++;
        }
        if (pos < values.length) {
            // if we found equal number, there is no peak in this sequence, return current index;
            if (values[pos] == values[pos - 1]) {
                return Pair.of(pos, 0);
            }
            // found peak = value[pos]
            // find full length of peak sequence
            while (pos < values.length && values[pos] < values[pos - 1]) {
                pos++;
            }
        }
        if (pos < values.length) {
            if (max < pos - startPos) {
                max = pos - startPos;
            }
            return Pair.of(pos, max);
        }
        return Pair.of(0, max); // no peak sequence found
    }

    public static int maxPeakV2(int[] values) {
        int maxPeakLength = 0;

        for (int i = 1; i < values.length - 1; i++) {
            // Check if current element is a peak
            if (values[i] > values[i - 1] && values[i] > values[i + 1]) {
                // Explore left and right sides of the peak
                int left = i - 2;
                while (left >= 0 && values[left] < values[left + 1]) {
                    left--;
                }

                int right = i + 2;
                while (right < values.length && values[right] < values[right - 1]) {
                    right++;
                }

                // Update max peak length if necessary
                int currentPeakLength = right - left - 1;
                maxPeakLength = Math.max(maxPeakLength, currentPeakLength);

                // Skip the decreasing part of the peak
                i = right - 1;
            }
        }

        return maxPeakLength;
    }

    /**
     * @param values input values
     * @param k      range of subarray
     * @return the max average of all values in the subarray with k range
     */
    public static double maxSubarrayAverage(int[] values, int k) {
        if (k > values.length) {
            return -1;
        }

        double max = 0;
        for (int i = 0; i < values.length; i++) {
            double sum = 0;
            int j = i;
            // k + i: length of the current subarray from ith position
            if (k + i >= values.length) {
                return max;
            }
            while (j < k + i) {
                sum += values[j];
                j++;
            }
            // get average of current subarray sum
            sum /= k;
            if (max < sum) {
                max = sum;
            }
        }
        return max;
    }

    public static double maxSubarrayAverageV2(int[] values, int k) {
        if (k > values.length) {
            return -1;
        }

        double sum = 0;
        // calc sum of 1st subarray of size k
        for (int i = 0; i < k; i++) {
            sum += values[i];
        }

        // get average of 1st subarray to get an initial value for the max average
        double max = sum / k;
        // start from the end of the 1st subarray by sliding window of
        // k-range: subtract the element leaving the window, add the new element
        for (int i = k; i < values.length; i++) {
            sum += values[i] - values[i - k];
            if (max < sum / k) {
                max = sum / k;
            }
        }
        return max;
    }

    /**
     * Given an array of intervals where each interval is represented as a pair of integers [start, end],
     * merge all overlapping intervals and return an array of the non-overlapping intervals that cover all
     * the intervals in the input.
     * Two intervals [a, b] and [c, d] overlap if c <= b.
     * When merging two overlapping intervals, the resulting interval should span from the minimum
     * start point to the maximum end point of the intervals being merged.
     */
    public static int[][] mergeIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        if (intervals.length == 1) {
            return new int[][]{intervals[0]};
        }

        // sort input by starting range
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));


        ArrayList<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];

            if (interval[0] <= currentEnd) { // Overlapping intervals
                currentInterval[1] = Math.max(currentEnd, interval[1]);
            } else { // Non-overlapping interval, move to the next one
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * rotate a 2D matrix with size n * n 90 degrees clockwise.
     * rotation is done in-place.
     * <p>
     * image.length = number of rows
     * image[i].length = length of ith row
     * image[i][j] = index(use) a specific item at i row and j column
     * INPUT: [
     * [1, 2, 3],
     * [4, 5, 6],
     * [7, 8, 9]
     * ]
     * OUTPUT: [
     * [7, 4, 1],
     * [8, 5, 2],
     * [9, 6, 3]
     * ]
     * first row becomes last column
     * second row -> second column
     * third row -> first column
     *
     * @param image matrix to rotate
     * @return rotated image
     * <p>
     * [  [ 5, 1, 9,11],
     * [ 2, 4, 8,10],
     * [13, 3, 6, 7],
     * [15,14,12,16]
     * ]
     * <p>
     * [
     * [15,13, 2, 5],
     * [14, 3, 4, 1],
     * [12, 6, 8, 9],
     * [16, 7,10,11]
     * ]
     */
    public static int[][] rotateImage(int[][] image) {
        if (image.length <= 1) {
            return image;
        }
        // swap first and last rows
        int pos = 0;
        while (pos < image.length / 2) {
            int[] temp = image[pos];
            image[pos] = image[(image.length - 1) - pos];
            image[(image.length - 1) - pos] = temp;
            pos++;
        }

        // transpose algorithm for n*n matrix
        for (int i = 0; i < image.length - 1; i++) {
            for (int j = i + 1; j < image.length; j++) {
                int temp = image[i][j];
                image[i][j] = image[j][i];
                image[j][i] = temp;
            }
        }
        return image;
    }

    /**
     * {
     * {1,  2,  3,  4,  5,  6},
     * {7,  8,  9,  10, 11, 12},
     * {13, 14, 15, 16, 17, 18},
     * {19, 20, 21, 22, 23, 24},
     * {25, 26, 27, 28, 29, 30}
     * }
     * 1st layer:
     * 1    ->   6    ->   30   ->   25   ->   7
     * [0][0] -> [0][n] -> [n][m] -> [n][0] -> [1][0]
     * 2nd layer
     * [1][1] -> [1][n - 1] -> [n - 1][m - 1] -> [n - 1][1] -> [2][1]
     * 3rd layer
     * [2][2] -> [2][n - 2]
     * the values 0, 1, 2 represent some var(k) which will be incremented
     * when we have reached the end of the current
     * layer, denoted by the i, j indices where i - j = 1
     */
    public static List<Integer> spiralMatrix(int[][] matrix) {
        List<Integer> values = new ArrayList<>();
        int k = 0;
        while (values.size() < matrix.length * matrix[0].length) {
            int j = k;
            // move up ith column
            while (j < matrix[0].length - k) {
                values.add(matrix[k][j]);
                j++;
            }
            if (values.size() == matrix.length * matrix[0].length) {
                return values;
            }
            j = k + 1;
            // move down m - k row
            while (j < matrix.length - k) {
                values.add(matrix[j][(matrix[0].length - 1) - k]);
                j++;
            }
            if (values.size() == matrix.length * matrix[0].length) {
                return values;
            }
            // move down n - k column
            j = ((matrix[0].length - 1) - k) - 1;
            while (j >= k) {
                values.add(matrix[(matrix.length - 1) - k][j]);
                j--;
            }
            if (values.size() == matrix.length * matrix[0].length) {
                return values;
            }
            j = ((matrix.length - 1) - k) - 1;
            // move through row
            while (j > k) {
                values.add(matrix[j][k]);
                j--;
            }
            if (values.size() == matrix.length * matrix[0].length) {
                return values;
            }
            // denoting end of layer
            k++;
        }
        return values;
    }

    /**
     * a more clean approach to the spiralMatrix
     */
    public static List<Integer> spiralMatrixV2(int[][] matrix) {
        List<Integer> values = new ArrayList<>();
        if (matrix.length == 0) return values;

        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Traverse Right
            for (int j = colStart; j <= colEnd; j++) {
                values.add(matrix[rowStart][j]);
            }
            rowStart++;

            // Traverse Down
            for (int j = rowStart; j <= rowEnd; j++) {
                values.add(matrix[j][colEnd]);
            }
            colEnd--;

            if (rowStart <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colStart; j--) {
                    values.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            if (colStart <= colEnd) {
                // Traverse Up
                for (int j = rowEnd; j >= rowStart; j--) {
                    values.add(matrix[j][colStart]);
                }
            }
            colStart++;
        }
        return values;
    }

    public static String encode(String[] words) {
        char delimiter = '#';
        StringBuilder output = new StringBuilder();
        for (String word : words) {
            output.append(word.length()).append(delimiter).append(word);
        }
        return output.toString();
    }

    /**
     * The encoding pattern is length+delimiter+word making it easy to extract words after split by delimiter.
     * Correct length information is expected.
     */
    public static String[] decode(String encodedText) {
        // split by delimiter
        char delimiter = '#';
//        String[] words = encodedText.split("#");
        // custom single-character split implementation
        // count the number of delimiters to get the exact size for the output array
        int wordCount = 1; // to address for when there are no delimiters
        for (int i = 0; i < encodedText.length(); i++) {
            if (encodedText.charAt(i) == delimiter) {
                wordCount++;
            }
        }
        String[] encodedWords = new String[wordCount];

        int idx = 0; // array index
        int start = 0; // start index of a word
        for (int i = 0; i < encodedText.length(); i++) {
            if (encodedText.charAt(i) == delimiter) {
                encodedWords[idx++] = encodedText.substring(start, i);
                // move start to next char after the delimiter
                start = i + 1;
            }
        }
        // handle the last word
        if (start < encodedText.length()) {
            encodedWords[idx] = encodedText.substring(start);
        }

        // length is -1 to account for the first element that we know is just the length of the next word
        String[] decodedWords = new String[wordCount - 1];
        // getting the length since we know the pattern
        int length = Integer.parseInt(encodedWords[0]);
        for (int i = 1; i < encodedWords.length; i++) {
            String currentEncodedWord = encodedWords[i];
            // extract current word
            String decodedWord = currentEncodedWord.substring(0, length);
            decodedWords[i - 1] = decodedWord;

            // get length for the next word
            String l = currentEncodedWord.substring(length);
            // if there is no length, we have extracted the last word
            if (l.isEmpty()) {
                return decodedWords;
            }
            length = Integer.parseInt(l);
        }

        return decodedWords;
    }

    public static List<String> decodeV0(String encodedText) {
        // split by delimiter
        char delimiter = '#';
        String[] words = encodedText.split("#");


        List<String> output = new ArrayList<>();
        // getting the length since we know the pattern
        int length = Integer.parseInt(words[0]);

        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            int j = 0;
            StringBuilder currentWord = new StringBuilder();
            // extract word
            while (j < length) {
                currentWord.append(word.charAt(j));
                j++;
            }
            output.add(currentWord.toString());
            // get length for next word
            StringBuilder l = new StringBuilder();
            while (j < word.length()) {
                l.append(word.charAt(j));
                j++;
            }
            if (l.isEmpty()) {
                return output;
            }
            length = Integer.parseInt(l.toString());
        }

        return output;
    }

    private String[] split(String encodedText, char delimiter) {
        // custom single-character split implementation
        // count the number of delimiters to get the exact size for the output array
        int wordCount = 1; // to address for when there are no delimiters
        for (int i = 0; i < encodedText.length(); i++) {
            if (encodedText.charAt(i) == delimiter) {
                wordCount++;
            }
        }

        String[] words = new String[wordCount];
        int idx = 0; // array index
        int pos = 0; // index of the text
        while (pos < encodedText.length()) {
            StringBuilder currentWord = new StringBuilder();
            while (encodedText.charAt(pos) != delimiter) {
                currentWord.append(encodedText.charAt(pos));
                pos++;
            }
            words[idx++] = currentWord.toString();
            pos++; // skip delimiter
        }

        return words;
    }

    /**
     * [4, 1, 2, 7, 8, 9, 5, 10, 3]
     *
     * @param values
     * @return
     */
    public static int longestConsecutiveSequence(int[] values) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            map.put(values[i], i);
        }

        int max = 0;

        for (int i = 0; !map.isEmpty() || i < values.length; i++) {
            if (map.containsKey(values[i])) {
                int sequence = 0;
                int current = values[i];
                sequence++;
                // find all increasing by 1
                // removing already visited values to avoid duplicating effort
                while (map.containsKey(current + 1)) {
                    sequence++;
                    map.remove(current);
                    current = values[map.get(current + 1)];
                }
                map.remove(current);

                // reset current
                current = values[i];
                // find all decreasing by 1
                while (map.containsKey(current - 1)) {
                    sequence++;
                    map.remove(current);
                    current = values[map.get(current - 1)];
                }
                map.remove(current);
                // after each step check current sequence with current max
                if (max < sequence) {
                    max = sequence;
                }
            }
        }
        return max;
    }

    /**
     * input array is expected to have subarrays(consecutive sequences) that eventually equal k
     * values = [3, 4, 7, 2, -3, 1, 4, 2], k = 7
     */
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int cumulativeSum = 0;
        Map<Integer, Integer> sumFrequency = new HashMap<>();

        sumFrequency.put(0, 1); // for the subarray that starts from the beginning of the array
        for (int num : nums) {
            cumulativeSum += num;
            // check if there is a subarray whose sum is k
            count += sumFrequency.getOrDefault(cumulativeSum - k, 0);
            // update the frequency of the current sum
            sumFrequency.put(cumulativeSum, sumFrequency.getOrDefault(cumulativeSum, 0) + 1);
        }

        return count;
    }

    /**
     * [tea, eat] = true; [tan, ban] = false;
     */
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Integer> charFrequency = new HashMap<>();
        int pos = 0;
        // get initial frequency from s1
        while (pos < s1.length()) {
            charFrequency.put(s1.charAt(pos), charFrequency.getOrDefault(s1.charAt(pos), 0) + 1);
            pos++;
        }

        pos = 0;
        // decrement the frequency for s2 and check if any char doesn't exist in map
        while (pos < s2.length()) {
            if (!charFrequency.containsKey(s2.charAt(pos))) {
                return false;
            }
            charFrequency.put(s2.charAt(pos), charFrequency.getOrDefault(s2.charAt(pos), 0) - 1);
            pos++;
        }
        // all frequencies must be 0
        for (int freq : charFrequency.values()) {
            if (freq != 0) {
                return false;
            }
        }
        return true;
    }


    public static List<List<String>> groupAnagrams(String[] anagrams) {
        if (anagrams.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> sortedGroups = new HashMap<>();
        for (String word : anagrams) {
            // sort chars of current word, that way words are easily checked if they are anagrams;
            char[] chars = word.toCharArray();
            Arrays.sort(chars);

            // add key if not in map
            String key = String.valueOf(chars);
            if (!sortedGroups.containsKey(key)) {
                sortedGroups.put(key, new ArrayList<>());
            }
            // update anagram group for current key
            sortedGroups.get(key).add(word);
        }

        return new ArrayList<>(sortedGroups.values());
    }


    public static List<Integer> topFrequent(int[] values, int k) {
        // k = number, v = freq
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int v : values) {
            frequencies.put(v, frequencies.getOrDefault(v, 0) + 1);
        }
        // sort based on frequency
        return frequencies.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static List<Integer> topFrequentV2(int[] values, int k) {
        // k = number, v = freq
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int v : values) {
            frequencies.put(v, frequencies.getOrDefault(v, 0) + 1);
        }
        // create priority queue
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                Comparator.comparingInt(frequencies::get)
        );

        for (int n : frequencies.keySet()) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        List<Integer> topFrequent = new LinkedList<>();
        while (!heap.isEmpty()) {
            topFrequent.add(heap.poll());
        }
        Collections.reverse(topFrequent);
        return topFrequent;
    }

    public static List<Integer> mergeLists(int[][] listGroups) {
        List<Integer> merged = new ArrayList<>();
        for (int[] list : listGroups) {
            int i = 0;
            while (i < list.length) {
                merged.add(list[i]);
                i++;
            }
        }
        return merged.stream().sorted().collect(Collectors.toList());
    }

    public static int longestUniqueSubstring(String text) {
        int max = 1;
        for (int i = 1; i < text.length(); i++) {
            int seq = 1;
            if (text.charAt(i) != text.charAt(i - 1)) {
                String uniques = text.substring(i - 1, i);
                int j = i;
                while (j < text.length() && !uniques.contains("" + text.charAt(j))) {
                    j++;
                    seq++;
                    uniques = text.substring(i - 1, j);
                }
                if (j == text.length()) {
                    return max;
                }
                i = j;
            }
            if (max < seq) {
                max = seq;
            }
        }
        return max;
    }

    /**
     * rotate array to the right with k-steps for k < length
     * [1, 2, 3] k = 1
     * [3, 1, 2]
     * [1,2,3,4,5,6,7] k = 3
     * [5,6,7,1,2,3,4]
     * <p>
     * [5,6,7,4,1,2,3]
     */
    public static int[] rotateArray(int[] nums, int k) {
        if (k >= nums.length) {
            return null;
        }

        int j = 0;
        while (j < k) {
            int tmp = nums[nums.length - 1 - j];
            nums[nums.length - 1 - j] = nums[k - j - 1];
            nums[k - j - 1] = tmp;
            j++;
        }
        while (j + 1 < nums.length) {
            int tmp = nums[j + 1];
            nums[j + 1] = nums[j];
            nums[j] = tmp;
            j++;
        }

        return nums;
    }

    /**
     * for any k
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] rotateArrayV2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        return nums;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static Integer[] findMissingValues(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> missingValues = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                missingValues.add(i);
            }
        }
        return missingValues.toArray(new Integer[0]);
    }

    public static int[][] set0(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Step 1: Determine if the first row or first column needs to be zeroed
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Step 2: Use first row and column as markers for other rows and columns
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 3: Zero out marked rows and columns
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 4: Zero out the first row and column if needed
        if (firstRowZero) {
            Arrays.fill(matrix[0], 0);
        }
        if (firstColZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        return matrix;
    }

    public static List<Integer> prod(int[] values) {
        List<Integer> res = new ArrayList<>();
        int[] prefix = new int[values.length];
        prefix[0] = values[0];
        for (int i = 2; i < values.length; i++) {
            prefix[i] = values[i - 2] * values[i - 1];
        }

        return res;
    }

    /**
     * using hare tortoise algorithm, O(n) time O(1) space, no set
     *
     * @param nums
     * @return
     */

    public static int findDuplicate(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }

    /**
     * XOR returns 0 when two bits are the same and 1 when they are different, the order
     * in which you combine the numbers doesn't matter.
     * x XOR x = 0 and x XOR 0 = x. Therefore, if we XOR all the numbers
     * in the array, the duplicates will cancel out (since x XOR x = 0),
     * and we'll be left with the single number that doesn't have a duplicate.
     */
    public static int singleNumber(int[] values) {
        int single = 0;
        for (int val : values) {
            single ^= val;
        }
        return single;
    }

    /**
     * longest common sequence of characters at the start of each string for every string.
     *
     * @param words
     * @return
     */
    public static String longestCommonPrefix(String[] words) {
        String prefix = words[0]; // init prefix to first word

        for (int i = 1; i < words.length; i++) {
            if (prefix.charAt(0) != words[i].charAt(0)) {
                return ""; // return empty str on first mismatch
            } else {
                int j = 1;
                while ((j < words[i].length() && j < prefix.length()) && prefix.charAt(j) == words[i].charAt(j)) {
                    j++;
                }
                prefix = words[i].substring(0, j);
            }
        }
        return prefix;
    }

    public static boolean canJump(int[] values) {
        if (values[0] <= 0) {
            return false;
        }
        int max = 0;
        int idx = 0;
        while (idx < values.length - 1) {
            if (idx > max) {
                return false;
            }
            if (max < idx + values[idx]) {
                max = idx + values[idx];
            }
            idx++;
        }
        return max == values.length - 1;
    }


    public static int isPresent(Node root, int val) {
        if (root == null) {
            return 0;
        }
        if (root.data == val) {
            return 1;
        }
        return isPresent(root.left, val) == 1 ? 1 : isPresent(root.right, val);
    }

    public static void miniMaxSum(List<Integer> arr) {
        // get the sum of all values
        long fullSum = 0;
        for (int i = 0; i < arr.size(); i++) {
            fullSum += arr.get(i);
        }

        long minSum = fullSum;
        long maxSum = 0;
        // get sums for each value by subtracting the current value from the full sum.
        for (int i = 0; i < arr.size(); i++) {
            maxSum = Math.max(maxSum, fullSum - arr.get(i));
            minSum = Math.min(minSum, fullSum - arr.get(i));
        }
        System.out.println(minSum + " " + maxSum);
    }

    /**
     * [0, 1, 2, 3, 4, 5, 6, 7, 8]
     * [0, -2, -4, 1, 2, 4, 6, 7, 8] => 3
     * must run in O(n) Time
     * must take O(1) Space
     */
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int correctPos = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[correctPos];
                nums[correctPos] = temp;
            }
        }

        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    /**
     * it1: 20 / 2 = 10
     * it2: 10 / 2 = 5
     * it3: 5 / 2 = 2
     * it4: 2 / 2 = 1
     * it5: return
     */

    public static int binarySearchRecursive(int[] values, int k, int low, int high) {
        if (high >= low) {
            int mid = (high + low) / 2;
            if (values[mid] == k) {
                return mid;
            } else if (values[mid] > k) {
                return binarySearchRecursive(values, k, low, mid - 1);
            } else {
                return binarySearchRecursive(values, k, mid + 1, high);
            }
        } else {
            return -1;
        }
    }

    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        // (()())
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // push open paren in stack
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                // closing paren, check for its open
                if (stack.isEmpty() || stack.pop() != '(') {
                    // string is not balanced.
                    return false;
                }
            }
        }
        // stack is empty, all parentheses were matched; otherwise not balanced
        return stack.isEmpty();
    }

    /**
     * both lists are sorted and unique from 1 to 1000. Make random unique pairs of users and address ids ->
     * if there is a pair with user 32, it cannot be added to another pair.
     *
     * @param users
     * @param addresses
     * @return
     */
    public static List<Pair<Integer, Integer>> uniqueUserAddressPairs(List<Integer> users, List<Integer> addresses) {
        if (users.size() != addresses.size()) {
            throw new RuntimeException("User and address lists must be of the same size");
        }

        Collections.shuffle(users);
        Collections.shuffle(addresses);

        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            pairs.add(Pair.of(users.get(i), addresses.get(i)));
        }
        return pairs;
    }

    public static List<List<Integer>> getRanges(List<List<Integer>> ranges) {
        // sort by comparing starting points of intervals
        ranges.sort(Comparator.comparingInt(interval -> interval.get(0)));

        // merge sorted ranges to new list
        List<List<Integer>> merged = new ArrayList<>();
        // current interval to account for continuous overlapping intervals
        List<Integer> currentInterval = ranges.get(0);
        merged.add(currentInterval);

        for (List<Integer> range : ranges) {
            if (range.get(0) <= currentInterval.get(1)) {
                // update end of current interval
                currentInterval.set(1, Math.max(currentInterval.get(1), range.get(1)));
            } else {
                // Move to nex interval
                currentInterval = range;
                merged.add(currentInterval);
            }
        }

        return merged;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> view = new ArrayList<>();
        if (root == null) {
            return view;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelLength = queue.size();

            for (int i = 0; i < levelLength; ++i) {
                TreeNode node = queue.poll();
                // Replace or add the last node of each level
                if (i == levelLength - 1) {
                    view.add(node.val);
                }

                // Add child nodes in the queue
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return view;
    }


    public static String timeConversion(String timeString) {
        String amHour = timeString.substring(0, 2);
        String format24 = timeString.substring(0, timeString.length() - 2);
        if (timeString.endsWith("PM")) {
            return Integer.parseInt(amHour) < 12
                    ? format24.replace(amHour, Integer.parseInt(amHour) + 12 + "")
                    : format24;
        }
        return amHour.equals("12")
                ? format24.replace(amHour, "00")
                : format24;
    }


    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        List<Integer> results = new ArrayList<>();

        for (String query : queries) {
            int currentCount = 0;
            for (String string : strings) {
                if (query.equals(string)) {
                    currentCount++;
                }
            }
            results.add(currentCount);
        }

        return results;
    }

    /**
     * XOR returns 0 when two operands are the same,
     * and when you XOR all numbers together, the ones that are duplicates cancel
     * each other out (because x ^ x = 0), leaving the lonely integer.
     */
    public static int lonelyInteger(List<Integer> a) {
        int result = 0;
        for (int i : a) {
            result ^= i;
        }
        return result;
    }

    public static long flippingBits(long n) {
        // Since we're dealing with 32 bits, we'll use a mask of 32 ones (0xFFFFFFFF) to flip the bits
        long mask = 0xFFFFFFFFL; // The 'L' specifies that it's a long literal
        return n ^ mask; // XOR operation with the mask flips all bits
    }

    /**
     * int[][] matrix = {
     * {1, 2, 3},
     * {4, 5, 6},
     * {9, 8, 9}
     * }
     * <p>
     * 1 ,2 ,3 ,4
     * 5, 6, 7, 8,
     * 1, 2, 4, 5
     * 4, 5, 6, 7
     */
    public static int diagonalDifference(List<List<Integer>> arr) {
        //1. suml2r , sumr2l
        //2. Math.abs(l2r - r2l);
        int sumr = 0;
        int sumc = 0;
        for (int i = 0, j = arr.size() - 1; i < arr.size(); i++, j--) {
            sumc += arr.get(i).get(j);
            sumr += arr.get(i).get(i);
        }
        return Math.abs(sumr - sumc);
    }

    public static String pangrams(String s) {
        Set<Character> alphabet = new HashSet<>();
        for (char c = 'a'; c <= 'z'; c++) {
            alphabet.add(c);
        }
        for (int i = 0; i < s.trim().length(); i++) {
            if (!alphabet.contains(Character.toLowerCase(s.charAt(i)))) {
                return "not pangram";
            }
        }

        return "pangram";
    }

    public static int[] targetSum(int[] nums, int target) {
        Map<Integer, Integer> targetIndices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (targetIndices.containsKey(num)) {
                return new int[]{targetIndices.get(num), i};
            }
            targetIndices.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {

        return 1;
    }

}
