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
public class JugadaHorizontal implements Winable {

    @Override
    public boolean verificarJugada(Carton carton) {
            System.out.println(">>> [JugadaHorizontal] ===== VERIFICANDO =====");
        boolean[][] marcados = carton.getMarcados();
        for (int i = 0; i < 5; i++) {
            boolean filaCompleta = true;
            int marcadasEnFila = 0;
            String detalle = "    >>> Fila " + i + ": ";
            for (int j = 0; j < 5; j++) {
                boolean marcado = marcados[i][j];
                detalle += (marcado ? "[X]" : "[ ]") + " ";
                if (!marcado) {
                    filaCompleta = false;
                    //break; poner el break luego
                }else{
                    marcadasEnFila ++;
                }
            }
            detalle += " (" + marcadasEnFila + "/5 marcadas)";
            
            if (filaCompleta) {
            detalle += " ✓✓✓ COMPLETA ✓✓✓";
        }
            
            if (filaCompleta) {
                           System.out.println(">>> [JugadaHorizontal] ¡¡¡LÍNEA HORIZONTAL ENCONTRADA EN FILA " + i + "!!!");
                return true;
            }
        }
            System.out.println(">>> [JugadaHorizontal] No hay líneas horizontales completas");
        return false;
    }
}
