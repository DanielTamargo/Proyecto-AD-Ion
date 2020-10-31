package com.company.DB4O;

import com.company.Modelo.*;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;

import javax.swing.*;

public class BorrarDatosDB4O {

    public BorrarDatosDB4O() { }

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
     * Elimina el empleado de la BBDD
     * @param emple <- cliente a eliminar
     */
    public void borrarEmpleado(Empleado emple) {
        ObjectContainer bd = new ConexionDB4O().conectarBD();
        String titulo = "Empleado eliminado";
        String mensaje = "Empleado " + emple.getDni() + " eliminado con éxito.";
        int tipo = 1;
        boolean error = false;

        if (bd != null) {
            try {
                Empleado empQuery = new Empleado(emple.getDni()); // Buscamos por la clave primaria única
                ObjectSet<Empleado> result = bd.queryByExample(empQuery);

                if (result.size() <= 0) {
                    error = true;
                } else {
                    while (result.hasNext()) {
                        Empleado e = result.next();
                        borrarVisitasEmpleado(bd, emple);
                        borrarRegistrosEmpleado(bd, emple);
                        bd.delete(e);
                    }
                }
            } catch (Db4oException ex) {
                error = true;
            }
            bd.close();
        } else
            error = true;

        if (error) {
            titulo = "Error al eliminar";
            mensaje = "Error al eliminar al empleado " + emple.getDni() + ".";
            tipo = 0;
        }

        mostrarJOPtionPane(titulo, mensaje, tipo);
    }

    /**
     * Elimina el cliente de la BBDD
     * @param cli <- cliente a eliminar
     */
    public void borrarCliente(Cliente cli) {
        ObjectContainer bd = new ConexionDB4O().conectarBD();
        String titulo = "Cliente eliminado";
        String mensaje = "Cliente " + cli.getDni() + " eliminado con éxito.";
        int tipo = 1;
        boolean error = false;

        if (bd != null) {
            try {
                Cliente cliQuery = new Cliente(cli.getDni()); // Buscamos por la clave primaria única
                ObjectSet<Cliente> result = bd.queryByExample(cliQuery);

                if (result.size() <= 0) {
                    error = true;
                } else {
                    while (result.hasNext()) {
                        Cliente c = result.next();
                        borrarVisitasCliente(bd, c);
                        borrarRegistrosCliente(bd, c);
                        bd.delete(c);
                    }
                }
            } catch (Db4oException ex) {
                error = true;
            }
            bd.close();
        } else
            error = true;

        if (error) {
            titulo = "Error al eliminar";
            mensaje = "Error al eliminar al cliente " + cli.getDni() + ".";
            tipo = 0;
        }

        mostrarJOPtionPane(titulo, mensaje, tipo);
    }

    /**
     * Elimina la visita de la BBDD
     * @param visita <- visita a eliminar
     */
    public void borrarVisita(Visita visita) {
        ObjectContainer bd = new ConexionDB4O().conectarBD();
        String titulo = "Visita eliminada";
        String mensaje = "Visita eliminada con éxito.";
        int tipo = 1;
        boolean error = false;

        if (bd != null) {
            try {
                Visita empQuery = new Visita(visita.getCod()); // Buscamos por la clave primaria única
                ObjectSet<Visita> result = bd.queryByExample(empQuery);

                if (result.size() <= 0) {
                    error = true;
                } else {
                    while (result.hasNext()) {
                        Visita v = result.next();
                        borrarVisitasCliente(bd, v);
                        bd.delete(v);
                    }
                }
            } catch (Db4oException ex) {
                error = true;
            }
            bd.close();
        } else
            error = true;

        if (error) {
            titulo = "Error al eliminar";
            mensaje = "Error al eliminar la visita.";
            tipo = 0;
        }

        mostrarJOPtionPane(titulo, mensaje, tipo);
    }

    /**
     * Elimina el registro de un empleado en una visita (cancela el registro)
     *
     * @param cliente <- cliente apuntado que va a cancelar el registro
     * @param visita <- visita de la que se desapunta el cliente
     */
    public void borrarVisitaCliente(Cliente cliente, Visita visita) {
        ObjectContainer bd = new ConexionDB4O().conectarBD();
        String titulo = "Registro cancelado";
        String mensaje = "Registro cancelado con éxito.";
        int tipo = 1;
        boolean error = false;
        if (bd != null) {
            try {
                VisitaCliente visCliQuery = new VisitaCliente(cliente, visita); // Buscamos por la clave primaria única
                ObjectSet<VisitaCliente> result = bd.queryByExample(visCliQuery);

                if (result.size() > 0) {
                    while (result.hasNext()) {
                        VisitaCliente vc = result.next();
                        bd.delete(vc);
                    }
                } else
                    error = true;
                bd.close();
            } catch (Db4oException ignored) {
                error = true;
            }
        } else
            error = true;

        if (error) {
            titulo = "Error";
            mensaje = "Error al cancelar el registro.";
            tipo = 0;
        }

        mostrarJOPtionPane(titulo, mensaje, tipo);
    }


    /**
     * Elimina las visitas organizadas por un empleado
     * @param bd <- objeto de la base de datos para realizar la búsqueda
     * @param empleado <- empleado usado para buscar sus visitas
     */
    public void borrarVisitasEmpleado(ObjectContainer bd, Empleado empleado) {
        if (bd != null) {
            try {
                Visita visQuery = new Visita(empleado); // Buscamos por la clave primaria única
                ObjectSet<Visita> result = bd.queryByExample(visQuery);

                if (result.size() > 0) {
                    while (result.hasNext()) {
                        Visita v = result.next();
                        bd.delete(v);
                    }
                }
            } catch (Db4oException ignored) { }
        }
    }

    /**
     * Elimina las visitasclientes en las que ha participado el empleado
     * @param bd <- objeto de la base de datos para realizar la búsqueda
     * @param cliente <- cliente usado para buscar sus visitasclientes
     */
    public void borrarVisitasCliente(ObjectContainer bd, Cliente cliente) {
        if (bd != null) {
            try {
                VisitaCliente visCliQuery = new VisitaCliente(cliente); // Buscamos por la clave primaria única
                ObjectSet<VisitaCliente> result = bd.queryByExample(visCliQuery);

                if (result.size() > 0) {
                    while (result.hasNext()) {
                        VisitaCliente vc = result.next();
                        bd.delete(vc);
                    }
                }
            } catch (Db4oException ignored) { }
        }
    }

    /**
     * Elimina las visitasclientes de la visita
     * @param bd <- objeto de la base de datos para realizar la búsqueda
     * @param visita <- visita usada para eliminar las visitasclientes
     */
    public void borrarVisitasCliente(ObjectContainer bd, Visita visita) {
        if (bd != null) {
            try {
                VisitaCliente visCliQuery = new VisitaCliente(visita); // Buscamos por la clave primaria única
                ObjectSet<VisitaCliente> result = bd.queryByExample(visCliQuery);

                if (result.size() > 0) {
                    while (result.hasNext()) {
                        VisitaCliente vc = result.next();
                        bd.delete(vc);
                    }
                }
            } catch (Db4oException ignored) { }
        }
    }

    public void borrarRegistrosEmpleado(ObjectContainer bd, Empleado emp) {
        if (bd != null) {
            try {
                RegistroEmpleado regQuery = new RegistroEmpleado(emp); // Buscamos por la clave primaria única
                ObjectSet<RegistroEmpleado> result = bd.queryByExample(regQuery);
                if (result.size() > 0) {
                    while (result.hasNext()) {
                        RegistroEmpleado re = result.next();
                        bd.delete(re);
                    }
                }
            } catch (Db4oException ignored) { }
        }
    }

    public void borrarRegistrosCliente(ObjectContainer bd, Cliente cli) {
        if (bd != null) {
            try {
                RegistroCliente regQuery = new RegistroCliente(cli); // Buscamos por la clave primaria única
                ObjectSet<RegistroCliente> result = bd.queryByExample(regQuery);
                if (result.size() > 0) {
                    while (result.hasNext()) {
                        RegistroCliente rc = result.next();
                        bd.delete(rc);
                    }
                }
            } catch (Db4oException ignored) { }
        }
    }

}
