package com.example.bingo;

import com.example.bingo.Data.Game;
import com.example.bingo.Data.Jugador;

import java.util.ArrayList;
import java.util.List;

public class ViewModelGeneral {

    static Jugador jugador;

    static boolean primeraVez = true;

    static List<Integer> generados = new ArrayList<Integer>();

    public ViewModelGeneral() {
    }

    public void setJugador(Jugador juga) {
        jugador = juga;
    }

    public String getNameJugador() {
        return jugador.getName();
    }

    public int getNumCartones() {
        return jugador.getNumCartones();
    }

    public boolean isPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVezFalse() {
        primeraVez = false;
    }

    public boolean generadosContiene(int e) {
        return generados.contains(e);
    }

    public void generadosAdd(int e) {
        generados.add(e);
    }

    static Game game = new Game();
    static Game game2 = new Game();
    static Game game3 = new Game();
    static Game game4 = new Game();
    static Game game5 = new Game();
    static Game game6 = new Game();
    static Game game7 = new Game();
    static Game game8 = new Game();
    static Game game9 = new Game();
    static Game game10 = new Game();


}
