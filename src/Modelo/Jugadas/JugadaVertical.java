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
public class JugadaVertical implements Winable {

    @Override
    public boolean verificarJugada(Carton carton) {
        boolean[][] marcados = carton.getMarcados();
        for (int j = 0; j < 5; j++) {
            boolean columnaCompleta = true;
            for (int i = 0; i < 5; i++) {
                if (!marcados[i][j]) {
                    columnaCompleta = false;
                    break;
                }
            }
            if (columnaCompleta) {
                return true;
            }
        }
        return false;
    }
}
