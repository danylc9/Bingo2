package com.example.bingo;

import com.example.bingo.Data.Jugador;

public class ViewModelGeneral {

    static Jugador jugador;

    static boolean primeraVez = true;



    public ViewModelGeneral(){
    }

   public void setJugador(Jugador juga){
        jugador = juga;
    }
    public String getNameJugador(){
       return jugador.getName();
    }
    public int getNumCartones(){
       return jugador.getNumCartones();
    }

    public boolean isPrimeraVez(){
        return primeraVez;
    }
    public void setPrimeraVezFalse(){
        primeraVez = false;
    }
}
