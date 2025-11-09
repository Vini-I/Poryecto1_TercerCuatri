/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.factorymethod;

import Modelo.Carton;
import java.util.Objects;

/**
 *
 * @author rodol
 */
public class CartonManualFactory extends CartonFactory{
    private int[][] numeros;

    public void setNumeros(int[][] numeros) {
        this.numeros = numeros;
    }
    
     public CartonManualFactory(int[][] numeros) {
        this.numeros = Objects.requireNonNull(numeros, "Debe proporcionar los numeros");
    }
     
    @Override
    public Carton crearCarton(String id) throws Exception {
        return new Carton(id, numeros);
    }
    
}
