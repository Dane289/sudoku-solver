package ro.danielst.verifier;

public class Verifier {
    public static boolean isFull(int[][] board) {
        for(int i = 0; i< board.length; i++) {
            for(int j = 0; j< board[i].length; j++) {
                if(board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
