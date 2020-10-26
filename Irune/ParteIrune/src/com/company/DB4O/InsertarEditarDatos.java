package com.company.DB4O;

import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;
import com.company.Modelo.VisitaCliente;
import com.db4o.ObjectContainer;

public class InsertarEditarDatos {

    public InsertarEditarDatos() { }

    /**
     * Método utilizado para insertar o editar un nuevo empleado
     * Se añadirá a la lista de empleados, y al registro de la BD
     *
     * @param emple <- objeto de tipo Empleado con todos los datos a insertar o editar
     */
    public void insertarEditarEditarEmpleado(Empleado emple) {
        ObjectContainer bd = new Conexion().conectarBD();
        bd.store(emple);
        bd.close();
    }

    /**
     * Método utilizado para insertar o editar un nuevo cliente
     * Se añadirá a la lista de clientes, y al registro de la BD
     *
     * @param cli <- objeto de tipo Cliente con todos los datos a insertar
     */
    public void insertarEditarCliente(Cliente cli) {
        ObjectContainer bd = new Conexion().conectarBD();
        bd.store(cli);
        bd.close();
    }

    /**
     * Método utilizado para insertar o editar una nueva visita
     * Se añadirá a la lista de visitas, y al registro de la BD
     *
     * @param visita <- objeto de tipo Visita con todos los datos a insertar o editar
     */
    public void insertarEditarVisitas(Visita visita) {
        ObjectContainer bd = new Conexion().conectarBD();
        bd.store(visita);
        bd.close();
    }

    /**
     * Método utilizado para insertar o editar una nueva visita del cliente
     * Se añadirá a la lista de visitasCliente, y al registro de la BD
     *
     * @param visitaCliente <- objeto de tipo VisitaCliente con todos los datos a insertar o editar
     */
    public void insertarVisitaCliente(VisitaCliente visitaCliente) {
        ObjectContainer bd = new Conexion().conectarBD();
        bd.store(visitaCliente);
        bd.close();
    }
}
