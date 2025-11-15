/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Jugadas;

import Modelo.Carton;

/**
 *
 * @author autoa
 */
public class JugadaCuatroEsquinas implements Winable {

    @Override
    public boolean verificarJugada(Carton carton) {
        boolean[][] marcados = carton.getMarcados();
        return marcados[0][0] && marcados[0][4]
                && marcados[4][0] && marcados[4][4];
    }
}
