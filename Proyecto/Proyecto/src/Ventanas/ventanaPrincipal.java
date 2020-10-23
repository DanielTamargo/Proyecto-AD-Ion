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
    private JPanel informacion;
    private JButton infoButton;
    private JLabel telefonolbl;
    private JLabel telefonolbl2;
    private JLabel telefonolbl3;
    private JLabel telefonolbl4;
    private JLabel direccionlbl;
    private JLabel direccionlbl2;
    private JLabel direccionlbl3;
    private JLabel direccionlbl4;

    // TODO los listeners dentro del constructor vacío de la ventana
    public ventanaPrincipal() {
        oracleButton.setIcon(new ImageIcon("Assets/oracle.png"));
        MYSQLButton.setIcon(new ImageIcon("Assets/mysql.png"));
        SQLiteButton.setIcon(new ImageIcon("Assets/sqlite.png"));
        DB4OButton.setIcon(new ImageIcon("Assets/db4o.png"));
        infoButton.setIcon(new ImageIcon("Assets/info.png"));
        telefonolbl.setIcon(new ImageIcon("Assets/telefono.png"));
        telefonolbl2.setIcon(new ImageIcon("Assets/telefono.png"));
        telefonolbl3.setIcon(new ImageIcon("Assets/telefono.png"));
        telefonolbl4.setIcon(new ImageIcon("Assets/telefono.png"));
        direccionlbl.setIcon(new ImageIcon("Assets/direccion.png"));
        direccionlbl2.setIcon(new ImageIcon("Assets/direccion.png"));
        direccionlbl3.setIcon(new ImageIcon("Assets/direccion.png"));
        direccionlbl4.setIcon(new ImageIcon("Assets/direccion.png"));
        informacion.setVisible(false);

        oracleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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


infoButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        informacion.setVisible(true);
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
