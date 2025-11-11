/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.observer;

import java.util.ArrayList;

/**
 *
 * @author rodol
 */
public class NotificadorJuego {
    
    private ArrayList<ObservadorJuego> observadores;
    
    public NotificadorJuego() {
        this.observadores = new ArrayList<>();
    }
    
    public void agregarObservador(ObservadorJuego observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }
    
    public void eliminarObservador(ObservadorJuego observador) {
        observadores.remove(observador);
    }
    
    public void notificar(EventoJuego evento) {
        for (ObservadorJuego observador : observadores) {
            observador.actualizar(evento);
        }
    }
    
    public void notificar(String tipo, Object datos) {
       EventoJuego evento = new EventoJuego(tipo,datos);
       notificar(evento);
    }
    
    public int getCantidadObservadores() {
        return observadores.size();
    }
    
    
}
