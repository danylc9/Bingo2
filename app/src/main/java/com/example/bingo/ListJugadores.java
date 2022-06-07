package com.example.bingo;

import com.example.bingo.Data.Jugador;

import java.util.ArrayList;

public class ListJugadores {
    Jugador jug;
    ArrayList<Jugador> lista=new ArrayList();

    public ListJugadores() {

    };

    public Jugador getJug() {
        return jug;
    }

    public void setJug(Jugador jug) {
        this.jug = jug;
    }

    public ArrayList<Jugador> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Jugador> lista) {
        this.lista = lista;
    }

    public void addjugador(Jugador j) {
        lista.add(j);
    }

    public void removejugador(Jugador j) {
        for(int i=0;i<lista.size();i++) {
            if(j.getName().equals(lista.get(i).getName()))
            {
                lista.remove(i);
            }
        }
    }



}