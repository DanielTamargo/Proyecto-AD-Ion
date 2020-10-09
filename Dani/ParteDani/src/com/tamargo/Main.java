package com.tamargo;

import com.tamargo.Modelo.Empleado;
import com.tamargo.sql.CargarDatos;
import com.tamargo.sql.Conexiones;

import java.text.ParseException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //new Conexiones().mostrarErrorAlConectar(1);

        ArrayList<Empleado> empleados = new CargarDatos().cargarEmpleados(1);
        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }
}
