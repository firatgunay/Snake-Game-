/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.util.Random;

/**
 *
 * @author firatgunay
 */
public class GameBoard {
    private int size;
    private Snake snake;
    private Food food;
    private Bomb bomb;

    public GameBoard(int size) {
        this.size = size;
        initializeGame();
    }

    private void initializeGame() {
        snake = new Snake(new Point(size / 2, size / 2));
        generateFood();
        generateBomb();
    }

    private void generateFood() {
        Random random = new Random();
        Point foodPosition = generateRandomPosition();
        while (snake.collidesWith(foodPosition)) {
            foodPosition = generateRandomPosition();
        }
        food = new Food(foodPosition);
    }

    private void generateBomb() {
        Random random = new Random();
        Point bombPosition = generateRandomPosition();
        while (snake.collidesWith(bombPosition) || bombPosition.equals(food.getPosition())) {
            bombPosition = generateRandomPosition();
        }
        bomb = new Bomb(bombPosition);
    }

    private Point generateRandomPosition() {
        Random random = new Random();
        return new Point(random.nextInt(size), random.nextInt(size));
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Point currentPoint = new Point(i, j);
                if (snake.getHead().getPosition().equals(currentPoint)) {
                    System.out.print("@ "); // Yılanın başı
                } else if (snake.collidesWith(currentPoint)) {
                    System.out.print("| "); // Yılanın vücudu
                } else if (food.getPosition().equals(currentPoint)) {
                    System.out.print("F "); // Yem
                } else if (bomb.getPosition().equals(currentPoint)) {
                    System.out.print("X "); // Bomba
                } else {
                    System.out.print(". "); // Boş hücre
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean checkCollision() {
        return snake.collidesWithItself() || snakeCollidesWithFood() || snakeCollidesWithBomb() || snake.getNodeCount() <= 3;
    }

    private boolean snakeCollidesWithFood() {
        return snake.getHead().getPosition().equals(food.getPosition());
    }

    private boolean snakeCollidesWithBomb() {
        return snake.getHead().getPosition().equals(bomb.getPosition());
    }
    
    private boolean checkGameOver() {
        return snake.collidesWithItself() || snake.getNodeCount() <= 3;
    }

    public void moveSnake(String direction) {
        Point newHeadPosition = calculateNewHeadPosition(direction);
        snake.move(newHeadPosition);

        if (snakeCollidesWithFood()) {
            snake.grow(food.getPosition());
            generateFood();
        }

        if (snakeCollidesWithBomb()) {
            snake.shrink();
            generateBomb();
        }

        if (checkCollision()) {
            System.out.println("Oyun bitti!");
            System.exit(0);
        }

        if (checkGameOver()) {
            System.out.println("Oyun sonlandı!");
            System.exit(0);
        }
    }

    private Point calculateNewHeadPosition(String direction) {
        Point head = snake.getHead().getPosition();
        switch (direction.toLowerCase()) {
            case "up":
                return new Point(head.getX() - 1, head.getY());
            case "down":
                return new Point(head.getX() + 1, head.getY());
            case "left":
                return new Point(head.getX(), head.getY() - 1);
            case "right":
                return new Point(head.getX(), head.getY() + 1);
            default:
                return head;
        }
    }
}

