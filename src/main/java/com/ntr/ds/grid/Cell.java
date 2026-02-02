package com.ntr.ds.grid;

public class Cell {

    State state;

    Cell(String state) {
        setState(state);
    }

    public void setState(String state) {
        state = state.toUpperCase();
        if (!state.equals("NIL") && !state.equals("OPEN") && !state.equals("CLOSED")) {
            throw new IllegalArgumentException("invalid state: " + state);
        }
        this.state = State.valueOf(state);
    }

    enum State {
        NIL, OPEN, CLOSED
    }

    @Override
    public String toString() {
        return String.format("{%s}", state.name());
    }
}