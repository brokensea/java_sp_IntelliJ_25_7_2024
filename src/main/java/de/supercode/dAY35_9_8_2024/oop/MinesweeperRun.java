package de.supercode.dAY35_9_8_2024.oop;

import java.util.Scanner;

public class MinesweeperRun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();

        System.out.print("Enter number of mines: ");
        int mines = scanner.nextInt();

        Game game = new Game(rows, cols, mines);
        game.start();
    }
}