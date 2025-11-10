/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Jugadas;

/**
 *
 * @author autoa
 */
public class JugadaCuatroEsquinas implements Winable{
    @Override
    public boolean verificarJugada(boolean[][] marcados) {
        return marcados[0][0] && marcados[0][4] &&
               marcados[4][0] && marcados[4][4];
    }
}
