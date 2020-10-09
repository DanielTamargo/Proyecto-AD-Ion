package com.tamargo.sql;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utilizaremos esta clase para obtener de una forma rápida y eficiente las conexiones a las distintas BBDD SQL
 * Las BBDD contempladas son: MySQL, SQLite, Oracle
 *
 * Así, si en algún momento cambiamos el driver, sólo tendremos que modificar la línea correspondiente aquí
 */
public class Conexiones {

    /**
     * Constructor vacío, sólo nos interesa llamar a sus métodos para obtener las conexiones a la BBDD
     */
    public Conexiones() {
    }

    /**
     * @return conexion <- devuelve la conexión, en este caso a la BBDD SQLite
     */
    public Connection conexionSQlite() {

        Connection conexion = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch (SQLException | ClassNotFoundException throwables) {
            mostrarErrorAlConectar(1);
            throwables.printStackTrace();

        }
        return conexion;
    }

    /**
     * @return conexion <- devuelve la conexión, en este caso a la BBDD MySQL
     */
    public Connection conexionMySQL() {

        Connection conexion = null;
        try {
            Class.forName("org.sqlite.JDBC"); // TODO hay que cambiar esta conexión
            conexion = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch (SQLException | ClassNotFoundException throwables) {
            mostrarErrorAlConectar(2);
            throwables.printStackTrace();

        }
        return conexion;
    }

    /**
     * @return conexion <- devuelve la conexión, en este caso a la BBDD Oracle
     */
    public Connection conexionOracle() {

        Connection conexion = null;
        try {
            Class.forName("org.sqlite.JDBC"); // TODO hay que cambiar esta conexión
            conexion = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch (SQLException | ClassNotFoundException throwables) {
            mostrarErrorAlConectar(3);
            throwables.printStackTrace();

        }
        return conexion;
    }

    /**
     * Reutilizaremos este método con los 3 métodos de las conexiones
     * Así, si al intentar realizar la conexión salta al catch por un error, podremos mostrar siempre el
     * JOptionPane correspondiente.
     *
     * @param bbdd <- será un int que controlará cuál es la BBDD que ha fallado
     */
    public void mostrarErrorAlConectar(int bbdd) {

        // Primero miramos a ver cuál es la BBDD que ha fallado
        String nombreBBDD = "Desconocida";

        if (bbdd == 1)
            nombreBBDD = "SQLite"; //TODO igual aquí queremos mostrar el nombre de la agencia como tal
        else if (bbdd == 2)
            nombreBBDD = "MySQL";
        else if (bbdd == 3)
            nombreBBDD = "Oracle";

        JOptionPane.showMessageDialog(null,
                "Error al conectar con la BBDD '" + nombreBBDD + "'",
                "Error al conectar",
                JOptionPane.ERROR_MESSAGE);

    }


}
