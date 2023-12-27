/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;
/**
 *
 * @author firatgunay
 */
public class SnakeNode extends GameObject{
   private SnakeNode next;

    public SnakeNode(Point position) {
        this.position = position;
        this.next = null;
    }

    public SnakeNode getNext() {
        return next;
    }

    public void setNext(SnakeNode next) {
        this.next = next;
    }
}
