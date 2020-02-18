package com.smoko.pieces;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private List<GridBox> snake;
    private GridBox head;
   

    public Snake() {
        
        this.snake = new ArrayList<>();
        this.head = new GridBox();
        this.snake.add(new GridBox());
    
    }

   

    public int getLength() {
        return snake.size();
    }

    public void eat(int x, int y) {
        snake.add(new GridBox(x, y));
    }


    public void move() {
     
    }


}

