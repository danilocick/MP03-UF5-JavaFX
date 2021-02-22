package sample.Data;

public class Jugador {
    public int partidas_ganadas;
    public int partidas_jugadas;
    public String nombre;

    public Jugador(String nombre) {
        this.partidas_ganadas = 0;
        this.partidas_jugadas = 0;
        this.nombre = nombre;
    }
}
