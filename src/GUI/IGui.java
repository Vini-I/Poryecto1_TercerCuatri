/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GUI;

import Modelo.Carton;
import Modelo.Jugadas.ModoJuego;
import Modelo.ModoEntrada;

/**
 *
 * @author rodol
 */
public interface IGui {
    
    void mostrarFrameAñadirCarton();
    void mostrarFrameCartonManual();
    void mostrarFrameModoEntrada();
    void mostrarFrameTipoPartida();
    void mostrarFrameTablero();
    void mostrarFrameTombolaAutomatica();
    void mostrarFrameTombolaManual();
    void cerrarFramesConfiguracion();
    void agregarFrameCarton(Carton carton);
    void actualizarCartones();
    void eliminarFrameCarton(String idCarton);
    void marcarNumeroEnCartones(int numero);
    void reiniciarMarcasCartones();
    void marcarNumeroEnTablero(int numero);
    void desmarcarNumeroEnTablero(int numero);
    void reiniciarTablero();
    void reiniciarTombola();
    void actualizarUltimoNumeroTombola(int numero);
    void habilitarBotonIngresarManual(boolean habilitar);
    void habilitarBotonGenerarAuto(boolean habilitar);
    void habilitarConfiguracion(boolean habilitar);
    void actualizarModoEntrada(ModoEntrada modo);
    void actualizarTipoPartida(ModoJuego modo);
    void mostrarMensaje(String mensaje, String titulo);
    void mostrarError(String mensaje);
    boolean confirmar(String mensaje, String titulo);
    String solicitarTexto(String mensaje, String titulo);
    void mostrarGanador(Carton carton, String tipoJugada);
    int[][] obtenerMatrizCartonManual();
    
    
    
    
    
    
    
    
}
