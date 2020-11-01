package com.company.DB4O;

import com.company.modelo.*;
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

    /**
     * Método que devuelve un arraylist con todas las visitasclientes de un cliente
     *
     * @param cli <- cliente del cual se quiere conocer sus visitas
     * @return visitasClientes <- arraylist con todas las visitasclientes del cliente
     */
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

    /**
     * Método que devuelve el número de clientes apuntados a una visita
     *
     * error: aparentemente produce un bucle infinito, desconocemos por qué, sospechamos que es porque el hash se realiza
     * con dos objetos enteros y produce conflicto por lo que recorre miles de resultados cuando deberían ser unos pocos
     *
     * @param vis <- visita de la cual se quiere saber el número de clientes
     * @return numClientes <- el número de clientes apuntados
     */
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

    /**
     * Devuelve un StringBuilder con las líneas a mostrar de los clientes apuntados a una visita
     *
     * @param v <- visita de la cual se quieren recoger los clientes apuntados
     * @return sb <- StrinBuilder con las líneas de texto
     */
    public StringBuilder cargarClientesApuntados(Visita v) {
        StringBuilder sb = new StringBuilder();

        ArrayList<Cliente> clientes = cargarClientes();
        ArrayList<VisitaCliente> visitaClientes = cargarVisitasClientes();
        ArrayList<Cliente> clientesApuntados = new ArrayList<>();

        for (Cliente cliente : clientes) {
            for (VisitaCliente visitaCliente : visitaClientes) {
                if (cliente.getDni().equalsIgnoreCase(visitaCliente.getCliente().getDni())
                        && visitaCliente.getVisita().getCod() == v.getCod()) {
                    clientesApuntados.add(cliente);
                    break;
                }
            }
        }

        if (clientesApuntados.size() > 0)
            sb.append("CLIENTES APUNTADOS A LA VISITA:\n\n");
        for (Cliente clienteApuntado : clientesApuntados) {
            sb.append("\t").append(clienteApuntado).append("\n");
        }

        return sb;
    }

    /**
     * Método que devuelve un array list con todos los registros de todos los empleados
     *
     * @return registrosEmpleados <- devuelve los registros de los empleados
     */
    public ArrayList<RegistroEmpleado> cargarRegistrosEmpleados() {
        ArrayList<RegistroEmpleado> registrosEmpleados = new ArrayList<>();

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                RegistroEmpleado regQuery = new RegistroEmpleado();
                ObjectSet<RegistroEmpleado> result = bd.queryByExample(regQuery);

                while (result.hasNext()) {
                    RegistroEmpleado reg = result.next();
                    registrosEmpleados.add(reg);
                }
            } catch (Db4oException ignored) { }
            bd.close();
        }
        return registrosEmpleados;
    }

    /**
     * Método que devuelve un array list con todos los registros de todos los clientes
     *
     * @return registrosClientes <- devuelve los registros de los clientes
     */
    public ArrayList<RegistroCliente> cargarRegistrosClientes() {
        ArrayList<RegistroCliente> registrosClientes = new ArrayList<>();

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                RegistroCliente regQuery = new RegistroCliente();
                ObjectSet<RegistroCliente> result = bd.queryByExample(regQuery);

                while (result.hasNext()) {
                    RegistroCliente reg = result.next();
                    registrosClientes.add(reg);
                }
            } catch (Db4oException ignored) { }
            bd.close();
        }
        return registrosClientes;
    }

}

