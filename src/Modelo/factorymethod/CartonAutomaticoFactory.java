/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.factorymethod;

import Modelo.Carton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rodol
 */
public class CartonAutomaticoFactory extends CartonFactory{
     private Random random;
    
    public CartonAutomaticoFactory() {
        this.random = new Random();
    }
    
    @Override
    public Carton crearCarton(String id) throws Exception {
        int[][] numeros = new int[5][5];
        
        for (int columna = 0; columna < 5; columna++) {
            generarNumerosColumna(numeros, columna);
        }
        
        numeros[2][2] = 0;
        
        return new Carton(id, numeros);
    }
    
    /**
     * 
     */
    private void generarNumerosColumna(int[][] numeros, int columna) {
        int minimo = (columna * 15) + 1;
        int maximo = (columna + 1) * 15;
        
        List<Integer> disponibles = new ArrayList<>();
        for (int i = minimo; i <= maximo; i++) {
            disponibles.add(i);
        }
        
        Collections.shuffle(disponibles, random);
        for (int fila = 0; fila < 5; fila++) {
            if (fila == 2 && columna == 2) {
                continue;
            }
            numeros[fila][columna] = disponibles.get(fila);
        }
    }
}
