package com.company.DB4O;

import com.company.Modelo.*;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;

import java.util.ArrayList;

public class CargarDatosDB4O {

    public CargarDatosDB4O() { }

    /**
     * Método que devuelve todos los empleados
     *
     * @return <- devuelve un ArrayList con todos los empleados cargados
     */
    public ArrayList<Empleado> cargarEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<>();

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                Empleado emp = new Empleado();
                ObjectSet<Empleado> resultadoEmp = bd.queryByExample(emp);

                if (resultadoEmp.size() == 0) {
                    System.out.println("No existen empleados en la BBDD");
                }
                while (resultadoEmp.hasNext()) {
                    Empleado empl = resultadoEmp.next();
                    empleados.add(empl);
                }
            } catch (Db4oException ex) {
                System.out.println("Error al cargar los empleados");
            }
            bd.close();
        }
        return empleados;
    }

    /**
     * Método que devuelve los datos de un empleado al iniciar sesión (solo si el usuario y contraseña son correctos
     *
     * @param dni <- dni del usuario
     * @param contrasenya <- contraseña del usuario
     * @return emp <- devuelve los datos del empleado si ha iniciado sesión correctamente
     */
    public Empleado iniciarSesionEmpleado(String dni, String contrasenya) {
        Empleado emp = null;

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                Empleado empQuery = new Empleado(dni, contrasenya);
                ObjectSet<Empleado> resultadoEmp = bd.queryByExample(empQuery);

                while (resultadoEmp.hasNext()) {
                    emp = resultadoEmp.next();
                }

            } catch (Db4oException ignored) { }
            bd.close();
        }

        return emp;
    }

    /**
     * Método que devuelve todos los clientes
     *
     * @return <- devuelve un ArrayList con todos los clientes cargados
     */
    public ArrayList<Cliente> cargarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                Cliente emp = new Cliente();
                ObjectSet<Cliente> resultadoCli = bd.queryByExample(emp);

                if (resultadoCli.size() == 0) {
                    System.out.println("No existen clientes en la BBDD");
                }
                while (resultadoCli.hasNext()) {
                    Cliente cli = resultadoCli.next();
                    clientes.add(cli);
                }
            } catch (Db4oException ex) {
                System.out.println("Error al cargar los clientes");
            }
            bd.close();
        }
        return clientes;
    }


    /**
     * Método que devuelve los datos de un cliente al iniciar sesión (solo si el usuario y contraseña son correctos
     *
     * @param dni <- dni del usuario
     * @param contrasenya <- contraseña del usuario
     * @return cli <- devuelve los datos del cliente si ha iniciado sesión correctamente
     */
    public Cliente iniciarSesionCliente(String dni, String contrasenya) {
        Cliente cli = null;

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                Cliente cliQuery = new Cliente(dni, contrasenya);
                ObjectSet<Cliente> resultadoCli = bd.queryByExample(cliQuery);

                while (resultadoCli.hasNext()) {
                    cli = resultadoCli.next();
                }

            } catch (Db4oException ignored) { }
            bd.close();
        }

        return cli;
    }

    /**
     * Método que devuelve todas las visitas
     *
     * @return <- devuelve un ArrayList con todos las visitas cargados
     */
    public ArrayList<Visita> cargarVisitas() {
        ArrayList<Visita> visitas = new ArrayList<>();

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                Visita vis = new Visita();
                ObjectSet<Visita> resultadoVis = bd.queryByExample(vis);

                if (resultadoVis.size() == 0) {
                    System.out.println("No existen visitas en la BBDD");
                }
                while (resultadoVis.hasNext()) {
                    Visita v = resultadoVis.next();
                    visitas.add(v);
                }
            } catch (Db4oException ex) {
                System.out.println("Error al cargar las visitas");
            }
            bd.close();
        }
        return visitas;
    }

    /**
     * Método que devuelve todas las visitas de un empleado
     *
     * @return <- devuelve un ArrayList con todos las visitas de un empleado cargados
     */
    public ArrayList<Visita> cargarVisitasEmpleado(Empleado emp) {
        ArrayList<Visita> visitas = new ArrayList<>();

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                Visita vis = new Visita(emp);
                ObjectSet<Visita> resultadoVis = bd.queryByExample(vis);

                if (resultadoVis.size() == 0) {
                    System.out.println("El empleado '" + emp.getDni() + "' no ha organizado ninguna visita aún");
                }
                while (resultadoVis.hasNext()) {
                    Visita v = resultadoVis.next();
                    visitas.add(v);
                }
            } catch (Db4oException ex) {
                System.out.println("Error al cargar las visitas del empleado '" + emp.getDni() + "'");
            }
            bd.close();
        }
        return visitas;
    }

    public ArrayList<VisitaCliente> cargarVisitasCliente(Cliente cli) {
        ArrayList<VisitaCliente> visitasClientes = new ArrayList<>();

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                VisitaCliente visCliQuery = new VisitaCliente(cli);
                ObjectSet<VisitaCliente> resultadoVisCli = bd.queryByExample(visCliQuery);

                if (resultadoVisCli.size() == 0) {
                    System.out.println("El cliente no ha participado en ninguna visita");
                }
                while (resultadoVisCli.hasNext()) {
                    VisitaCliente visCli = resultadoVisCli.next();
                    visitasClientes.add(visCli);
                }
            } catch (Db4oException ex) {
                System.out.println("Error al cargar las visitas de los clientes");
            }
            bd.close();
        }
        return visitasClientes;
    }

    /**
     * Método que devuelve todas las visitas de los clientes
     *
     * @return <- devuelve un ArrayList con todos los empleados cargados
     */
    public ArrayList<VisitaCliente> cargarVisitasClientes() {
        ArrayList<VisitaCliente> visitasClientes = new ArrayList<>();

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                VisitaCliente visCliQuery = new VisitaCliente();
                ObjectSet<VisitaCliente> resultadoVisCli = bd.queryByExample(visCliQuery);

                if (resultadoVisCli.size() == 0) {
                    System.out.println("El cliente no ha participado en ninguna visita");
                }
                while (resultadoVisCli.hasNext()) {
                    VisitaCliente visCli = resultadoVisCli.next();
                    visitasClientes.add(visCli);
                }
            } catch (Db4oException ex) {
                System.out.println("Error al cargar las visitas de los clientes");
            }
            bd.close();
        }
        return visitasClientes;
    }

    public int numClientesApuntadosAVisita(Visita vis) {
        int numClientes = 0;

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                VisitaCliente visCliQuery = new VisitaCliente(vis);
                ObjectSet<VisitaCliente> resultadoVisCli = bd.queryByExample(visCliQuery);
                while (resultadoVisCli.hasNext()) {
                    numClientes++;
                }
            } catch (Db4oException ignored) { }
            bd.close();
        }

        return numClientes;
    }

    public int cargarNumClientesApuntadosAVisitaDelEmpleado(Visita v) {
        int numClientes = 0;
        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            VisitaCliente visCliQuery = new VisitaCliente(v);
            ObjectSet<VisitaCliente> resultadoVisCli = bd.queryByExample(visCliQuery);
            while (resultadoVisCli.hasNext()) {
                numClientes++;
            }
            bd.close();
        }

        return numClientes;
    }


    public ArrayList<RegistroEmpleado> cargarRegistrosEmpleados() {
        ArrayList<RegistroEmpleado> registroEmpleados = new ArrayList<>();

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                RegistroEmpleado regQuery = new RegistroEmpleado();
                ObjectSet<RegistroEmpleado> result = bd.queryByExample(regQuery);

                while (result.hasNext()) {
                    RegistroEmpleado reg = result.next();
                    registroEmpleados.add(reg);
                }
            } catch (Db4oException ignored) { }
            bd.close();
        }
        return registroEmpleados;
    }

    public ArrayList<RegistroCliente> cargarRegistrosClientes() {
        ArrayList<RegistroCliente> registroClientes = new ArrayList<>();

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                RegistroCliente regQuery = new RegistroCliente();
                ObjectSet<RegistroCliente> result = bd.queryByExample(regQuery);

                while (result.hasNext()) {
                    RegistroCliente reg = result.next();
                    registroClientes.add(reg);
                }
            } catch (Db4oException ignored) { }
            bd.close();
        }
        return registroClientes;
    }

}

