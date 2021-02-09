package sample.Dinamica;

import java.util.List;

public class Jugador {
    String nom;
    int guanyats;
    int perduts;
    List<Jugador> llistaJugadors;

    public List<Jugador> getLlistaJugadors() {
        return llistaJugadors;
    }

    public Jugador(String nom, int guanyats, int perduts) {
        this.nom = nom;
        this.guanyats = guanyats;
        this.perduts = perduts;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getGuanyats() {
        return guanyats;
    }

    public void setGuanyats(int guanyats) {
        this.guanyats = guanyats;
    }

    public int getPerduts() {
        return perduts;
    }

    public void setPerduts(int perduts) {
        this.perduts = perduts;
    }
}
