/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Exceptions.Matriz5por5Excepcion;
import Exceptions.NumeroDuplicadoExcepcion;
import Exceptions.NumeroInvalidoExcepcion;
import Exceptions.RangoColumnaExcepcion;
import Modelo.validaciones.ValidacionCarton;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author rodol
 */
public class Carton {

    private String id;
    private int[][] numeros;
    private boolean[][] marcados;
    private ValidacionCarton validaciones;

    public Carton(String id, int[][] numeros) throws Matriz5por5Excepcion, NumeroInvalidoExcepcion, NumeroDuplicadoExcepcion, RangoColumnaExcepcion {
        this.validaciones = new ValidacionCarton();
        validaciones.validarMatrizCompleta(numeros);
        this.id = Objects.requireNonNull(id, "El id no puede estar vacio");
        this.numeros = numeros;
        this.marcados = new boolean[5][5];
        this.marcados[2][2] = true;
    }

    public String getId() {
        return id;
    }

    public int[][] getNumeros() {
        return numeros;
    }

    public boolean[][] getMarcados() {
        return marcados;
    }

    public boolean marcarNumero(int numero) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (numeros[i][j] == numero) {
                    marcados[i][j] = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean desmarcarNumero(int numero) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (numeros[i][j] == numero && !(i == 2 && j == 2)) {
                    marcados[i][j] = false;
                    return true;
                }
            }
        }
        return false;
    }

    public void reiniciar() {
        for (int i = 0; i < 5; i++) {
            Arrays.fill(marcados[i], false);
        }
        marcados[2][2] = true;
    }

    public boolean contieneNumero(int numero) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (numeros[i][j] == numero) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getNumero(int fila, int columna) {
        return numeros[fila][columna];
    }
}
