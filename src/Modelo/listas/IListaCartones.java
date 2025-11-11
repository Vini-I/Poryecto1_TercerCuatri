/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo.listas;

import Modelo.Carton;

/**
 *
 * @author rodol
 */
public interface IListaCartones {
    public Carton agregarCartonAutomatico(String id) throws Exception;
    public Carton agregarCartonManual(String id, int[][] numeros) throws Exception;
    public boolean eliminarCarton(String id);
    public Carton buscarCarton(String id);
}
