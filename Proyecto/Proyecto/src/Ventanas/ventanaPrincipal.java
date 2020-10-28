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

        //informacion.setVisible(false);
        cargarImagenes();

        oracleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bbdd = 3;
                abrirVentanaLogin();
            }
        });

        MYSQLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bbdd = 2;
                abrirVentanaLogin();
            }
        });

        DB4OButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bbdd = 4;
                abrirVentanaLogin();
            }
        });

        SQLiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bbdd = 1;
                abrirVentanaLogin();
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

    public void cargarImagenes() {
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
    }

    public void abrirVentanaLogin() {
        JFrame frame = new JFrame("Login");
        Login login = new Login();
        frame.setContentPane(login.getPanel());
        login.setBbdd(bbdd);
        login.setVentanaLogin(frame);
        login.setVentanaPrincipal(frameVentanaPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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
