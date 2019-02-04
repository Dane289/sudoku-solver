package ro.danielst.verifier;

import ro.danielst.model.SudokuBoard;
import ro.danielst.tree.MyTree;
import ro.danielst.tree.Node;

public class TreeSolver {

    private MyTree<SudokuBoard> tree;
    private SudokuBoard solution;

    public int[][] solve(int[][] board) {
        tree = new MyTree<SudokuBoard>(new Node<SudokuBoard>(new SudokuBoard(board)));

        Node<SudokuBoard> root = tree.getRoot();
        generateNodes(root);

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
                if(root.getValue().getBoard()[i][j] == 0) {
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
