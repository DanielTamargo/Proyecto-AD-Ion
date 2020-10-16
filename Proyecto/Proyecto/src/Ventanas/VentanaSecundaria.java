package Ventanas;

import javax.swing.*;

public class VentanaSecundaria {
    private JTabbedPane tabbedPane1;
    private JPanel ventanaSecundaria;
    private JButton editarButton;
    private JButton eliminarButton;
    private JList list1;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField2;
    private JTextField textField5;
    private JButton añadirButton;
    private JButton clienteButton;
    private JButton empleadoButton;
    private JTextField textField6;

    public VentanaSecundaria(JFrame frame) {
        añadirButton.setIcon(new ImageIcon("Assets/plus.png"));
        editarButton.setIcon(new ImageIcon("Assets/edit.png"));
        eliminarButton.setIcon(new ImageIcon("Assets/delete.png"));
        clienteButton.setIcon(new ImageIcon("Assets/cliente.png"));
        empleadoButton.setIcon(new ImageIcon("Assets/empleado.png"));



    }


    public JPanel getPanelSecundario() {return ventanaSecundaria;}


}

