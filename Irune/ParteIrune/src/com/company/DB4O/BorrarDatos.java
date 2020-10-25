package com.company.DB4O;

import com.company.Modelo.Visita;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class BorrarDatos {

    //public static ObjectContainer db = new Conexion().conectarBD();

    public static void main(String[] args) {

        /*Visita prueba = new InsertarDatosBase().visitas.get(2);
        System.out.println(prueba);
        eliminarVisita(prueba);*/
    }
    public BorrarDatos(){}

    public static void eliminarVisita(Visita visita){
        //Conectamos con la DB .yap

        ObjectContainer db = new Conexion().conectarBD();
        //Coger el objeto entero de lo que se seleccione en el JList
        //Visita visita1 = new Visita();
        ObjectSet<Visita> result = db.queryByExample(visita);

        if (result.size() == 0) {
            System.out.println("No existe esa visita en la BD");
        } else {
            while (result.hasNext()) {
                Visita v = result.next();
                db.delete(v);
                System.out.print("La visita se ha borrado con éxito");
            }
        }
    }
}
