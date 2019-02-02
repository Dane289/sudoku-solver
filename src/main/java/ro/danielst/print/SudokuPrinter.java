package ro.danielst.print;

public class SudokuPrinter {

    public static String prettyPrint(int[][] sudoku) {
        StringBuilder sb = new StringBuilder();
        sb.append("*************************\n");

        for(int i = 0; i< sudoku.length; i++) {
            sb.append("* ");
            for (int j = 0; j< sudoku[i].length; j++) {
                sb.append(sudoku[i][j] + " ");
                if( j < 8 && (j + 1) % 3 == 0) {
                    sb.append("| ");
                }
                if(j == sudoku[i].length - 1) {
                    sb.append("*\n");
                }
            }
            if(i < 8 && (i + 1) % 3 == 0) {
                sb.append("*-----------------------*\n");
            }
        }
        sb.append("*************************\n");
        return sb.toString();
    }
}
