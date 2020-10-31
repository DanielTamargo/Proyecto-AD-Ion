package com.company;

import Ventanas.ventanaPrincipal;
import javax.swing.*;

public class Main {

    /**
     * Arranca la aplicación
     */
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal vp = new ventanaPrincipal();
        frame.setContentPane(vp.getVentanaPrincipal());
        vp.setFrameVentanaPrincipal(frame);
        frame.pack();
        /*
        Dimension windowSize = frame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2 - 60;
        frame.setLocation(dx, dy);
        */
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
