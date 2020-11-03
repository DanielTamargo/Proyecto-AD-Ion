package com.company;

import com.company.ventanas.VentanaPrincipal;
import javax.swing.*;

public class Main {

    /**
     * Ejecutar desde aquí.
     * Arranca la aplicación
     */
    public static void main(String[] args) {

        JFrame frame = new JFrame("Agencias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        VentanaPrincipal vp = new VentanaPrincipal();
        frame.setContentPane(vp.getVentanaPrincipal());
        vp.setFrameVentanaPrincipal(frame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
