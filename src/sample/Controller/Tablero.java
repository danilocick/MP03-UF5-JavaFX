package sample.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablero implements Initializable {

    private Scene scene;
    private Stage stage;

    private boolean torn;
    public Button button1play;
    public Button button2play;
    public Button button3play;
    public Button button4play;
    public Button button5play;
    public Button button6play;
    public Button button7play;
    public Button button8play;
    public Button button9play;
    public Button buttonStart;


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
    public void onClickStart(ActionEvent actionEvent) throws IOException {
        Button b = (Button) actionEvent.getSource();
//        if(exists human){
        b.setOnMouseClicked((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../insertarNombre.fxml"));
                /*
                 * if "fx:controller" is not set in fxml
                 * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load(), 150, 150);
                Stage stage = new Stage();
                stage.setTitle("Nom");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
        });

//        }
//        b.setText("The match has begun");
    }

    //onclickToPlay
    public void onClickButtonPlay(ActionEvent actionEvent) {
        setTorn(false);

        Button b = (Button) actionEvent.getSource();
        Text textWins;
        //textWins.setVisible(true);

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
