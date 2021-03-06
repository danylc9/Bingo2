package com.example.bingo.Data;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;

import com.example.bingo.ViewModelGeneral;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


public class Game {
    ViewModelGeneral viewModel = new ViewModelGeneral();

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
        viewModel.generadosAdd(0);

        String str = " ";

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (viewModel.generadosContiene(matrix[i][j])) {
                    str += "X" + "     ";
                } else {
                    str += (matrix[i][j] + "     ");

                }
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


        for (int i = 0; i < 5; i++) {
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

        for (int i = 0; i < 5; i++) {
            int min_val = 51;
            int max_val = 75;
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

        for (int i = 0; i < 5; i++) {
            int min_val = 76;
            int max_val = 85;
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

        for (int i = 0; i < 5; i++) {
            int min_val = 86;
            int max_val = 99;
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


        Integer[] filaGenerados = new Integer[generados.size()];
        generados.toArray(filaGenerados);
        Arrays.sort(filaGenerados);

        int x = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrix[i][j] = filaGenerados[x];
                x++;
            }
        }


        return matrix;
    }


}
