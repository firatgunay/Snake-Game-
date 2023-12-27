/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.util.LinkedList;

/**
 *
 * @author firatgunay
 */
public class Snake {
      private LinkedList<SnakeNode> body;

    public Snake(Point initialPosition) {
        body = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Point nodePosition = new Point(initialPosition.getX() + i, initialPosition.getY());
            SnakeNode node = new SnakeNode(nodePosition);
            body.add(node);
        }
    }

    public LinkedList<SnakeNode> getBody() {
        return body;
    }

    public SnakeNode getHead() {
        return body.getFirst();
    }

    public void move(Point newPosition) {
        SnakeNode newHead = new SnakeNode(newPosition);
        newHead.setNext(body.getFirst());
        body.addFirst(newHead);
        body.removeLast(); // Son düğümü kaldır, çünkü yılan ilerledi
    }

    public void grow(Point foodPosition) {
        SnakeNode newTail = new SnakeNode(foodPosition);
        body.getLast().setNext(newTail);
        body.addLast(newTail);
    }

    public void shrink() {
        if (body.size() > 1) {
            body.removeLast();
        }
    }

    public boolean collidesWith(Point point) {
        return body.stream().anyMatch(node -> node.getPosition().equals(point));
    }

    public boolean collidesWithItself() {
        Point headPosition = getHead().getPosition();
        return body.stream().skip(1).anyMatch(node -> node.getPosition().equals(headPosition));
    }

    public int getNodeCount() {
        return body.size();
    }
}
