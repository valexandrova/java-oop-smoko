package com.smoko.manager;

import com.smoko.pieces.BoxSet;
import com.smoko.pieces.MovingFood;
import com.smoko.pieces.Snake;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoardManager extends JPanel {

    private static final int BOARD_WIDTH = 30;
    private static final int BOARD_HEIGHT = 30;
    private static final int SQUARE_SIZE = 20;


    private Snake snake = new Snake(BOARD_WIDTH, BOARD_HEIGHT);
    private BoxSet obstacles = new BoxSet(20, BOARD_WIDTH, BOARD_HEIGHT);
    private BoxSet staticFood = new BoxSet(20, BOARD_WIDTH, BOARD_HEIGHT);
    private MovingFood movingFood = new MovingFood(20, BOARD_WIDTH, BOARD_HEIGHT);
    private Timer snakeTimer;
    private Timer movingFoodTimer;

    private int points = 0;

    private JLabel gameStatus = new JLabel("Points: " + points);

    public GameBoardManager() {
      
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyAction(e);
            }
        });
        setFocusable(true);

        add(gameStatus);

        snakeTimer = new Timer(100, (ActionEvent e) -> move());
        snakeTimer.start();
        movingFoodTimer = new Timer(1000, (ActionEvent e) -> movingFood.move());
        movingFoodTimer.start();
    }

    public static int getBoardWidth() {
        return BOARD_WIDTH * SQUARE_SIZE + 15;
    }

    public static int getBoardHeight() {
        return BOARD_HEIGHT * SQUARE_SIZE + 40;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set square borders to be wider
        ((Graphics2D) g).setStroke(new BasicStroke(2));

        // Draw already fell pieces, saved in board matrix
   
        g.setColor(Color.CYAN);
        for (int i = 0; i < snake.getLength(); i++) {
            g.fillRect(snake.getRectX(i) * SQUARE_SIZE, snake.getRectY(i) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }

        g.setColor(Color.YELLOW);
        drawBoxSet(g, staticFood);

        g.setColor(Color.RED);
        drawBoxSet(g, movingFood);

        g.setColor(Color.BLACK);
        drawBoxSet(g, obstacles);
    }

    private void drawBoxSet(Graphics g, BoxSet food) {
        for (int i = 0; i < food.length(); i++) {
            g.fillRect(food.getRectX(i) * SQUARE_SIZE, food.getRectY(i) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    private void move() {
        snake.move();
        int headX = snake.getHeadX();
        int headY = snake.getHeadY();
        if (isDead(headX, headY)) {
            snakeTimer.stop();
            movingFoodTimer.stop();
            gameStatus.setText("Game Over! Points: " + points);
        } else if (points == 300) {
        	 snakeTimer.stop();
             movingFoodTimer.stop();
            gameStatus.setText("Congratulations! You achieved 300 points!");
        } else {
            tryToEat(staticFood, headX, headY);
            tryToEat(movingFood, headX, headY);
            repaint();
        }
    }

    private boolean isDead(int headX, int headY) {
        for (int i = 0; i < obstacles.length(); i++) {
            if (obstacles.getRectX(i) == headX && obstacles.getRectY(i) == headY) {
                return true;
            }
        }
        for (int i = 0; i < snake.getLength() - 1; i++) {
            if (snake.getRectX(i) == headX && snake.getRectY(i) == headY) {
                return true;
            }
        }
        return false;
    }

    private void tryToEat(BoxSet food, int headX, int headY) {
        for (int i = 0; i < food.length(); i++) {
            if (food.getRectX(i) == headX && food.getRectY(i) == headY) {
                snake.eat(headX, headY);
                points += food.getPoints();
                gameStatus.setText("Points: " + points);
                food.removeFood(i);
            }
        }
    }

    private void keyAction(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            snake.setDirection('U');
        } else if (code == KeyEvent.VK_DOWN) {
            snake.setDirection('D');
        } else if (code == KeyEvent.VK_LEFT) {
            snake.setDirection('L');
        } else if (code == KeyEvent.VK_RIGHT) {
            snake.setDirection('R');
        }
    }
}
