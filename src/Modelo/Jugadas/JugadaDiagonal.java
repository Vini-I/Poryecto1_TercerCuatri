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
public class JugadaDiagonal implements Winable {

    @Override
    public boolean verificarJugada(Carton carton) {
        boolean[][] marcados = carton.getMarcados();
        boolean diagonal1 = true;
        boolean diagonal2 = true;

        for (int i = 0; i < 5; i++) {
            if (!marcados[i][i]) {
                diagonal1 = false;
            }
            if (!marcados[i][4 - i]) {
                diagonal2 = false;
            }
        }

        return diagonal1 || diagonal2;
    }
}
