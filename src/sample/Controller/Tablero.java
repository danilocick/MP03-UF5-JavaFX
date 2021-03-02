package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Data.Jugador;
import sample.Main;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.scene;

public class Tablero implements Initializable {

    private Scene scenePrincipal;
    private Scene sceneInsertar;
    private Scene sceneResults;
    private Stage stagePrincipal;
    public static Stage stageInsertar;
    public static Stage stageResults;

    private static boolean torn = true;
    private boolean iniciopartida = false;
    private boolean primeraJugada = true;
    private boolean wins;
    private int tiradas = 0;
    private boolean css=false;
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
    public MenuItem dark;
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

                //RADIO BUTTON BOT VS BOT
                if (radioButtonA.isSelected()){

                    if (Tablero.jugadorA == null){
                        Tablero.jugadorA = new Jugador("jugador1", true);
                        Tablero.jugadorB = new Jugador("jugador2", true);
                    }
                    reBegin();
                    wins = false;
                    JuegoBots();

                //RADIO BUTTON BOT VS human
                }
                else if (radioButtonB.isSelected()){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../insertarSoloUnJugador.fxml"));

                    sceneInsertar = new Scene(fxmlLoader.load(), 150,150);
                    stageInsertar = new Stage();

                    stageInsertar.setTitle("Name");
                    stageInsertar.setScene(sceneInsertar);
                    stageInsertar.show();

                //RADIO BUTTON human VS human
                }
                else if (radioButtonC.isSelected()){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../insertarNombre.fxml"));

                    sceneInsertar = new Scene(fxmlLoader.load(), 150,200);
                    stageInsertar = new Stage();

                    stageInsertar.setTitle("Name");
                    stageInsertar.setScene(sceneInsertar);
                    stageInsertar.show();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        setIniciopartida(true);
        buttonStart.setDisable(true);
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
                    textTorn.setText("Torn: "+jugadorB.getNombre());
                    if (jugadorB.robot) {
                        comprovarGanador();
                        comprovarEmpate();
                        TiradaBot();
                        textTorn.setText("Torn: "+jugadorA.getNombre());
                        setTorn(true);
                    }
                }else{
                    b.setText(jugadorB.getNombre());
                    setTorn(true);
                    textTorn.setText("Torn: "+jugadorA.getNombre());
                }
                comprovarGanador();
                comprovarEmpate();
            }
        }else{
            textTorn.setText("Click on Start to begin");
        }
    }

    //closeApp
    public void onClickCloseApp(ActionEvent actionEvent) {
        stagePrincipal.close();
    }

    //Show  results
    public void clickShowResult(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../resultadosJugadores.fxml"));

        sceneResults = new Scene(fxmlLoader.load());
        stageResults = new Stage();

        stageResults.setTitle("Name");
        stageResults.setScene(sceneResults);
        stageResults.show();
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





    private void setStartEnable() {
        buttonStart.setDisable(false);
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

    public void OnClickChangeCSSm(ActionEvent actionEvent) {
        scene.getStylesheets().clear();
        if(css) {
            scene.getStylesheets().add("sample/CSS/button-styles.css");
            dark.setText("White");
            css=false;
        }else {
            scene.getStylesheets().add("sample/CSS/button-styles-dark.css");
            dark.setText("Dark");
            css=true;
        }
    }


    //VARIANTS DEL JOC
    private void comprovarGanador() {
        if (isTorn()) {
            //JGADOR 2

            if (button1play.getText().equals(jugadorB.getNombre()) && button2play.getText().equals(jugadorB.getNombre()) && button3play.getText().equals(jugadorB.getNombre()) || button4play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button6play.getText().equals(jugadorB.getNombre()) || button7play.getText().equals(jugadorB.getNombre()) && button8play.getText().equals(jugadorB.getNombre()) && button9play.getText().equals(jugadorB.getNombre())){
                jugadorB.partidas_ganadas++;
                reBegin();
                setTextJugadores();
                wins =true;
                setStartEnable();
                tiradas = 0;
            } else if (button1play.getText().equals(jugadorB.getNombre()) && button4play.getText().equals(jugadorB.getNombre()) && button7play.getText().equals(jugadorB.getNombre()) || button2play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button8play.getText().equals(jugadorB.getNombre()) || button3play.getText().equals(jugadorB.getNombre()) && button6play.getText().equals(jugadorB.getNombre()) && button9play.getText().equals(jugadorB.getNombre())){
                jugadorB.partidas_ganadas++;
                reBegin();
                setTextJugadores();
                wins =true;
                setStartEnable();
                tiradas = 0;
            } else if (button1play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button9play.getText().equals(jugadorB.getNombre()) || button3play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button7play.getText().equals(jugadorB.getNombre())){
                jugadorB.partidas_ganadas++;
                reBegin();
                setTextJugadores();
                wins =true;
                setStartEnable();
                tiradas = 0;
            }
        }else {
            //JGADOR 1

            if (button1play.getText().equals(jugadorA.getNombre()) && button2play.getText().equals(jugadorA.getNombre()) && button3play.getText().equals(jugadorA.getNombre()) || button4play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button6play.getText().equals(jugadorA.getNombre()) || button7play.getText().equals(jugadorA.getNombre()) && button8play.getText().equals(jugadorA.getNombre()) && button9play.getText().equals(jugadorA.getNombre())){
                jugadorA.partidas_ganadas++;
                reBegin();
                setTextJugadores();
                wins =true;
                setStartEnable();
                tiradas = 0;
            } else if (button1play.getText().equals(jugadorA.getNombre()) && button4play.getText().equals(jugadorA.getNombre()) && button7play.getText().equals(jugadorA.getNombre()) || button2play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button8play.getText().equals(jugadorA.getNombre()) || button3play.getText().equals(jugadorA.getNombre()) && button6play.getText().equals(jugadorA.getNombre()) && button9play.getText().equals(jugadorA.getNombre())){
                jugadorA.partidas_ganadas++;
                reBegin();
                setTextJugadores();
                wins =true;
                setStartEnable();
                tiradas = 0;
            } else if (button1play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button9play.getText().equals(jugadorA.getNombre()) || button3play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button7play.getText().equals(jugadorA.getNombre())){
                jugadorA.partidas_ganadas++;
                reBegin();
                setTextJugadores();
                wins =true;
                setStartEnable();
                tiradas = 0;
            }
        }
    }
    private void comprovarEmpate() {
        tiradas++;
        if (tiradas == 9){
            tiradas = 0;
            jugadorA.partidas_empatadas++;
            jugadorB.partidas_empatadas++;
            wins=true;
            setTextJugadores();
            reBegin();
        }

    }

    //BOTS
    public void TiradaBot() {
        int x;
        if(isTorn()){
            x = ((int) (Math.random()*(9 - 1))) + 1;
            while (true) {
                if(x == 1){
                    if (button1play.getText().equals("")) {
                        button1play.setText(jugadorA.getNombre());
                        break;
                    }
                    x++;
                }else if(x == 2){
                    if (button2play.getText().equals("")) {
                        button2play.setText(jugadorA.getNombre());
                        break;
                    }
                    x++;
                }else if(x == 3){
                    if (button3play.getText().equals("")) {
                        button3play.setText(jugadorA.getNombre());
                        break;
                    }
                    x++;
                }else if(x == 4){
                    if (button4play.getText().equals("")) {
                        button4play.setText(jugadorA.getNombre());
                        break;
                    }
                    x++;
                }else if(x == 5){
                    if (button5play.getText().equals("")) {
                        button5play.setText(jugadorA.getNombre());
                        break;
                    }
                    x++;
                }else if(x == 6){
                    if (button6play.getText().equals("")) {
                        button6play.setText(jugadorA.getNombre());
                        break;
                    }
                    x++;
                }else if(x == 7){
                    if (button7play.getText().equals("")) {
                        button7play.setText(jugadorA.getNombre());
                        break;
                    }
                    x++;
                }else if(x == 8){
                    if (button8play.getText().equals("")) {
                        button8play.setText(jugadorA.getNombre());
                        break;
                    }
                    x++;
                }else if(x == 9){
                    if (button9play.getText().equals("")) {
                        button9play.setText(jugadorA.getNombre());
                        break;
                    }
                    x = 1;
                }
            }
            boolean thre = comprovarGanadorBot();
            if (!thre) {
                comprovarEmpateBot();
            }
            setTorn(false);
            if (radioButtonB.isSelected()){ reBegin();}
            textTorn.setText("Torn: "+jugadorB.getNombre());
        }
        else{
            x = ((int) (Math.random()*(9 - 1))) + 1;
            boolean m = true;
            while (m) {
                if(x == 1){
                    if (button1play.getText().equals("")) {
                        button1play.setText(jugadorB.getNombre());
                        m = false;
                    } else x++;
                }else if(x == 2){
                    if (button2play.getText().equals("")) {
                        button2play.setText(jugadorB.getNombre());
                        m = false;
                    } else x++;
                }else if(x == 3){
                    if (button3play.getText().equals("")) {
                        button3play.setText(jugadorB.getNombre());
                        m = false;
                    } else x++;
                }else if(x == 4){
                    if (button4play.getText().equals("")) {
                        button4play.setText(jugadorB.getNombre());
                        m = false;
                    } else x++;
                }else if(x == 5){
                    if (button5play.getText().equals("")) {
                        button5play.setText(jugadorB.getNombre());
                        m = false;
                    } else x++;
                }else if(x == 6){
                    if (button6play.getText().equals("")) {
                        button6play.setText(jugadorB.getNombre());
                        m = false;
                    } else x++;
                }else if(x == 7){
                    if (button7play.getText().equals("")) {
                        button7play.setText(jugadorB.getNombre());
                        m = false;
                    } else x++;
                }else if(x == 8){
                    if (button8play.getText().equals("")) {
                        button8play.setText(jugadorB.getNombre());
                        m = false;
                    } else x++;
                }else if(x == 9){
                    if (button9play.getText().equals("")) {
                        button9play.setText(jugadorB.getNombre());
                        m = false;
                    }else x = 1;
                }
            }
            boolean thre = comprovarGanadorBot();
            if (!thre) {
                comprovarEmpateBot();
            }
            setTorn(true);
            textTorn.setText("Torn: "+jugadorA.getNombre());
        }
    }
    public void JuegoBots() {
        while (!wins){
            TiradaBot();
        }
    }

    private boolean comprovarGanadorBot() {
        if (isTorn()) {
            //JGADOR 2

            if (button1play.getText().equals(jugadorB.getNombre()) && button2play.getText().equals(jugadorB.getNombre()) && button3play.getText().equals(jugadorB.getNombre()) || button4play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button6play.getText().equals(jugadorB.getNombre()) || button7play.getText().equals(jugadorB.getNombre()) && button8play.getText().equals(jugadorB.getNombre()) && button9play.getText().equals(jugadorB.getNombre())){
                jugadorB.partidas_ganadas++;
                setTextJugadores();
                wins =true;
                setStartEnable();
                return true;
            } else if (button1play.getText().equals(jugadorB.getNombre()) && button4play.getText().equals(jugadorB.getNombre()) && button7play.getText().equals(jugadorB.getNombre()) || button2play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button8play.getText().equals(jugadorB.getNombre()) || button3play.getText().equals(jugadorB.getNombre()) && button6play.getText().equals(jugadorB.getNombre()) && button9play.getText().equals(jugadorB.getNombre())){
                jugadorB.partidas_ganadas++;
                setTextJugadores();
                wins =true;
                setStartEnable();
                return true;
            } else if (button1play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button9play.getText().equals(jugadorB.getNombre()) || button3play.getText().equals(jugadorB.getNombre()) && button5play.getText().equals(jugadorB.getNombre()) && button7play.getText().equals(jugadorB.getNombre())){
                jugadorB.partidas_ganadas++;
                setTextJugadores();
                wins =true;
                setStartEnable();
                return true;
            }
        }else {
            //JGADOR 1

            if (button1play.getText().equals(jugadorA.getNombre()) && button2play.getText().equals(jugadorA.getNombre()) && button3play.getText().equals(jugadorA.getNombre()) || button4play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button6play.getText().equals(jugadorA.getNombre()) || button7play.getText().equals(jugadorA.getNombre()) && button8play.getText().equals(jugadorA.getNombre()) && button9play.getText().equals(jugadorA.getNombre())){
                jugadorA.partidas_ganadas++;
                setTextJugadores();
                wins =true;
                setStartEnable();
                return true;
            } else if (button1play.getText().equals(jugadorA.getNombre()) && button4play.getText().equals(jugadorA.getNombre()) && button7play.getText().equals(jugadorA.getNombre()) || button2play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button8play.getText().equals(jugadorA.getNombre()) || button3play.getText().equals(jugadorA.getNombre()) && button6play.getText().equals(jugadorA.getNombre()) && button9play.getText().equals(jugadorA.getNombre())){
                jugadorA.partidas_ganadas++;
                setTextJugadores();
                wins =true;
                setStartEnable();
                return true;
            } else if (button1play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button9play.getText().equals(jugadorA.getNombre()) || button3play.getText().equals(jugadorA.getNombre()) && button5play.getText().equals(jugadorA.getNombre()) && button7play.getText().equals(jugadorA.getNombre())){
                jugadorA.partidas_ganadas++;
                setTextJugadores();
                wins =true;
                setStartEnable();
                return true;
            }
        }
        return false;
    }
    private void comprovarEmpateBot() {
        if(button1play.getText().equals("")||button2play.getText().equals("")||button3play.getText().equals("")||button4play.getText().equals("")||button5play.getText().equals("")||button6play.getText().equals("")||button7play.getText().equals("")||button8play.getText().equals("")||button9play.getText().equals("")){
        } else {
            jugadorA.partidas_empatadas++;
            jugadorB.partidas_empatadas++;
            setTextJugadores();
            wins =true;
            setStartEnable();
        }
    }
}
