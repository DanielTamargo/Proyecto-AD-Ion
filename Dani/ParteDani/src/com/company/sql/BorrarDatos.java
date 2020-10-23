package com.company.sql;

import javax.swing.*;
import java.sql.*;

public class BorrarDatos {

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
     * Método para eliminar un empleado concreto, recibirá el dni para eliminarlo a través de su clave
     *
     * @param bbdd <- int que controlará a que bbdd se conectará
     * @param dni <- dni que usará como clave para eliminar al empleado deseado
     */
    public void borrarEmpleado(int bbdd, String dni) {
        boolean eliminado = true;
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (sentencia.executeUpdate("DELETE FROM empleados WHERE dni='" + dni + "'") <= 0)
                    eliminado = false;// Ejecutamos la sentencia

                if (bbdd == 1) {
                    borrarRegistrosEmpleado(conexion, dni);
                    borrarVisitasEmpleado(conexion, dni);
                }

                conexion.close();
            } catch (SQLException throwables) {
                eliminado = false;
            }

            String mensajeJOptionPane;
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0; // 0 = error, 1 = información

            if (eliminado) {
                tituloJOptionPane = "Empleado eliminado";
                mensajeJOptionPane = "Empleado '" + dni + "' eliminado con éxito.";
                tipoJOptionPane = 1;
            }
            else
                mensajeJOptionPane = "Error al eliminar al empleado '" + dni + "'.";

            mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }

    /**
     * Método para eliminar un cliente concreto, recibirá el dni para eliminarlo a través de su clave
     *
     * @param bbdd <- int que controlará a que bbdd se conectará
     * @param dni <- dni que usará como clave para eliminar al cliente deseado
     */
    public void borrarCliente(int bbdd, String dni) {
        boolean eliminado = true;
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (sentencia.executeUpdate("DELETE FROM clientes WHERE dni='" + dni + "'") <= 0)
                    eliminado = false;// Ejecutamos la sentencia

                if (bbdd == 1) {
                    borrarRegistrosCliente(conexion, dni);
                    borrarVisitasCliente(conexion, dni);
                }

                conexion.close();
            } catch (SQLException throwables) {
                eliminado = false;
            }

            String mensajeJOptionPane;
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0; // 0 = error, 1 = información

            if (eliminado) {
                tituloJOptionPane = "Cliente eliminado";
                mensajeJOptionPane = "Cliente '" + dni + "' eliminado con éxito.";
                tipoJOptionPane = 1;
            }
            else
                mensajeJOptionPane = "Error al eliminar al cliente '" + dni + "'.";

            mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }


    /**
     * Método para eliminar un visita concreto, recibirá el dni para eliminarlo a través de su clave
     *
     * @param bbdd <- int que controlará a que bbdd se conectará
     * @param codigo <- codigo que usará como clave para eliminar la visita deseada
     */
    public void borrarVisita(int bbdd, int codigo) {
        boolean eliminado = true;
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (sentencia.executeUpdate("DELETE FROM visitas WHERE cod=" + codigo) <= 0)
                    eliminado = false;// Ejecutamos la sentencia

                if (bbdd == 1) {
                    borrarVisitasClientePorVisita(conexion, codigo);
                }

                conexion.close();
            } catch (SQLException throwables) {
                eliminado = false;
            }

            String mensajeJOptionPane;
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0; // 0 = error, 1 = información

            if (eliminado) {
                tituloJOptionPane = "Visita eliminada";
                mensajeJOptionPane = "Visita eliminada con éxito.";
                tipoJOptionPane = 1;
            }
            else
                mensajeJOptionPane = "Error al eliminar la visita '" + codigo + "'.";

            mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////// SIMULACIÓN ON DELETE CASCADE PARA SQLITE
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
        Los siguientes métodos serán utilizados para simular en SQLite la parte ON DELETE CASCADE de las FK
     */

    public void borrarVisitasEmpleado(Connection conexion, String dni) {
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                sentencia.executeUpdate("DELETE FROM visitasempleados WHERE dni='" + dni + "'");
            } catch (SQLException | NullPointerException ignored) { }
        }
    }

    public void borrarVisitasCliente(Connection conexion, String dni) {
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                sentencia.executeUpdate("DELETE FROM visitasclientes WHERE cliente='" + dni + "'");
            } catch (SQLException | NullPointerException ignored) {  }
        }
    }

    public void borrarVisitasClientePorVisita(Connection conexion, int codigo) {
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                sentencia.executeUpdate("DELETE FROM visitasclientes WHERE visita=" + codigo);
            } catch (SQLException | NullPointerException ignored) { }
        }
    }

    public void borrarRegistrosEmpleado(Connection conexion, String dni) {
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                sentencia.executeUpdate("DELETE FROM registrosempleados WHERE empleado='" + dni + "'");
            } catch (SQLException | NullPointerException ignored) { }
        }
    }

    public void borrarRegistrosCliente(Connection conexion, String dni) {
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                sentencia.executeUpdate("DELETE FROM registrosclientes WHERE cliente='" + dni + "'");
            } catch (SQLException | NullPointerException ignored) { }
        }
    }

}
