/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Jugadas;

/**
 *
 * @author autoa
 */
public class JugadaHorizontal implements Winable{
    @Override
    public boolean verificarJugada(boolean[][] marcados) {
        for (int i = 0; i < 5; i++) {
            boolean filaCompleta = true;
            for (int j = 0; j < 5; j++) {
                if (!marcados[i][j]) {
                    filaCompleta = false;
                    break;
                }
            }
            if (filaCompleta) return true;
        }
        return false;
    }
}
