package com.company.varios;

import com.company.modelo.RegistroCliente;
import com.company.modelo.RegistroEmpleado;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ModeloTableRegistros extends AbstractTableModel {

    private String[] columnas;
    private ArrayList<RegistroEmpleado> registrosEmpleados = null;
    private ArrayList<RegistroCliente> registrosClientes = null;

    public ModeloTableRegistros(ArrayList<RegistroEmpleado> registrosEmpleados, ArrayList<RegistroCliente> registrosClientes) {
        this.registrosEmpleados = registrosEmpleados;
        this.registrosClientes = registrosClientes;

        if (this.registrosEmpleados == null)
            columnasCli();
        else
            columnasEmp();
    }


    @Override
    public int getRowCount() {
        if (registrosEmpleados == null)
            return registrosClientes.size();
        else
            return  registrosEmpleados.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        int cod;
        String dni;
        String fechaFormateada;
        String registro;

        try {
            if (registrosEmpleados == null) {
                RegistroCliente regCli = registrosClientes.get(rowIndex);
                cod = regCli.getCod();
                dni = regCli.getCliente().getDni();
                fechaFormateada = regCli.getFecha().format(formatter);
                registro = regCli.getRegistro();
            } else {
                RegistroEmpleado regEmp = registrosEmpleados.get(rowIndex);
                cod = regEmp.getCod();
                dni = regEmp.getEmpleado().getDni();
                fechaFormateada = regEmp.getFecha().format(formatter);
                registro = regEmp.getRegistro();
            }

            return switch (columnIndex) {
                case 0 -> String.valueOf(cod);
                case 1 -> dni;
                case 2 -> fechaFormateada;
                default -> registro;
            };
        } catch (NullPointerException ignored) {
            return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }



    public void columnasEmp() {
        columnas = new String[]{"Cod", "Empleado", "Fecha", "Registro"};
    }

    public void columnasCli() {
        columnas = new String[]{"Cod", "Cliente", "Fecha", "Registro"};
    }

}
