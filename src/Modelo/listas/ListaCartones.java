/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.listas;

import Exceptions.IdRepetidoExcepcion;
import Modelo.Carton;
import Modelo.factorymethod.CartonAutomaticoFactory;
import Modelo.factorymethod.CartonFactory;
import Modelo.factorymethod.CartonManualFactory;
import java.util.ArrayList;

/**
 *
 * @author rodol
 */
public class ListaCartones implements IListaCartones {
    
     private ArrayList<Carton> cartones;
    
    public ListaCartones() {
        this.cartones = new ArrayList<>();
    }

    public ArrayList<Carton> getCartones() {
        return cartones;
    }
    
    @Override
    public Carton agregarCartonAutomatico(String id) throws Exception {
        validarIdUnico(id);
        CartonFactory factory = new CartonAutomaticoFactory();
        Carton carton = factory.crearCarton(id);
        cartones.add(carton);
        return carton;
    }
    
    @Override
    public Carton agregarCartonManual(String id, int[][] numeros) throws Exception {
        validarIdUnico(id);
        CartonFactory factory = new CartonManualFactory(numeros);
        Carton carton = factory.crearCarton(id);
        cartones.add(carton);
        return carton;
    }
    
    @Override
    public boolean eliminarCarton(String id) {
        Carton carton = buscarCarton(id);
        if (carton != null) {
            cartones.remove(carton);
            return true;
        }
        return false;
    }
    
    @Override
    public Carton buscarCarton(String id) {
        for (Carton carton : cartones) {
            if (carton.getId().equals(id)) {
                return carton;
            }
        }
        return null;
    }
    
    public ArrayList<Carton> marcarNumeroEnTodos(int numero) {
        ArrayList<Carton> cartonesAfectados = new ArrayList<>();
        for (Carton carton : cartones) {
            if (carton.marcarNumero(numero)) {
                cartonesAfectados.add(carton);
            }
        }
        return cartonesAfectados;
    }
    
    public void desmarcarNumeroEnTodos(int numero) {
        for (Carton carton : cartones) {
            carton.desmarcarNumero(numero);
        }
    }
    
    public void reiniciarTodos() {
        for (Carton carton : cartones) {
            carton.reiniciar();
        }
    }
    
    private void validarIdUnico(String id) throws IdRepetidoExcepcion {
        if (buscarCarton(id) != null) {
            throw new IdRepetidoExcepcion();
        }
    }
    
    public int getCantidadCartones() {
        return cartones.size();
    }
    
    public boolean hayCartones() {
        return !cartones.isEmpty();
    }
    
    public void limpiarListaCartones() {
        cartones.clear();
    }
    
}
