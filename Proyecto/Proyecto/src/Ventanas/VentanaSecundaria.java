package Ventanas;

import com.company.DB4O.CargarDatosDB4O;
import com.company.DB4O.InsertarEditarDatosDB4O;
import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.RegistroEmpleado;
import com.company.Modelo.Visita;
import com.company.sql.CargarDatos;
import com.company.sql.EditarDatos;
import com.company.sql.InsertarDatos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private JPanel tabRRHH;
    private JPanel tabGUIA;
    private JPanel tabCLIENTE;
    private JPanel tabADMIN;
    private JPanel panelDatos;
    private JTextField DINERO;
    private JLabel DINEROlbl;

    private Empleado empleado;
    private Cliente cliente;

    private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Visita> visitas = new ArrayList<Visita>();

    private boolean verEmpleados = true;
    private double dineroRecGan = 0.0;

    private int bbdd = 1;
    private JFrame frameVentanaPrincipal;
    private JFrame frameVentanaSecundaria;

    public VentanaSecundaria() {
        cargarImagenes();
        mostrarLabelsYTextFieldsEmpleado();

        // Listeneres Tab RRHH
        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarLabelsYTextFieldsCliente();
                cargarClientesEnLista();
                verEmpleados = false;
                listEmpleadosOClientes.setSelectedIndex(0);
                NombreListaLbl.setText("Lista clientes");
            }
        });
        empleadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarLabelsYTextFieldsEmpleado();
                cargarEmpleadosEnLista();
                verEmpleados = true;
                listEmpleadosOClientes.setSelectedIndex(0);
                NombreListaLbl.setText("Lista empleados");
            }
        });
        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listEmpleadosOClientes.setEnabled(false);
                listEmpleadosOClientes.clearSelection();
                vaciarDatos();
                DNI.setEnabled(true);
                DNI.setEditable(true);
                //TODO cuando le demos a volver o guardemos volver a deshabilitar DNI

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
                boolean error = false;
                java.util.Date fechaNacUtil = null;
                java.util.Date fechaContrUtil = null;

                if (verEmpleados) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        fechaNacUtil = sdf.parse(FECHNAC.getText());
                        fechaContrUtil = sdf.parse(FECHACONTR.getText());
                    } catch (ParseException pe) {
                        error = true;
                    }
                }

                if (error) {
                    mostrarJOPtionPane("Error con las fechas", "Debes insertar la fecha con \n" +
                            "el formato: dd/mm/yyyy\n" +
                            "Ejemplo: 23/09/1995", 0);
                } else {
                    Empleado emp = null;
                    Cliente cli = null;
                    String registro = "";

                    if (verEmpleados) {
                        if (bbdd == 4) {
                            emp = new Empleado(DNI.getText(), NOMBRE.getText(), APELLIDO.getText(),
                                    fechaNacUtil, fechaContrUtil, NACIONALIDAD.getText(), CARGO.getText(),
                                    String.valueOf(CONTRASEÑA.getPassword()));
                        } else {
                            java.sql.Date fechaNacSQL = new java.sql.Date(fechaNacUtil.getTime());
                            java.sql.Date fechaContrSQL = new java.sql.Date(fechaContrUtil.getTime());;
                            emp = new Empleado(DNI.getText(), NOMBRE.getText(), APELLIDO.getText(),
                                    fechaNacSQL, fechaContrSQL, NACIONALIDAD.getText(), CARGO.getText(),
                                    String.valueOf(CONTRASEÑA.getPassword()));
                        }
                    } else {
                        cli = new Cliente(DNI.getText(), NOMBRE.getText(), APELLIDO.getText(), Integer.parseInt(EDAD.getText()),
                                PROFESION.getText(), String.valueOf(CONTRASEÑA.getPassword()));
                    }

                    if (listEmpleadosOClientes.isEnabled()) {
                        if (verEmpleados) {
                            registro = "Editado un empleado: " + emp.getDni();
                            if (bbdd == 4) {
                                new InsertarEditarDatosDB4O().insertarEditarEmpleado(emp);
                            } else {
                                new EditarDatos().editarEmpleado(bbdd, emp);
                            }
                            empleados.set(listEmpleadosOClientes.getSelectedIndex(), emp);
                        } else {
                            registro = "Editado un cliente: " + cli.getDni();
                            if (bbdd == 4) {
                                new InsertarEditarDatosDB4O().insertarEditarCliente(cli);
                            } else {
                                new EditarDatos().editarCliente(bbdd, cli);
                            }
                            clientes.set(listEmpleadosOClientes.getSelectedIndex(), cli);
                        }
                    } else {
                        if (verEmpleados) {
                            registro = "Creado un empleado: " + emp.getDni();
                            if (bbdd == 4) {
                                new InsertarEditarDatosDB4O().insertarEditarEmpleado(emp);
                            } else {
                                new InsertarDatos().insertarEmpleado(bbdd, emp);
                            }
                            empleados.set(listEmpleadosOClientes.getSelectedIndex(), emp);
                        } else {
                            registro = "Creado un cliente: " + cli.getDni();
                            if (bbdd == 4) {
                                new InsertarEditarDatosDB4O().insertarEditarCliente(cli);
                            } else {
                                new InsertarDatos().insertarCliente(bbdd, cli);
                            }
                            clientes.set(listEmpleadosOClientes.getSelectedIndex(), cli);
                        }
                    }
                    if (bbdd == 4) {
                        int cod = new CargarDatosDB4O().cargarRegistrosEmpleados().size() + 1;
                        RegistroEmpleado re = new RegistroEmpleado(cod, empleado, registro);
                        new InsertarEditarDatosDB4O().insertarEditarRegistroEmpleado(re);
                    } else {
                        new InsertarDatos().insertarRegistroEmpleado(bbdd, empleado.getDni(), registro);
                    }
                    actualizarListas();
                }
            }
        });
        listEmpleadosOClientes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (verEmpleados) {
                    actualizarDatosEmpleado();
                }
                else {
                    actualizarDatosCliente();
                }
            }
        });


        // Listeners Tab Guia




        // Listeners Tab Cliente




        // Listeners Tab Admin



    }

    public void mostrarLabelsYTextFieldsEmpleado() {
        EDAD.setVisible(false);
        EDADlbl.setVisible(false);

        PROFESION.setVisible(false);
        PROFESIONlbl.setVisible(false);

        CARGO.setVisible(true);
        CARGOlbl.setVisible(true);

        NACIONALIDAD.setVisible(true);
        NACIONALIDADlbl.setVisible(true);

        AELLIDOlbl.setText("Apellido");
        DINEROlbl.setText("Total dinero recaudado");

        FECHACONTR.setVisible(true);
        FECHCONTRlbl.setVisible(true);

        FECHNAC.setVisible(true);
        FECHNAClbl.setVisible(true);
    }

    public void mostrarLabelsYTextFieldsCliente() {
        EDAD.setVisible(true);
        EDADlbl.setVisible(true);

        PROFESION.setVisible(true);
        PROFESIONlbl.setVisible(true);

        CARGO.setVisible(false);
        CARGOlbl.setVisible(false);

        NACIONALIDAD.setVisible(false);
        NACIONALIDADlbl.setVisible(false);

        AELLIDOlbl.setText("Apellidos");
        DINEROlbl.setText("Total dinero gastado");

        FECHACONTR.setVisible(false);
        FECHCONTRlbl.setVisible(false);

        FECHNAC.setVisible(false);
        FECHNAClbl.setVisible(false);
    }

    public void cargarImagenes() {
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
        if (empleado != null) {
            if (empleado.getCargo().equalsIgnoreCase("RRHH")) { //RRHH
                cargarListasRRHH();
                tabbedPane1.removeTabAt(3);
                tabbedPane1.removeTabAt(2);
                tabbedPane1.removeTabAt(1);
            } else if (empleado.getCargo().equalsIgnoreCase("Guía")) { //GUIA
                cargarListasGuia();
                tabbedPane1.removeTabAt(3);
                tabbedPane1.removeTabAt(2);
                tabbedPane1.removeTabAt(0);
            } else { //ADMINISTRADOR
                cargarListasAdmin();
            }
        } else { // CLIENTE
            cargarListasCliente();
            tabbedPane1.removeTabAt(3);
            tabbedPane1.removeTabAt(1);
            tabbedPane1.removeTabAt(0);
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
            if (v.getFecha().isAfter(LocalDateTime.now()))
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

    public void vaciarDatos() {
        DNI.setText("");
        NOMBRE.setText("");
        APELLIDO.setText("");
        EDAD.setText("");
        PROFESION.setText("");

        DINERO.setVisible(false);
        DINEROlbl.setVisible(false);

        FECHNAC.setText("");
        FECHACONTR.setText("");
        NACIONALIDAD.setText("");
        CARGO.setText("");

        CONTRASEÑA.setText("");
    }

    public void actualizarDatosCliente() {
        Cliente c = (Cliente) listEmpleadosOClientes.getSelectedValue();
        if (c != null) {
            DNI.setText(c.getDni());
            NOMBRE.setText(c.getNombre());
            APELLIDO.setText(c.getApellidos());
            EDAD.setText(String.valueOf(c.getEdad()));
            PROFESION.setText(c.getProfesion());
            CONTRASEÑA.setText(c.getContrasenya());

            DINERO.setText(String.valueOf(dineroRecGan));

            DINERO.setVisible(true);
            DINEROlbl.setVisible(true);
        }
    }

    public void actualizarDatosEmpleado() {
        Empleado e = (Empleado) listEmpleadosOClientes.getSelectedValue();
        if (e != null) {
            String fechaNac;
            String fechaContr;

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            if (bbdd != 4) {
                fechaNac = df.format(e.getFechaNac());
                fechaContr = df.format(e.getFechaContratacion());
            } else {
                fechaNac = df.format(e.getFechaNacDB4O());
                fechaContr = df.format(e.getFechaContratacionDB4O());
            }

            DNI.setText(e.getDni());
            NOMBRE.setText(e.getNombre());
            APELLIDO.setText(e.getPrimerapellido());
            FECHNAC.setText(fechaNac);
            FECHACONTR.setText(fechaContr);

            NACIONALIDAD.setText(e.getNacionalidad());
            CARGO.setText(e.getCargo());
            CONTRASEÑA.setText(e.getContrasenya());

            DINERO.setText(String.valueOf(dineroRecGan));

            DINERO.setVisible(true);
            DINEROlbl.setVisible(true);
        }
    }


    public void calcularDineroRecaudadoGastado() {

    }

    public void cargarListasRRHH() {
        if (bbdd == 4) { //BD4O
            empleados = new CargarDatosDB4O().cargarEmpleados();
            clientes = new CargarDatosDB4O().cargarClientes();
        } else { //SQL
            empleados = new CargarDatos().cargarEmpleados(bbdd);
            clientes = new CargarDatos().cargarClientes(bbdd);
        }
    }

    public void cargarListasGuia() {
        if (bbdd == 4) { //BD4O
            visitas = new CargarDatosDB4O().cargarVisitas();
        } else { //SQL
            visitas = new CargarDatos().cargarVisitas(bbdd);
        }
    }

    public void cargarListasAdmin() {
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

    public void cargarListasCliente() {
        if (bbdd == 4) { //BD4O
            visitas = new CargarDatosDB4O().cargarVisitas();
        } else { //SQL
            visitas = new CargarDatos().cargarVisitas(bbdd);
        }
    }

    public void mostrarJOPtionPane(String titulo, String mensaje, int tipo) {
        JButton okButton = new JButton("Entendido");
        okButton.setFocusPainted(false);
        Object[] options = {okButton};
        final JOptionPane pane = new JOptionPane(mensaje, tipo, JOptionPane.YES_NO_OPTION, null, options);
        JDialog dialog = pane.createDialog(titulo);
        okButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFrameVentanaSecundaria(JFrame frameVentanaSecundaria) {
        this.frameVentanaSecundaria = frameVentanaSecundaria;
    }

    public void setFrameVentanaPrincipal(JFrame frameVentanaPrincipal) {
        this.frameVentanaPrincipal = frameVentanaPrincipal;
    }

    public void setBbdd(int bbdd) {
        this.bbdd = bbdd;
    }

    public JPanel getPanelSecundario() {return ventanaSecundaria;}


}

