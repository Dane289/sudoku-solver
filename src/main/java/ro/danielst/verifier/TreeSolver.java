package ro.danielst.verifier;

import ro.danielst.model.SudokuBoard;
import ro.danielst.tree.Node;

public class TreeSolver {

    private SudokuBoard solution;

    public int[][] solve(int[][] board) {
        generateNodes(new Node<>(new SudokuBoard(board)));
        if(solution != null) {
            System.out.println("Solved - brute force");
        }
        return solution.getBoard();
    }

    private void generateNodes(Node<SudokuBoard> root) {
        if(Verifier.isFull(root.getValue().getBoard())) {
            solution = root.getValue();
            return;
        }
            if(solution == null && fillCellWithAllPossibleValues(root)) {
                for(Node<SudokuBoard> n : root.getChildren()) {
                    generateNodes(n);
                }
            }
    }

    private boolean fillCellWithAllPossibleValues(Node<SudokuBoard> root) {
        for(int i=0; i<root.getValue().getBoard().length; i++) {
            for(int j = 0; j< root.getValue().getBoard()[i].length; j++){
                if(Solver.isCellEmpty(root.getValue().getBoard()[i][j])) {
                    int[] possibleEntries = Solver.findPossibleEntries(root.getValue().getBoard(), i, j);
                    if(possibleEntries.length == 0) {
                        return false;
                    }
                    for (int p = 0; p < possibleEntries.length; p++) {
                        root.getValue().getBoard()[i][j] = possibleEntries[p];
                        Node<SudokuBoard> n = new Node<>(new SudokuBoard(root.getValue().getBoard()));
                        root.addChild(n);
                    }
                    return true;
                }

            }
        }
        return false;
    }
}
