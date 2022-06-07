package com.example.bingo;

public class Jugador {
    private String name;
    private int NumCartones;

    public Jugador(){}

    public Jugador(String nombre,int num){
        this.name=nombre;
        this.NumCartones=num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumCartones() {
        return NumCartones;
    }

    public void setNumCartones(int numCartones) {
        NumCartones = numCartones;
    }
}
