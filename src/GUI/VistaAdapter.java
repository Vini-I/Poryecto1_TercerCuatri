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
    }

    @Override
    public void mostrarFrameTombolaAutomatica() {
    }

    @Override
    public void mostrarFrameTombolaManual() {
    }

    @Override
    public void cerrarFramesConfiguracion() {
    }

    @Override
    public void agregarFrameCarton(Carton carton) {
        desktop.agregarFrameCarton(carton);
    }

    @Override
    public void actualizarFrameCarton(Carton carton) {
    }

    @Override
    public void eliminarFrameCarton(String idCarton) {
        desktop.eliminarFrameCarton(idCarton);
    }

    @Override
    public void marcarNumeroEnCartones(int numero) {
    }

    @Override
    public void reiniciarMarcasCartones() {
    }

    @Override
    public void marcarNumeroEnTablero(int numero) {
    }

    @Override
    public void reiniciarTablero() {
    }

    @Override
    public void actualizarUltimoNumero(int numero) {
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
    }

    @Override
    public int[][] obtenerMatrizCartonManual() {
        return cartonManual.obtenerMatriz();
    }
    
       public void setFrameCartonManualActual(IFrmCartonManual frame) {
        this.cartonManual = frame;
    }
}
