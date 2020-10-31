package Ventanas;

import com.company.DB4O.BorrarDatosDB4O;
import com.company.DB4O.CargarDatosDB4O;
import com.company.DB4O.InsertarDatosBaseDB4O;
import com.company.DB4O.InsertarEditarDatosDB4O;
import com.company.HiloCloud;
import com.company.Modelo.*;
import com.company.sql.BorrarDatos;
import com.company.sql.CargarDatos;
import com.company.sql.EditarDatos;
import com.company.sql.InsertarDatos;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class VentanaSecundaria {

    // GRAFICO
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
    private JButton b_guiaVisNueva;
    private JButton b_guiaVisEditar;
    private JButton b_guiaVisEliminar;
    private JList listVisitasEmp;
    private JTextField t_guiaVisNOMBRE;
    private JTextField t_guiaVisPUNTOPART;
    private JTextField t_guiaVisFECHA;
    private JTextField t_guiaVisDUREST;
    private JTextField t_guiaVisTEMATICA;
    private JTextField t_guiaVisCOSTE;
    private JList listVisitasCli;
    private JTextField t_cliVisGUIA;
    private JTextField t_cliVisPLAZAS;
    private JTextField t_cliVisPUNTOPART;
    private JTextField t_cliVisFECHA;
    private JTextField t_cliVisDUREST;
    private JTextField t_cliVisTEMATICA;
    private JTextField t_cliVisCOSTE;
    private JButton b_cliReservar;
    private JSpinner spinnerMAXCLIENTES;
    private JButton b_cliMisVisitas;
    private JButton b_guiaVisMisVisitas;
    private JPanel tabRRHH;
    private JPanel tabGUIA;
    private JPanel tabCLIENTE;
    private JPanel tabADMIN;
    private JPanel panelDatos;
    private JTextField DINERO;
    private JLabel DINEROlbl;
    private JComboBox CARGO;
    private JTextField t_guiaVisANYO;
    private JTextField t_guiaVisGUIA;
    private JTextField t_cliVisNOMBRE;
    private JPanel panelAdmin;

    // GENERALES
    private int bbdd = 1;
    private JFrame frameVentanaPrincipal;
    private JFrame frameVentanaSecundaria;
    private Empleado empleado;
    private Cliente cliente;

    private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Visita> visitas = new ArrayList<Visita>();

    private int selectedEmpleado = 0;
    private int selectedCliente = 0;
    private int selectedGuiaVisita = 0;

    // RRHH
    private boolean verEmpleados = true;
    private double dineroRecGan = 0.0;

    // GUIA
    private boolean empleadoTodasVisitas = true;
    private int todasVisitasSize = 0;
    private int misVisitasSize = 0;

    // CLIENTE
    private boolean clienteTodasVisitas = true;

    // ADMIN
    private JLabel l_fondoCloud;
    private JLabel l_gananciasAnualesText;
    private JLabel l_gananciasAnualesCifra;
    private JButton b_adminMetadatos;
    private JButton b_adminRegistros;

    public VentanaSecundaria() {
        cargarImagenes();
        mostrarLabelsYTextFieldsEmpleado();

        // LISTENERS TAB RRHH
        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminarAnyadirEmpleadoCliente();
                mostrarLabelsYTextFieldsCliente();
                cargarClientesEnLista();
                verEmpleados = false;
                vaciarDatosListEmpCli();
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
                vaciarDatosListEmpCli();
                listEmpleadosOClientes.setSelectedIndex(0);
                NombreListaLbl.setText("Lista empleados");
            }
        });
        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listEmpleadosOClientes.setEnabled(false);
                listEmpleadosOClientes.clearSelection();
                vaciarDatosListEmpCli();
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
                    eliminarPresionado(1);
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


        // LISTENERS TAB GUIA
        b_guiaVisEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO si es eliminar: eliminar | si es cancelar: volver a todas las visitas
                //TODO desbloquear la lista
                if (b_guiaVisEliminar.getText().equalsIgnoreCase("Cancelar")) {
                    terminarAnyadirGuiaVisita();
                } else {
                    Visita visita = null;
                    try {
                        visita = (Visita) listVisitasEmp.getSelectedValue();
                    } catch (NullPointerException ignored) { }
                    if (visita != null)
                        eliminarPresionado(2);
                }
            }
        });
        b_guiaVisEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarInsertarGuiaVisita();
            }
        });
        b_guiaVisMisVisitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (empleadoTodasVisitas) {
                    t_guiaVisGUIA.setText(empleado.getNombre() + " " + empleado.getPrimerapellido());
                    empleadoTodasVisitas = false;
                    b_guiaVisMisVisitas.setText("Todas visitas");
                    b_guiaVisMisVisitas.setIcon(new ImageIcon("assets/mis.png")); //TODO nuevo icono (?)
                } else {
                    empleadoTodasVisitas = true;
                    b_guiaVisMisVisitas.setText("Mis visitas");
                    b_guiaVisMisVisitas.setIcon(new ImageIcon("assets/mis.png"));
                }
                actualizarListaGuia();
                vaciarDatosGuiaVisita();
                try {
                    listVisitasEmp.setSelectedIndex(0);
                    selectedGuiaVisita = 0;
                } catch (NullPointerException ignored) { }

                terminarAnyadirGuiaVisita();
                //listVisitasEmp.setEnabled(true);
            }
        });
        b_guiaVisNueva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarDatosGuiaVisita();
                t_guiaVisGUIA.setText(empleado.getNombre() + " " + empleado.getPrimerapellido());
                b_guiaVisEliminar.setText("Cancelar");
                b_guiaVisEliminar.setIcon(new ImageIcon("assets/cancel.png"));
                b_guiaVisEditar.setText("Añadir");
                b_guiaVisEditar.setIcon(new ImageIcon("assets/save.png"));
                listVisitasEmp.setEnabled(false);

                b_guiaVisEditar.setEnabled(true);
                b_guiaVisEliminar.setEnabled(true);
            }
        });
        listVisitasEmp.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedGuiaVisita = listVisitasEmp.getSelectedIndex();
                actualizarDatosGuiaVisita();
            }
        });
        spinnerMAXCLIENTES.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if ((int) spinnerMAXCLIENTES.getValue() < 5)
                    spinnerMAXCLIENTES.setValue(5);
            }
        });

        // LISTENERS TAB CLIENTE
        b_cliMisVisitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clienteTodasVisitas) {
                    clienteTodasVisitas = false;
                    b_cliMisVisitas.setText("Todas visitas");
                    b_cliReservar.setText("Cancelar reserva");
                } else {
                    clienteTodasVisitas = true;
                    b_cliMisVisitas.setText("Mis visitas");
                    b_cliReservar.setText("Realizar reserva");
                }

                actualizarListaCliente();
                vaciarDatosClienteVisita();

                try {
                    listVisitasCli.setSelectedIndex(0);
                } catch (NullPointerException ignored) { }

            }
        });
        b_cliReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cliente == null) {
                    mostrarJOPtionPane("Imposible reservar" , "¡Un administrador no puede apuntarse" +
                            " a una visita!", 0);
                } else {
                    if (b_cliReservar.getText().equalsIgnoreCase("Cancelar reserva")) {
                        eliminarPresionado(3);
                    } else {
                        insertarCliVisita();
                    }
                }
            }
        });
        listVisitasCli.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                actualizarDatosClienteVisita();
            }
        });


        // TAB ADMIN
        panelAdmin.setLayout(null);
        Dimension dim = new Dimension();

        l_gananciasAnualesText = new JLabel("Ganancias anuales totales:");
        dim.setSize(250, 50);
        l_gananciasAnualesText.setFont(new Font("Microsoft Yahei UI", Font.PLAIN, 16));
        l_gananciasAnualesText.setHorizontalAlignment(JLabel.RIGHT);
        l_gananciasAnualesText.setPreferredSize(dim);
        l_gananciasAnualesText.setMinimumSize(dim);
        l_gananciasAnualesText.setMaximumSize(dim);
        l_gananciasAnualesText.setBounds(450, 50, dim.width, dim.height);
        panelAdmin.add(l_gananciasAnualesText);

        l_gananciasAnualesCifra = new JLabel("470.5"); //TODO CALCULAR GANANCIAS ANUALES REALES
        dim.setSize(250, 50);
        l_gananciasAnualesCifra.setFont(new Font("Microsoft Yahei UI", Font.BOLD, 24));
        l_gananciasAnualesCifra.setHorizontalAlignment(JLabel.RIGHT);
        l_gananciasAnualesCifra.setPreferredSize(dim);
        l_gananciasAnualesCifra.setMinimumSize(dim);
        l_gananciasAnualesCifra.setMaximumSize(dim);
        l_gananciasAnualesCifra.setBounds(450, 80, dim.width, dim.height);
        panelAdmin.add(l_gananciasAnualesCifra);

        JLabel l_opcionesDev = new JLabel("Opciones especiales desarrollador");
        dim.setSize(250, 50);
        l_opcionesDev.setFont(new Font("Microsoft Yahei UI", Font.PLAIN, 14));
        l_opcionesDev.setHorizontalAlignment(JLabel.RIGHT);
        l_opcionesDev.setPreferredSize(dim);
        l_opcionesDev.setMinimumSize(dim);
        l_opcionesDev.setMaximumSize(dim);
        l_opcionesDev.setBounds(450, 280, dim.width, dim.height);
        panelAdmin.add(l_opcionesDev);

        b_adminRegistros = new JButton("Registros");
        dim.setSize(150, 50);
        b_adminRegistros.setFont(new Font("Microsoft Yahei UI", Font.BOLD, 14));
        b_adminRegistros.setPreferredSize(dim);
        b_adminRegistros.setMaximumSize(dim);
        b_adminRegistros.setMinimumSize(dim);
        b_adminRegistros.setBounds(550, 340, dim.width, dim.height);
        b_adminRegistros.setFocusPainted(false);
        panelAdmin.add(b_adminRegistros);

        b_adminMetadatos = new JButton("Metadatos");
        dim.setSize(150, 50);
        b_adminMetadatos.setFont(new Font("Microsoft Yahei UI", Font.BOLD, 14));
        b_adminMetadatos.setPreferredSize(dim);
        b_adminMetadatos.setMaximumSize(dim);
        b_adminMetadatos.setMinimumSize(dim);
        b_adminMetadatos.setFocusPainted(false);
        b_adminMetadatos.setBounds(550, 410, dim.width, dim.height);
        panelAdmin.add(b_adminMetadatos);

        l_fondoCloud = new JLabel("");
        l_fondoCloud.setIcon(new ImageIcon("Assets/cloud1.png"));
        panelAdmin.add(l_fondoCloud);
        dim.setSize(300, 450);
        l_fondoCloud.setPreferredSize(dim);
        l_fondoCloud.setMinimumSize(dim);
        l_fondoCloud.setMaximumSize(dim);
        l_fondoCloud.setBounds(30, 55, dim.width, dim.height);

        HiloCloud hiloCloud = new HiloCloud(l_fondoCloud);
        hiloCloud.start();



    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MÉTODOS INICIALES
    public void cargarDatos() {
        cargarListas();
        actualizarListas();
        actualizarDatosEmpleado();
        actualizarListaCliente();
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
        actualizarListaEmpleados();
        actualizarListaGuiaTodasVisitas();
        actualizarListaVisitasClientes();

        try {
            listEmpleadosOClientes.setSelectedIndex(0);
        } catch (Exception ignored) {}
        try {
            listVisitasEmp.setSelectedIndex(0);
        } catch (Exception ignored) {}
        try {
            listVisitasCli.setSelectedIndex(0);
        } catch (Exception ignored) {}
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
        visitas.sort(Comparator.comparing(Visita::getFecha));
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
        visitas.sort(Comparator.comparing(Visita::getFecha));
    }

    public void cargarListasCliente() {
        if (bbdd == 4) { //BD4O
            visitas = new CargarDatosDB4O().cargarVisitas();
        } else { //SQL
            visitas = new CargarDatos().cargarVisitas(bbdd);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MÉTODOS COMUNES
    public void mostrarJOPtionPane(String titulo, String mensaje, int tipo) {
        JButton okButton = new JButton("Entendido");
        okButton.setFocusPainted(false);
        Object[] options = {okButton};
        final JOptionPane pane = new JOptionPane(mensaje, tipo, JOptionPane.YES_NO_OPTION, null, options);
        JDialog dialog = pane.createDialog(titulo);
        okButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    public void eliminarPresionado(int tipo) {
        JButton noButton = new JButton("Mejor no");
        JButton eliminarButton = new JButton("Eliminar");
        noButton.setFocusPainted(false);
        Object[] options = {noButton, eliminarButton};

        String titulo = "Eliminando datos";
        String mensaje = "Una vez eliminados los datos no podrán recuperarse.";
        if (tipo == 3) {
            mensaje = "Si cancelas la reserva podrás volver a reservar mientras\nsiga habiendo plazas libres";
            titulo = "Cancelando reserva";
        }

        final JOptionPane pane = new JOptionPane("¿Estás seguro?\n" +
               mensaje, JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options);
        JDialog dialog = pane.createDialog(titulo);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tipo == 1)
                    borrarClienteEmpleado();
                else if (tipo == 2)
                    borrarGuiaVisita();
                else if (tipo == 3)
                    borrarCliVisita();
                dialog.dispose();
            }
        });
        dialog.setVisible(true);
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

    public void registroCliente(String registro) {
        if (bbdd == 4) {
            int cod = new CargarDatosDB4O().cargarRegistrosClientes().size() + 1;
            RegistroCliente re = new RegistroCliente(cod, cliente, registro);
            new InsertarEditarDatosDB4O().insertarEditarRegistroCliente(re);
        } else {
            new InsertarDatos().insertarRegistroCliente(bbdd, cliente.getDni(), registro);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MÉTODOS TAB CLIENTE

    public void insertarCliVisita() {
        Visita vis = null;
        try {
            vis = (Visita) listVisitasCli.getSelectedValue();
        } catch (NullPointerException ignored) { }
        if (vis != null) {
            if (bbdd == 4) {
                VisitaCliente visitaCliente = new VisitaCliente(cliente, vis);
                new InsertarEditarDatosDB4O().insertarEditarVisitaCliente(visitaCliente);
            } else {
                new InsertarDatos().insertarVisitaCliente(bbdd, vis.getCod(), cliente.getDni());
            }

            registroCliente("Realizado reserva a visita " + vis.getCod());
            actualizarListaCliente();

        } else {
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0;
            String mensajeJOptionPane = "Error al realizar la reserva.";
            mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }

    public void borrarCliVisita() {
        Visita vis = null;
        try {
            vis = (Visita) listVisitasCli.getSelectedValue();
        } catch (NullPointerException ignored) { }
        if (vis != null) {

            if (bbdd == 4) {
                new BorrarDatosDB4O().borrarVisitaCliente(cliente, vis);
            } else {
                new BorrarDatos().borrarVisitaCliente(bbdd, cliente.getDni(), vis.getCod());
            }

            registroCliente("Cancelado reserva a visita " + vis.getCod());
            actualizarListaCliente();

        } else {
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0;
            String mensajeJOptionPane = "Error al cancelar la reserva.";
            mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }

    public void vaciarDatosClienteVisita() {
        // TODO vaciar cuando se apunte a una visita (por si se ha apuntado a todas las disponibles
        t_cliVisGUIA.setText("");
        t_cliVisNOMBRE.setText("");
        t_cliVisPLAZAS.setText("");
        t_cliVisPUNTOPART.setText("");
        t_cliVisFECHA.setText("");
        t_cliVisDUREST.setText("");
        t_cliVisTEMATICA.setText("");
        t_cliVisCOSTE.setText("");
    }

    public void actualizarDatosClienteVisita() {
        Visita visita = null;
        try {
            visita = (Visita) listVisitasCli.getSelectedValue();
        } catch (Exception ignored) { }
        if (visita != null) {
            b_cliReservar.setEnabled(true);

            try {
                t_cliVisGUIA.setText(visita.getGuia().getNombre() + " " + visita.getGuia().getPrimerapellido());
            } catch (NullPointerException ignored) {
                t_cliVisGUIA.setText("");
            }

            t_cliVisNOMBRE.setText(visita.getNombre());

            int totalPlazas = visita.getNumMaxClientes();
            int plazasOcupadas = 0;
            if (bbdd == 4) {
                ArrayList<VisitaCliente> visitasClientes = new CargarDatosDB4O().cargarVisitasClientes();
                for (VisitaCliente visitaCliente : visitasClientes) {
                    if (visitaCliente.getVisita().getCod() == visita.getCod())
                        plazasOcupadas++;
                }
            } else {
                plazasOcupadas = new CargarDatos().numClientesApuntadosAVisita(bbdd, visita.getCod());
            }
            int plazasLibres = totalPlazas - plazasOcupadas;
            t_cliVisPLAZAS.setText(String.valueOf(plazasLibres));
            
            if (clienteTodasVisitas && plazasLibres <= 0) {
                b_cliReservar.setEnabled(false);
            } else if (!clienteTodasVisitas && visita.getFecha().isBefore(LocalDateTime.now())) {
                b_cliReservar.setEnabled(false);
            }

            t_cliVisPUNTOPART.setText(visita.getPuntoPartida());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String fechaFormateada = visita.getFecha().format(formatter);
            t_cliVisFECHA.setText(fechaFormateada);

            t_cliVisDUREST.setText(String.valueOf(visita.getDuracionEstimada()));
            t_cliVisTEMATICA.setText(visita.getTematica());
            t_cliVisCOSTE.setText(String.valueOf(visita.getCoste()));

        } else {
            b_cliReservar.setEnabled(false);
            vaciarDatosClienteVisita();
        }
    }

    public void actualizarListaClienteTodasVisitas() {
        DefaultListModel<Visita> modeloVisCli = new DefaultListModel<>();
        try {
            if (bbdd == 4) {
                ArrayList<VisitaCliente> visitasClientes = new CargarDatosDB4O().cargarVisitasCliente(cliente);
                boolean reservada;
                for (Visita vis : visitas) {
                    reservada = false;
                    for (VisitaCliente visCli : visitasClientes) {
                        if (vis.getCod() == visCli.getVisita().getCod()) {
                            reservada = true;
                            break;
                        }
                    }
                    if (!reservada && vis.getFecha().isAfter(LocalDateTime.now())) {
                        modeloVisCli.addElement(vis);
                    }
                }
            } else {
                ArrayList<Visita> visitasCliente = new CargarDatos().cargarVisitasCliente(bbdd, cliente.getDni());
                boolean reservada;
                for (Visita vis : visitas) {
                    reservada = false;
                    for (Visita visCli : visitasCliente) {
                        if (vis.getCod() == visCli.getCod()) {
                            reservada = true;
                            break;
                        }
                    }
                    if (!reservada && vis.getFecha().isAfter(LocalDateTime.now())) {
                        modeloVisCli.addElement(vis);
                    }
                }
            }
        } catch (NullPointerException ex) {
            for (Visita vis : visitas) {
                modeloVisCli.addElement(vis);
            }
        }
        listVisitasCli.setModel(modeloVisCli);
    }

    public void actualizarListaClienteMisVisitas() {
        DefaultListModel<Visita> modeloVisCli = new DefaultListModel<>();
        ArrayList<Visita> visitasCliente = new ArrayList<>();
        try {
            if (bbdd == 4) {
                ArrayList<VisitaCliente> visitasClientes = new CargarDatosDB4O().cargarVisitasCliente(cliente);
                for (Visita vis : visitas) {
                    for (VisitaCliente visCli : visitasClientes) {
                        if (vis.getCod() == visCli.getVisita().getCod()) {
                            visitasCliente.add(vis);
                            break;
                        }
                    }
                }

            } else {
                visitasCliente = new CargarDatos().cargarVisitasCliente(bbdd, cliente.getDni());
            }

            for (Visita v : visitasCliente) {
                modeloVisCli.addElement(v);
            }
        } catch (NullPointerException ex) {

        }
        listVisitasCli.setModel(modeloVisCli);
    }

    public void actualizarListaCliente() {
        if (clienteTodasVisitas)
            actualizarListaClienteTodasVisitas();
        else
            actualizarListaClienteMisVisitas();
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MÉTODOS TAB GUIA
    
    public void actualizarDatosGuiaVisita() {
        Visita visita = null;
        try {
            visita = (Visita) listVisitasEmp.getSelectedValue();
        } catch (NullPointerException ignored) { }

        if (visita != null) {
            try {
                t_guiaVisGUIA.setText(visita.getGuia().getNombre() + " " + visita.getGuia().getPrimerapellido());
            } catch (NullPointerException ignored) {
                t_guiaVisGUIA.setText("");
            }
            t_guiaVisNOMBRE.setText(visita.getNombre());
            spinnerMAXCLIENTES.setValue(visita.getNumMaxClientes());
            t_guiaVisPUNTOPART.setText(visita.getPuntoPartida());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String fechaFormateada = visita.getFecha().format(formatter);
            t_guiaVisFECHA.setText(fechaFormateada);
            try {
                t_guiaVisANYO.setText(String.valueOf(visita.getFecha().getYear()));
            } catch (Exception ignored) {
                t_guiaVisANYO.setText(String.valueOf(visita.getAnyo()));
            }
            t_guiaVisDUREST.setText(String.valueOf(visita.getDuracionEstimada()));
            t_guiaVisTEMATICA.setText(visita.getTematica());
            t_guiaVisCOSTE.setText(String.valueOf(visita.getCoste()));

            if (visita.getGuia().getDni().equalsIgnoreCase(empleado.getDni())
                    || empleado.getCargo().equalsIgnoreCase("Administrador")) {
                b_guiaVisEditar.setEnabled(true);
                b_guiaVisEliminar.setEnabled(true);
            } else {
                b_guiaVisEditar.setEnabled(false);
                b_guiaVisEliminar.setEnabled(false);
            }
        }
    }

    public void actualizarListaGuiaTodasVisitas() {
        todasVisitasSize = 0;
        DefaultListModel<Visita> modeloVisEmp = new DefaultListModel<>();
        for (Visita v: visitas) {
            if (v.getFecha().isAfter(LocalDateTime.now())) {
                modeloVisEmp.addElement(v);
                todasVisitasSize++;
            }
        }
        listVisitasEmp.setModel(modeloVisEmp);
    }

    public void actualizarListaGuiaMisVisitas() {
        misVisitasSize = 0;
        DefaultListModel<Visita> modeloVisEmp = new DefaultListModel<>();
        for (Visita v: visitas) {
            try {
                if (v.getGuia().getDni().equalsIgnoreCase(empleado.getDni())) {
                    modeloVisEmp.addElement(v);
                    misVisitasSize++;
                }
            } catch (Exception ignored) {
            }
        }
        listVisitasEmp.setModel(modeloVisEmp);
    }

    public void actualizarListaGuia() {
        if (empleadoTodasVisitas)
            actualizarListaGuiaTodasVisitas();
        else
            actualizarListaGuiaMisVisitas();
    }

    public void vaciarDatosGuiaVisita() {
        t_guiaVisNOMBRE.setText("");
        spinnerMAXCLIENTES.setValue(0);
        t_guiaVisPUNTOPART.setText("");
        t_guiaVisFECHA.setText("");
        t_guiaVisANYO.setText("");
        t_guiaVisDUREST.setText("");
        t_guiaVisTEMATICA.setText("");
        t_guiaVisCOSTE.setText("");
    }

    public void terminarAnyadirGuiaVisita() {
        //t_guiaVisGUIA.setText("");
        b_guiaVisEliminar.setText("Eliminar");
        b_guiaVisEliminar.setIcon(new ImageIcon("assets/delete.png"));
        b_guiaVisEditar.setText("Editar");
        b_guiaVisEditar.setIcon(new ImageIcon("assets/edit.png"));
        listVisitasEmp.setEnabled(true);
        //listVisitasEmp.clearSelection();
        if (selectedGuiaVisita < 0)
            selectedGuiaVisita = 0;
        else if (selectedGuiaVisita >= listVisitasEmp.getLastVisibleIndex())
            selectedGuiaVisita = listVisitasEmp.getLastVisibleIndex();
        listVisitasEmp.setSelectedIndex(selectedGuiaVisita);
        actualizarDatosGuiaVisita();
    }

    public void borrarGuiaVisita() {
        Visita visita = (Visita) listVisitasEmp.getSelectedValue();
        if (bbdd == 4) {
            new BorrarDatosDB4O().borrarVisita(visita);
        } else {
            new BorrarDatos().borrarVisita(bbdd, visita.getCod());
        }
        Visita visitaEliminar = null;
        for (Visita v: visitas) {
            if (v.getCod() == visita.getCod())
                visitaEliminar = v;
        }
        if (visitaEliminar != null)
            visitas.remove(visitaEliminar);

        String registro = "Eliminado la visita " + visita.getCod();
        registroEmpleado(registro);
        actualizarListaGuia();
        selectedGuiaVisita = 0;
        terminarAnyadirGuiaVisita();
    }

    public void editarInsertarGuiaVisita() {
        if (!comprobarDatosGuiaVisitaInsertados()) {
            mostrarJOPtionPane("Faltan datos", "Debes rellenar todos los datos necesarios", 0);
        } else {
            boolean error = false;
            LocalDateTime fecha = null;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            try {
                fecha = LocalDateTime.parse(t_guiaVisFECHA.getText(), formatter);
            } catch (Exception pe) {
                error = true;
            }

            if (error) {
                mostrarJOPtionPane("Error con la fecha", "Debes insertar la fecha con \n" +
                        "el formato: dd/mm/yyyy hh:mm\n" +
                        "Ejemplo: 09/11/2020 17:30", 0);
            } else {
                boolean error2 = false;
                try {
                    float coste = Float.parseFloat(t_guiaVisCOSTE.getText());
                    float duracion = Float.parseFloat(t_guiaVisDUREST.getText());
                } catch (NumberFormatException ignored) {
                    error2 = true;
                }

                if (error2) {
                    mostrarJOPtionPane("Error con duración o coste", "Debes insertar la duración estimada \n" +
                            "y el coste con el formato: número.decimal\n" +
                            "Ejemplo: 7.5", 0);
                } else {
                    Visita visita = null;
                    if (bbdd == 4) {
                        visita = new Visita(visitas.size() + 1, empleado, t_guiaVisNOMBRE.getText(), (int) spinnerMAXCLIENTES.getValue(),
                                t_guiaVisPUNTOPART.getText(), fecha, fecha.getYear(), Float.parseFloat(t_guiaVisDUREST.getText()),
                                t_guiaVisTEMATICA.getText(), Float.parseFloat(t_guiaVisCOSTE.getText()));
                        new InsertarEditarDatosDB4O().insertarEditarVisita(visita);
                    } else {
                        if (listVisitasEmp.isEnabled()) {
                            Visita visitaElegida = (Visita) listVisitasEmp.getSelectedValue();
                            visita = new Visita(visitaElegida.getCod(), empleado, t_guiaVisNOMBRE.getText(), (int) spinnerMAXCLIENTES.getValue(),
                                    t_guiaVisPUNTOPART.getText(), fecha, fecha.getYear(), Float.parseFloat(t_guiaVisDUREST.getText()),
                                    t_guiaVisTEMATICA.getText(), Float.parseFloat(t_guiaVisCOSTE.getText()));
                            new EditarDatos().editarVisitas(bbdd, visitaElegida);
                        } else {
                            visita = new Visita(empleado, t_guiaVisNOMBRE.getText(), (int) spinnerMAXCLIENTES.getValue(),
                                    t_guiaVisPUNTOPART.getText(), fecha, fecha.getYear(), Float.parseFloat(t_guiaVisDUREST.getText()),
                                    t_guiaVisTEMATICA.getText(), Float.parseFloat(t_guiaVisCOSTE.getText()));
                            new InsertarDatos().insertarVisita(bbdd, visita);
                        }
                    }

                    String registro;
                    if (listVisitasEmp.isEnabled()) {
                        selectedGuiaVisita = listVisitasEmp.getSelectedIndex();
                        registro = "Editada la visita " + visita.getCod();
                    } else {
                        if (empleadoTodasVisitas)
                            selectedGuiaVisita = todasVisitasSize;
                        else
                            selectedGuiaVisita = misVisitasSize;
                        registro = "Creada nueva visita";
                    }

                    registroEmpleado(registro);
                    cargarListasGuia();

                    if (empleadoTodasVisitas)
                        actualizarListaGuiaTodasVisitas();
                    else
                        actualizarListaGuiaMisVisitas();

                    terminarAnyadirGuiaVisita();
                }
            }
        }
    }

    public boolean comprobarDatosGuiaVisitaInsertados() {
        boolean datosInsertados = true;

        ArrayList<JTextField> textos = new ArrayList<>();
        textos.add(t_guiaVisNOMBRE); textos.add(t_guiaVisPUNTOPART);
        textos.add(t_guiaVisFECHA); textos.add(t_guiaVisDUREST);
        textos.add(t_guiaVisTEMATICA); textos.add(t_guiaVisCOSTE);

        for (JTextField texto: textos){
            if (texto.getText().equalsIgnoreCase(""))
                datosInsertados = false;
        }

        return datosInsertados;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MÉTODOS TAB ADMIN






    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MÉTODOS TAB RRHH

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
        DINEROlbl.setText("Dinero gastado " + LocalDateTime.now().getYear());

        FECHACONTR.setVisible(false);
        FECHCONTRlbl.setVisible(false);

        FECHNAC.setVisible(false);
        FECHNAClbl.setVisible(false);
    }

    public void cargarImagenes() {
        añadirButton.setIcon(new ImageIcon("Assets/añadir.png"));
        editarButton.setIcon(new ImageIcon("Assets/save.png"));
        b_guiaVisEditar.setIcon(new ImageIcon("Assets/edit.png"));
        eliminarButton.setIcon(new ImageIcon("Assets/delete.png"));
        b_guiaVisEliminar.setIcon(new ImageIcon("Assets/delete.png"));
        clienteButton.setIcon(new ImageIcon("Assets/cliente.png"));
        empleadoButton.setIcon(new ImageIcon("Assets/empleado.png"));
        b_guiaVisNueva.setIcon(new ImageIcon("Assets/nuevaVisita.png"));
        b_cliMisVisitas.setIcon(new ImageIcon("Assets/mis.png"));
        b_guiaVisMisVisitas.setIcon(new ImageIcon("Assets/mis.png"));
        b_cliReservar.setIcon(new ImageIcon("Assets/pedido.png"));
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

    public void actualizarListaVisitasClientes() {
        DefaultListModel<Visita> modeloVisCli = new DefaultListModel<>();
        for (Visita v: visitas) {
            if (v.getFecha().isAfter(LocalDateTime.now()))
                modeloVisCli.addElement(v);
        }
        listVisitasCli.setModel(modeloVisCli);
    }

    public void actualizarListaEmpleados() {
        DefaultListModel<Empleado> modeloEmp = new DefaultListModel<>();
        for (Empleado e: empleados)
            modeloEmp.addElement(e);
        listEmpleadosOClientes.setModel(modeloEmp);
    }

    public void vaciarDatosListEmpCli() {
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
                if (bbdd == 4) {
                    ArrayList<VisitaCliente> visitasClientes = new CargarDatosDB4O().cargarVisitasClientes();
                    for (VisitaCliente visitaCliente : visitasClientes) {
                        if (visitaCliente.getVisita().getCod() == v.getCod())
                            numClientes++;
                    }
                }
                else
                    numClientes = new CargarDatos().numClientesApuntadosAVisita(bbdd, v.getCod());
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
            }
            for (Visita v: visitasEmpCli) {
                if (v.getFecha().getYear() == LocalDateTime.now().getYear())
                    dineroRecGan += v.getCoste(); // TODO SOLO SUMA LA CANTIDAD DE ESTE AÑO, ASÍ TENEMOS RECOGIDO EL DINERO ANUAL
            }
        }

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SETTERS Y GETTERS

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

