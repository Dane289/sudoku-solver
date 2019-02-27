package ro.danielst.ui;

import ro.danielst.model.SudokuBoard;

public class SudokuComboItem {
    private final String displayName;
    private final SudokuBoard board;

    public SudokuComboItem(String displayName, SudokuBoard board) {
        this.displayName = displayName;
        this.board = board;
    }

    public SudokuBoard getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
