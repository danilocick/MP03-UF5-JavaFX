package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent bp = FXMLLoader.load(getClass().getResource("tablero.fxml"));
        primaryStage.setTitle("TIC TAC TOE");
        primaryStage.setScene(new Scene(bp));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
