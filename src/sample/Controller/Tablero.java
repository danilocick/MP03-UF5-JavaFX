package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Data.Jugador;

import java.awt.*;
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
    private boolean primeraJugada = true;
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
    public RadioButton radioButtonA;
    public RadioButton radioButtonB;
    public RadioButton radioButtonC;
    public Text textTorn;

    //PLAYERS
    public static Jugador jugadorA;
    public static Jugador jugadorB;
    public Text playerA;
    public Text playerB;

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

            if (primeraJugada) {
                primeraJugada=false;
                setTextJugadores();
            }

            if (b.getText().equals("")) {
                if(isTorn()){
                    b.setText(jugadorA.getNombre());
                    setTorn(false);
                    comprovarGanador();
                    textTorn.setText("Torn: "+jugadorB.getNombre());
                }else{
                    b.setText(jugadorB.getNombre());
                    setTorn(true);
                    comprovarGanador();
                    textTorn.setText("Torn: "+jugadorA.getNombre());
                }
            }

            comprovarEmpate();
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


    public void setTextJugadores() {
        playerA.setText(jugadorA.getNombre()+": "+Tablero.jugadorA.getPartidas_ganadas()+"/"+Tablero.jugadorA.getPartidas_empatadas());
        playerB.setText(jugadorB.getNombre()+": "+Tablero.jugadorB.getPartidas_ganadas()+"/"+Tablero.jugadorB.getPartidas_empatadas());
    }

    private void comprovarGanador() {
        if (isTorn()) {
            //JGADOR 2

            if (button1play.getText().equals(jugadorB.getNombre()) && button2play.getText().equals(jugadorB.getNombre()) && button3play.getText().equals(jugadorB.getNombre()) || button4play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button6play.getText().equals(jugadorB.getNombre()) || button7play.getText().equals(jugadorB.getNombre()) && button8play.getText().equals(jugadorB.getNombre()) && button9play.getText().equals(jugadorB.getNombre())){
                jugadorB.partidas_ganadas++;
                reBegin();
                setTextJugadores();
            } else if (button1play.getText().equals(jugadorB.getNombre()) && button4play.getText().equals(jugadorB.getNombre()) && button7play.getText().equals(jugadorB.getNombre()) || button2play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button8play.getText().equals(jugadorB.getNombre()) || button3play.getText().equals(jugadorB.getNombre()) && button6play.getText().equals(jugadorB.getNombre()) && button9play.getText().equals(jugadorB.getNombre())){
                jugadorB.partidas_ganadas++;
                reBegin();
                setTextJugadores();
            } else if (button1play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button9play.getText().equals(jugadorB.getNombre()) || button3play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button7play.getText().equals(jugadorB.getNombre())){
                jugadorB.partidas_ganadas++;
                reBegin();
                setTextJugadores();
            }
        }else {
            //JGADOR 1

            if (button1play.getText().equals(jugadorA.getNombre()) && button2play.getText().equals(jugadorA.getNombre()) && button3play.getText().equals(jugadorA.getNombre()) || button4play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button6play.getText().equals(jugadorA.getNombre()) || button7play.getText().equals(jugadorA.getNombre()) && button8play.getText().equals(jugadorA.getNombre()) && button9play.getText().equals(jugadorA.getNombre())){
                jugadorA.partidas_ganadas++;
                reBegin();
                setTextJugadores();
            } else if (button1play.getText().equals(jugadorA.getNombre()) && button4play.getText().equals(jugadorA.getNombre()) && button7play.getText().equals(jugadorA.getNombre()) || button2play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button8play.getText().equals(jugadorA.getNombre()) || button3play.getText().equals(jugadorA.getNombre()) && button6play.getText().equals(jugadorA.getNombre()) && button9play.getText().equals(jugadorA.getNombre())){
                jugadorA.partidas_ganadas++;
                reBegin();
                setTextJugadores();
            } else if (button1play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button9play.getText().equals(jugadorA.getNombre()) || button3play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button7play.getText().equals(jugadorA.getNombre())){
                jugadorA.partidas_ganadas++;
                reBegin();
                setTextJugadores();
            }
        }
    }

    private void comprovarEmpate() {
        if(button1play.getText().equals(jugadorA.getNombre()) || button1play.getText().equals(jugadorB.getNombre()) && button2play.getText().equals(jugadorA.getNombre()) || button2play.getText().equals(jugadorB.getNombre()) && button3play.getText().equals(jugadorA.getNombre()) || button3play.getText().equals(jugadorB.getNombre()) && button4play.getText().equals(jugadorA.getNombre()) || button4play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) || button5play.getText().equals(jugadorB.getNombre()) && button6play.getText().equals(jugadorA.getNombre()) || button6play.getText().equals(jugadorB.getNombre()) && button7play.getText().equals(jugadorA.getNombre()) || button7play.getText().equals(jugadorB.getNombre()) && button8play.getText().equals(jugadorA.getNombre()) || button8play.getText().equals(jugadorB.getNombre()) && button9play.getText().equals(jugadorA.getNombre()) || button9play.getText().equals(jugadorB.getNombre())){
            jugadorA.partidas_empatadas++;
            jugadorB.partidas_empatadas++;
            setTextJugadores();
            reBegin();
        }
    }

    private void reBegin() {
        button1play.setText("");
        button2play.setText("");
        button3play.setText("");
        button4play.setText("");
        button5play.setText("");
        button6play.setText("");
        button7play.setText("");
        button8play.setText("");
        button9play.setText("");


    }

}
