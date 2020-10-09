package com.tamargo.sql;

import com.tamargo.Modelo.Cliente;
import com.tamargo.Modelo.Empleado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CargarDatos {

    public CargarDatos() {
    }

    /**
     * Método que devuelve todos los empleados. Lo reutilizaremos con las 3 BBDD que usan lenguaje SQL
     *
     * @param bbdd <- recibe un int que determinará a qué BBDD nos conectaremos
     * @return empleados <- devuelve un ArrayList con todos los empleados cargados
     */
    public ArrayList<Empleado> cargarEmpleados(int bbdd) {

        ArrayList<Empleado> empleados = new ArrayList<>();
        Connection conexion;
        Conexiones conexiones = new Conexiones();

        if (bbdd == 1)
            conexion = conexiones.conexionSQlite();
        else if (bbdd == 2)
            conexion = conexiones.conexionMySQL();
        else if (bbdd == 3)
            conexion = conexiones.conexionOracle();
        else {
            // TODO error mandando el int para elegir la BBDD al pulsar el botón
            return empleados;
        }

        try {
            Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
            ResultSet r = sentencia.executeQuery("SELECT * FROM empleados"); // Ejecutamos la sentencia
            while (r.next()) {

                Date fechaNac;
                Date fechaContratacion;

                if (bbdd == 1) { // SQLite es tonto y no sabe leer con r.getDate
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        fechaNac = new Date(format.parse(r.getString(4)).getTime());
                        fechaContratacion = new Date(format.parse(r.getString(5)).getTime());
                    } catch (ParseException e) {
                        fechaNac = null;
                        fechaContratacion = null;
                        e.printStackTrace();
                    }
                } else {
                    fechaNac = r.getDate(4);
                    fechaContratacion = r.getDate(5);
                }

                empleados.add(new Empleado(r.getString(1), r.getString(2),
                        r.getString(3), fechaNac, fechaContratacion,
                        r.getString(6), r.getString(7), r.getString(8)));
            }
            r.close(); // Cerrar ResultSet
            sentencia.close();// Cerrar Statement
            conexion.close(); // Cerrar Conexión
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Empleados cargados\n");
        return empleados;
    }

    /**
     * Método que devuelve todos los clientes. Lo reutilizaremos con las 3 BBDD que usan lenguaje SQL
     *
     * @param bbdd <- recibe un int que determinará a qué BBDD nos conectaremos
     * @return clientes <- devuelve un ArrayList con todos los clientes cargados
     */
    public ArrayList<Cliente> cargarClientes(int bbdd) {

        ArrayList<Cliente> clientes = new ArrayList<>();
        Connection conexion;
        Conexiones conexiones = new Conexiones();

        if (bbdd == 1)
            conexion = conexiones.conexionSQlite();
        else if (bbdd == 2)
            conexion = conexiones.conexionMySQL();
        else if (bbdd == 3)
            conexion = conexiones.conexionOracle();
        else {
            // TODO error mandando el int para elegir la BBDD al pulsar el botón
            return clientes;
        }

        try {
            Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
            ResultSet r = sentencia.executeQuery("SELECT * FROM clientes"); // Ejecutamos la sentencia
            while (r.next()) { // Recorremos los datos
                clientes.add(new Cliente(r.getString(1), r.getString(2),
                        r.getString(3), r.getInt(4), r.getString(5),
                        r.getString(6)));
            }
            r.close(); // Cerrar ResultSet
            sentencia.close();// Cerrar Statement
            conexion.close(); // Cerrar Conexión
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Clientes cargados\n");
        return clientes;
    }



}
