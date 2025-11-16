package Controladores;

import Exceptions.NumeroInvalidoExcepcion;
import GUI.IGui;
import Modelo.Carton;
import Modelo.GestorJuego;
import Modelo.ModoEntrada;
import Modelo.command.ComandoMarcarNumero;
import Modelo.command.HistorialComandos;
import Modelo.observer.EventoJuego;
import Modelo.observer.ObservadorJuego;
import javax.swing.Timer;

/**
 * @author rodol
 */
public class ControladorPartida implements ObservadorJuego {

    private IGui vista;
    private GestorJuego gestor;
    private HistorialComandos historial;
    private Timer timerAutomatico;
    private int intervaloSegundos = 2;
    
    public ControladorPartida(IGui vista, GestorJuego gestor) {
        this.vista = vista;
        this.gestor = gestor;
        this.gestor.agregarObservador(this);
        this.historial = new HistorialComandos();
        inicializarTimer();
    }
    
    private void inicializarTimer() {
        int milisegundos = intervaloSegundos * 1000;
        
        timerAutomatico = new Timer(milisegundos, e -> {
            try {
                procesarNumeroAutomatico();
            } catch (NumeroInvalidoExcepcion ex) {
                ex.getMessage();
            }
        });
        
        timerAutomatico.setRepeats(true);
    }
    

    public void iniciarModoAutomatico() {
        if (!gestor.isJuegoIniciado()) {
            vista.mostrarError("Debe iniciar el juego primero");
            return;
        }
        
        if (gestor.getModoEntrada() != ModoEntrada.AUTOMATICO) {
            vista.mostrarError("El juego no está en modo automático");
            return;
        }
        
        if (gestor.getCartonGanador() != null) {
            vista.mostrarError("Ya hay un ganador");
            return;
        }
        
        timerAutomatico.start();
        vista.habilitarBotonGenerarAuto(false);
    }

    public void detenerModoAutomatico() {
        timerAutomatico.stop();
        vista.habilitarBotonGenerarAuto(true);
    }
    
    private void procesarNumeroAutomatico() throws NumeroInvalidoExcepcion {
        try {
            if (gestor.getCartonGanador() != null) {
                detenerModoAutomatico();
                return;
            }
            
            if (!gestor.hayNumerosDisponibles()) {
                detenerModoAutomatico();
                vista.mostrarMensaje("No quedan más números", "Fin del juego");
                return;
            }
            
            int numero = gestor.procesarSiguienteJugada();
            
            ComandoMarcarNumero comando = new ComandoMarcarNumero(numero, gestor.getCartones());
            historial.ejecutarComando(comando);
            
        } catch (IllegalStateException e) {
            detenerModoAutomatico();
            vista.mostrarError(e.getMessage());
        }
    }
    
   
    public void procesarNumeroManual(String numeroTexto) throws NumeroInvalidoExcepcion {
        if (!gestor.isJuegoIniciado()) {
            vista.mostrarError("Debe iniciar el juego primero");
            return;
        }
        
        if (gestor.getCartonGanador() != null) {
            vista.mostrarError("Ya hay un ganador");
            return;
        }
        
        if (gestor.getModoEntrada() != ModoEntrada.MANUAL) {
            vista.mostrarError("El juego no está en modo manual");
            return;
        }
        
        try {
            int numero = Integer.parseInt(numeroTexto);
            
            if (numero < 1 || numero > 75) {
                vista.mostrarError("El número debe estar entre 1 y 75");
                return;
            }
            
            if (gestor.estaGenerado(numero)) {
                vista.mostrarError("El número " + numero + " ya fue generado");
                return;
            }

            gestor.procesarNumeroManual(numero);

            ComandoMarcarNumero comando = new ComandoMarcarNumero(numero, gestor.getCartones());
            historial.ejecutarComando(comando);
            
        } catch (NumberFormatException e) {
            vista.mostrarError("Debe ingresar un número válido");
        } catch (IllegalStateException e) {
            vista.mostrarError(e.getMessage());
        }
    }
    

    public void desmarcarUltimoNumero() {
        if (!gestor.isJuegoIniciado()) {
            vista.mostrarError("El juego no ha iniciado");
            return;
        }
        
        if (!historial.hayComandos()) {
            vista.mostrarError("No hay números para desmarcar");
            return;
        }
        
        boolean confirma = vista.confirmar(
            "¿Desmarcar el último número?", 
            "Confirmar"
        );
        
        if (confirma) {
            historial.deshacer();
            vista.mostrarMensaje("Número desmarcado", "Deshacer");
        }
    }

    public void setIntervaloSegundos(int segundos) {
        if (segundos < 1 || segundos > 10) {
            return;
        }
        
        this.intervaloSegundos = segundos;
        timerAutomatico.setDelay(segundos * 1000);
    }

    public void reiniciarHistorial() {
        historial.limpiar();
    }

    @Override
    public void actualizar(EventoJuego evento) {
        switch (evento.getTipo()) {
            case "NUMERO_PROCESADO":
                int numero = (Integer) evento.getDatos();
                vista.actualizarUltimoNumero(numero);
                vista.marcarNumeroEnTablero(numero);
                vista.marcarNumeroEnCartones(numero);
                break;
                
            case "NUMERO_DESMARCADO":
                break;

            case "GANADOR_ENCONTRADO":
                Carton ganador = (Carton) evento.getDatos();
                detenerModoAutomatico();
                vista.mostrarGanador(ganador, gestor.getModoJuego().toString());
                vista.habilitarBotonGenerarAuto(false);
                vista.habilitarBotonIngresarManual(false);
                break;

            case "JUEGO_REINICIADO":
                detenerModoAutomatico();
                reiniciarHistorial();
                vista.habilitarBotonGenerarAuto(true);
                vista.habilitarBotonIngresarManual(true);
                break;
        }
    }
}
