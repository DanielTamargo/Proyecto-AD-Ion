package com.company.sql;

import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InsertarDatos {

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
     * Método utilizado para insertar un nuevo empleado en la BBDD deseada
     * El método notificará a través de un JOptionPane el resultado de la sentencia SQL (éxito, error)
     * En caso de error podrá especificar si el error se debe a que el dni (PK) ya existía previamente en la BBDD
     *
     * @param bbdd <- int que controlará a que bbdd se conectará
     * @param emp <- objeto tipo Empleado con todos los datos del empleado a insertar
     */
    public void insertarEmpleado(int bbdd, Empleado emp) {
        boolean insertado = true;
        boolean unico = true;
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
            try {
                String sql;
                if (bbdd == 3) {
                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    String fechaNac = df.format(emp.getFechaNac());
                    String fechaContratacion = df.format(emp.getFechaContratacion());
                    sql = String.format(
                            "INSERT INTO empleados VALUES('%s', '%s', '%s', TO_DATE('%s', 'yyyy/mm/dd'), TO_DATE('%s', 'yyyy/mm/dd'), '%s', '%s', '%s')",
                            emp.getDni(), emp.getNombre(), emp.getPrimerapellido(), fechaNac,
                            fechaContratacion, emp.getNacionalidad(), emp.getCargo(), emp.getContrasenya());
                } else {
                    sql = String.format(
                            "INSERT INTO empleados VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                            emp.getDni(), emp.getNombre(), emp.getPrimerapellido(), emp.getFechaNac(),
                            emp.getFechaContratacion(), emp.getNacionalidad(), emp.getCargo(), emp.getContrasenya());
                }
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (sentencia.executeUpdate(sql) <= 0)
                    insertado = false;// Ejecutamos la sentencia

                conexion.close();
            } catch (SQLException throwables) {
                if (throwables.getLocalizedMessage().contains("dni is not unique") || throwables.getLocalizedMessage().contains("Duplicate")
                        || throwables.getLocalizedMessage().contains("única") || throwables.getLocalizedMessage().contains("unique constraint"))
                    unico = false;
                insertado = false;
            }

            String mensajeJOptionPane;
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0; // 0 = error, 1 = información

            if (insertado) {
                tituloJOptionPane = "Empleado insertado";
                mensajeJOptionPane = "Empleado '" + emp.getDni() + "' insertado con éxito.";
                tipoJOptionPane = 1;
            }
            else
                mensajeJOptionPane = "Error al insertar al empleado '" + emp.getDni() + "'.";

            if (!unico)
                mensajeJOptionPane += "\nYa hay un empleado registrado con ese DNI.";

            mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }

    /**
     * Método utilizado para insertar un nuevo cliente en la BBDD deseada
     * El método notificará a través de un JOptionPane el resultado de la sentencia SQL (éxito, error)
     * En caso de error podrá especificar si el error se debe a que el dni (PK) ya existía previamente en la BBDD
     *
     * @param bbdd <- int que controlará a que bbdd se conectará
     * @param cli <- objeto tipo Cliente con todos los datos del cliente a insertar
     */
    public void insertarCliente(int bbdd, Cliente cli) {
        boolean insertado = true;
        boolean unico = true;
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
            try {
                String sql = String.format(
                        "INSERT INTO clientes VALUES('%s', '%s', '%s', %d, '%s', '%s')",
                        cli.getDni(), cli.getNombre(), cli.getApellidos(), cli.getEdad(),
                        cli.getProfesion(), cli.getContrasenya());
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (sentencia.executeUpdate(sql) <= 0)
                    insertado = false;// Ejecutamos la sentencia

                conexion.close();
            } catch (SQLException throwables) {
                if (throwables.getLocalizedMessage().contains("dni is not unique") || throwables.getLocalizedMessage().contains("Duplicate")
                        || throwables.getLocalizedMessage().contains("única") || throwables.getLocalizedMessage().contains("unique constraint"))
                    unico = false;
                insertado = false;
            }

            String mensajeJOptionPane;
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0; // 0 = error, 1 = información

            if (insertado) {
                tituloJOptionPane = "Cliente insertado";
                mensajeJOptionPane = "Cliente '" + cli.getDni() + "' insertado con éxito.";
                tipoJOptionPane = 1;
            }
            else
                mensajeJOptionPane = "Error al insertar al cliente '" + cli.getDni() + "'.";

            if (!unico)
                mensajeJOptionPane += "\nYa hay un cliente registrado con ese DNI.";

        mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }

    /**
     * Método utilizado para insertar un nuevo visita en la BBDD deseada
     *
     * @param bbdd <- int que controlará a que bbdd se conectará
     * @param vis <- objeto tipo Visita con todos los datos de la visita a insertar
     */
    public void insertarVisita(int bbdd, Visita vis) {
        boolean insertado = true;
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
            try {
                String sql;
                if (bbdd == 3) {
                    String fecha = vis.getFecha().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                    sql = "INSERT INTO " +
                            "visitas(guia, nombre, numMaxClientes, puntoPartida, fecha, anyo, duracionEstimada, tematica, coste) " +
                            "VALUES('" + vis.getGuia().getDni() + "', " +
                            "'" + vis.getNombre() + "', " + vis.getNumMaxClientes() + ", " +
                            "'" + vis.getPuntoPartida() + "', TO_DATE('" + fecha + "', 'yyyy/mm/dd hh24:mi:ss'), " +
                            "" + vis.getAnyo() + ", " + vis.getDuracionEstimada() + ", " +
                            "'" + vis.getTematica() + "', " + vis.getCoste() + ")";
                } else {
                    sql = "INSERT INTO " +
                            "visitas(guia, nombre, numMaxClientes, puntoPartida, fecha, anyo, duracionEstimada, tematica, coste) " +
                            "VALUES('" + vis.getGuia().getDni() + "', " +
                            "'" + vis.getNombre() + "', " + vis.getNumMaxClientes() + ", " +
                            "'" + vis.getPuntoPartida() + "', '" + vis.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                            "" + vis.getAnyo() + ", " + vis.getDuracionEstimada() + ", " +
                            "'" + vis.getTematica() + "', " + vis.getCoste() + ")";
                }
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (sentencia.executeUpdate(sql) <= 0)
                    insertado = false;// Ejecutamos la sentencia

                conexion.close();
            } catch (SQLException throwables) {
                insertado = false;
                throwables.printStackTrace();
            }

            String mensajeJOptionPane;
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0; // 0 = error, 1 = información

            if (insertado) {
                tituloJOptionPane = "Visita insertada";
                mensajeJOptionPane = "Visita insertada con éxito.";
                tipoJOptionPane = 1;
            }
            else
                mensajeJOptionPane = "Error al insertar la visita.";

            mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }

    /**
     * Método utilizado para insertar una nueva visita en la BBDD deseada
     *
     * @param bbdd <- int que controlará a que bbdd se conectará
     * @param codigoVisita <- codigo de la visita para relacionarla con el cliente
     * @param dniCliente <- dni del cliente para relacionarlo con la visita
     */
    public void insertarVisitaCliente(int bbdd, int codigoVisita, String dniCliente) {
        boolean insertado = true;
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
            try {
                String sql = "INSERT INTO visitasclientes VALUES('" + dniCliente + "', " + codigoVisita + ")";
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (sentencia.executeUpdate(sql) <= 0)
                    insertado = false;// Ejecutamos la sentencia

                conexion.close();
            } catch (SQLException throwables) {
                insertado = false;
            }

            String mensajeJOptionPane;
            String tituloJOptionPane = "Error";
            int tipoJOptionPane = 0; // 0 = error, 1 = información

            if (insertado) {
                tituloJOptionPane = "Cliente apuntado";
                mensajeJOptionPane = "Cliente apuntado a la visita con éxito.";
                tipoJOptionPane = 1;
            }
            else
                mensajeJOptionPane = "Error al apuntar al cliente a la visita.";

            mostrarJOPtionPane(tituloJOptionPane, mensajeJOptionPane, tipoJOptionPane);
        }
    }

    /**
     * Método utilizado para insertar un nuevo registro de un empleado en la BBDD deseada
     *
     * @param bbdd <- int que controlará a que bbdd se conectará
     * @param dni <- dni del empleado a guardar en el registro
     * @param registro <- descripción del registro a guardar
     */
    public void insertarRegistroEmpleado(int bbdd, String dni, String registro) {
        boolean insertado = true;
        Connection conexion = realizarConexion(bbdd);
        LocalDateTime fecha = LocalDateTime.now();
        if (conexion != null) {
            try {
                String sql;
                if (bbdd == 3) {
                    sql = "INSERT INTO registrosempleados" +
                            "(empleado, fecha, registro) " +
                            "VALUES('" + dni + "', TO_DATE('" +
                            fecha.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) +
                            "', 'yyyy/mm/dd'), '" + registro + "')";
                } else {
                    sql = "INSERT INTO registrosempleados" +
                            "(empleado, fecha, registro) " +
                            "VALUES('" + dni + "', '" +
                            fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                            "', '" + registro + "')";
                }
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (sentencia.executeUpdate(sql) <= 0)
                    insertado = false;// Ejecutamos la sentencia

                conexion.close();
            } catch (SQLException throwables) {
                insertado = false;
            }

            if (insertado) {
                System.out.println("Registro creado:" +
                        "\n\tEmpleado '" + dni + "' a las " + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                        "\n\t" + registro + "\n");
            }
        }
    }

    /**
     * Método utilizado para insertar un nuevo registro de un cliente en la BBDD deseada
     *
     * @param bbdd <- int que controlará a que bbdd se conectará
     * @param dni <- dni del cliente a guardar en el registro
     * @param registro <- descripción del registro a guardar
     */
    public void insertarRegistroCliente(int bbdd, String dni, String registro) {
        boolean insertado = true;
        Connection conexion = realizarConexion(bbdd);
        LocalDateTime fecha = LocalDateTime.now();
        if (conexion != null) {
            try {
                String sql;
                if (bbdd == 3) {
                    sql = "INSERT INTO registrosclientes" +
                            "(cliente, fecha, registro) " +
                            "VALUES('" + dni + "', TO_DATE('" +
                            fecha.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) +
                            "', 'yyyy/mm/dd'), '" + registro + "')";
                } else {
                    sql = "INSERT INTO registrosclientes" +
                            "(cliente, fecha, registro) " +
                            "VALUES('" + dni + "', '" +
                            fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                            "', '" + registro + "')";
                }
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                if (sentencia.executeUpdate(sql) <= 0)
                    insertado = false;// Ejecutamos la sentencia

                conexion.close();
            } catch (SQLException throwables) {
                insertado = false;
            }

            if (insertado) {
                System.out.println("Registro creado:" +
                        "\n\tCliente '" + dni + "' a las " + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                        "\n\t" + registro + "\n");
            }
        }
    }

}
