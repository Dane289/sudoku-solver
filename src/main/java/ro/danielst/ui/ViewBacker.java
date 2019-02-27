package ro.danielst.ui;

import ro.danielst.Puzzles;
import ro.danielst.model.SudokuBoard;
import ro.danielst.verifier.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewBacker implements ModelChangeListener {
    private Controller controller;
    private int[][] basePuzzle, workingPuzzle, diffPuzzle;
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
        steps.add(findChanges(diffPuzzle, newModel));
        diffPuzzle = new SudokuBoard(newModel).getBoard();
    }

    private List<Change> findChanges(int[][] basePuzzle, int[][] newModel) {
        List<Change> changes = new ArrayList<>();
        for (int i = 0; i < newModel.length; i++) {
            for (int j = 0; j < newModel[i].length; j++) {
                if (basePuzzle[i][j] != newModel[i][j]) {
                    changes.add(new Change(i, j, newModel[i][j], basePuzzle[i][j]));
                }
            }
        }
        return changes;
    }

    public void solve() {
        controller.display(diffPuzzle, false);
        currentStep = steps.size();
        controller.setProgression(currentStep);
        controller.enablePrevNextButtons(false);
    }

    public void nextState() {
        nextState(true);
    }

    private void nextState(boolean updateView) {
        if (currentStep == steps.size()) {
            return;
        }
        for (Change c : steps.get(currentStep)) {
            workingPuzzle[c.getRow()][c.getCol()] = c.getNewValue();
        }
        currentStep++;
        controller.display(workingPuzzle, true);
        if (updateView) {
            controller.setProgression(currentStep);
        }
    }

    public void previousState() {
        previousState(true);
    }

    private void previousState(boolean updateView) {
        if (currentStep == 0) {
            return;
        }
        currentStep--;
        for (Change c : steps.get(currentStep)) {
            workingPuzzle[c.getRow()][c.getCol()] = c.getOldValue();
        }
        controller.display(workingPuzzle, true);
        if (updateView) {
            controller.setProgression(currentStep);
        }
    }

    public void sliderChange() {
        int progression = (int) controller.getProgression();

        if (progression < currentStep) {
            for (int i = currentStep; i > progression; i--) {
                previousState(false);
            }
        } else {
            for (int i = currentStep; i < progression; i++) {
                nextState(false);
            }
        }
        System.out.println("size " + steps.size());
        System.out.println("step " + progression);
    }

    public void reset() {
        workingPuzzle = new SudokuBoard(basePuzzle).getBoard();
        currentStep = 0;
        controller.setProgression(currentStep);
        controller.display(basePuzzle, false);
        controller.enablePrevNextButtons(true);
    }

    public void newPuzzleSelected() {
        setBasePuzzle(controller.getSelectedPuzzle().getBoard());
    }

    public void setBasePuzzle(int[][] sudokuBoard) {
        steps.clear();
        currentStep = 0;
        this.basePuzzle = new SudokuBoard(sudokuBoard).getBoard();
        this.diffPuzzle = new SudokuBoard(sudokuBoard).getBoard();
        this.workingPuzzle = new SudokuBoard(basePuzzle).getBoard();
        controller.display(basePuzzle, false);
        new Solver(this).solve(new SudokuBoard(sudokuBoard).getBoard());
        controller.setProgressionMax(this.steps.size());
        controller.setProgression(currentStep);
    }

    public void initView() {
        controller.initPuzzleList(Arrays.asList(
                new SudokuBoard(new Puzzles().PUZZLE_5),
                new SudokuBoard(new Puzzles().PUZZLE_2),
                new SudokuBoard(new Puzzles().PUZZLE_EXTREME),
                new SudokuBoard(new Puzzles().PUZZLE_6)
        ));
    }
}
