/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.UtilGUI;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author rodol
 */
public class UtilsGUI {
     public static boolean confirmar(Component compoment, String msg, String titulo){
        return JOptionPane.showConfirmDialog(compoment, msg, titulo, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION;
    }
    
    private static void mostrarMensaje(Component compoment,String msg, String titulo, int tipo){
        JOptionPane.showMessageDialog(compoment, msg, titulo, tipo);
    }
    
    public static void mostrarMensaje(Component compoment,String msg, String titulo){
        mostrarMensaje(compoment, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void mostrarError(Component compoment, String msg){
        mostrarMensaje(compoment, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static String solicitar(Component compoment, String msg,String titulo){
        return JOptionPane.showInputDialog(compoment, msg, titulo, JOptionPane.QUESTION_MESSAGE);
    }
}
