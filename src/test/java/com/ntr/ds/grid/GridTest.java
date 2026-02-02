package com.ntr.ds.grid;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    private Grid grid = new Grid(3);

    @Test
    public void t1() {
        int c = 0;

        while (c < 100) {
            changeGridState("open");

            changeGridState("closed");
            changeGridState("nil");
            c++;
        }

    }

    private void changeGridState(String state) {
        for (int i = 0; i < grid.getCells().size(); i++) {
            for (int j = 0; j < grid.getCells().size(); j++) {
                Cell cell = grid.getCells().get(i).get(j);
                cell.setState(state);
            }
        }
        System.out.println(grid);
    }

}