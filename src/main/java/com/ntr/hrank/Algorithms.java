package com.ntr.hrank;


import java.util.*;

public class Algorithms {

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

}


