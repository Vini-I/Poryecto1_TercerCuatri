package Controladores;

import GUI.IGui;
import Modelo.GestorJuego;
import Modelo.Jugadas.ModoJuego;
import Modelo.ModoEntrada;
import Modelo.observer.EventoJuego;
import Modelo.observer.ObservadorJuego;

/**
 *
 *
 * @author rodol
 */
public class ControladorPrincipal implements ObservadorJuego {

    private GestorJuego gestor;
    private IGui vista;
    private ControladorCartones controladorCartones;
    private ControladorPartida controladorPartida;

    public ControladorPrincipal(IGui vista) {
        this.vista = vista;
        this.gestor = GestorJuego.getInstancia();
        this.gestor.agregarObservador(this);
        this.controladorCartones = new ControladorCartones(vista, gestor);
        this.controladorPartida = new ControladorPartida(vista, gestor);
    }

    public void iniciarJuego() {
        try {
            gestor.iniciarJuego();
            
            vista.habilitarConfiguracion(false);

            vista.mostrarFrameTablero();

            boolean modoManual = gestor.getModoEntrada() == ModoEntrada.MANUAL;
            vista.habilitarBotonDeshacer(modoManual);

            if (modoManual) {
                vista.mostrarFrameTombolaManual();

            } else {
                vista.mostrarFrameTombolaAutomatica();
            }

            vista.mostrarMensaje("¡Juego iniciado! Buena suerte", "Bingo");

        } catch (IllegalStateException e) {
            vista.mostrarError(e.getMessage());
        }
    }
    
    public void cambiarModoEntrada(ModoEntrada modo) {
        if (gestor.isJuegoIniciado()) {
            vista.mostrarError("No puede cambiar el modo durante el juego");
            return;
        }
        
        gestor.setModoEntrada(modo);
    }
    
    public void cambiarTipoPartida(ModoJuego modo) {
        if (gestor.isJuegoIniciado()) {
            vista.mostrarError("No puede cambiar el tipo durante el juego");
            return;
        }
        
        gestor.setModoJuego(modo);
    }

    public void reiniciarJuego() {
        if (!gestor.isJuegoIniciado()) {
            return;
        }
        
        boolean confirma = vista.confirmar(
            "¿Está seguro de reiniciar el juego?", 
            "Reiniciar"
        );
        
        if (confirma) {
            gestor.reiniciarJuego();
            controladorPartida.reiniciarHistorial();
            vista.reiniciarTombola();
            vista.reiniciarMarcasCartones();
            vista.reiniciarTablero();
            vista.habilitarConfiguracion(true);
            
            vista.mostrarMensaje("Juego reiniciado", "Bingo");
        }
    }
 
    @Override
    public void actualizar(EventoJuego evento) {
        switch (evento.getTipo()) {
            case "MODO_ENTRADA_CAMBIADO":
                ModoEntrada modo = (ModoEntrada) evento.getDatos();
                vista.actualizarModoEntrada(modo);
                break;
                
            case "MODO_JUEGO_CAMBIADO":
                ModoJuego modoJuego = (ModoJuego) evento.getDatos();
                vista.actualizarTipoPartida(modoJuego);
                break;
        }
    }
    
    public ControladorCartones getControladorCartones() {
        return controladorCartones;
    }
    
    public ControladorPartida getControladorPartida() {
        return controladorPartida;
    }
    
    public GestorJuego getGestorJuego(){
        return gestor;
    }
}
