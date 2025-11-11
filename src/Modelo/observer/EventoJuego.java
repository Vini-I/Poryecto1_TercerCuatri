/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.observer;

/**
 *
 * @author rodol
 */
public class EventoJuego {
    private String tipo;
    private Object datos; 
    
    public EventoJuego(String tipo, Object datos) {
        this.tipo = tipo;
        this.datos = datos;
    }

    public String getTipo() {
        return tipo;
    }

    public Object getDatos() {
        return datos;
    }
    
    @Override
    public String toString() {
        return "EventoJuego{tipo= '" + tipo + "', datos= " + datos + "}";
    }
}
