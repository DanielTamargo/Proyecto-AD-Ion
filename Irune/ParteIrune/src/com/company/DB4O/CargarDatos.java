package com.company.DB4O;

import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;
import com.company.Modelo.VisitaCliente;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class CargarDatos {

   public CargarDatos(){}

    /**
     * Método que devuelve todos los empleados
     *
     */
    public void mostrarEmpleados() {
        ObjectContainer db = new Conexion().conectarBD();

        System.out.println("\n ---- EMPLEADOS ---- ");
        Empleado emp = new Empleado();
        ObjectSet<Empleado> resultadoEmp = db.queryByExample(emp);

        if (resultadoEmp.size() == 0) {
            System.out.println("No hay empleados en la BD");
        }
        while (resultadoEmp.hasNext()) {
            Empleado empl = resultadoEmp.next();
            System.out.println(empl); //txtArea.setText(empl);
        }
    }

    /**
     * Método que devuelve un solo empleado
     *
     * @param emple <- recibe un empleado para luego mostrar sus datos
     */
    public void mostrarEmpleado(Empleado emple){
        ObjectContainer db = new Conexion().conectarBD();

        ObjectSet<Empleado> resultadoEmp = db.queryByExample(emple);

        if (resultadoEmp.size() == 0) {
            System.out.println("No existe ese empleado en la BD");
        }
        while (resultadoEmp.hasNext()) {
            Empleado empl = resultadoEmp.next();
            System.out.println(empl); //txtArea.setText(empl);
        }
    }

    /**
     * Método que devuelve todos los clientes
     *
     */
    public void mostrarClientes() {
        ObjectContainer db = new Conexion().conectarBD();

        System.out.println("\n ---- CLIENTES ---- ");
        Cliente cli = new Cliente();
        ObjectSet<Cliente> resultadoCli = db.queryByExample(cli);

        if (resultadoCli.size() == 0) {
            System.out.println("No hay clientes en la BD");
        }
        while (resultadoCli.hasNext()) {
            Cliente cliente = resultadoCli.next();
            System.out.println(cliente);
        }
    }

    /**
     * Método que devuelve un solo cliente
     *
     * @param cli <- recibe un empleado para luego mostrar sus datos
     */
    public void mostrarCliente(Cliente cli) {
        ObjectContainer db = new Conexion().conectarBD();

        ObjectSet<Cliente> resultadoCli = db.queryByExample(cli);

        if (resultadoCli.size() == 0) {
            System.out.println("No existe ese cliente en la BD");
        }
        while (resultadoCli.hasNext()) {
            Cliente cliente = resultadoCli.next();
            System.out.println(cliente);
        }
    }

    /**
     * Método que devuelve todas las visitas
     *
     */
    public void mostrarVisitas() {
        ObjectContainer db = new Conexion().conectarBD();

        System.out.println("\n ---- VISITAS ---- ");
        Visita visita = new Visita();
        ObjectSet<Visita> resultadoVisita = db.queryByExample(visita);

        if (resultadoVisita.size() == 0) {
            System.out.println("No hay visitas en la BD");
        }
        while (resultadoVisita.hasNext()) {
            Visita visit = resultadoVisita.next();
            System.out.println(visit);
        }
    }

    /**
     * Método que devuelve todas las visitas de los clientes
     *
     */
    public void mostrarVisitasClientes() {
        ObjectContainer db = new Conexion().conectarBD();

        System.out.println("\n ---- VISITAS CLIENTES ---- ");
        VisitaCliente visitaCliente = new VisitaCliente();
        ObjectSet<VisitaCliente> resultadoVisitaCliente = db.queryByExample(visitaCliente);

        if (resultadoVisitaCliente.size() == 0) {
            System.out.println("No hay visitas de clientes en la BD");
        }
        while (resultadoVisitaCliente.hasNext()) {
            VisitaCliente visitCli = resultadoVisitaCliente.next();
            System.out.println(visitCli);
        }
    }
}
