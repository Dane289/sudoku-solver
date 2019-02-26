package ro.danielst.ui;

public class Change {
    private final int row, col, newValue, oldValue;

    public Change(int row, int col, int newValue, int oldValue) {
        this.row = row;
        this.col = col;
        this.newValue = newValue;
        this.oldValue = oldValue;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getNewValue() {
        return newValue;
    }

    public int getOldValue() {
        return oldValue;
    }
}
