package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Tablero implements Initializable {

    private Scene scene;
    private Stage stage;
    private boolean torn;

    //Al carregar, es fa el metode initialize
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setScene(Scene scene) {
        this.scene=scene;
    }

    public void setStage(Stage primaryStage) {
        this.stage =primaryStage;
    }



    //onclick On Start
    public void onClickStart(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        b.setText("The match has begun");
    }

    //onclickToPlay
    public void onClickButtonPlay(ActionEvent actionEvent) {
        setTorn(false);

        Button b = (Button) actionEvent.getSource();

        if(isTorn()){
            b.setText("true");
        }else b.setText("false");
    }

    //closeApp
    public void onClickCloseApp(ActionEvent actionEvent) {
        stage.close();
    }


    //Getters i Setters
    public boolean isTorn() {
        return torn;
    }
    public void setTorn(boolean torn) {
        this.torn = torn;
    }
}
