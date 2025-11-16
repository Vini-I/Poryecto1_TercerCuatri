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
    private ListaCartones cartones;
    private ArrayList<Carton> cartonesAfectados;

    public ComandoMarcarNumero(int numero, ArrayList<Carton> cartonesAfectados) {
        this.numero = numero;
        this.cartones = ListaCartones.getInstancia();
        this.cartonesAfectados = cartonesAfectados;
    }

    @Override
    public void ejecutar() {
        cartonesAfectados.clear();
        for (Carton carton : cartones.getCartones()) {
            if (carton.marcarNumero(numero)) {
                cartonesAfectados.add(carton);
            }
        }
    }

    @Override
    public void deshacer() {
        for (Carton carton : cartonesAfectados) {
            carton.desmarcarNumero(numero);
        }
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<Carton> getCartonesAfectados() {
        return cartonesAfectados;
    }
}
