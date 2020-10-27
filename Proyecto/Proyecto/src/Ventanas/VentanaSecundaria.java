package Ventanas;

import com.company.DB4O.CargarDatosDB4O;
import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;
import com.company.sql.CargarDatos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VentanaSecundaria {
    private JTabbedPane tabbedPane1;
    private JPanel ventanaSecundaria;
    private JButton editarButton;
    private JButton eliminarButton;
    private JList listEmpleadosOClientes;
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
    private JList listVisitasEmp;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JComboBox comboBox2;
    private JList listVisitasCli;
    private JTextField textField2;
    private JTextField textField5;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JButton realizarReservaButton;
    private JSpinner spinner1;
    private JButton misVisitasButton;
    private JButton misVisitasButton2;

    private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Visita> visitas = new ArrayList<Visita>();

    private int bbdd = 1;
    private JFrame ventanaPrincipal;

    public VentanaSecundaria() {
        añadirButton.setIcon(new ImageIcon("Assets/añadir.png"));
        editarButton.setIcon(new ImageIcon("Assets/save.png"));
        editarVisitaButton.setIcon(new ImageIcon("Assets/save.png"));
        eliminarButton.setIcon(new ImageIcon("Assets/delete.png"));
        eliminarVisitaButton.setIcon(new ImageIcon("Assets/delete.png"));
        clienteButton.setIcon(new ImageIcon("Assets/cliente.png"));
        empleadoButton.setIcon(new ImageIcon("Assets/empleado.png"));
        nuevaVisitaButton.setIcon(new ImageIcon("Assets/nuevaVisita.png"));
        misVisitasButton.setIcon(new ImageIcon("Assets/mis.png"));
        misVisitasButton2.setIcon(new ImageIcon("Assets/mis.png"));
        realizarReservaButton.setIcon(new ImageIcon("Assets/pedido.png"));

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
        //listEmpleadosOClientes.setVisible(true);
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
                cargarClientesEnLista();
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
                cargarEmpleadosEnLista();
            }
        });

        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listEmpleadosOClientes.setVisible(false);
                NombreListaLbl.setVisible(false);


            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listEmpleadosOClientes.setVisible(true);
                NombreListaLbl.setVisible(true);


            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listEmpleadosOClientes.setVisible(true);
                NombreListaLbl.setVisible(true);


            }
        });
        //Acciones ventanas guía


    }

    public void cargarClientesEnLista() {
        DefaultListModel<Cliente> modeloCli = new DefaultListModel<>();
        for (Cliente e: clientes)
            modeloCli.addElement(e);
        listEmpleadosOClientes.setModel(modeloCli);
    }

    public void cargarEmpleadosEnLista() {
        DefaultListModel<Empleado> modeloEmp = new DefaultListModel<>();
        for (Empleado e: empleados)
            modeloEmp.addElement(e);
        listEmpleadosOClientes.setModel(modeloEmp);
    }

    public void cargarDatos() {
        cargarListas();
        actualizarListas();
        actualizarDatosEmpleado();
    }

    public void cargarListas() {
        if (bbdd == 4) { //BD4O
            empleados = new CargarDatosDB4O().cargarEmpleados();
            clientes = new CargarDatosDB4O().cargarClientes();
            visitas = new CargarDatosDB4O().cargarVisitas();
        } else { //SQL
            empleados = new CargarDatos().cargarEmpleados(bbdd);
            clientes = new CargarDatos().cargarClientes(bbdd);
            visitas = new CargarDatos().cargarVisitas(bbdd);
        }
    }

    public void actualizarListas() {
        DefaultListModel<Empleado> modeloEmp = new DefaultListModel<>();
        for (Empleado e: empleados)
            modeloEmp.addElement(e);
        listEmpleadosOClientes.setModel(modeloEmp);

        // TODO comprobar si es cliente o empleado y filtrar las listas
        DefaultListModel<Visita> modeloVisEmp = new DefaultListModel<>();
        for (Visita v: visitas)
            modeloVisEmp.addElement(v);
        listVisitasEmp.setModel(modeloVisEmp);

        // TODO filtrar la lista en base a las visitas disponibles
        DefaultListModel<Visita> modeloVisCli = new DefaultListModel<>();
        for (Visita v: visitas) {
            if (v.getFecha().isAfter(LocalDateTime.now()))
            modeloVisCli.addElement(v);
        }
        listVisitasCli.setModel(modeloVisCli);

        try {
            listEmpleadosOClientes.setSelectedIndex(0);
        } catch (NullPointerException ignored) {}
        try {
            listVisitasEmp.setSelectedIndex(0);
        } catch (NullPointerException ignored) {}
        try {
            listVisitasCli.setSelectedIndex(0);
        } catch (NullPointerException ignored) {}

    }

    public void actualizarDatosEmpleado() {
        Empleado e = (Empleado) listEmpleadosOClientes.getSelectedValue();
        DNI.setText(e.getDni());
        NOMBRE.setText(e.getNombre());
        APELLIDO.setText(e.getPrimerapellido());
        FECHNAC.setText(e.getFechaNac().toString());
        FECHACONTR.setText(e.getFechaContratacion().toString());
        CARGO.setText(e.getCargo());
        CONTRASEÑA.setText(e.getContrasenya());
    }

    public void setVentanaPrincipal(JFrame ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public void setBbdd(int bbdd) {
        this.bbdd = bbdd;
    }

    public JPanel getPanelSecundario() {return ventanaSecundaria;}


}

