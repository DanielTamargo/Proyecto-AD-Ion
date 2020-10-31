package Ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private JButton oracleButton;
    private JButton DB4OButton;
    private JButton MYSQLButton;
    private JButton SQLiteButton;
    private JPanel VentanaPrincipal;
    private JPanel informacion;
    private JButton infoButton;

    private JLabel iconoContact1;
    private JLabel iconoDir1;
    private JLabel iconoBuild1;
    private JLabel iconoContact2;
    private JLabel iconoContact4;
    private JLabel iconoContact3;
    private JLabel iconoBuild2;
    private JLabel iconoBuild3;
    private JLabel iconoBuild4;
    private JLabel iconoDir2;
    private JLabel iconoDir3;
    private JLabel iconoDir4;

    private JFrame frameVentanaPrincipal;
    private int bbdd = 1;

    // TODO los listeners dentro del constructor vacío de la ventana
    public VentanaPrincipal() {
        informacion.setVisible(false);
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
                if (informacion.isVisible()) {
                    informacion.setVisible(false);
                    frameVentanaPrincipal.pack();
                    //frameVentanaPrincipal.setLocationRelativeTo(null);
                }
                else {
                    informacion.setVisible(true);
                    frameVentanaPrincipal.pack();
                    //frameVentanaPrincipal.setLocationRelativeTo(null);
                }
            }
        });


    }

    public void cargarImagenes() {

        //AGENCIAS
        oracleButton.setIcon(new ImageIcon("Assets/oracle.png"));
        MYSQLButton.setIcon(new ImageIcon("Assets/mysql.png"));
        SQLiteButton.setIcon(new ImageIcon("Assets/sqlite.png"));
        DB4OButton.setIcon(new ImageIcon("Assets/db4o.png"));

        //INFO
        infoButton.setIcon(new ImageIcon("Assets/info.png"));

        iconoDir1.setIcon(new ImageIcon("Assets/dir.png"));
        iconoDir2.setIcon(new ImageIcon("Assets/dir.png"));
        iconoDir3.setIcon(new ImageIcon("Assets/dir.png"));
        iconoDir4.setIcon(new ImageIcon("Assets/dir.png"));

        iconoBuild1.setIcon(new ImageIcon("Assets/build.png"));
        iconoBuild2.setIcon(new ImageIcon("Assets/build.png"));
        iconoBuild3.setIcon(new ImageIcon("Assets/build.png"));
        iconoBuild4.setIcon(new ImageIcon("Assets/build.png"));

        iconoContact1.setIcon(new ImageIcon("Assets/contact.png"));
        iconoContact2.setIcon(new ImageIcon("Assets/contact.png"));
        iconoContact3.setIcon(new ImageIcon("Assets/contact.png"));
        iconoContact4.setIcon(new ImageIcon("Assets/contact.png"));
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
        frame.setContentPane(new VentanaPrincipal().VentanaPrincipal);
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
