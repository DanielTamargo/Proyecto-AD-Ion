package Ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventanaPrincipal {
    private JButton oracleButton;
    private JButton DB4OButton;
    private JButton MYSQLButton;
    private JButton SQLiteButton;
    private JPanel VentanaPrincipal;

    // TODO los listeners dentro del constructor vacío de la ventana
    public ventanaPrincipal() {


        oracleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hola");
                JFrame frame = new JFrame("VentaSecundaria");
                frame.setContentPane(new VentanaSecundaria(frame).getPanelSecundario());
                frame.pack();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
        });

        MYSQLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hola");
                JFrame frame = new JFrame("Venta Secundaria");
                frame.setContentPane(new VentanaSecundaria(frame).getPanelSecundario());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        DB4OButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Venta Secundaria");
                frame.setContentPane(new VentanaSecundaria(frame).getPanelSecundario());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        SQLiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new VentanaSecundaria(frame).getPanelSecundario());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ventanaPrincipal().VentanaPrincipal);
        frame.pack();
        frame.setVisible(true);
    }

}
