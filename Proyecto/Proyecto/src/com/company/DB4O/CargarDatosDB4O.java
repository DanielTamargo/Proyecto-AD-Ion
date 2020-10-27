package com.company.DB4O;

import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;
import com.company.Modelo.VisitaCliente;
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
     * Método que devuelve todas las visitas
     *
     * @return <- devuelve un ArrayList con todos las visitas cargados
     */
    public ArrayList<Visita> cargarVisitas() {
        ArrayList<Visita> visitas = new ArrayList<>();

        ObjectContainer bd = new ConexionDB4O().conectarBD();
        if (bd != null) {
            try {
                Visita emp = new Visita();
                ObjectSet<Visita> resultadoVis = bd.queryByExample(emp);

                if (resultadoVis.size() == 0) {
                    System.out.println("No existen visitas en la BBDD");
                }
                while (resultadoVis.hasNext()) {
                    Visita vis = resultadoVis.next();
                    visitas.add(vis);
                }
            } catch (Db4oException ex) {
                System.out.println("Error al cargar las visitas");
            }
            bd.close();
        }
        return visitas;
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
                VisitaCliente emp = new VisitaCliente();
                ObjectSet<VisitaCliente> resultadoVisCli = bd.queryByExample(emp);

                if (resultadoVisCli.size() == 0) {
                    System.out.println("No existen visitas de clientes en la BBDD");
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
}

