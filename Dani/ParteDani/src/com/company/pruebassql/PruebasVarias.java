package com.company.pruebassql;

import com.company.Modelo.Empleado;
import com.company.sql.CargarDatos;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PruebasVarias {

    public static void main(String[] args) {

        ArrayList<Empleado> empleados = new CargarDatos().cargarEmpleados(1);
        Empleado emple = empleados.get(0);
        String fechaNac = emple.getFechaNac().toString();
        String fechaContr = emple.getFechaContratacion().toString();
        System.out.println(fechaNac);
        System.out.println(fechaContr);

    }

}
