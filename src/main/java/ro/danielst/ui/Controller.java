package ro.danielst.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.util.Random;

public class Controller {

    static Random  rand = new Random();
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
                    label.setText(String.valueOf(basePuzzle[i][j]));
                }
            }
        }
    }


    public double getSpeed() {
        return ((Slider) loader.getNamespace().get("ctrlSpeed")).getValue();
    }
}
