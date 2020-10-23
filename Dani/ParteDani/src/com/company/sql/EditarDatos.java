package com.company.sql;

import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class EditarDatos {

    /**
     * Constructor vacío. De la clase sólo necesitamos los métodos que devuelven los datos a través de realizar
     * conexiones a la BBDD que corresponda
     */
    public EditarDatos() {
    }

    /**
     * Método reutilizado por los distintos métodos para cargar los datos que necesitemos
     *
     * @param bbdd <- dependiendo de este parámetro devolveremos una conexión u otra
     * @return <- devuelve la variable conexión
     */
    public Connection realizarConexion(int bbdd) {
        Connection conexion;
        Conexiones conexiones = new Conexiones();
        switch (bbdd) {
            case 1 -> conexion = conexiones.conexionSQlite();
            case 2 -> conexion = conexiones.conexionMySQL();
            case 3 -> conexion = conexiones.conexionOracle();
            default -> conexion = null;
        }
        return conexion;
    }

    /**
     * JOptionPane que informará del resultado de la eliminación del dato en cuestión
     *
     * @param titulo  <- titulo del JOptionPane
     * @param mensaje <- mensaje del JOptionPane
     * @param tipo    <- tipo del JOptionPane
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
     * Método para editar un empleado concreto, recibirá el dni para editarlo a través de su clave
     *
     * @param bbdd  <- int que controlará a que bbdd se conectará
     * @param emple <- dni que usará como clave para editar al empleado deseado
     */
    public void editarEmpleado(int bbdd, Empleado emple) {
        boolean editado = true;
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (bbdd == 3) {
                    if (sentencia.executeUpdate("UPDATE empleados SET dni='" + emple.getDni() + "', " +
                            "nombre='" + emple.getNombre() + "', " +
                            "primerApellido='" + emple.getPrimerapellido() + "', " +
                            "fechaNac=TO_DATE('" + emple.getFechaNac() + "', 'yyyy-mm-dd'), " +
                            "fechaContratacion=TO_DATE('" + emple.getFechaContratacion() + "', 'yyyy-mm-dd'), " +
                            "nacionalidad='" + emple.getNacionalidad() + "', " +
                            "cargo='" + emple.getCargo() + "', " +
                            "contrasenya='" + emple.getContrasenya() + "' " +
                            "WHERE dni='" + emple.getDni() + "';") <= 0)
                        editado = false;// Ejecutamos la sentencia
                } else {
                    if (sentencia.executeUpdate("UPDATE empleados SET dni='" + emple.getDni() + "', " +
                            "nombre='" + emple.getNombre() + "', " +
                            "primerApellido='" + emple.getPrimerapellido() + "', " +
                            "fechaNac='" + emple.getFechaNac() + "', " +
                            "fechaContratacion='" + emple.getFechaContratacion() + "', " +
                            "nacionalidad='" + emple.getNacionalidad() + "', " +
                            "cargo='" + emple.getCargo() + "', " +
                            "contrasenya='" + emple.getContrasenya() + "' " +
                            "WHERE dni='" + emple.getDni() + "';") <= 0)
                        editado = false;// Ejecutamos la sentencia
                }

                conexion.close();
            } catch (SQLException throwables) {
                editado = false;
            }
            
            String mensajeJOptionPane;
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0; // 0 = error, 1 = información

            if (editado) {
                tituloJOptionPane = "Empleado editado";
                mensajeJOptionPane = "Empleado '" + emple.getDni() + "' editado con éxito.";
                tipoJOptionPane = 1;
            } else
                mensajeJOptionPane = "Error al editar al empleado '" + emple.getDni() + "'.";

            mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }

    /**
     * Método para editar un cliente concreto, recibirá el dni para editarlo a través de su clave
     *
     * @param bbdd    <- int que controlará a que bbdd se conectará
     * @param cliente <- dni que usará como clave para editar al cliente deseado
     */
    public void editarCliente(int bbdd, Cliente cliente) {
        boolean editado = true;
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (sentencia.executeUpdate("UPDATE clientes SET dni='" + cliente.getDni() + "', " +
                        "nombre='" + cliente.getNombre() + "', " +
                        "apellidos='" + cliente.getApellidos() + "', " +
                        "edad=" + cliente.getEdad() + ", " +
                        "profesion='" + cliente.getProfesion() + "', " +
                        "contrasenya='" + cliente.getContrasenya() + "' " +
                        "WHERE dni='" + cliente.getDni() + "';") <= 0)
                    editado = false;// Ejecutamos la sentencia
                conexion.close();
            } catch (SQLException throwables) {
                editado = false;
            }

            String mensajeJOptionPane;
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0; // 0 = error, 1 = información

            if (editado) {
                tituloJOptionPane = "Cliente editado";
                mensajeJOptionPane = "Cliente '" + cliente.getDni() + "' editado con éxito.";
                tipoJOptionPane = 1;
            } else
                mensajeJOptionPane = "Error al editar al cliente '" + cliente.getDni() + "'.";

            mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }

    /**
     * Método para editar una visita concreta, recibirá el codiog para editarla a través de su clave
     *
     * @param bbdd    <- int que controlará a que bbdd se conectará
     * @param visita <- codigo que usará como clave para editar a la visita deseada
     */
    public void editarVisitas(int bbdd, Visita visita) {
        boolean editado = true;
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (bbdd == 3) {
                    if (sentencia.executeUpdate("UPDATE visitas SET cod=" + visita.getCod() + ", " +
                            "guia='" + visita.getGuia().getDni() + "', " +
                            "nombre='" + visita.getNombre() + "', " +
                            "numMaxClientes=" + visita.getNumMaxClientes() + ", " +
                            "puntoPartida='" + visita.getPuntoPartida() + "', " +
                            "fecha=TO_DATE('" + visita.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', 'yyyy-mm-dd hh24:mi:ss'), " +
                            "anyo=" + visita.getAnyo() + ", " +
                            "duracionEstimada=" + visita.getDuracionEstimada() + ", " +
                            "tematica='" + visita.getTematica() + "', " +
                            "coste=" + visita.getCoste() + " " +
                            "WHERE cod=" + visita.getCod() + ";") <= 0)
                        editado = false;// Ejecutamos la sentencia
                } else {
                    if (sentencia.executeUpdate("UPDATE visitas SET cod=" + visita.getCod() + ", " +
                            "guia='" + visita.getGuia().getDni() + "', " +
                            "nombre='" + visita.getNombre() + "', " +
                            "numMaxClientes=" + visita.getNumMaxClientes() + ", " +
                            "puntoPartida='" + visita.getPuntoPartida() + "', " +
                            "fecha='" + visita.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                            "anyo=" + visita.getAnyo() + ", " +
                            "duracionEstimada=" + visita.getDuracionEstimada() + ", " +
                            "tematica='" + visita.getTematica() + "', " +
                            "coste=" + visita.getCoste() + " " +
                            "WHERE cod=" + visita.getCod() + ";") <= 0)
                        editado = false;// Ejecutamos la sentencia
                }
                conexion.close();
            } catch (SQLException throwables) {
                editado = false;
            }

            String mensajeJOptionPane;
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0; // 0 = error, 1 = información

            if (editado) {
                tituloJOptionPane = "Visita editada";
                mensajeJOptionPane = "Visita editada con éxito.";
                tipoJOptionPane = 1;
            } else
                mensajeJOptionPane = "Error al editar la visita.";

            mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }
}
