package com.company;

import com.company.sql.Conexiones;

import java.sql.*;
import java.util.ArrayList;

public class Metadatos {

    public String nombresTablas = "empleados clientes visitas visitasclientes bonificaciones bonificacionesclientes registrosempleados registrosclientes" ;

    public Metadatos() { }

    public static void main(String[] args) {
        new Metadatos().generarMetadatos(3);
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
     * Método reutilizado por las distintas BBDD para generar los metadatos de cada una de ellas
     *
     * @param bbdd <- dependiendo de este parámetro utilizaremos una conexión u otra
     */
    public void generarMetadatos(int bbdd) {
        Connection conexion = realizarConexion(bbdd);

        if (conexion != null) {
            try {
                DatabaseMetaData dbmd = conexion.getMetaData(); //Creamos objeto DatabaseMetaData
                String nombre = dbmd.getDatabaseProductName();
                String driver = dbmd.getDriverName();
                String versionDriver = dbmd.getDriverVersion();
                String url = dbmd.getURL();
                String usuario = dbmd.getUserName();

                System.out.println("INFORMACION SOBRE LA BASE DE DATOS: " + nombre);
                System.out.println("Driver            : " + driver);
                System.out.println("Versión del Driver: " + versionDriver);
                System.out.println("URL               : " + url);
                System.out.println("Usuario           : " + usuario);

                System.out.println();
                ResultSet resul;
                ResultSet rsColumnas;
                resul = dbmd.getTables("grupo3", null, null, null);

                while (resul.next()) {
                    String catalogo = resul.getString(1);//columna 1 que devuelve ResulSet
                    String esquema = resul.getString(2); //columna 2
                    String tabla = resul.getString(3);   //nombre de la tabla
                    String tipo = resul.getString(4);  //tipo
                    if (nombresTablas.contains(tabla.toLowerCase()) && !tabla.equalsIgnoreCase("f")
                            && !tabla.equalsIgnoreCase("p") && !tabla.equalsIgnoreCase("v")) {
                        System.out.println("===================================================================");
                        System.out.println(tipo + " - Catalogo: " + catalogo + ", Esquema : " + esquema + ", Tabla : " + tabla);
                        System.out.println();
                        ArrayList<String> clavesPrimarias = new ArrayList<>();
                        try {
                            //Devuelve las PK de cada tabla de la BBDD
                            ResultSet rsp = dbmd.getPrimaryKeys(null, null, tabla);

                            while (rsp.next())
                                clavesPrimarias.add(rsp.getObject(4).toString());
                            //System.out.println("PK (clave primaria): " + rsp.getObject(4));
                            rsp.close();
                        } catch (SQLException ignored) {
                        }
                        if (clavesPrimarias.size() > 1) {
                            System.out.print("PKs (claves primarias): ");
                            boolean primero = true;
                            for (String clave: clavesPrimarias) {
                                if (primero) {
                                    System.out.print(clave);
                                    primero = false;
                                } else
                                    System.out.print(", " + clave);
                            }
                            System.out.println();
                        } else if (clavesPrimarias.size() == 1)
                            System.out.println("PK (clave primaria): " + clavesPrimarias.get(0));
                        //Mostramos el nombre de las columnas y su tipo de dato
                        rsColumnas = dbmd.getColumns(null, null, tabla, null);
                        System.out.println("Columnas " + tabla);
                        while (rsColumnas.next()) {
                            System.out.format("\t %-17s | %s\n",
                                     rsColumnas.getString(4), rsColumnas.getString("TYPE_NAME"));
                        }
                        rsColumnas.close();
                    }
                }
                resul.close();
                conexion.close();

            } catch (SQLException cn) {
                cn.printStackTrace();
            }
        }
    }
}



