/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GUI;

import Modelo.Carton;
import Modelo.Jugadas.ModoJuego;
import Modelo.ModoEntrada;
import java.util.List;

/**
 *
 * @author rodol
 */
public interface IGui {
    void actualizarUltimoNumero(int numero);
    void actualizarTablero(int numero);
    void actualizarCartones(List<Carton> cartones);
    void agregarCartonAVista(Carton carton);
    void eliminarCartonDeVista(String idCarton);
    void mostrarGanador(Carton carton, String tipoJugada);
    void habilitarConfiguracion(boolean habilitar);
    void habilitarControlesJuego(boolean habilitar);
    void actualizarModoJuego(ModoJuego modo);
    void actualizarModoEntrada(ModoEntrada modo);
    void mostrarMensaje(String mensaje, String titulo);
    void mostrarError(String mensaje);
    boolean confirmar(String mensaje, String titulo);
    String solicitarTexto(String mensaje, String titulo);
    int[][] solicitarMatrizCartonManual();
    void limpiarTodo();
    void limpiarMarcasCartones();
    void limpiarTablero();
}
