package Ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSecundaria {
    private JTabbedPane tabbedPane1;
    private JPanel ventanaSecundaria;
    private JButton editarButton;
    private JButton eliminarButton;
    private JList listaList;
    private JTextField DNI;
    private JTextField CARGO;
    private JTextField NOMBRE;
    private JTextField NACIONALIDAD;
    private JTextField FECHNAC;
    private JButton añadirButton;
    private JButton clienteButton;
    private JButton empleadoButton;
    private JTextField APELLIDO;
    private JTextField FECHACONTR;
    private JTextField EDAD;
    private JTextField PROFESION;
    private JPasswordField CONTRASEÑA;
    private JLabel EDADlbl;
    private JLabel FECHNAClbl;
    private JLabel NACIONALIDADlbl;
    private JLabel PROFESIONlbl;
    private JLabel CARGOlbl;
    private JLabel FECHCONTRlbl;
    private JLabel DNIlbl;
    private JLabel NOMBRElbl;
    private JLabel AELLIDOlbl;
    private JLabel Contraseñalbl;
    private JLabel NombreListaLbl;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public VentanaSecundaria(JFrame frame) {
        añadirButton.setIcon(new ImageIcon("Assets/plus.png"));
        editarButton.setIcon(new ImageIcon("Assets/edit.png"));
        eliminarButton.setIcon(new ImageIcon("Assets/delete.png"));
        clienteButton.setIcon(new ImageIcon("Assets/cliente.png"));
        empleadoButton.setIcon(new ImageIcon("Assets/empleado.png"));
//Ocultamos todos los campos
        DNI.setVisible(false);
        EDAD.setVisible(false);
        PROFESION.setVisible(false);
        CARGO.setVisible(false);
        NOMBRE.setVisible(false);
        NACIONALIDAD.setVisible(false);
        APELLIDO.setVisible(false);
        FECHACONTR.setVisible(false);
        FECHNAC.setVisible(false);
        CONTRASEÑA.setVisible(false);
        FECHNAClbl.setVisible(false);
        NACIONALIDADlbl.setVisible(false);
        PROFESIONlbl.setVisible(false);
        CARGOlbl.setVisible(false);
        FECHCONTRlbl.setVisible(false);
        EDADlbl.setVisible(false);
        DNIlbl.setVisible(false);
        NOMBRElbl.setVisible(false);
        AELLIDOlbl.setVisible(false);
        Contraseñalbl.setVisible(false);
        listaList.setVisible(false);
        NombreListaLbl.setVisible(false);

        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DNI.setVisible(true);
                EDAD.setVisible(true);
                PROFESION.setVisible(true);
                CARGO.setVisible(false);
                NOMBRE.setVisible(true);
                NACIONALIDAD.setVisible(false);
                APELLIDO.setVisible(true);
                FECHACONTR.setVisible(false);
                FECHNAC.setVisible(false);
                CONTRASEÑA.setVisible(true);
                FECHNAClbl.setVisible(false);
                NACIONALIDADlbl.setVisible(false);
                PROFESIONlbl.setVisible(true);
                CARGOlbl.setVisible(false);
                FECHCONTRlbl.setVisible(false);
                EDADlbl.setVisible(true);
                DNIlbl.setVisible(true);
                NOMBRElbl.setVisible(true);
                AELLIDOlbl.setVisible(true);
                Contraseñalbl.setVisible(true);



            }
        });

        empleadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DNI.setVisible(true);
                EDAD.setVisible(false);
                PROFESION.setVisible(false);
                CARGO.setVisible(true);
                NOMBRE.setVisible(true);
                NACIONALIDAD.setVisible(true);
                APELLIDO.setVisible(true);
                FECHACONTR.setVisible(true);
                FECHNAC.setVisible(true);
                CONTRASEÑA.setVisible(true);
                FECHNAClbl.setVisible(true);
                NACIONALIDADlbl.setVisible(true);
                PROFESIONlbl.setVisible(false);
                CARGOlbl.setVisible(true);
                FECHCONTRlbl.setVisible(true);
                EDADlbl.setVisible(false);
                DNIlbl.setVisible(true);
                NOMBRElbl.setVisible(true);
                AELLIDOlbl.setVisible(true);
                Contraseñalbl.setVisible(true);

            }
        });

        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaList.setVisible(false);
                NombreListaLbl.setVisible(false);


            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaList.setVisible(true);
                NombreListaLbl.setVisible(true);


            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaList.setVisible(true);
                NombreListaLbl.setVisible(true);


            }
        });

    }


    public JPanel getPanelSecundario() {return ventanaSecundaria;}


}

