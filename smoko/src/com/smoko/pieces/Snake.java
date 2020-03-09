package com.smoko.pieces;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private int maxX;
    private int maxY;

    private List<GridBox> snake;
    private GridBox head;
    private char direction;

    public Snake(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.direction = 'L';
        this.snake = new ArrayList<>();
        this.head = new GridBox();
        this.snake.add(new GridBox());
    
    }

    public void setDirection(char direction) {
        if ((this.direction == 'U' && direction == 'D') || (this.direction == 'D' && direction == 'U')
                || (this.direction == 'R' && direction == 'L') || (this.direction == 'L' && direction == 'R')) {
            return;
        }
        this.direction = direction;
    }

    public int getLength() {
        return snake.size();
    }

    public void eat(int x, int y) {
        snake.add(new GridBox(x, y));
    }

    public int getHeadX() {
        return head.getX();
    }

    public int getHeadY() {
        return head.getY();
    }

    public int getRectX(int i) {
        if (i < 0 || i >= snake.size()) {
            throw new IllegalArgumentException("No such element in the snake");
        }
        return snake.get(i).getX();
    }

    public int getRectY(int i) {
        if (i < 0 || i >= snake.size()) {
            throw new IllegalArgumentException("No such element in the snake");
        }
        return snake.get(i).getY();
    }

    public void move() {
        switch (direction) {
            case 'U':
                moveHead(0, -1);
                break;
            case 'D':
                moveHead(0, 1);
                break;
            case 'L':
                moveHead(-1, 0);
                break;
            case 'R':
                moveHead(1, 0);
                break;
        }

        int lastIndex = snake.size() - 1;
        for (int i = 0; i < lastIndex; i++) {
            final GridBox nextBox = snake.get(i + 1);
            snake.get(i).setX(nextBox.getX());
            snake.get(i).setY(nextBox.getY());
        }
        // set head
        snake.get(lastIndex).setX(head.getX());
        snake.get(lastIndex).setY(head.getY());
    }


    private void moveHead(int offsetX, int offsetY) {
        int futureX = head.getX() + offsetX;
        if (futureX < 0) {
            futureX = maxX - 1;
        } else if (futureX >= maxX) {
            futureX = 0;
        }
        int futureY = head.getY() + offsetY;
        if (futureY < 0) {
            futureY = maxY - 1;
        } else if (futureY >= maxY) {
            futureY = 0;
        }
        head.setX(futureX);
        head.setY(futureY);
    }
}
