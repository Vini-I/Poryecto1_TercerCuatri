/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.command;

import java.util.Stack;

/**
 *
 * @author rodol
 */
public class HistorialComandos {
    private Stack<Comando> historial;

    public HistorialComandos() {
        this.historial = new Stack<>();
    }
    
    public void ejecutarComando(Comando comando) {
        comando.ejecutar();
        historial.push(comando);
    }
    
    public boolean deshacer() {
        if (historial.isEmpty()) {
            return false;
        }
        
        Comando comando = historial.pop();
        comando.deshacer();
        return true;
    }
    
    public boolean hayComandos() {
        return !historial.isEmpty();
    }

    public int getCantidadComandos() {
        return historial.size();
    }

    public void limpiar() {
        historial.clear();
    }
    
    public Comando getUltimoComando() {
        if (historial.isEmpty()) {
            return null;
        }
        return historial.peek();
    }
    
    
}
