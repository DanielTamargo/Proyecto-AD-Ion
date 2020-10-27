package com.company;

import Ventanas.ventanaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal vp = new ventanaPrincipal();
        frame.setContentPane(vp.getVentanaPrincipal());
        vp.setFrameVentanaPrincipal(frame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
