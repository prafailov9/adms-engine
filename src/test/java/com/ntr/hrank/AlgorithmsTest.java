package com.ntr.hrank;

import com.ntr.cache.DoublyLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        int[] array = new int[] {1, 5, 3, 8, 6, 11, 2};

        // index range for the sorting algorithm to work upon
        int low = 0;
        int high = array.length - 1;

        Algorithms.quicksort(array, low, high);

        for (int j : array) {
            System.out.println(j);
        }
    }

}
