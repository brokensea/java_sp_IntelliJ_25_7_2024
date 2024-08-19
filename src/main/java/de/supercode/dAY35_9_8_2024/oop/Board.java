package de.supercode.dAY35_9_8_2024.oop;

import java.util.Random;

public class Board {
    private Cell[][] board;
    private int rows;
    private int cols;
    private int totalMines;


    public Board(int rows, int cols, int totalMines) {
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        this.board = new Cell[rows][cols];
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getTotalMines() {
        return totalMines;
    }

    public void setTotalMines(int totalMines) {
        this.totalMines = totalMines;
    }

    public void initializeBoard() {
        // 初始化棋盘上的每个单元格
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell(false, false, false, 0);
            }
        }

        // 放置地雷
        placeMines();

        // 计算每个单元格的相邻地雷数量
        calculateAdjacentMines();
    }

    private void placeMines() {
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            if (!board[r][c].hasMine()) {
                board[r][c].setMine(true);
                minesPlaced++;
            }
        }
    }

   /* private void calculateAdjacentMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!board[i][j].hasMine()) {
                    int count = countAdjacentMines(i, j);
                    board[i][j].setAdjacentMines(count);
                }
            }
        }
    }*/

    private void calculateAdjacentMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!board[i][j].hasMine()) {
                    int count = countAdjacentMines(i, j);
                    board[i][j].setAdjacentMines(count);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (isValidPosition(newRow, newCol) && board[newRow][newCol].hasMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /* public void printBoard() {
         System.out.println("Board Size: " + rows + " x " + cols);
         for (int i = 0; i < rows; i++) {
             for (int j = 0; j < cols; j++) {
                 Cell cell = board[i][j];
                 if (cell == null) {
                     System.out.print("E "); // 这里打印错误标记
                 } else if (cell.isRevealed()) {
                     if (cell.hasMine()) {
                         System.out.print("* ");
                     } else {
                         int adjacentMines = cell.getAdjacentMines();
                         System.out.print(adjacentMines == 0 ? "  " : adjacentMines + " ");
                     }
                 } else if (cell.isFlagged()) {
                     System.out.print("F ");
                 } else {
                     System.out.print(". ");
                 }
             }
             System.out.println();
         }
     }*/
    public void printBoard() {
        System.out.println("Board Size: " + rows + " x " + cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = board[i][j];
                if (cell.isRevealed()) {
                    if (cell.hasMine()) {
                        System.out.print("* ");
                    } else {
                        int adjacentMines = cell.getAdjacentMines();
                        System.out.print(adjacentMines == 0 ? "  " : adjacentMines + " ");
                    }
                } else if (cell.isFlagged()) {
                    System.out.print("F ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }


    public void revealCell(int row, int col) {
        // 先检查是否越界
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return; // 如果坐标越界，什么都不做
        }

        Cell cell = board[row][col];
        if (cell.isRevealed() || cell.isFlagged()) {
            return; // 如果已经被揭开或被标记，什么都不做
        }

        cell.reveal(); // 揭开单元格

        if (cell.hasMine()) {
            System.out.println("You hit a mine! Game over.");
            printBoard(); // 显示包含雷的棋盘
            System.exit(0); // 游戏结束
        }

        // 检查是否所有安全单元格都被揭开
        if (allSafeCellsRevealed()) {
            System.out.println("You've won the game.");
            printBoard(); // 显示整个棋盘
            System.exit(0); // 游戏结束
        }
    }


    public boolean allSafeCellsRevealed() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = board[i][j];
                if (!cell.hasMine() && !cell.isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }


    // markiert F
    public void toggleFlag(int row, int col) {
        if (isValidPosition(row, col) && !board[row][col].isRevealed()) {
            board[row][col].toggleFlag();
        }
    }


}
