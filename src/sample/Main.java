package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import sample.Controller.Tablero;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Carregar FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tablero.fxml"));
        Parent root = loader.load();

        //SCENE TO WORK
        Scene scene = new Scene(root);

        //Injectar Scene i Stage
        Tablero tablero = loader.getController();
        tablero.setScene(scene);
        tablero.setStage(primaryStage);

        //CSS
        //scene.getStylesheets().add("CSS/button-styles.css");

        primaryStage.setTitle("TIC TAC TOE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
