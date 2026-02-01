package com.ntr.cache;

public class HashIndex {

    private int[] keys;
    private String[] values;
    private boolean[] occupied;
    private int capacity;

    public HashIndex(int size) {
        this.capacity = size;
        this.keys = new int[size];
        this.values = new String[size];
        this.occupied = new boolean[size];
    }

    private int hash(int key) {
        return key % capacity;
    }

    public void insert(int key, String value) {
        int index = hash(key);
        int startIndex = index;

        while (occupied[index]) {
            if (keys[index] == key) {
                values[index] = value;
                return;
            }
            index = (index + 1) % capacity;
            if (index == startIndex) { // Full table check
                System.out.println("Hash table is full!");
                return;
            }
        }

        keys[index] = key;
        values[index] = value;
        occupied[index] = true;
    }
//    G = 7
//    C = 70

}
