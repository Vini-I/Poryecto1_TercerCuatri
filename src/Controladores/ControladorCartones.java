package Controladores;

import Exceptions.NumeroInvalidoExcepcion;
import GUI.IGui;
import Modelo.Carton;
import Modelo.GestorJuego;
import Modelo.observer.EventoJuego;
import Modelo.observer.ObservadorJuego;

/**
 * 
 * 
 * @author rodol
 */
public class ControladorCartones implements ObservadorJuego {
    private IGui vista;
    private GestorJuego gestor;
    
    public ControladorCartones(IGui vista, GestorJuego gestor) {
        this.vista = vista;
        this.gestor = gestor;
        this.gestor.agregarObservador(this);
    }
    
    public void agregarCartonAutomatico() {
        if (gestor.isJuegoIniciado()) {
            vista.mostrarError("No puede agregar cartones durante el juego");
            return;
        }

        String id = vista.solicitarTexto("Ingrese ID del cartón:", "Nuevo Cartón");
        
        if (id == null || id.trim().isEmpty()) {
            return; 
        }
        
        try {
            Carton carton = gestor.agregarCartonAutomatico(id);
            vista.agregarFrameCarton(carton);
        } catch (Exception e) {
            vista.mostrarError("Error al agregar cartón: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public void abrirCreacionCartonManual() {
        if (gestor.isJuegoIniciado()) {
            vista.mostrarError("No puede agregar cartones durante el juego");
            return;
        }
        
        vista.mostrarFrameCartonManual();
    }

    public void agregarCartonManual() {
        if (gestor.isJuegoIniciado()) {
            vista.mostrarError("No puede agregar cartones durante el juego");
            return;
        }

        String id = vista.solicitarTexto("Ingrese ID del cartón:", "Nuevo Cartón Manual");

        if (id == null || id.trim().isEmpty()) {
            return;
        }

        int[][] numeros = vista.obtenerMatrizCartonManual();

        if (numeros == null) {
            vista.mostrarError("Debe llenar todas las casillas con números válidos");
            return;
        }

        try {
            Carton carton = gestor.agregarCartonManual(id, numeros);
            vista.agregarFrameCarton(carton);

        } catch (Exception e) {
            vista.mostrarError("Error al agregar cartón: " + e.getMessage());
        }
    }

    public void eliminarCarton(String id) {
        if (gestor.isJuegoIniciado()) {
            vista.mostrarError("No puede eliminar cartones durante el juego");
            return;
        }

        boolean eliminado = gestor.eliminarCarton(id);

        if (eliminado) {
            vista.eliminarFrameCarton(id);
        } else {
            vista.mostrarError("No se encontró el cartón");
        }
    }
    
    public void marcarNumeroEnTodosLosCartones(int numero) throws NumeroInvalidoExcepcion {
    gestor.procesarNumero(numero);
}
 
    @Override
    public void actualizar(EventoJuego evento) {
        switch (evento.getTipo()) {
            case "CARTON_AGREGADO":
                Carton cartonAgregado = (Carton) evento.getDatos();
                vista.mostrarMensaje("Cartón agregado: " + cartonAgregado.getId(), "Éxito");
                break;

            case "CARTON_ELIMINADO":
                Carton cartonEliminado = (Carton) evento.getDatos();
                vista.mostrarMensaje("Cartón " + cartonEliminado.getId() + " eliminado", "Éxito");
                break;
        }
    }
    
}