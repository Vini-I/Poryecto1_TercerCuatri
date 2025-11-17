/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.command;

import Exceptions.NumeroInvalidoExcepcion;
import Modelo.Carton;
import Modelo.Tablero;
import Modelo.listas.ListaCartones;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodol
 */
public class ComandoMarcarNumero implements Comando {
    
     private int numero;
    private ArrayList<Carton> cartones;
    private ArrayList<Carton> cartonesAfectados;

    public ComandoMarcarNumero(int numero, ArrayList<Carton> cartones) {
        this.numero = numero;
        this.cartones = new ArrayList(cartones);
        this.cartonesAfectados = new ArrayList();
    }

    @Override
    public void ejecutar() {
      System.out.println(">>> [ComandoMarcarNumero.ejecutar] Guardando para deshacer. Número: " + numero);
        
        for (Carton carton : cartones) {
            if (cartonTieneNumero(carton, numero)) {
                cartonesAfectados.add(carton);
                System.out.println(">>> [ComandoMarcarNumero.ejecutar] Cartón afectado: " + carton.getId());
            }
        }
        
        System.out.println(">>> [ComandoMarcarNumero.ejecutar] Total cartones afectados: " + cartonesAfectados.size());
    }

    @Override
    public void deshacer() {
        System.out.println("desmarcando numero " + numero);
        for (Carton carton : cartonesAfectados) {
            System.out.println("desmarcando en carton: " + carton.getId());
            carton.desmarcarNumero(numero);
        }
    }
    
    private boolean cartonTieneNumero(Carton carton, int numero) {
        int[][] numeros = carton.getNumeros();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (numeros[i][j] == numero) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<Carton> getCartonesAfectados() {
        return cartonesAfectados;
    }
}
