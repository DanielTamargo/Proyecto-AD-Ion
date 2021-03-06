package com.company.DB4O;

import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;
import com.company.Modelo.VisitaCliente;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;

import javax.swing.*;

public class InsertarEditarDatosDB4O {

    public InsertarEditarDatosDB4O() { }

    /**
     * JOptionPane que informará del resultado de la eliminación del dato en cuestión
     *
     * @param titulo <- titulo del JOptionPane
     * @param mensaje <- mensaje del JOptionPane
     * @param tipo <- tipo del JOptionPane
     */
    public void mostrarJOPtionPane(String titulo, String mensaje, int tipo) {
        JButton okButton = new JButton("Entendido");
        okButton.setFocusPainted(false);
        Object[] options = {okButton};
        final JOptionPane pane = new JOptionPane(mensaje, tipo, JOptionPane.YES_NO_OPTION, null, options);
        JDialog dialog = pane.createDialog(titulo);
        okButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    /**
     * Método utilizado para insertar o editar un nuevo empleado
     * Se añadirá a la lista de empleados, y al registro de la BD
     *
     * @param emple <- objeto de tipo Empleado con todos los datos a insertar o editar
     */
    public void insertarEditarEmpleado(Empleado emple) {
        ObjectContainer bd = new ConexionDB4O().conectarBD();
        String titulo = "";
        String mensaje = "";
        int tipo = 0;
        boolean error = false;

        if (bd != null) {
            Empleado empQuery = new Empleado(emple.getDni());
            try {
                ObjectSet<Empleado> result = bd.queryByExample(empQuery);

                if (result.size() == 0) {
                    bd.store(emple);
                    titulo = "Empleado insertado";
                    mensaje = "Empleado '" + emple.getDni() + "' insertado con éxito.";
                } else {
                    Empleado existe = result.next();
                    existe.cambiarDatosDB4O(emple);
                    bd.store(existe);
                    titulo = "Empleado editado";
                    mensaje = "Empleado '" + emple.getDni() + "' editado con éxito.";
                }
                tipo = 1;

            } catch (Db4oException ex) {
                error = true;
            }
            bd.close();

        } else
            error = true;

        if (error) {
            titulo = "Error al insertar/editar";
            mensaje = "Error al insertar/editar el empleado '" + emple.getDni() + "'.";
            tipo = 0;
        }
        mostrarJOPtionPane(titulo, mensaje, tipo);
    }

    /**
     * Método utilizado para insertar o editar un nuevo cliente
     * Se añadirá a la lista de clientes, y al registro de la BD
     *
     * @param cli <- objeto de tipo Cliente con todos los datos a insertar
     */
    public void insertarEditarCliente(Cliente cli) {
        ObjectContainer bd = new ConexionDB4O().conectarBD();
        String titulo = "";
        String mensaje = "";
        int tipo = 0;
        boolean error = false;

        if (bd != null) {
            Cliente cliQuery = new Cliente(cli.getDni());
            try {
                ObjectSet<Cliente> result = bd.queryByExample(cliQuery);

                if (result.size() == 0) {
                    bd.store(cli);
                    titulo = "Cliente insertado";
                    mensaje = "Cliente '" + cli.getDni() + "' insertado con éxito.";
                } else {
                    Cliente existe = result.next();
                    existe.cambiarDatosDB4O(cli);
                    bd.store(existe);
                    titulo = "Cliente editado";
                    mensaje = "Cliente '" + cli.getDni() + "' editado con éxito.";
                }
                tipo = 1;

            } catch (Db4oException ex) {
                error = true;
            }
            bd.close();

        } else
            error = true;

        if (error) {
            titulo = "Error al insertar/editar";
            mensaje = "Error al insertar/editar el cliente '" + cli.getDni() + "'.";
            tipo = 0;
        }
        mostrarJOPtionPane(titulo, mensaje, tipo);
    }

    /**
     * Método utilizado para insertar o editar una nueva visita
     * Se añadirá a la lista de visitas, y al registro de la BD
     *
     * @param visita <- objeto de tipo Visita con todos los datos a insertar o editar
     */
    public void insertarEditarVisita(Visita visita) {
        ObjectContainer bd = new ConexionDB4O().conectarBD();
        String titulo = "";
        String mensaje = "";
        int tipo = 0;
        boolean error = false;

        if (bd != null) {
            Visita visQuery = new Visita(visita.getCod());
            try {
                ObjectSet<Visita> result = bd.queryByExample(visQuery);

                if (result.size() == 0) {
                    bd.store(visita);
                    titulo = "Visita insertada";
                    mensaje = "Visita insertada con éxito.";
                } else {
                    Visita existe = result.next();
                    existe.cambiarDatosDB4O(visita);
                    bd.store(existe);
                    titulo = "Visita editada";
                    mensaje = "Visita editada con éxito.";
                }
                tipo = 1;

            } catch (Db4oException ex) {
                error = true;
            }
            bd.close();

        } else
            error = true;

        if (error) {
            titulo = "Error al insertar/editar";
            mensaje = "Error al insertar/editar la visita.";
            tipo = 0;
        }
        mostrarJOPtionPane(titulo, mensaje, tipo);
    }

    /**
     * Método utilizado para insertar o editar una nueva visita del cliente
     * Se añadirá a la lista de visitasCliente, y al registro de la BD
     *
     * @param visitaCliente <- objeto de tipo VisitaCliente con todos los datos a insertar o editar
     */
    public void insertarEditarVisitaCliente(VisitaCliente visitaCliente) {
        ObjectContainer bd = new ConexionDB4O().conectarBD();
        String titulo = "";
        String mensaje = "";
        int tipo = 0;
        boolean error = false;

        if (bd != null) {
            VisitaCliente visQuery = new VisitaCliente(visitaCliente.getVisita());
            try {
                ObjectSet<VisitaCliente> result = bd.queryByExample(visQuery);

                if (result.size() == 0) {
                    bd.store(visitaCliente);
                    titulo = "VisitaCliente insertada";
                    mensaje = "Cliente apuntado a la visita con éxito.";
                } else {
                    VisitaCliente existe = result.next();
                    existe.cambiarDatosDB4O(visitaCliente);
                    bd.store(existe);
                    titulo = "VisitaCliente editada";
                    mensaje = "Visita del cliente editada con éxito.";
                }
                tipo = 1;

            } catch (Db4oException ex) {
                error = true;
            }
            bd.close();

        } else
            error = true;

        if (error) {
            titulo = "Error al insertar/editar";
            mensaje = "Error al insertar/editar la visita del cliente.";
            tipo = 0;
        }
        mostrarJOPtionPane(titulo, mensaje, tipo);
    }
}
