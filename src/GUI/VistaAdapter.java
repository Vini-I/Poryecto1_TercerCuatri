/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Modelo.Carton;
import Modelo.Jugadas.ModoJuego;
import Modelo.ModoEntrada;
import javax.swing.JOptionPane;

/**
 *
 * @author rodol
 */
public class VistaAdapter implements IGui {
    
    private FrmDesktopPane desktop;
    private IFrmCartonManual cartonManual;
    private IFrmTablero tablero;
    private IFrmTombolaAuto auto;
    private IFrmTombolaManual manual;

    public VistaAdapter(FrmDesktopPane desktop) {
        this.desktop = desktop;
    }

    @Override
    public void mostrarFrameAñadirCarton() {
        desktop.abrirFrameCartonBuilder();
    }

    @Override
    public void mostrarFrameCartonManual() {
        desktop.abrirFrameCartonManual();
    }

    @Override
    public void mostrarFrameModoEntrada() {
        desktop.abrirFrameModo();
    }

    @Override
    public void mostrarFrameTipoPartida() {
        desktop.abrirFramePartida();
    }

    @Override
    public void mostrarFrameTablero() {
        desktop.abrirFrameTablero();
    }

    @Override
    public void mostrarFrameTombolaAutomatica() {
        desktop.abrirFrameTombolaAuto();
    }

    @Override
    public void mostrarFrameTombolaManual() {
        desktop.abrirFrameTombolaManual();
    }

    @Override
    public void cerrarFramesConfiguracion() {
    }

    @Override
    public void agregarFrameCarton(Carton carton) {
        desktop.agregarFrameCarton(carton);
    }

    @Override
    public void actualizarCartones() {
        System.out.println("actualizando todos los cartones");
        desktop.actualizarTodosLosCartones();
    }

    @Override
    public void eliminarFrameCarton(String idCarton) {
        desktop.eliminarFrameCarton(idCarton);
    }

    @Override
    public void marcarNumeroEnCartones(int numero) {
        desktop.marcarNumeroEnTodosLosCartones(numero);
    }

    @Override
    public void reiniciarMarcasCartones() {
        desktop.reiniciarTodosLosCartones();
    }

    @Override
    public void marcarNumeroEnTablero(int numero) {
        tablero.marcarNumero(numero);
    }
    
    @Override
    public void desmarcarNumeroEnTablero(int numero){
      
    }

    @Override
    public void reiniciarTablero() {
        tablero.reiniciar();
    }

    @Override
    public void reiniciarTombola() {
        if (auto != null) {
            auto.reiniciar();
        }
        if (manual != null) {
            manual.reiniciar();
        }
    }

    @Override
    public void actualizarUltimoNumeroTombola(int numero) {
        if (auto != null) {
            auto.actualizarUltimoNumero(numero);
        }
        if (manual != null) {
            manual.actualizarUltimoNumero(numero);
        }
    }
    

    @Override
    public void habilitarBotonIngresarManual(boolean habilitar) {
    }

    @Override
    public void habilitarBotonGenerarAuto(boolean habilitar) {
    }

    @Override
    public void habilitarConfiguracion(boolean habilitar) {
    }

    @Override
    public void habilitarBotonDeshacer(boolean habilitar){
        if (tablero != null) {
        tablero.habilitarBotonDeshacer(habilitar);
    }
    }
    @Override
    public void actualizarModoEntrada(ModoEntrada modo) {
        System.out.println("Modo de entrada actualizado: " + modo);
    }

    @Override
    public void actualizarTipoPartida(ModoJuego modo) {
        System.out.println("Tipo de partida actualizado: " + modo);
    }

    @Override
    public void mostrarMensaje(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(desktop, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(desktop, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public boolean confirmar(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(desktop, mensaje, titulo, 
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    @Override
    public String solicitarTexto(String mensaje, String titulo) {
        return JOptionPane.showInputDialog(desktop, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
    }

    @Override
    public void mostrarGanador(Carton carton, String tipoJugada) {
         String mensaje = "🎉 ¡¡¡BINGO!!! 🎉\n\n" +
                     "Ganador: " + carton.getId() + "\n" +
                     "Tipo de jugada: " + tipoJugada + "\n\n" +
                     "¡Felicidades!";
    
    javax.swing.JOptionPane.showMessageDialog(desktop,
        mensaje,
        "¡TENEMOS UN GANADOR!",
        javax.swing.JOptionPane.INFORMATION_MESSAGE);
    
    desktop.resaltarCartonGanador(carton.getId());
    }

    @Override
    public int[][] obtenerMatrizCartonManual() {
        return cartonManual.obtenerMatriz();
    }
    
    public void setFrameCartonManualActual(IFrmCartonManual frame) {
        this.cartonManual = frame;
    }
    
    public void setFrameTablero(IFrmTablero frame) {
        this.tablero = frame;
    }
    
    public void setFrameTombolaAuto(IFrmTombolaAuto frame) {
        this.auto = frame;
    }
    
    public void setFrameTombolaManual(IFrmTombolaManual frame) {
        this.manual = frame;
    }
}
