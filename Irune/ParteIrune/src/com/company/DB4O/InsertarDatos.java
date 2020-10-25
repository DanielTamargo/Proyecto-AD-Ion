package com.company.DB4O;

import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;
import com.company.Modelo.VisitaCliente;
import com.db4o.ObjectContainer;

public class InsertarDatos {

    //public ObjectContainer bd = new Conexion().conectarBD();

    public InsertarDatos(){}

    /**
     * Método utilizado para insertar un nuevo empleado
     * Se añadirá a la lista de empleados, y al registro de la BD
     *
     * @param emple <- objeto de tipo Empleado con todos los datos a insertar
     */
    public void insertarEmpleado(Empleado emple){
        ObjectContainer bd = new Conexion().conectarBD();
        new InsertarDatosBase().empleados.add(emple);
        bd.store(emple);
    }

    /**
     * Método utilizado para insertar un nuevo cliente
     * Se añadirá a la lista de clientes, y al registro de la BD
     *
     * @param cli <- objeto de tipo Cliente con todos los datos a insertar
     */
    public void insertarCliente(Cliente cli){
        ObjectContainer bd = new Conexion().conectarBD();
        new InsertarDatosBase().clientes.add(cli);
        bd.store(cli);
        bd.close();
    }

    /**
     * Método utilizado para insertar una nueva visita
     * Se añadirá a la lista de visitas, y al registro de la BD
     *
     * @param visita <- objeto de tipo Visita con todos los datos a insertar
     */
    public void insertarVisitas(Visita visita){
        ObjectContainer bd = new Conexion().conectarBD();
        new InsertarDatosBase().visitas.add(visita);
        bd.store(visita);
        bd.close();
    }

    /**
     * Método utilizado para insertar una nueva visita del cliente
     * Se añadirá a la lista de visitasCliente, y al registro de la BD
     *
     * @param visitaCliente <- objeto de tipo VisitaCliente con todos los datos a insertar
     */
    public void insertarVisitaCliente(VisitaCliente visitaCliente){
        ObjectContainer bd = new Conexion().conectarBD();
        new InsertarDatosBase().visitasClientes.add(visitaCliente);
        bd.store(visitaCliente);
        bd.close();

    }
}
