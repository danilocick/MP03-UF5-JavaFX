package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultadosJugadores implements Initializable {
    public Text jugadorANombre;
    public Text jugadorAWins;
    public Text jugadorATied;
    public Text jugadorBNombre;
    public Text jugadorBWins;
    public Text jugadorBTied;


    public void onClickCloseResult(ActionEvent actionEvent) {
        Tablero.stageResults.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jugadorANombre.setText(Tablero.jugadorA.nombre);
        jugadorBNombre.setText(Tablero.jugadorB.nombre);
        jugadorAWins.setText(String.valueOf(Tablero.jugadorA.partidas_ganadas));
        jugadorBWins.setText(String.valueOf(Tablero.jugadorB.partidas_ganadas));
        jugadorATied.setText(String.valueOf(Tablero.jugadorA.partidas_empatadas));
        jugadorBTied.setText(String.valueOf(Tablero.jugadorB.partidas_empatadas));

    }
}
