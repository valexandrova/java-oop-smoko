package com.smoko.pieces;

public class GridBox {
    private int x;
    private int y;

    public GridBox() {
        this.x = 0;
        this.y = 0;
    }

    public GridBox(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
