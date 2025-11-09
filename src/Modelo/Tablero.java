/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Exceptions.NumeroInvalidoExcepcion;

/**
 *
 * @author rodol
 */
public class Tablero {

    private static Tablero instancia;
    private boolean[] numerosGenerados;

    private Tablero() {
        this.numerosGenerados = new boolean[76];
    }

    public static Tablero getInstancia() {
        if (instancia == null) {
            instancia = new Tablero();
        }
        return instancia;
    }

    public boolean marcarNumero(int numero) throws NumeroInvalidoExcepcion {
        if (numero < 1 || numero > 75) {
            throw new NumeroInvalidoExcepcion();
        }

        if (numerosGenerados[numero]) {
            return false;
        }

        numerosGenerados[numero] = true;
        return true;
    }

    public boolean desmarcarNumero(int numero) throws NumeroInvalidoExcepcion {
        if (numero < 1 || numero > 75) {
            throw new NumeroInvalidoExcepcion();
        }

        if (!numerosGenerados[numero]) {
            return false;
        }

        numerosGenerados[numero] = false;
        return true;
    }

    public boolean estaGenerado(int numero) {
        return numerosGenerados[numero];
    }

    public void reiniciar() {
        for (int i = 1; i <= 75; i++) {
            numerosGenerados[i] = false;
        }
    }

    public int getCantidadMarcados() {
        int contador = 0;
        for (int i = 1; i <= 75; i++) {
            if (numerosGenerados[i]) {
                contador++;
            }
        }
        return contador;
    }

}
