/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snakegame;

import java.util.Scanner;
/**
 *
 * @author firatgunay 
 *         erdem kucur  
 */
public class Main {
    
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);

        System.out.print("Oyun tahtası boyutunu girin: ");
        int boardSize = scanner.nextInt();
        GameBoard gameBoard = new GameBoard(boardSize);

        while (true) {
            gameBoard.printBoard();
            System.out.print("Yön seçin (up/down/left/right/exit): ");
            String direction = scanner.next();

            if (direction.equals("exit")) {
                System.out.println("Oyun sonlandırıldı.");
                break;
            }

            gameBoard.moveSnake(direction);
        }

        scanner.close();
    }

}

    

