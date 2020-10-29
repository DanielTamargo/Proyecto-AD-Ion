package Ventanas;

import com.company.DB4O.BorrarDatosDB4O;
import com.company.DB4O.CargarDatosDB4O;
import com.company.DB4O.InsertarEditarDatosDB4O;
import com.company.Modelo.*;
import com.company.sql.BorrarDatos;
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
    private JComboBox CARGO;

    private Empleado empleado;
    private Cliente cliente;

    private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Visita> visitas = new ArrayList<Visita>();

    private boolean verEmpleados = true;
    private double dineroRecGan = 0.0;

    private int selectedEmpleado = 0;
    private int selectedCliente = 0;

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
                terminarAnyadirEmpleadoCliente();
                mostrarLabelsYTextFieldsCliente();
                cargarClientesEnLista();
                verEmpleados = false;
                vaciarDatos();
                listEmpleadosOClientes.setSelectedIndex(0);
                NombreListaLbl.setText("Lista clientes");
            }
        });
        empleadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminarAnyadirEmpleadoCliente();
                mostrarLabelsYTextFieldsEmpleado();
                cargarEmpleadosEnLista();
                verEmpleados = true;
                vaciarDatos();
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
                eliminarButton.setText("Cancelar");
                eliminarButton.setIcon(new ImageIcon("assets/cancel.png"));
                editarButton.setText("Añadir");
                editarButton.setIcon(new ImageIcon("assets/save.png"));
                DNI.setEditable(true);
                //TODO cuando le demos a volver o guardemos volver a deshabilitar DNI

            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (eliminarButton.getText().equalsIgnoreCase("Cancelar")) {
                    terminarAnyadirEmpleadoCliente();
                } else {
                    eliminarPresionado();
                }
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarInsertarEmpleadoCliente();
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

    public void eliminarPresionado() {
        JButton noButton = new JButton("Mejor no");
        JButton eliminarButton = new JButton("Eliminar");
        noButton.setFocusPainted(false);
        Object[] options = {noButton, eliminarButton};
        final JOptionPane pane = new JOptionPane("¿Estás seguro?\n" +
                "Una vez eliminados los datos no podrán recuperarse.", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options);
        JDialog dialog = pane.createDialog("Eliminando datos");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarClienteEmpleado();
                dialog.dispose();
            }
        });
        dialog.setVisible(true);
    }

    public void borrarClienteEmpleado() {
        String registro = "";
        if (verEmpleados) {
            Empleado emp = (Empleado) listEmpleadosOClientes.getSelectedValue();
            if (bbdd == 4) {
                new BorrarDatosDB4O().borrarEmpleado(emp);
            } else {
                new BorrarDatos().borrarEmpleado(bbdd, emp.getDni());
            }
            selectedEmpleado = 0;
            empleados.remove(emp);
            registro = "Eliminado el empleado: " + emp.getDni();
        } else {
            Cliente cli = (Cliente) listEmpleadosOClientes.getSelectedValue();
            if (bbdd == 4) {
                new BorrarDatosDB4O().borrarCliente(cli);
            } else {
                new BorrarDatos().borrarCliente(bbdd, cli.getDni());
            }
            selectedCliente = 0;
            clientes.remove(cli);
            registro = "Eliminado el cliente: " + cli.getDni();
        }
        registroEmpleado(registro);

        if (verEmpleados)
            cargarEmpleadosEnLista();
        else
            cargarClientesEnLista();

        terminarAnyadirEmpleadoCliente();
    }

    public void terminarAnyadirEmpleadoCliente() {
        eliminarButton.setText("Eliminar");
        eliminarButton.setIcon(new ImageIcon("assets/delete.png"));
        editarButton.setText("Editar");
        editarButton.setIcon(new ImageIcon("assets/edit.png"));
        DNI.setEditable(false);
        listEmpleadosOClientes.setEnabled(true);
        if (verEmpleados)
            listEmpleadosOClientes.setSelectedIndex(selectedEmpleado);
        else
            listEmpleadosOClientes.setSelectedIndex(selectedCliente);
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
        editarVisitaButton.setIcon(new ImageIcon("Assets/edit.png"));
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


    public void editarInsertarEmpleadoCliente() {

        if (!comprobarDatosInsertados()) {
            mostrarJOPtionPane("Faltan datos", "Debes rellenar todos los datos necesarios", 0);
        } else {
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
                                fechaNacUtil, fechaContrUtil, NACIONALIDAD.getText(), (String) CARGO.getSelectedItem(),
                                String.valueOf(CONTRASEÑA.getPassword()));
                    } else {
                        java.sql.Date fechaNacSQL = new java.sql.Date(fechaNacUtil.getTime());
                        java.sql.Date fechaContrSQL = new java.sql.Date(fechaContrUtil.getTime());
                        ;
                        emp = new Empleado(DNI.getText(), NOMBRE.getText(), APELLIDO.getText(),
                                fechaNacSQL, fechaContrSQL, NACIONALIDAD.getText(), (String) CARGO.getSelectedItem(),
                                String.valueOf(CONTRASEÑA.getPassword()));
                    }
                } else {
                    cli = new Cliente(DNI.getText(), NOMBRE.getText(), APELLIDO.getText(), Integer.parseInt(EDAD.getText()),
                            PROFESION.getText(), String.valueOf(CONTRASEÑA.getPassword()));
                }

                if (listEmpleadosOClientes.isEnabled()) {
                    if (verEmpleados) {
                        registro = "Editado el empleado: " + emp.getDni();
                        if (bbdd == 4) {
                            new InsertarEditarDatosDB4O().insertarEditarEmpleado(emp);
                        } else {
                            new EditarDatos().editarEmpleado(bbdd, emp);
                        }
                        empleados.set(listEmpleadosOClientes.getSelectedIndex(), emp);
                        selectedEmpleado = listEmpleadosOClientes.getSelectedIndex();
                    } else {
                        registro = "Editado el cliente: " + cli.getDni();
                        if (bbdd == 4) {
                            new InsertarEditarDatosDB4O().insertarEditarCliente(cli);
                        } else {
                            new EditarDatos().editarCliente(bbdd, cli);
                        }
                        clientes.set(listEmpleadosOClientes.getSelectedIndex(), cli);
                        selectedCliente = listEmpleadosOClientes.getSelectedIndex();
                    }
                } else {
                    if (verEmpleados) {
                        registro = "Creado el empleado: " + emp.getDni();
                        if (bbdd == 4) {
                            new InsertarEditarDatosDB4O().insertarEditarEmpleado(emp);
                        } else {
                            new InsertarDatos().insertarEmpleado(bbdd, emp);
                        }
                        empleados.add(emp);
                        selectedEmpleado = empleados.size() - 1;
                    } else {
                        registro = "Creado el cliente: " + cli.getDni();
                        if (bbdd == 4) {
                            new InsertarEditarDatosDB4O().insertarEditarCliente(cli);
                        } else {
                            new InsertarDatos().insertarCliente(bbdd, cli);
                        }
                        clientes.add(cli);
                        selectedCliente = clientes.size() - 1;
                    }
                }
                registroEmpleado(registro);

                if (verEmpleados)
                    cargarEmpleadosEnLista();
                else
                    cargarClientesEnLista();
                terminarAnyadirEmpleadoCliente();
            }
        }
    }

    public boolean comprobarDatosInsertados() {
        boolean datosInsertados = true;

        ArrayList<JTextField> textos = new ArrayList<>();
        textos.add(DNI); textos.add(NOMBRE); textos.add(APELLIDO);

        if (verEmpleados) {
            textos.add(FECHNAC); textos.add(FECHACONTR); textos.add(NACIONALIDAD);
        } else {
            textos.add(EDAD); textos.add(PROFESION);
        }

        for (JTextField texto: textos){
            if (texto.getText().equalsIgnoreCase(""))
                datosInsertados = false;
        }

        if (datosInsertados)
            if (String.valueOf(CONTRASEÑA.getPassword()).equalsIgnoreCase(""))
                datosInsertados = false;

        return datosInsertados;
    }

    public void registroEmpleado(String registro) {
        if (bbdd == 4) {
            int cod = new CargarDatosDB4O().cargarRegistrosEmpleados().size() + 1;
            RegistroEmpleado re = new RegistroEmpleado(cod, empleado, registro);
            new InsertarEditarDatosDB4O().insertarEditarRegistroEmpleado(re);
        } else {
            new InsertarDatos().insertarRegistroEmpleado(bbdd, empleado.getDni(), registro);
        }
    }

    public void actualizarListas() {

        actualizarListaEmpleados();
        actualizarListaVisitasEmpleados();
        actualizarListaVisitasClientes();

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

    public void actualizarListaVisitasClientes() {
        DefaultListModel<Visita> modeloVisCli = new DefaultListModel<>();
        for (Visita v: visitas) {
            if (v.getFecha().isAfter(LocalDateTime.now()))
                modeloVisCli.addElement(v);
        }
        listVisitasCli.setModel(modeloVisCli);
    }

    public void actualizarListaVisitasEmpleados() {
        DefaultListModel<Visita> modeloVisEmp = new DefaultListModel<>();
        for (Visita v: visitas)
            if (v.getFecha().isAfter(LocalDateTime.now()))
                modeloVisEmp.addElement(v);
        listVisitasEmp.setModel(modeloVisEmp);

    }

    public void actualizarListaEmpleados() {
        DefaultListModel<Empleado> modeloEmp = new DefaultListModel<>();
        for (Empleado e: empleados)
            modeloEmp.addElement(e);
        listEmpleadosOClientes.setModel(modeloEmp);
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
        CARGO.setSelectedIndex(0);

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

            calcularDineroRecaudadoGastado();
            if (dineroRecGan <= 0)
                DINERO.setText("Nada");
            else
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

            if (e.getCargo().equalsIgnoreCase("RRHH"))
                CARGO.setSelectedIndex(0);
            else if (e.getCargo().equalsIgnoreCase("Guía"))
                CARGO.setSelectedIndex(1);
            else
                CARGO.setSelectedIndex(2);

            CONTRASEÑA.setText(e.getContrasenya());
            calcularDineroRecaudadoGastado();
            if (dineroRecGan <= 0)
                DINERO.setText("Nada");
            else
                DINERO.setText(String.valueOf(dineroRecGan));

            DINERO.setVisible(true);
            DINEROlbl.setVisible(true);
        }
    }


    public void calcularDineroRecaudadoGastado() {
        dineroRecGan = 0;
        ArrayList<Visita> visitasEmpCli = new ArrayList<>();
        if (verEmpleados) {
            Empleado emp = (Empleado) listEmpleadosOClientes.getSelectedValue();
            if (bbdd == 4) {
                visitasEmpCli = new CargarDatosDB4O().cargarVisitasEmpleado(emp);
            } else {
                visitasEmpCli = new CargarDatos().cargarVisitasEmpleado(bbdd, emp.getDni());
            }
            for (Visita v: visitasEmpCli) {
                int numClientes = 0;
                if (bbdd == 4)
                    numClientes = 0; //TODO FALTA METODO DB4O
                else
                    numClientes = new CargarDatos().numClientesApuntados(bbdd, v.getCod());
                dineroRecGan += (v.getCoste() * numClientes);
            }
        } else {
            Cliente cli = (Cliente) listEmpleadosOClientes.getSelectedValue();
            if (bbdd == 4) {
                ArrayList<VisitaCliente> visitaCliente = new CargarDatosDB4O().cargarVisitasClientes();
                for (VisitaCliente vc: visitaCliente) {
                    if (vc.getCliente().getDni().equalsIgnoreCase(cli.getDni()))
                        visitasEmpCli.add(vc.getVisita());
                }
            } else {
                visitasEmpCli = new CargarDatos().cargarVisitasCliente(bbdd, cli.getDni());
                // TODO mirar por que no carga mas de una visita
            }
            for (Visita v: visitasEmpCli) {
                dineroRecGan += v.getCoste();
            }
        }

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

