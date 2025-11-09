/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.validaciones;

import Exceptions.Matriz5por5Excepcion;
import Exceptions.NumeroDuplicadoExcepcion;
import Exceptions.NumeroInvalidoExcepcion;
import Exceptions.RangoColumnaExcepcion;
import java.util.HashSet;

/**
 *
 * @author rodol
 */
public class ValidacionCarton {
    
    public void validarMatrizCompleta(int[][] numeros) throws Matriz5por5Excepcion,
            NumeroInvalidoExcepcion, NumeroDuplicadoExcepcion, RangoColumnaExcepcion {
        
        validarDimensiones(numeros);
        validarNumeros(numeros);
    }
    
    private void validarDimensiones(int[][] numeros) throws Matriz5por5Excepcion {
        if (numeros == null || numeros.length != 5 || numeros[0].length != 5) {
            throw new Matriz5por5Excepcion();
        }
    }
    
     private void validarNumeros(int[][] numeros) throws NumeroInvalidoExcepcion,
            NumeroDuplicadoExcepcion, RangoColumnaExcepcion {

        HashSet<Integer> numerosVistos = new HashSet<>();

        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                if (fila == 2 && columna == 2) {
                    continue;
                }

                int numero = numeros[fila][columna];
                if (numero < 1 || numero > 75) {
                    throw new NumeroInvalidoExcepcion();
                }

                if (numerosVistos.contains(numero)) {
                    throw new NumeroDuplicadoExcepcion();
                }
                numerosVistos.add(numero);

                validarRangoColumna(numero, columna);
            }
        }

        if (numerosVistos.size() != 24) {
            throw new NumeroInvalidoExcepcion();
        }
    }
    
    private void validarRangoColumna(int numero, int columna) throws RangoColumnaExcepcion {
        int minimo = (columna * 15) + 1;
        int maximo = (columna + 1) * 15;
        
        if (numero < minimo || numero > maximo) {
            throw new RangoColumnaExcepcion();
        }
    }
}
