/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Controladores.ControladorPrincipal;
import Modelo.Carton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author llean
 */
public class IFrmCarton extends javax.swing.JInternalFrame {

    private Carton carton;
    private ControladorPrincipal controlador;
    private JLabel[][] labels;

    /**
     * Creates new form IFrmCarton
     */
    public IFrmCarton(Carton carton, ControladorPrincipal controlador) {
        this.carton = carton;
        this.controlador = controlador;
        this.labels = new JLabel[5][5];
        initComponents();
        llenarMatriz();
        configurarCerrar();
    }

    private void llenarMatriz() {
        Matriz.removeAll();
        Matriz.setBackground(new Color(32, 101, 137));

        int[][] numeros = getCarton().getNumeros();
        boolean[][] marcados = getCarton().getMarcados();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JLabel label = crearLabel(i, j, numeros[i][j], marcados[i][j]);
                labels[i][j] = label;
                Matriz.add(label);
            }
        }

        Matriz.revalidate();
        Matriz.repaint();
    }

    private JLabel crearLabel(int fila, int col, int numero, boolean marcado) {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(new Color(185, 72, 40), 3));

        if (fila == 2 && col == 2) {
            label.setText("FREE");
            label.setFont(new Font("Arial", Font.PLAIN, 36));
            label.setBackground(new Color(186, 74, 40));
            label.setForeground(new Color(242, 213, 148));
        } else {
            label.setText(String.valueOf(numero));
            label.setFont(new Font("Arial", Font.PLAIN, 36));

            if (marcado) {
                label.setBackground(new Color(186, 74, 40));
                label.setForeground(new Color(242, 213, 148));
            } else {
                label.setBackground(new Color(242, 213, 148));
                label.setForeground(new Color(186, 74, 40));
            }
            
        }

        return label;
    }

    public void actualizarMatriz() {
        boolean[][] marcados = getCarton().getMarcados();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JLabel label = labels[i][j];

                if (i == 2 && j == 2) {
                    continue;
                }

                if (marcados[i][j]) {
                    label.setBackground(new Color(186, 74, 40));
                    label.setForeground(new Color(242, 213, 148));
                } else {
                    label.setBackground(new Color(242, 213, 148));
                    label.setForeground(new Color(186, 74, 40));
                }
            }
        }

        Matriz.repaint();
    }
    
    public void reiniciar() {
    if (carton != null) {
        actualizarMatriz();
    }
}

    public void marcarNumero(int numero) {
        getCarton().marcarNumero(numero);

        actualizarMatriz();
    }

    public Carton getCarton() {
        return this.carton;
    }

    private void configurarCerrar() {
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                cerrar();
            }
        });
    }

    public void cerrar() {

        if (controlador.getGestorJuego().isJuegoIniciado()) {
            JOptionPane.showMessageDialog(this,
                    "No puede eliminar cartones durante el juego",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Desea eliminar el cartón " + carton.getId() + "?",
                "Eliminar Cartón",
                javax.swing.JOptionPane.YES_NO_OPTION);

        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            controlador.getControladorCartones().eliminarCarton(carton.getId());
            System.out.println(controlador.getGestorJuego().getCantidadCartones());
            this.dispose();
        }
        System.out.println(controlador.getGestorJuego().getCantidadCartones());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Matriz = new javax.swing.JPanel();
        Fondo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Matriz.setBackground(new java.awt.Color(242, 212, 147));
        Matriz.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(188, 75, 42), 5));
        Matriz.setLayout(new java.awt.GridLayout(5, 5));
        getContentPane().add(Matriz, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 520, 400));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Carton.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JPanel Matriz;
    // End of variables declaration//GEN-END:variables
}
