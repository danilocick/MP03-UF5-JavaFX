package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Data.Jugador;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertarNombre implements Initializable {

    public TextField nameFieldJugador1;
    public TextField nameFieldJugador2;
    public Button buttonClose;

    //Al carregar, es fa el metode initialize
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void OnSaveName(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        String jugador1 = nameFieldJugador1.getText();
        String jugador2 = nameFieldJugador2.getText();
        if (jugador1.equals("")){
            jugador1 = "jugador1";
        }
        if (jugador2.equals("")){
            jugador2 = "jugador2";
        }
        //TODO: pasar nombre para crear jugador.
        Tablero.jugadorA = new Jugador(jugador1, false);
        Tablero.jugadorB = new Jugador(jugador2, false);
        Tablero.stageInsertar.close();
    }
}
