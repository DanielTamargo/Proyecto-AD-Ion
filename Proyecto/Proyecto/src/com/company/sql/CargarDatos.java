package com.company.sql;

import com.company.modelo.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class CargarDatos {

    /**
     * Constructor vacío. De la clase sólo necesitamos los métodos que devuelven los datos a través de realizar
     * conexiones a la BBDD que corresponda
     */
    public CargarDatos() { }

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
     * Método reutilizado por los distintos métodos que necesiten cargar datos tipo Date de las BBDD
     * Como SQLite trata las fechas distintas a las demás BBDD, utilizo este método para reconstuirla y devolverla
     * correctamente, si no, siempre devolvería un valor predeterminado fijo e inservible
     *
     * @param fechaStr <- la fecha versión String
     * @return <- la fecha bien construida
     */
    public Date reconstruirFechaSQLite(String fechaStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = new Date(format.parse(fechaStr).getTime());
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha.");
        }
        return fecha;
    }

    /**
     * Método reutilizado cada vez que queramos convertir un string a localdatetime
     *
     * @param fechaStr <- fecha en una variable tipo string
     * @return <- devolvemos la variable tipo localdatetime con la fecha recibida cargada
     */
    public LocalDateTime stringToLocalDateTime(String fechaStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fecha = null;
        try {
            fecha = LocalDateTime.parse(fechaStr, formatter);
        } catch (Exception e) {
            System.out.println("Error al convertir la fecha.");
        }
        return fecha;
    }

    public LocalDateTime oracleStringToLocalDateTime(String fechaStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fecha = null;
        if (fechaStr.length() < 11)
        if (new Random().nextInt(10) > 4)
            fechaStr += " 17:00:00";
        else if (new Random().nextInt(10) > 4)
            fechaStr += " 18:00:00";
        else if (new Random().nextInt(10) > 4)
            fechaStr += " 19:30:00";
        else if (new Random().nextInt(10) > 4)
            fechaStr += " 17:30:00";
        else if (new Random().nextInt(10) > 4)
            fechaStr += " 20:00:00";
        else if (new Random().nextInt(10) > 4)
            fechaStr += " 19:30:00";
        else
            fechaStr += " 18:30:00";
        System.out.println(fechaStr);
        try {
            fecha = LocalDateTime.parse(fechaStr, formatter);
        } catch (Exception e) {
            System.out.println("Error al convertir la fecha.");
        }
        return fecha;
    }

    /**
     * Método reutilizado cada vez que queramos convertir un localdatetime a string
     *
     * @param date <- localdatetime recibida
     * @return <- devuelve un string con la fecha transformada
     */
    public String localDateTimeToString(LocalDateTime date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dateStr = "";
        try {
            dateStr = dateFormat.format(date);
        } catch (Exception e) {
            System.out.println("Error al convertir la fecha");
        }
        return dateStr;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////// EMPLEADOS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Método que devuelve todos los empleados. Lo reutilizaremos con las 3 BBDD que usan lenguaje SQL
     *
     * @param bbdd <- recibe un int que determinará a qué BBDD nos conectaremos
     * @return <- devuelve un ArrayList con todos los empleados cargados
     */
    public ArrayList<Empleado> cargarEmpleados(int bbdd) {

        ArrayList<Empleado> empleados = new ArrayList<>();
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                ResultSet r = sentencia.executeQuery("SELECT * FROM empleados"); // Ejecutamos la sentencia
                while (r.next()) {

                    Date fechaNac;
                    Date fechaContratacion;

                    if (bbdd == 1) { // SQLite es tonto y no sabe leer con r.getDate
                        fechaNac = reconstruirFechaSQLite(r.getString(4));
                        fechaContratacion = reconstruirFechaSQLite(r.getString(5));
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

                if (empleados.size() <= 0)
                    System.out.println("No existen empleados en la BBDD");
                else
                    System.out.println("Empleados cargados");

            } catch (SQLException throwables) {
                System.out.println("Error al cargar los empleados");
            }
        }

        return empleados;
    }


    /**
     * Este método se reutilizará desde todas aquellas tablas que tienen referencia con el empleado
     *
     * @param bbdd <- controlará el caso raro de SQLite con r.getDate
     * @param conexion <- podrá ejecutar la sentencia utilizando la conexión
     * @param dni <- sabrá qué empleado buscar basándose en la referencia en las tablas
     * @return <- devuelve el empleado en cuestión
     */
    public Empleado cargarEmpleado(int bbdd, Connection conexion, String dni) {

        Empleado emp = null;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet r = sentencia.executeQuery("SELECT * FROM empleados WHERE dni='" + dni + "'");

            while (r.next()) {
                Date fechaNac;
                Date fechaContratacion;

                if (bbdd == 1) { // SQLite es tonto y no sabe leer con r.getDate
                    fechaNac = reconstruirFechaSQLite(r.getString(4));
                    fechaContratacion = reconstruirFechaSQLite(r.getString(5));
                } else {
                    fechaNac = r.getDate(4);
                    fechaContratacion = r.getDate(5);
                }
                emp = new Empleado(r.getString(1), r.getString(2),
                        r.getString(3), fechaNac, fechaContratacion,
                        r.getString(6), r.getString(7), r.getString(8));
            }
            r.close(); // Cerrar ResultSet
            sentencia.close();// Cerrar Statement

            if (emp == null)
                System.out.println("No se pudo encontrar el empleado: '" + dni + "'");

        } catch (SQLException throwables) {
            System.out.println("Error al cargar el empleado '" + dni + "'");
        }

        return emp;
    }

    /**
     * Método que devuelve los datos de un empleado al iniciar sesión (solo si el usuario y contraseña son correctos
     *
     * @param dni <- dni del usuario
     * @param contrasenya <- contraseña del usuario
     * @return emp <- devuelve los datos del empleado si ha iniciado sesión correctamente
     */
    public Empleado iniciarSesionEmpleado(int bbdd, String dni, String contrasenya) {
        Empleado emp = null;
        Connection conexion = realizarConexion(bbdd);

        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement();
                ResultSet r = sentencia.executeQuery("SELECT * FROM empleados " +
                        "WHERE dni='" + dni + "' AND contrasenya='" + contrasenya + "'");

                while (r.next()) {
                    Date fechaNac;
                    Date fechaContratacion;

                    if (bbdd == 1) { // SQLite es tonto y no sabe leer con r.getDate
                        fechaNac = reconstruirFechaSQLite(r.getString(4));
                        fechaContratacion = reconstruirFechaSQLite(r.getString(5));
                    } else {
                        fechaNac = r.getDate(4);
                        fechaContratacion = r.getDate(5);
                    }
                    emp = new Empleado(r.getString(1), r.getString(2),
                            r.getString(3), fechaNac, fechaContratacion,
                            r.getString(6), r.getString(7), r.getString(8));
                }
                r.close(); // Cerrar ResultSet
                sentencia.close();// Cerrar Statement

                if (emp == null)
                    System.out.println("No se pudo encontrar el empleado: '" + dni + "'");

            } catch (SQLException throwables) {
                System.out.println("Error al cargar el empleado '" + dni + "'");
            }
        }

        return emp;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////// CLIENTES
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Método que devuelve todos los clientes. Lo reutilizaremos con las 3 BBDD que usan lenguaje SQL
     *
     * @param bbdd <- recibe un int que determinará a qué BBDD nos conectaremos
     * @return <- devuelve un ArrayList con todos los clientes cargados
     */
    public ArrayList<Cliente> cargarClientes(int bbdd) {

        ArrayList<Cliente> clientes = new ArrayList<>();
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {
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

                if (clientes.size() <= 0)
                    System.out.println("No existen clientes en la BBDD");
                else
                    System.out.println("Clientes cargados");

            } catch (SQLException throwables) {
                System.out.println("Error al cargar los clientes.");
            }
        }
        return clientes;
    }

    /**
     * Este método se reutilizará desde todas aquellas tablas que tienen referencia con el cliente
     *  (No necesita el parámetro bbdd porque en la tabla no se usa Date y no hay problemas con SQLite)
     *
     * @param conexion <- podrá ejecutar la sentencia utilizando la conexión
     * @param dni <- sabrá qué cliente buscar basándose en la referencia en las tablas
     * @return <- devuelve el cliente en cuestión
     */
    public Cliente cargarCliente(Connection conexion, String dni) {

        Cliente cli = null;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet r = sentencia.executeQuery("SELECT * FROM clientes WHERE dni='" + dni + "'");

            while (r.next()) {
                cli = new Cliente(r.getString(1), r.getString(2),
                        r.getString(3), r.getInt(4), r.getString(5),
                        r.getString(6));
            }
            r.close(); // Cerrar ResultSet
            sentencia.close();// Cerrar Statement

            if (cli == null)
                System.out.println("No se pudo encontrar el cliente: '" + dni + "'");

        } catch (SQLException throwables) {
            System.out.println("Error al cargar el cliente: '" + dni + "'");
        }

        return cli;
    }

    /**
     * Método que devuelve los datos de un cliente al iniciar sesión (solo si el usuario y contraseña son correctos
     *
     * @param dni <- dni del usuario
     * @param contrasenya <- contraseña del usuario
     * @return cli <- devuelve los datos del cliente si ha iniciado sesión correctamente
     */
    public Cliente iniciarSesionCliente(int bbdd, String dni, String contrasenya) {
        Cliente cli = null;
        Connection conexion = realizarConexion(bbdd);

        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement();
                ResultSet r = sentencia.executeQuery("SELECT * FROM clientes " +
                        "WHERE dni='" + dni + "' AND contrasenya='" + contrasenya + "'");

                while (r.next()) {
                    cli = new Cliente(r.getString(1), r.getString(2),
                            r.getString(3), r.getInt(4), r.getString(5),
                            r.getString(6));
                }
                r.close(); // Cerrar ResultSet
                sentencia.close();// Cerrar Statement

                if (cli == null)
                    System.out.println("No se pudo encontrar el cliente: '" + dni + "'");

            } catch (SQLException throwables) {
                System.out.println("Error al cargar el cliente: '" + dni + "'");
            }
        }

        return cli;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////// VISITAS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Método que devuelve todas las visitas. Lo reutilizaremos con las 3 BBDD que usan lenguaje SQL
     *
     * @param bbdd <- recibe un int que determinará a qué BBDD nos conectaremos
     * @return <- devuelve un ArrayList con todas las visitas cargadas
     */
    public ArrayList<Visita> cargarVisitas(int bbdd) {

        ArrayList<Visita> visitas = new ArrayList<>();
        Connection conexion = realizarConexion(bbdd);

        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                ResultSet r = sentencia.executeQuery("SELECT * FROM visitas"); // Ejecutamos la sentencia
                while (r.next()) { // Recorremos los datos

                    //TODO fecha en MySQL?
                    LocalDateTime fecha = null;
                    if (bbdd != 3)
                        fecha = stringToLocalDateTime(r.getString(6));
                    else
                        fecha = oracleStringToLocalDateTime(r.getString(6));

                    visitas.add(new Visita(r.getInt(1),
                            cargarEmpleado(bbdd, conexion, r.getString(2)),
                            r.getString(3), r.getInt(4),
                            r.getString(5), fecha, r.getInt(7),
                            r.getFloat(8), r.getString(9),
                            r.getFloat(10)));
                }
                r.close(); // Cerrar ResultSet
                sentencia.close();// Cerrar Statement
                conexion.close(); // Cerrar Conexión

                if (visitas.size() <= 0)
                    System.out.println("No existen visitas en la BBDD");
                else
                    System.out.println("Visitas cargadas");

            } catch (SQLException throwables) {
                System.out.println("Error al cargar las visitas.");
            }
        }
        return visitas;
    }

    /**
     * Método que devuelve todas las visitas que ha organizado un empleado (guía).
     * Lo reutilizaremos con las 3 BBDD que usan lenguaje SQL
     *
     * @param bbdd <- recibe un int que determinará a qué BBDD nos conectaremos
     * @return <- devuelve un ArrayList con todas las visitas organizadas por el guía
     */
    public ArrayList<Visita> cargarVisitasEmpleado(int bbdd, String dni) {

        ArrayList<Visita> visitas = new ArrayList<>();
        Connection conexion = realizarConexion(bbdd);

        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                ResultSet r = sentencia.executeQuery("SELECT * FROM visitas WHERE guia='" + dni + "'"); // Ejecutamos la sentencia
                while (r.next()) { // Recorremos los datos

                    LocalDateTime fecha = null;
                    if (bbdd != 3)
                        fecha = stringToLocalDateTime(r.getString(6));
                    else
                        fecha = oracleStringToLocalDateTime(r.getString(6));

                    visitas.add(new Visita(r.getInt(1),
                            cargarEmpleado(bbdd, conexion, r.getString(2)),
                            r.getString(3), r.getInt(4),
                            r.getString(5), fecha, r.getInt(7),
                            r.getFloat(8), r.getString(9),
                            r.getFloat(10)));
                }
                r.close(); // Cerrar ResultSet
                sentencia.close();// Cerrar Statement
                conexion.close(); // Cerrar Conexión

                if (visitas.size() <= 0)
                    System.out.println("El empleado " + dni + " no ha organizado ninguna visita aún");
                else
                    System.out.println("Visitas organizadas por el empleado '" + dni + "' cargadas");

            } catch (SQLException throwables) {
                System.out.println("Error al cargar las visitas del empleado '" + dni + "'");
            }
        }
        return visitas;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////// VISITASCLIENTES
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Método que devuelve todas las visitas en las que ha participado un cliente.
     * Lo reutilizaremos con las 3 BBDD que usan lenguaje SQL
     *
     * @param bbdd <- recibe un int que determinará a qué BBDD nos conectaremos
     * @return <- devuelve un ArrayList con todas las visitas de un cliente cargadas
     */
    public ArrayList<Visita> cargarVisitasCliente(int bbdd, String dni) {

        ArrayList<Visita> visitasCliente = new ArrayList<>();
        Connection conexion = realizarConexion(bbdd);

        if (conexion != null) {
            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                ResultSet r = sentencia.executeQuery("SELECT * FROM visitasclientes WHERE cliente='" + dni + "'"); // Ejecutamos la sentencia
                while (r.next()) { // Recorremos los datos
                    Statement sentencia2 = conexion.createStatement();
                    ResultSet r2 = sentencia2.executeQuery("SELECT * FROM visitas WHERE cod=" + r.getInt(2));
                    while (r2.next()) {

                        LocalDateTime fecha = null;
                        if (bbdd != 3)
                            fecha = stringToLocalDateTime(r2.getString(6));
                        else
                            fecha = oracleStringToLocalDateTime(r2.getString(6));
                        visitasCliente.add(new Visita(r2.getInt(1),
                                cargarEmpleado(bbdd, conexion, r2.getString(2)),
                                r2.getString(3), r2.getInt(4),
                                r2.getString(5), fecha, r2.getInt(7),
                                r2.getFloat(8), r2.getString(9),
                                r2.getFloat(10)));
                    }
                    r2.close();
                    sentencia2.close();
                }
                r.close(); // Cerrar ResultSet
                sentencia.close();// Cerrar Statement
                conexion.close(); // Cerrar Conexión

                if (visitasCliente.size() <= 0)
                    System.out.println("El cliente '" + dni + "' no ha participado ni se ha apuntado en ninguna visita aún");
                else
                    System.out.println("Visitas en las que ha participado el cliente '" + dni + "' cargadas");

            } catch (SQLException throwables) {
                System.out.println("Error al cargar las visitas en las que ha participado '" + dni + "'");
                throwables.printStackTrace();
            }
        }
        return visitasCliente;
    }

    public ArrayList<Cliente> clientesApuntadosAVisita(int bbdd, int cod) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {

            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                ResultSet r = sentencia.executeQuery("SELECT cliente FROM visitasclientes WHERE visita=" + cod); // Ejecutamos la sentencia
                while (r.next()) { // Recorremos los datos
                    Statement sentencia2 = conexion.createStatement(); // Preparamos la sentencia
                    ResultSet r2 = sentencia2.executeQuery("SELECT * FROM clientes WHERE dni='" + r.getString(1) + "'");
                    while (r2.next()) {
                        clientes.add(new Cliente(r2.getString(1), r2.getString(2),
                                r2.getString(3), r2.getInt(4), r2.getString(5),
                                r2.getString(6)));
                    }
                    r2.close();
                    sentencia2.close();
                }
                r.close(); // Cerrar ResultSet
                sentencia.close();// Cerrar Statement
                conexion.close(); // Cerrar Conexión

            } catch (SQLException ignored) {
                ignored.printStackTrace();
            }
        }
        return clientes;
    }


    public StringBuilder clientesApuntados(int bbdd, int codVisita) {
        StringBuilder sb = new StringBuilder();

        ArrayList<Cliente> clientes = clientesApuntadosAVisita(bbdd, codVisita);
        if (clientes.size() > 0)
            sb.append("CLIENTES APUNTADOS A LA VISITA:\n\n");
        for (Cliente cliente : clientes) {
            sb.append("\t").append(cliente).append("\n");
        }

        return sb;
    }


    /**
     * Devuelve el número de clientes apuntados a la visita (útil para calcular el dinero que ha ganado cada visita)
     *
     * @param bbdd <- int que controlará a que base de datos conectarse
     * @param cod <- codigo de la visita para saber cuantos clientes apuntados hay
     * @return numClientes <- int con el número de clientes apuntados
     */
    public int numClientesApuntadosAVisita(int bbdd, int cod) {
        int numClientes = 0;
        Connection conexion = realizarConexion(bbdd);
        if (conexion != null) {

            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                ResultSet r = sentencia.executeQuery("SELECT * FROM visitasclientes WHERE visita=" + cod); // Ejecutamos la sentencia
                while (r.next()) { // Recorremos los datos
                    numClientes++;
                }
                r.close(); // Cerrar ResultSet
                sentencia.close();// Cerrar Statement
                conexion.close(); // Cerrar Conexión

            } catch (SQLException ignored) {
                ignored.printStackTrace();
            }
        }
        return numClientes;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////// REGISTROS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<RegistroEmpleado> cargarRegistrosEmpleados(int bbdd) {
        ArrayList<RegistroEmpleado> registrosEmpleados = new ArrayList<>();
        Connection conexion = realizarConexion(bbdd);
        boolean datos = false;

        if (conexion != null) {
            System.out.println("Todos los registros de los empleados: ");

            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                ResultSet r = sentencia.executeQuery("SELECT * FROM registrosEmpleados ORDER BY fecha DESC"); // Ejecutamos la sentencia
                while (r.next()) { // Recorremos los datos
                    datos = true;
                    // cod int, empleado (dni) string, fecha date, registro string
                    LocalDateTime fecha = stringToLocalDateTime(r.getString(3));
                    String fechaStr = localDateTimeToString(fecha);

                    System.out.format("%5d, %s | %s | %s\n", r.getInt(1), fechaStr, r.getString(2), r.getString(4));
                    Empleado emp = cargarEmpleado(bbdd, conexion, r.getString(2));
                    RegistroEmpleado regEmp = new RegistroEmpleado(r.getInt(1), emp, fecha, r.getString(4));
                    registrosEmpleados.add(regEmp);
                }
                r.close(); // Cerrar ResultSet
                sentencia.close();// Cerrar Statement
                conexion.close(); // Cerrar Conexión

                if (!datos)
                    System.out.println("Aún no existen registros de ningún empleado");

            } catch (SQLException throwables) {
                System.out.println("Error al cargar los registros de los empleados");
            }
        }
        return registrosEmpleados;
    }

    public ArrayList<RegistroCliente> cargarRegistrosClientes(int bbdd) {
        ArrayList<RegistroCliente> registrosClientes = new ArrayList<>();
        Connection conexion = realizarConexion(bbdd);
        boolean datos = false;

        if (conexion != null) {
            System.out.println("Todos los registros de los clientes: ");

            try {
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                ResultSet r = sentencia.executeQuery("SELECT * FROM registrosClientes ORDER BY fecha DESC"); // Ejecutamos la sentencia
                while (r.next()) { // Recorremos los datos
                    datos = true;
                    // cod int, empleado (dni) string, fecha date, registro string
                    LocalDateTime fecha = stringToLocalDateTime(r.getString(3));
                    String fechaStr = localDateTimeToString(fecha);

                    System.out.format("%5d, %s | %s | %s\n", r.getInt(1), fechaStr, r.getString(2), r.getString(4));
                    Cliente cli = cargarCliente(conexion, r.getString(2));
                    RegistroCliente regCli = new RegistroCliente(r.getInt(1), cli, fecha, r.getString(4));
                    registrosClientes.add(regCli);
                }
                r.close(); // Cerrar ResultSet
                sentencia.close();// Cerrar Statement
                conexion.close(); // Cerrar Conexión

                if (!datos)
                    System.out.println("Aún no existen registros de ningún cliente");

            } catch (SQLException throwables) {
                System.out.println("Error al cargar los registros de los clientes");
            }
        }
        return registrosClientes;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////// MISCELÁNEA (VARIOS)
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Método particular para realizar las pruebas de funcionamiento, recibiremos un Objeto visita entero donde el
     * único dato que le falta es el código, por lo que buscaremos ese objeto y obtendremos su código
     *
     * @param bbdd <- recibe un int que determinará a qué BBDD nos conectaremos
     * @param vis <- el objeto que usaremos para recoger su código
     * @return <- el código que necesitamos
     */
    public int cargarCodigoVisita(int bbdd, Visita vis) {
        int codigo = 0;
        Connection conexion = realizarConexion(bbdd);
        boolean datos = false;

        if (conexion != null) {
            try {
                String sql;
                if (bbdd == 3) {
                    String fecha = vis.getFecha().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    sql = "SELECT cod FROM visitas WHERE " +
                            "guia='" + vis.getGuia().getDni() + "' AND " +
                            "nombre='" + vis.getNombre() + "' AND " +
                            "numMaxClientes=" + vis.getNumMaxClientes() + " AND " +
                            "puntoPartida='" + vis.getPuntoPartida() + "' AND " +
                            "anyo=" + vis.getAnyo() + " AND " +
                            "duracionEstimada=" + vis.getDuracionEstimada() + " AND " +
                            "tematica='" + vis.getTematica() + "' AND " +
                            "coste=" + vis.getCoste();
                } else {
                    sql = "SELECT cod FROM visitas WHERE " +
                            "guia='" + vis.getGuia().getDni() + "' AND " +
                            "nombre='" + vis.getNombre() + "' AND " +
                            "numMaxClientes=" + vis.getNumMaxClientes() + " AND " +
                            "puntoPartida='" + vis.getPuntoPartida() + "' AND " +
                            "fecha='" + vis.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "' AND " +
                            "anyo=" + vis.getAnyo() + " AND " +
                            "duracionEstimada=" + vis.getDuracionEstimada() + " AND " +
                            "tematica='" + vis.getTematica() + "' AND " +
                            "coste=" + vis.getCoste();
                }
                Statement sentencia = conexion.createStatement(); // Preparamos la sentencia
                ResultSet r = sentencia.executeQuery(sql); // Ejecutamos la sentencia
                while (r.next()) { // Recorremos los datos
                    datos = true;
                    codigo = r.getInt(1);
                }
                r.close(); // Cerrar ResultSet
                sentencia.close();// Cerrar Statement
                conexion.close(); // Cerrar Conexión

                if (!datos)
                    System.out.println("No se ha encontrado la visita en cuestión");

            } catch (SQLException throwables) {
                System.out.println("Error al cargar el código concreto");
                throwables.printStackTrace();
            }
        }
        return codigo;
    }

}
