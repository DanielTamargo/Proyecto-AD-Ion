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

    private JFrame frameVentanaPrincipal;
    private int bbdd = 1;

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
        //informacion.setVisible(false);

        oracleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bbdd = 3;
                abrirVentanaSecundaria();
            }
        });

        MYSQLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bbdd = 2;
                abrirVentanaSecundaria();
            }
        });

        DB4OButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bbdd = 4;
                abrirVentanaSecundaria();
            }
        });

        SQLiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bbdd = 1;
                abrirVentanaSecundaria();
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (informacion.isVisible())
                    informacion.setVisible(false);
                else
                    informacion.setVisible(true);
            }
        });


    }

    public void abrirVentanaSecundaria() {
        JFrame frame = new JFrame("VentaSecundaria");
        VentanaSecundaria vs = new VentanaSecundaria();
        frame.setContentPane(vs.getPanelSecundario());
        vs.setBbdd(bbdd);
        vs.cargarDatos();
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frameVentanaPrincipal.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ventanaPrincipal().VentanaPrincipal);
        frame.pack();
        frame.setVisible(true);
    }

    public void setFrameVentanaPrincipal(JFrame frameVentanaPrincipal) {
        this.frameVentanaPrincipal = frameVentanaPrincipal;
    }

    public JPanel getVentanaPrincipal() {
        return VentanaPrincipal;
    }
}
