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

    public void ventanaInicio() {


    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ventanaPrincipal().VentanaPrincipal);
        frame.pack();
        frame.setVisible(true);
    }}
