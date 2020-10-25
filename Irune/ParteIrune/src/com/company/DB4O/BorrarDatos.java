package com.company.DB4O;

import com.company.Modelo.Cliente;
import com.company.Modelo.Empleado;
import com.company.Modelo.Visita;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.ArrayList;

public class BorrarDatos {

    //public static ObjectContainer db = new Conexion().conectarBD();

    public static void main(String[] args) {

    }

    public BorrarDatos(){}

    /**
     * Método utilizado para eliminar un cliente
     * Se eliminará de la BD y de la lista
     *
     * @param emple <- objeto de tipo Visita con todos los datos a insertar
     */
    public void eliminarEmpleado(Empleado emple){

        ObjectContainer bd = new Conexion().conectarBD();

        ObjectSet<Empleado> result = bd.queryByExample(emple);

        if (result.size() == 0) {
            System.out.println("No existe ese empleado en la BD");
        } else {
            while (result.hasNext()) {
                Empleado e = result.next();
                bd.delete(e);

                ArrayList<Empleado> empleados = new InsertarDatosBase().empleados;
                empleados.removeIf(empl -> empl.equals(emple));  //Elimina la visita de la lista de visitas
                System.out.print("El empleado se ha borrado con éxito");
            }
        }

        bd.close();
    }
    public void eliminarCliente(Cliente cliente){

        ObjectContainer bd = new Conexion().conectarBD();

        ObjectSet<Cliente> result = bd.queryByExample(cliente);

        if (result.size() == 0) {
            System.out.println("No existe ese cliente en la BD");
        } else {
            while (result.hasNext()) {
                Cliente c = result.next();
                bd.delete(c);

                ArrayList<Cliente> clientes = new InsertarDatosBase().clientes;
                clientes.removeIf(cli -> cli.equals(cliente));  //Elimina la visita de la lista de visitas
                System.out.print("El empleado se ha borrado con éxito");
            }
        }
        bd.close();
    }

    public void eliminarVisita(Visita visita){
        //Conectamos con la DB .yap

        ObjectContainer bd = new Conexion().conectarBD();
        //Coger el objeto entero de lo que se seleccione en el JList
        //Visita visita1 = new Visita();
        ObjectSet<Visita> result = bd.queryByExample(visita);

        if (result.size() == 0) {
            System.out.println("No existe esa visita en la BD");
        } else {
            while (result.hasNext()) {
                Visita v = result.next();
                bd.delete(v);

                ArrayList<Visita> visitas = new InsertarDatosBase().visitas;
                visitas.removeIf(vis -> vis.equals(visita)); //Elimina la visita de la lista de visitas
                System.out.print("La visita se ha borrado con éxito");
            }
        }
        bd.close();
    }
}
