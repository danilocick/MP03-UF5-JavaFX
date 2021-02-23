package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablero implements Initializable {

    private Scene scenePrincipal;
    private Scene sceneInsertar;
    private Stage stagePrincipal;
    public static Stage stageInsertar;

    private boolean torn;
    private boolean iniciopartida = false;
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
    public Text textTorn;


    //Al carregar, es fa el metode initialize
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setScenePrincipal(Scene scenePrincipal) {
        this.scenePrincipal = scenePrincipal;
    }

    public void setStagePrincipal(Stage primaryStage) {
        this.stagePrincipal =primaryStage;
    }



    //onclick On Start
    public void onClickStart(ActionEvent actionEvent) throws IOException {
        Button b = (Button) actionEvent.getSource();
        //insertar nombre
        b.setOnMouseClicked((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../insertarNombre.fxml"));

                sceneInsertar = new Scene(fxmlLoader.load(), 150,200);
                stageInsertar = new Stage();

                stageInsertar.setTitle("Name");
                stageInsertar.setScene(sceneInsertar);
                stageInsertar.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setIniciopartida(true);
    }

    //onclickToPlay
    public void onClickButtonPlay(ActionEvent actionEvent) {
        if (isIniciopartida()){
            Button b = (Button) actionEvent.getSource();

            if(isTorn()){
                b.setText("X");
                setTorn(false);
            }else{
                b.setText("O");
                setTorn(true);
            }
        }else{
            textTorn.setText("Click on Start to begin");
        }
    }

    //closeApp
    public void onClickCloseApp(ActionEvent actionEvent) {
        stagePrincipal.close();
    }

    //Getters i Setters
    public boolean isTorn() {
        return torn;
    }

    public void setTorn(boolean torn) {
        this.torn = torn;
    }

    public boolean isIniciopartida() {
        return iniciopartida;
    }

    public void setIniciopartida(boolean iniciopartida) {
        this.iniciopartida = iniciopartida;
    }
}
