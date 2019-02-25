package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class Controller {

    static Random  rand = new Random();
    private FXMLLoader loader;

    public void changeValue() {
        ((Label)loader.getNamespace().get("lbl" + rand.nextInt(9) + rand.nextInt(9))).setText("x");
    }

    public void nextState(){
        System.out.println("Next State");
    }

    public void previousState() {
        System.out.println("Previous State");
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }
}
