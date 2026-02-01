package com.ntr.ds.grid;

import java.util.ArrayList;
import java.util.List;

/**
 * NxN grid
 */
public class Grid {

    private List<List<Cell>> cells;
    private int len;

    public Grid(int len) {
        if (len < 1) {
            throw new IllegalArgumentException("Invalid length given: " + len);
        }

        this.len = len;
        initGrid();

    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    private void initGrid() {
        cells = new ArrayList<>(len);
        int c = 0;
        while (c < len) {
            List<Cell> col = new ArrayList<>(len);
            for (int i = 0; i < len; i++) {
                col.add(new Cell("nil"));
            }
            cells.add(col);
            c++;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        cells.forEach(c -> sb.append(c).append("\n"));

        return sb.toString();
    }

}
