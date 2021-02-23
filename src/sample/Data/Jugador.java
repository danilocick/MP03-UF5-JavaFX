package sample.Data;

public class Jugador {
    public int partidas_ganadas = 0;
    public int partidas_empatadas = 0;
    public String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPartidas_ganadas() {
        return partidas_ganadas;
    }

    public void setPartidas_ganadas(int partidas_ganadas) {
        this.partidas_ganadas = partidas_ganadas;
    }

    public int getPartidas_empatadas() {
        return partidas_empatadas;
    }

    public void setPartidas_empatadas(int partidas_empatadas) {
        this.partidas_empatadas = partidas_empatadas;
    }
}