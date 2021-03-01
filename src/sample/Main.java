package sample;

import com.sun.glass.ui.EventLoop;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller.Tablero;

public class Main extends Application {

    public static Scene scene;
    public static boolean css = true;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Carregar FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tablero.fxml"));
        Parent root = loader.load();

        //SCENE TO WORK
        scene = new Scene(root);

        //Injectar Scene i Stage
        Tablero tablero = loader.getController();
        tablero.setScenePrincipal(scene);
        tablero.setStagePrincipal(primaryStage);

        //CSS
//        http://fxexperience.com/2011/12/styling-fx-buttons-with-css/
        if(css){
            scene.getStylesheets().add("sample/CSS/button-styles-dark.css");

        }else
        scene.getStylesheets().add("sample/CSS/button-styles.css");

        primaryStage.setTitle("TIC TAC TOE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
