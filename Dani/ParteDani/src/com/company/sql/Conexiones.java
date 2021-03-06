package com.company.sql;

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
     * Es una línea muy larga, para dejar el código más claro creamos una variable con dicha línea y luego la llamamos
     */
    String driverMySQL = "jdbc:mysql://localhost/grupo3?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EET";

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
            //throwables.printStackTrace();
        }
        return conexion;
    }

    /**
     * @return conexion <- devuelve la conexión, en este caso a la BBDD MySQL
     */
    public Connection conexionMySQL() {

        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(driverMySQL, "grupo3", "grupo3");
        } catch (SQLException | ClassNotFoundException throwables) {
            mostrarErrorAlConectar(2);
            //throwables.printStackTrace();
        }
        return conexion;
    }

    /**
     * @return conexion <- devuelve la conexión, en este caso a la BBDD Oracle
     */
    public Connection conexionOracle() {

        Connection conexion = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "grupo3", "grupo3");
        } catch (SQLException | ClassNotFoundException throwables) {
            mostrarErrorAlConectar(3);
            //throwables.printStackTrace();
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

        JButton okButton = new JButton("Entendido");
        okButton.setFocusPainted(false);
        Object[] options = {okButton};
        final JOptionPane pane = new JOptionPane("Error al conectar con la BBDD '" + nombreBBDD + "'", JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION, null, options);
        JDialog dialog = pane.createDialog("Error al conectar");
        okButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);

    }


}
