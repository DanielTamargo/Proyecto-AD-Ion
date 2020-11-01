package com.company.ventanas;

import com.company.DB4O.CargarDatosDB4O;
import com.company.DB4O.InsertarEditarDatosDB4O;
import com.company.modelo.Cliente;
import com.company.modelo.Empleado;
import com.company.modelo.RegistroCliente;
import com.company.modelo.RegistroEmpleado;
import com.company.sql.CargarDatos;
import com.company.sql.InsertarDatos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    private JFrame ventanaLogin;
    private JFrame ventanaPrincipal;
    private JPanel panel;

    private JTextField DNItxt;
    private JPasswordField passwordtxt;
    private JButton b_iniciar;
    private JButton b_volver;

    private int bbdd = 1;

    private Empleado empleado = null;
    private Cliente cliente = null;


    public Login() {


        b_volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaLogin.dispose();
                ventanaPrincipal.setVisible(true);
            }
        });
        b_iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tituloJOpt = "";
                String mensajeJOpt = "";
                int tipo = 0;
                boolean error = false;

                if (DNItxt.getText().length() > 0 && String.valueOf(passwordtxt.getPassword()).length() > 0) {
                    iniciarSesionEmpleado();
                    if (empleado == null)
                        iniciarSesionCliente();

                    //TODO recordatorio: login registra quién y cuándo inicia sesión
                    if (empleado != null)
                        registroEmpleado("Iniciado sesión");
                    if (cliente != null)
                        registroCliente("Iniciado sesión");

                } else {
                    tituloJOpt = "Faltan datos";
                    mensajeJOpt = "Debes introducir tus datos para poder iniciar sesión";
                    error = true;
                }

                if (empleado == null && cliente == null) {
                    tituloJOpt = "Error al iniciar sesión";
                    mensajeJOpt = "Usuario y/o contraseña incorrectos.";
                    error = true;
                } else {
                    abrirVentanaSecundaria();
                }

                if (error)
                    mostrarJOPtionPane(tituloJOpt, mensajeJOpt, tipo);

            }
        });
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

    public void mostrarJOPtionPane(String titulo, String mensaje, int tipo) {
        JButton okButton = new JButton("Entendido");
        okButton.setFocusPainted(false);
        Object[] options = {okButton};
        final JOptionPane pane = new JOptionPane(mensaje, tipo, JOptionPane.YES_NO_OPTION, null, options);
        JDialog dialog = pane.createDialog(titulo);
        okButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    public void iniciarSesionEmpleado() {
        if (bbdd == 4) {
            empleado = new CargarDatosDB4O().iniciarSesionEmpleado(DNItxt.getText(), String.valueOf(passwordtxt.getPassword()));
        } else {
            empleado = new CargarDatos().iniciarSesionEmpleado(bbdd, DNItxt.getText(), String.valueOf(passwordtxt.getPassword()));
        }
    }

    public void iniciarSesionCliente() {
        if (bbdd == 4) {
            cliente = new CargarDatosDB4O().iniciarSesionCliente(DNItxt.getText(), String.valueOf(passwordtxt.getPassword()));
        } else {
            cliente = new CargarDatos().iniciarSesionCliente(bbdd, DNItxt.getText(), String.valueOf(passwordtxt.getPassword()));
        }
    }

    public void abrirVentanaSecundaria() {
        JFrame frame = new JFrame("Datos");
        VentanaSecundaria vs = new VentanaSecundaria();
        frame.setContentPane(vs.getPanelSecundario());
        vs.setBbdd(bbdd);
        vs.setEmpleado(empleado);
        vs.setCliente(cliente);
        vs.setFrameVentanaPrincipal(ventanaPrincipal);
        vs.setFrameVentanaSecundaria(frame);
        vs.cargarDatos();
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ventanaLogin.dispose();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setBbdd(int bbdd) {
        this.bbdd = bbdd;
    }


    public void setVentanaLogin(JFrame ventanaLogin) {
        this.ventanaLogin = ventanaLogin;
    }

    public void setVentanaPrincipal(JFrame ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
