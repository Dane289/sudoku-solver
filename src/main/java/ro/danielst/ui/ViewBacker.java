package ro.danielst.ui;

public class ViewBacker implements ModelChangeListener {
    private final Controller controller;
    private int[][] basePuzzle;

    public ViewBacker(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void pushNewModel(int[][] newModel) {
        basePuzzle = newModel;
        controller.display(basePuzzle);
    }

}
