package com.smoko.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoxSet {

    protected static final Random rand = new Random();

    protected List<GridBox> food;

    public BoxSet(int count, int maxX, int maxY) {
        food = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            food.add(new GridBox(rand.nextInt(maxX), rand.nextInt(maxY)));
        }
    }

    public int length() {
        return food.size();
    }

    public int getRectX(int i) {
        if (i < 0 || i >= food.size()) {
            throw new IllegalArgumentException("No such element in the food");
        }
        return food.get(i).getX();
    }

    public int getRectY(int i) {
        if (i < 0 || i >= food.size()) {
            throw new IllegalArgumentException("No such element in the food");
        }
        return food.get(i).getY();
    }

    public void removeFood(int i) {
        food.remove(i);
    }

    public int getPoints() {
        return 10;
    }

	public int getLength() {
		
		return food.size();
	}
}
