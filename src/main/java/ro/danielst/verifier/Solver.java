package ro.danielst.verifier;

import ro.danielst.print.SudokuPrinter;

import java.util.ArrayList;
import java.util.TreeSet;

public class Solver {
    public static int[][] solve(int[][] board) {

        if (humanWay(board)) {
            return board;
        } else {
            return new TreeSolver().solve(board);
        }
    }

    private static boolean humanWay(int[][] board) {
        for(int i = 0; i<board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (isCellEmpty(board[i][j])) {
                    int[] possibleEntries = findPossibleEntries(board, i, j);
                    if (possibleEntries.length == 1) {
                        board[i][j] = possibleEntries[0];
                        if (Verifier.isFull(board)) {
                            System.out.println("Solved human way");
                            return true;
                        }
                        i = -1;
                        j = -1;
                        break;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isCellEmpty(int cellValue) {
        return cellValue == 0;
    }


    public static int[] findPossibleEntries(int[][] board, int row, int col) {
        if(!isCellEmpty(board[row][col])) {
            return new int[0];
        }
        boolean[] possibleEntries = {true,true,true,true,true,true,true,true,true};
        findPossibleEntriesOnRowsAndColumns(board, row, col, possibleEntries);
        findPossibleEntriesOnCell(board, row, col, possibleEntries);

        ArrayList<Integer> entries = new ArrayList<>();
        for(int index = 0; index < possibleEntries.length; index++) {
            if(possibleEntries[index]) {
                entries.add(index + 1);
            }
        }
        int[] intArray = new int[entries.size()];
        for (int i = 0; i<intArray.length; i++) {
            intArray[i] = entries.get(i);
        }
        return intArray;
    }

    public static void findPossibleEntriesOnCell(int[][] board, int row, int col, boolean[] possibleEntries) {
        int startRow, startCol;
        startRow = resolveFirstCellOfArea(row);
        startCol = resolveFirstCellOfArea(col);

        for (int i = startRow; i < startRow + 3; i++) {
            for(int j = startCol; j<startCol + 3; j++) {
                if(!isCellEmpty(board[i][j])) {
                    possibleEntries[board[i][j] - 1] = false;
                }
            }
        }
    }

    private static int resolveFirstCellOfArea(int colOrRow) {
        if(colOrRow < 3) {
            return 0;
        } else if(colOrRow <6){
            return 3;
        } else {
            return 6;
        }
    }

    public static void findPossibleEntriesOnRowsAndColumns(int[][] board, int row, int col, boolean[] possibleEntries) {
        //verify row
        for (int j = 0; j<board.length; j++) {
            if(!isCellEmpty(board[row][j])) {
                possibleEntries[board[row][j] - 1] = false;
            }
        }

        //verify column
        for(int i = 0; i<board.length; i++){
            if(!isCellEmpty(board[i][col])) {
                possibleEntries[board[i][col] - 1] = false;
            }
        }
    }
}
