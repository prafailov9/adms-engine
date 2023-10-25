package com.ntr.ds.list;

import com.ntr.cache.DoublyLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DoublyLinkedListTest {

    private DoublyLinkedList<Integer> doublyLinkedList;


    @BeforeEach
    public void setup() {
        doublyLinkedList = new DoublyLinkedList<>();
    }

    @Test
    public void addTest() {
        addItems(3);

        Assertions.assertEquals(doublyLinkedList.size(), 3);
    }

    @Test
    public void removeTest() {
        addItems(3);

        for (int i = 1; i <= 3; i++) {
            doublyLinkedList.remove();
        }

        Assertions.assertEquals(doublyLinkedList.size(), 0);
    }

    private void addItems(final int numOfItems) {
        for(int i = 1; i <= numOfItems; i++) {
            doublyLinkedList.add(i);
        }
    }

}