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
    private JButton nuevaVisitaButton;
    private JButton editarVisitaButton;
    private JButton eliminarVisitaButton;
    private JList list1;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JComboBox comboBox2;
    private JList list2;
    private JTextField textField2;
    private JTextField textField5;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JButton realizarReservaButton;
    private JButton aceptarButton;
    private JSpinner spinner1;
    private JButton misVisitasButton;

    public VentanaSecundaria(JFrame frame) {
        añadirButton.setIcon(new ImageIcon("Assets/añadir.png"));
        editarButton.setIcon(new ImageIcon("Assets/save.png"));
        eliminarButton.setIcon(new ImageIcon("Assets/delete.png"));
        clienteButton.setIcon(new ImageIcon("Assets/cliente.png"));
        empleadoButton.setIcon(new ImageIcon("Assets/empleado.png"));
        nuevaVisitaButton.setIcon(new ImageIcon("Assets/nuevaVisita.png"));

//Ocultamos todos los campos ventana RRHH
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
        listaList.setVisible(true);
        NombreListaLbl.setVisible(true);
//Acciones ventana RRHH
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
        //Acciones ventanas guía


    }


    public JPanel getPanelSecundario() {return ventanaSecundaria;}


}

