package ro.danielst.model;

import ro.danielst.print.SudokuPrinter;

import java.util.Arrays;

public class SudokuBoard {
    private int[][] board;

    public SudokuBoard(int[][] board) {
        this.board = cloneBoard(board);
    }

    private static int[][] cloneBoard(int[][] board) {
        int[][] clone = new int[9][9];
        for(int i =0 ; i<board.length; i++) {
            for (int j = 0; j<board.length; j++) {
                clone[i][j] = board[i][j];
            }
        }
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuBoard that = (SudokuBoard) o;
        return Arrays.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }

    public int[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return SudokuPrinter.prettyPrint(board);
    }
}
