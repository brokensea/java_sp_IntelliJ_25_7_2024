package de.supercode.dAY35_9_8_2024.oop;

import java.util.Scanner;

public class Game {
    private Board board;
    private boolean gameOver;

    public Game(int rows, int cols, int mines) {
        this.board = new Board(rows, cols, mines);
        this.gameOver = false;
    }

    public void start() {
        board.initializeBoard();  // 确保这个方法被调用
        board.printBoard();
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            board.printBoard();
            System.out.print("Enter (r/f row col): ");
            String input = scanner.nextLine();
            processInput(input);
        }
        scanner.close();
    }


    private void processInput(String input) {
        try {
            String[] parts = input.trim().split(" ");
            if (parts.length != 3) {
                System.out.println("Invalid input format. Please use the format (r/f row col).");
                return;
            }

            String action = parts[0];
            int row = Integer.parseInt(parts[1]);
            int col = Integer.parseInt(parts[2]);

            if (row < 0 || row >= board.getRows() || col < 0 || col >= board.getCols()) {
                System.out.println("Invalid row or column index.");
                return;
            }

            if (action.equals("r")) {
                board.revealCell(row, col);
                if (board.allSafeCellsRevealed()) {
                    System.out.println("You've won the game.");
                    gameOver = true;
                }
            } else if (action.equals("f")) {
                board.toggleFlag(row, col);
            } else {
                System.out.println("Invalid action. Use 'r' to reveal or 'f' to flag.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter integers for row and column.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
