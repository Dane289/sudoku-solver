package ro.danielst.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Paint;
import ro.danielst.model.SudokuBoard;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private FXMLLoader loader;

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }


    public void display(int[][] basePuzzle, boolean useColoring) {
        for (int i = 0; i<basePuzzle.length ; i++) {
            for (int j = 0; j < basePuzzle[i].length; j++) {
                Label label = (Label) loader.getNamespace().get("lbl" + i + j);
                if(basePuzzle[i][j] == 0) {
                    label.setText(" ");
                } else {
                    String initialValue = label.getText();
                    label.setText(String.valueOf(basePuzzle[i][j]));
                    if(useColoring && initialValue.equals(String.valueOf(basePuzzle[i][j]))) {
                        label.setTextFill(Paint.valueOf("#000000"));
                    } else {
                        label.setTextFill(Paint.valueOf("#999999"));
                    }
                }
            }
        }
    }

    public double getProgression() {
        return ((Slider) loader.getNamespace().get("ctrlSpeed")).getValue();
    }

    public void setProgression(int progress) {
        ((Slider) loader.getNamespace().get("ctrlSpeed")).setValue(progress);
    }

    public void setProgressionMax(int maxProgress) {
        ((Slider) loader.getNamespace().get("ctrlSpeed")).setMax(maxProgress);
    }

    public void initPuzzleList(List<SudokuBoard> puzzleList) {
        List<SudokuComboItem> toDisplay = new ArrayList<>();
        for(int i = 0; i<puzzleList.size(); i++) {
            toDisplay.add(new SudokuComboItem("Puzzle " + (i+1), puzzleList.get(i)));
        }
        ((ComboBox)loader.getNamespace().get("cboPuzzle")).setItems(FXCollections.observableArrayList(toDisplay));
    }

    public SudokuBoard getSelectedPuzzle() {
        return ((SudokuComboItem)((ComboBox)loader.getNamespace().get("cboPuzzle")).getValue()).getBoard();
    }

    public void enablePrevNextButtons(boolean enabled) {
        ((Button)loader.getNamespace().get("nextStep")).setDisable(!enabled);
        ((Button)loader.getNamespace().get("prevStep")).setDisable(!enabled);
    }


}
