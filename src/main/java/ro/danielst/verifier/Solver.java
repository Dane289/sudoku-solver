package ro.danielst.verifier;

import ro.danielst.print.SudokuPrinter;

import java.util.ArrayList;

public class Solver {
    public static int[][] solve(int[][] board) {

        if (humanWay(board)) {
            return board;
        } else {
            System.out.println("Human way failed");
            System.out.println(SudokuPrinter.prettyPrint(board));
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {
                    int[] possibleEntries = findPossibleEntries(board, x, y);
                    if (board[x][y] == 0 && possibleEntries.length == 2) {
                        System.out.println("Found a cell with 2 possibilities " + x + " " + y + "=" + possibleEntries[0] + possibleEntries[1]);
                        int[][] clone = cloneBoard(board);
                        clone[x][y] = possibleEntries[0];
                        if(humanWay(clone)){
                            System.out.println(SudokuPrinter.prettyPrint(clone));
                            return clone;
                        } else {
                            clone[x][y] = possibleEntries[1];
                            humanWay(clone);
                            System.out.println(SudokuPrinter.prettyPrint(clone));
                            return clone;
                        }
                    }
                }
            }
        }
        return board;
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


    private static boolean humanWay(int[][] board) {
        for(int i = 0; i<board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    int[] possibleEntries = findPossibleEntries(board, i, j);
                    if (possibleEntries.length == 1) {
                        board[i][j] = possibleEntries[0];
                        if (Verifier.isFull(board)) {
                            System.out.println("Solved");
                            return true;
                        } else {
                            System.out.println("not full yet");
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


    public static int[] findPossibleEntries(int[][] board, int row, int col) {
        if(board[row][col] != 0) {
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
        if(row < 3) {
            startRow = 0;
        } else if(row<6){
            startRow = 3;
        } else {
            startRow = 6;
        }
        if(col < 3) {
            startCol = 0;
        } else if(col<6){
            startCol = 3;
        } else {
            startCol = 6;
        }

        for (int i = startRow; i < startRow + 3; i++) {
            for(int j = startCol; j<startCol + 3; j++) {
                if(board[i][j] != 0) {
                    possibleEntries[board[i][j] - 1] = false;
                }
            }
        }

    }

    public static void findPossibleEntriesOnRowsAndColumns(int[][] board, int row, int col, boolean[] possibleEntries) {
        //verify row
        for (int j = 0; j<board.length; j++) {
            if(board[row][j] != 0) {
                possibleEntries[board[row][j] - 1] = false;
            }
        }

        //verify column
        for(int i = 0; i<board.length; i++){
            if(board[i][col] != 0) {
                possibleEntries[board[i][col] - 1] = false;
            }
        }
    }
}