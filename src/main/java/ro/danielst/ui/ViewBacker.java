package ro.danielst.ui;

import ro.danielst.Puzzles;
import ro.danielst.model.SudokuBoard;
import ro.danielst.verifier.Solver;

import java.util.ArrayList;
import java.util.List;

public class ViewBacker implements ModelChangeListener {
    private Controller controller;
    private int[][] basePuzzle, workingPuzzle;
    private int speed = 500;
    private List<List<Change>> steps = new ArrayList<>();
    private int currentStep = 0;

    public ViewBacker() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
        basePuzzle = new Puzzles().PUZZLE_5;
    }

    @Override
    public void pushNewModel(int[][] newModel) {
        steps.add(findChanges(basePuzzle, newModel));
        this.basePuzzle = new SudokuBoard(newModel).getBoard();
    }

    private List<Change> findChanges(int[][] basePuzzle, int[][] newModel) {
        List<Change> changes = new ArrayList<>();
        for(int i = 0; i< newModel.length; i++) {
            for(int j = 0; j < newModel[i].length; j++) {
                if(basePuzzle[i][j] != newModel[i][j]) {
                    changes.add(new Change(i, j, newModel[i][j], basePuzzle[i][j]));
                }
            }
        }
        return changes;
    }

    public void changeValue() {
    }

    public void nextState(){
        if(currentStep - 1 == steps.size()) {
            return;
        }
        for(Change c : steps.get(currentStep)) {
            workingPuzzle[c.getRow()][c.getCol()] = c.getNewValue();
        }
        currentStep++;
        controller.display(workingPuzzle);
    }

    public void previousState() {
        if(currentStep == 0) {
            return;
        }
        currentStep--;
        for(Change c : steps.get(currentStep)) {
            workingPuzzle[c.getRow()][c.getCol()] = c.getOldValue();
        }
        controller.display(workingPuzzle);
    }

    public void sliderChange() {
        speed = (int) (1000 - 10 * controller.getSpeed());
    }

    public void start() {
        new Solver(this).solve(new SudokuBoard(basePuzzle).getBoard());
    }

    public void setBasePuzzle(int[][] sudokuBoard) {
        this.basePuzzle = sudokuBoard;
        this.workingPuzzle = new SudokuBoard(basePuzzle).getBoard();
        controller.display(basePuzzle);
    }
}
