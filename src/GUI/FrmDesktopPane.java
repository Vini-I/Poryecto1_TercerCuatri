/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Controladores.ControladorPrincipal;
import Modelo.Carton;
import javax.swing.JFrame;

/**
 *
 * @author llean
 */
public class FrmDesktopPane extends javax.swing.JFrame {
    private ControladorPrincipal controlador;
    private VistaAdapter vistaAdaptador;
    /**
     * Creates new form FrmDesktopPane
     */
    public FrmDesktopPane() {
        initComponents();
        vistaAdaptador = new VistaAdapter(this);
        controlador = new ControladorPrincipal(vistaAdaptador);
        abrirFrameModo();

      setExtendedState(JFrame.MAXIMIZED_BOTH);


    }
    
    public void abrirFrameModo(){
        IFrmModo modoFrm = new IFrmModo(controlador,this);
        jDesktopPane1.add(modoFrm);
        modoFrm.setVisible(true);
        centrarFrame(modoFrm);
    }
    
     public void abrirFramePartida(){
        IFrmPartida partidaFrm = new IFrmPartida(controlador,this);
        jDesktopPane1.add(partidaFrm);
        partidaFrm.setVisible(true);
        centrarFrame(partidaFrm);
    }
     
    public void abrirFrameCartonBuilder() {
        IFrmCartonBuilder builderFrm = new IFrmCartonBuilder(controlador, this);
        jDesktopPane1.add(builderFrm);
        builderFrm.setVisible(true);
        centrarFrame(builderFrm);
    }
    
    public void abrirFrameCartonManual() {
        IFrmCartonManual manualFrm = new IFrmCartonManual(controlador,this);
        jDesktopPane1.add(manualFrm);
        manualFrm.setVisible(true);
        centrarFrame(manualFrm);
        vistaAdaptador.setFrameCartonManualActual(manualFrm);
    }
    
    public void abrirFrameTablero() {
        IFrmTablero tableroFrm = new IFrmTablero(controlador);
        jDesktopPane1.add(tableroFrm);
        tableroFrm.setVisible(true);
        tableroFrm.setLocation(10,10);
        vistaAdaptador.setFrameTablero(tableroFrm);
    }
    
    public void abrirFrameTombolaAuto() {
        IFrmTombolaAuto frameTombola = new IFrmTombolaAuto(controlador);
        jDesktopPane1.add(frameTombola);
        frameTombola.setLocation(10, 416);
        frameTombola.setVisible(true);
        
        if (vistaAdaptador != null) {
            vistaAdaptador.setFrameTombolaAuto(frameTombola);
        }
    }
    
    public void abrirFrameTombolaManual() {
        IFrmTombolaManual frameTombola = new IFrmTombolaManual(controlador);
        jDesktopPane1.add(frameTombola);
        frameTombola.setLocation(10, 416);
        frameTombola.setVisible(true);

        if (vistaAdaptador != null) {
            vistaAdaptador.setFrameTombolaManual(frameTombola);
        }
    }

    
     private void centrarFrame(javax.swing.JInternalFrame frame) {
        int x = (jDesktopPane1.getWidth() - frame.getWidth()) / 2;
        int y = (jDesktopPane1.getHeight() - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }
 
    public void agregarFrameCarton(Carton carton) {
        
        IFrmCarton frameCarton = new IFrmCarton(carton, controlador);
        jDesktopPane1.add(frameCarton);
        frameCarton.setVisible(true);

        posicionarEnCascada(frameCarton);
    }

    public void eliminarFrameCarton(String id) {
        for (javax.swing.JInternalFrame frame : jDesktopPane1.getAllFrames()) {
            if (frame instanceof IFrmCarton) {
                IFrmCarton frameCarton = (IFrmCarton) frame;
                if (frameCarton.getCarton().getId().equals(id)) {
                    frame.dispose();
                    break;
                }
            }
        }
}
    
    public void marcarNumeroEnTodosLosCartones(int numero) {
    for (javax.swing.JInternalFrame frame : jDesktopPane1.getAllFrames()) {
        if (frame instanceof IFrmCarton) {
            IFrmCarton frameCarton = (IFrmCarton) frame;
            frameCarton.marcarNumero(numero);
        }
    }
}
    
    public void reiniciarTodosLosCartones() {
    for (javax.swing.JInternalFrame frame : jDesktopPane1.getAllFrames()) {
        if (frame instanceof IFrmCarton) {
            IFrmCarton frameCarton = (IFrmCarton) frame;
            frameCarton.reiniciar();
        }
    }
}
    
    public void actualizarTodosLosCartones() {
    for (javax.swing.JInternalFrame frame : jDesktopPane1.getAllFrames()) {
        if (frame instanceof IFrmCarton) {
            IFrmCarton frameCarton = (IFrmCarton) frame;
            System.out.println("Actualizando caarton: "  + frameCarton.getCarton().getId());
            frameCarton.actualizarMatriz();
        }
    }
}
    
    public void resaltarCartonGanador(String idGanador) {
    for (javax.swing.JInternalFrame frame : jDesktopPane1.getAllFrames()) {
        if (frame instanceof IFrmCarton) {
            IFrmCarton frameCarton = (IFrmCarton) frame;
            if (frameCarton.getCarton().getId().equals(idGanador)) {
                frameCarton.setTitle("🏆 GANADOR: " + idGanador + " 🏆");
                frameCarton.toFront();
                break;
            }
        }
    }
}
    
    

    private int offsetX = 0;
    private int offsetY = 0;

    private void posicionarEnCascada(javax.swing.JInternalFrame frame) {
        int xInicial = 1920 - 552 - 20;
        int yInicial = 10;
        
        int x = xInicial + offsetX;
        int y = yInicial + offsetY;
        
        frame.setLocation(x, y);
        
        offsetX += 30;
        offsetY += 30;
        
        if (offsetX > 300 || offsetY > 400) {
            offsetX = 0;
            offsetY = 0;
        }
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo.png"))); // NOI18N

        jDesktopPane1.setLayer(Fondo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(Fondo)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(Fondo)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmDesktopPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDesktopPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDesktopPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDesktopPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDesktopPane().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JDesktopPane jDesktopPane1;
    // End of variables declaration//GEN-END:variables

  
}
