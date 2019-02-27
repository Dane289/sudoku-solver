package ro.danielst.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Paint;

public class Controller {

    private FXMLLoader loader;

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }


    public void display(int[][] basePuzzle) {
        for (int i = 0; i<basePuzzle.length ; i++) {
            for (int j = 0; j < basePuzzle[i].length; j++) {
                Label label = (Label) loader.getNamespace().get("lbl" + i + j);
                if(basePuzzle[i][j] == 0) {
                    label.setText(" ");
                } else {
                    String initialValue = label.getText();
                    label.setText(String.valueOf(basePuzzle[i][j]));
                    if(initialValue.equals(String.valueOf(basePuzzle[i][j]))) {
                        label.setTextFill(Paint.valueOf("#000000"));
                    } else {
                        label.setTextFill(Paint.valueOf("#999999"));
                    }
                }
            }
        }
    }

    public void setProgression(int progress) {
        ((Slider) loader.getNamespace().get("ctrlSpeed")).setValue(progress);
    }

    public void setProgressionMax(int maxProgress) {
        ((Slider) loader.getNamespace().get("ctrlSpeed")).setMax(maxProgress);
    }
}
