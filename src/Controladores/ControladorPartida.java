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
        System.out.println("llamando inicializar timer");
        inicializarTimer();
        System.out.println("constructor fin");
    }
    
    private void inicializarTimer() {
        int milisegundos = intervaloSegundos * 1000;
        System.out.println(" inicializando timer con intervalo " + milisegundos);
        timerAutomatico = new Timer(milisegundos, e -> {
            System.out.println("timer iniciado");
            try {
                procesarNumeroAutomatico();
            } catch (NumeroInvalidoExcepcion ex) {
                System.out.println("Error " +ex.getMessage());
                ex.printStackTrace();
            }
        });
        
        timerAutomatico.setRepeats(true);
        System.out.println("Time inicializado correctamente");
    }
    

    public void iniciarModoAutomatico() {
        if (!gestor.isJuegoIniciado()) {
            vista.mostrarError("Debe iniciar el juego primero");
            return;
        }
        
        if (gestor.getModoEntrada() != ModoEntrada.AUTOMATICO) {
            System.out.println("modo actual "+ gestor.getModoEntrada());
            vista.mostrarError("El juego no está en modo automático");
            return;
        }
        
        if (gestor.getCartonGanador() != null) {
            System.out.println("hay ganador");
            vista.mostrarError("Ya hay un ganador");
            return;
        }
        System.out.println("sin ganador");
        timerAutomatico.start();
        System.out.println("timer iniciado " + timerAutomatico.isRunning());
        vista.habilitarBotonGenerarAuto(false);
        System.out.println("fin");
    }

    public void detenerModoAutomatico() {
        timerAutomatico.stop();
        vista.habilitarBotonGenerarAuto(true);
    }
    
    private void procesarNumeroAutomatico() throws NumeroInvalidoExcepcion {
        try {
            if (gestor.getCartonGanador() != null) {
                System.out.println("hay ganador detener timer");
                detenerModoAutomatico();
                return;
            }
            
            if (!gestor.hayNumerosDisponibles()) {
                System.out.println("no hay mas numeros detener");
                detenerModoAutomatico();
                vista.mostrarMensaje("No quedan más números", "Fin del juego");
                return;
            }
            
            System.out.println("llamando a procesarSiguenteJugada");
            int numero = gestor.procesarSiguienteJugada();
            System.out.println("Numero procesado " + numero);
            
            ComandoMarcarNumero comando = new ComandoMarcarNumero(numero, gestor.getCartones());
            historial.ejecutarComando(comando);
            System.out.println("comando ejecutado");
            
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
          Integer numeroADesmarcar = historial.obtenerUltimoNumero();
          
        boolean confirma = vista.confirmar(
        "¿Desmarcar el número " + numeroADesmarcar + " de los cartones?", 
        "Confirmar"
        );
        
        if (confirma) {
            System.out.println(historial.getCantidadComandos());
            historial.deshacer();
            System.out.println(historial.getCantidadComandos());
            vista.actualizarCartones();
            vista.mostrarMensaje("Número " + numeroADesmarcar + " desmarcado de los cartones", 
            "Deshacer");
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
                vista.actualizarUltimoNumeroTombola(numero);
                vista.marcarNumeroEnTablero(numero);
                vista.marcarNumeroEnCartones(numero);
                break;
                
            case "NUMERO_DESMARCADO":
                int numeroDesmarcado = (Integer) evento.getDatos();
                vista.actualizarUltimoNumeroTombola(0);
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
            
            case "MODO_ENTRADA_CAMBIADO":
                ModoEntrada modo = (ModoEntrada) evento.getDatos();
                System.out.println(">>> [ControladorPartida.actualizar] Modo entrada: " + modo);

                vista.habilitarBotonDeshacer(modo == ModoEntrada.MANUAL);
                break;
        }
    }
}
