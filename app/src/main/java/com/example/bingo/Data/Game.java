package com.example.bingo.Data;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


public class Game {

    private int filas = 5;
    private int columnas = 5;
    private int matrix[][] = new int[columnas][filas];

    public Game() {
        matrix = SizesMatrix();
    }

    public int[][] getMatrix() {
        return matrix;
    }

    @NonNull
    @Override
    public String toString() {

        String str = " ";

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                str += (matrix[i][j] + "   ");
            }
            str += "\n";
        }
        return str;

    }

    public int[][] SizesMatrix() {
        Set<Integer> generados = new HashSet<Integer>();

        for (int i = 0; i < 5; i++) {
            int min_val = 0;
            int max_val = 25;
            boolean generado = false;
            while (!generado) {
                ThreadLocalRandom tlr = ThreadLocalRandom.current();
                int randomNum = tlr.nextInt(min_val, max_val + 1);
                if (!generados.contains(randomNum)) {
                    generados.add(randomNum);
                    generado = true;
                }

            }

        }


        for (int i = 5; i < 10; i++) {
            int min_val = 26;
            int max_val = 50;
            int aleatorio = -1;
            boolean generado = false;
            while (!generado) {
                ThreadLocalRandom tlr = ThreadLocalRandom.current();
                int randomNum = tlr.nextInt(min_val, max_val + 1);
                if (!generados.contains(randomNum)) {
                    generados.add(randomNum);
                    aleatorio = randomNum;
                    generado = true;
                }

            }
        }

        for (int i = 10; i < 15; i++) {
            int min_val = 51;
            int max_val = 75;
            int aleatorio = -1;
            boolean generado = false;
            while (!generado) {
                ThreadLocalRandom tlr = ThreadLocalRandom.current();
                int randomNum = tlr.nextInt(min_val, max_val + 1);
                if (!generados.contains(randomNum)) {
                    generados.add(randomNum);
                    aleatorio = randomNum;
                    generado = true;
                }

            }
        }

        for (int i = 15; i < 20; i++) {
            int min_val = 76;
            int max_val = 85;
            int aleatorio = -1;
            boolean generado = false;
            while (!generado) {
                ThreadLocalRandom tlr = ThreadLocalRandom.current();
                int randomNum = tlr.nextInt(min_val, max_val + 1);
                if (!generados.contains(randomNum)) {
                    generados.add(randomNum);
                    aleatorio = randomNum;
                    generado = true;
                }

            }
        }

        for (int i = 20; i < 25; i++) {
            int min_val = 86;
            int max_val = 99;
            int aleatorio = -1;
            boolean generado = false;
            while (!generado) {
                ThreadLocalRandom tlr = ThreadLocalRandom.current();
                int randomNum = tlr.nextInt(min_val, max_val + 1);
                if (!generados.contains(randomNum)) {
                    generados.add(randomNum);
                    aleatorio = randomNum;
                    generado = true;
                }

            }
        }


        Integer[] matriz = new Integer[generados.size()];
        generados.toArray(matriz);
        Arrays.sort(matriz);

        int x = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrix[i][j] = matriz[x];
                x++;
            }
        }


        return matrix;
    }





}
