/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Exceptions.NumeroInvalidoExcepcion;
import Modelo.Jugadas.JugadaCartonLleno;
import Modelo.Jugadas.JugadaCuatroEsquinas;
import Modelo.Jugadas.JugadaDiagonal;
import Modelo.Jugadas.JugadaHorizontal;
import Modelo.Jugadas.JugadaVertical;
import Modelo.Jugadas.ModoJuego;
import static Modelo.Jugadas.ModoJuego.CARTON_LLENO;
import static Modelo.Jugadas.ModoJuego.CUATRO_ESQUINAS;
import Modelo.Jugadas.Winable;
import Modelo.listas.ListaCartones;
import Modelo.observer.NotificadorJuego;
import Modelo.observer.ObservadorJuego;
import java.util.ArrayList;

/**
 *
 * @author rodol
 */
public class GestorJuego {
    private static GestorJuego instancia;
    
    private ListaCartones gestorCartones;
    private NotificadorJuego notificador;
    private Tombola tombola;
    private Tablero tablero;
    private ModoJuego modoJuego;
    private ModoEntrada modoEntrada;
    private boolean juegoIniciado;
    private Carton cartonGanador;
    
    private GestorJuego() {
        this.gestorCartones = ListaCartones.getInstancia();
        this.notificador = new NotificadorJuego();
        this.tombola = Tombola.getInstancia();
        this.tablero = Tablero.getInstancia();
        this.modoJuego = ModoJuego.NORMAL;
        this.modoEntrada = ModoEntrada.AUTOMATICO;
        this.juegoIniciado = false;
        this.cartonGanador = null;
    }
    
    public static GestorJuego getInstancia() {
        if (instancia == null) {
            instancia = new GestorJuego();
        }
        return instancia;
    }
    
    // ========================================================================
    // GESTIÓN DE CARTONES
    // ========================================================================
    
    /**
     * Agrega un cartón automático
     */
    public Carton agregarCartonAutomatico(String id) throws Exception {
        Carton carton = gestorCartones.agregarCartonAutomatico(id);
        if(carton == null){
            throw new Exception("Error al crear carton automatico");
        }
        notificador.notificar("CARTON_AGREGADO", carton);
        return carton;
    }
    
    /**
     * Agrega un cartón manual
     */
    public Carton agregarCartonManual(String id, int[][] numeros) throws Exception {
        Carton carton = gestorCartones.agregarCartonManual(id, numeros);
        notificador.notificar("CARTON_AGREGADO", carton);
        return carton;
    }
    
    /**
     * Elimina un cartón
     */
    public boolean eliminarCarton(String id) {
        Carton carton = gestorCartones.buscarCarton(id);
        boolean eliminado = gestorCartones.eliminarCarton(id);
        if (eliminado) {
            notificador.notificar("CARTON_ELIMINADO", carton);
        }
        return eliminado;
    }
    
    /**
     * Busca un cartón por ID
     */
    public Carton buscarCarton(String id) {
        return gestorCartones.buscarCarton(id);
    }
    
    // ========================================================================
    // CONTROL DEL JUEGO
    // ========================================================================
    
    /**
     * Inicia el juego
     */
    public void iniciarJuego() throws IllegalStateException {
        if (!gestorCartones.hayCartones()) {
            throw new IllegalStateException("Debe agregar al menos un cartón");
        }
        if (juegoIniciado) {
            throw new IllegalStateException("El juego ya está iniciado");
        }
        juegoIniciado = true;
        notificador.notificar("JUEGO_INICIADO", null);
    }
    
    /**
     * Procesa la siguiente jugada automática
     * @return El número extraído
     */
    public int procesarSiguienteJugada() throws IllegalStateException, NumeroInvalidoExcepcion {
        validarJuegoActivo();
        
        if (modoEntrada != ModoEntrada.AUTOMATICO) {
            throw new IllegalStateException("El juego no está en modo automático");
        }
        
        int numero = tombola.extraerNumeroAleatorio();
        if (numero == -1) {
            throw new IllegalStateException("No quedan números disponibles");
        }
        
        procesarNumero(numero);
        return numero;
    }
    
    /**
     * Procesa un número ingresado manualmente
     */
    public void procesarNumeroManual(int numero) throws IllegalStateException, NumeroInvalidoExcepcion {
        validarJuegoActivo();
        
        if (modoEntrada != ModoEntrada.MANUAL) {
            throw new IllegalStateException("El juego no está en modo manual");
        }
        
        tombola.extraerNumeroManual(numero);
        procesarNumero(numero);
    }
    
    /**
     * Procesa un número (marca en tablero y cartones, verifica ganador)
     */
    private void procesarNumero(int numero) throws NumeroInvalidoExcepcion {
        tablero.marcarNumero(numero);
        gestorCartones.marcarNumeroEnTodos(numero);
        notificador.notificar("NUMERO_PROCESADO", numero);
        
        verificarGanador();
    }
    
    /**
     * Desmarca un número (solo en cartones, no en tablero)
     */
    public void desmarcarNumero(int numero) {
        gestorCartones.desmarcarNumeroEnTodos(numero);
        cartonGanador = null;
        notificador.notificar("NUMERO_DESMARCADO", numero);
    }
    
    /**
     * Reinicia el juego completo
     */
    public void reiniciarJuego() {
        tombola.reiniciar();
        tablero.reiniciar();
        gestorCartones.reiniciarTodos();
        cartonGanador = null;
        juegoIniciado = false;
        notificador.notificar("JUEGO_REINICIADO", null);
    }
    
    // ========================================================================
    // VERIFICACIÓN DE GANADORES (Strategy Pattern)
    // ========================================================================
    
    /**
     * Verifica si hay un ganador según el modo de juego
     */
    private void verificarGanador() {
        if (cartonGanador != null) {
            return; // Ya hay ganador
        }
        
        Carton ganador = null;
        
        if (modoJuego == ModoJuego.NORMAL) {
            ganador = verificarGanadorModoNormal();
        } else {
            Winable estrategia = obtenerEstrategia();
            ganador = verificarConEstrategia(estrategia);
        }
        
        if (ganador != null) {
            cartonGanador = ganador;
            notificador.notificar("GANADOR_ENCONTRADO", ganador);
        }
    }
    
    /**
     * Verifica ganador en modo normal (múltiples formas de ganar)
     */
    private Carton verificarGanadorModoNormal() {
        Winable[] estrategias = {
            new JugadaHorizontal(),
            new JugadaVertical(),
            new JugadaDiagonal(),
            new JugadaCuatroEsquinas()
        };
        
        for (Carton carton : gestorCartones.getCartones()) {
            for (Winable estrategia : estrategias) {
                if (estrategia.verificarJugada(carton)) {
                    return carton;
                }
            }
        }
        return null;
    }
    
    /**
     * Verifica con una estrategia específica
     */
    private Carton verificarConEstrategia(Winable estrategia) {
        for (Carton carton : gestorCartones.getCartones()) {
            if (estrategia.verificarJugada(carton)) {
                return carton;
            }
        }
        return null;
    }
    
    /**
     * Obtiene la estrategia según el modo de juego
     */
    private Winable obtenerEstrategia() {
        switch (modoJuego) {
            case CUATRO_ESQUINAS:
                return new JugadaCuatroEsquinas();
            case CARTON_LLENO:
                return new JugadaCartonLleno();
            default:
                return new JugadaHorizontal();
        }
    }
    
    /**
     * Valida que el juego esté activo
     */
    private void validarJuegoActivo() throws IllegalStateException {
        if (!juegoIniciado) {
            throw new IllegalStateException("El juego no ha sido iniciado");
        }
        if (cartonGanador != null) {
            throw new IllegalStateException("Ya hay un ganador");
        }
    }
    
    // ========================================================================
    // CONFIGURACIÓN
    // ========================================================================
    
    /**
     * Cambia el modo de juego
     */
    public void setModoJuego(ModoJuego modoJuego) {
        this.modoJuego = modoJuego;
        notificador.notificar("MODO_JUEGO_CAMBIADO", modoJuego);
    }
    
    /**
     * Cambia el modo de entrada
     */
    public void setModoEntrada(ModoEntrada modoEntrada) {
        this.modoEntrada = modoEntrada;
        notificador.notificar("MODO_ENTRADA_CAMBIADO", modoEntrada);
    }
    
    // ========================================================================
    // OBSERVER
    // ========================================================================
    
    public void agregarObservador(ObservadorJuego observador) {
        notificador.agregarObservador(observador);
    }
    
    public void eliminarObservador(ObservadorJuego observador) {
        notificador.eliminarObservador(observador);
    }
    
    // ========================================================================
    // CONSULTAS (Getters)
    // ========================================================================
    
    public ArrayList<Carton> getCartones() {
        return gestorCartones.getCartones();
    }
    
    public int getCantidadCartones() {
        return gestorCartones.getCantidadCartones();
    }
    
    public boolean hayCartones() {
        return gestorCartones.hayCartones();
    }
    
    public Carton getCartonGanador() {
        return cartonGanador;
    }
    
    public boolean isJuegoIniciado() {
        return juegoIniciado;
    }
    
    public ModoJuego getModoJuego() {
        return modoJuego;
    }
    
    public ModoEntrada getModoEntrada() {
        return modoEntrada;
    }
    
    public int getUltimoNumero() {
        return tombola.getUltimoNumeroExtraido();
    }
    
    public boolean hayNumerosDisponibles() {
        return tombola.hayNumerosDisponibles();
    }
    
    public boolean estaGenerado(int numero) {
        return tablero.estaGenerado(numero);
    }
}
