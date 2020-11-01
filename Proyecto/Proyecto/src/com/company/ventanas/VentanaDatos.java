package com.company.ventanas;

import com.company.varios.Metadatos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaDatos {
    private JPanel panel;
    private JButton b_volver;
    private JTextPane textPane1;
    private StringBuilder sb;

    private JFrame ventanaDatos;
    private JFrame ventanaSecundaria;

    public VentanaDatos(StringBuilder sb) {
        this.sb = sb;

        volcarDatos();

        b_volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaDatos.dispose();
                ventanaSecundaria.setVisible(true);
            }
        });
    }


    public void volcarDatos() {
        textPane1.setText(String.valueOf(sb));
    }

    public void setVentanaDatos(JFrame ventanaDatos) {
        this.ventanaDatos = ventanaDatos;
    }

    public void setVentanaSecundaria(JFrame ventanaSecundaria) {
        this.ventanaSecundaria = ventanaSecundaria;
    }

    public JPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaDatos");
        frame.setContentPane(new VentanaDatos(new Metadatos().generarMetadatos(1)).panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
