package com.smoko.pieces;

public class MovingFood extends BoxSet {

    private int maxX;
    private int maxY;

    public MovingFood(int count, int maxX, int maxY) {
        super(count, maxX, maxY);
        this.maxX = maxX;
        this.maxY = maxY;
    }

    @Override
    public int getPoints() {
        return 15;
    }

    public void move() {
        for (GridBox box: food) {
            randomMove(box);
        }
    }

    private void randomMove(GridBox box) {
        int r = rand.nextInt(4);
        switch (r) {
            case 0: move(box, -1, 0); break; // LEFT
            case 1: move(box, 1, 0); break; // RIGHT
            case 2: move(box, 0, -1); break; // UP
            case 3: move(box, 0, 1); break; // DOWN
        }
    }

    private void move(GridBox box, int offsetX, int offsetY) {
        int futureX = box.getX() + offsetX;
        if (futureX < 0) {
            futureX = maxX - 1;
        } else if (futureX >= maxX) {
            futureX = 0;
        }
        int futureY = box.getY() + offsetY;
        if (futureY < 0) {
            futureY = maxY - 1;
        } else if (futureY >= maxY) {
            futureY = 0;
        }
        box.setX(futureX);
        box.setY(futureY);
    }
}

