package ro.danielst.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.danielst.Puzzles;
import ro.danielst.verifier.Solver;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setLoader(loader);
        ViewBacker viewBacker = new ViewBacker(controller);
        new Solver(viewBacker).solve(new Puzzles().PUZZLE_2);
        primaryStage.setTitle("Sudoku-solver");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
