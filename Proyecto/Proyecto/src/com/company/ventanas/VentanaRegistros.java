package com.company.ventanas;

import com.company.DB4O.CargarDatosDB4O;
import com.company.modelo.RegistroCliente;
import com.company.modelo.RegistroEmpleado;
import com.company.sql.CargarDatos;
import com.company.varios.ModeloTableRegistros;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaRegistros {
    private JPanel panel;
    private JButton b_volver;
    private JTable table1;
    private JButton b_empleados;
    private JButton b_clientes;

    private JFrame ventanaRegistros;
    private JFrame ventanaSecundaria;

    private boolean empleados = true;
    private int bbdd = 1;

    public VentanaRegistros() {


        b_volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaRegistros.dispose();
                ventanaSecundaria.setVisible(true);
            }
        });
        b_clientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empleados = false;
                actualizarDatosJTable();
            }
        });
        b_empleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empleados = true;
                actualizarDatosJTable();
            }
        });
    }

    public void actualizarDatosJTable() {
        ArrayList<RegistroEmpleado> registrosEmpleados = null;
        ArrayList<RegistroCliente> registrosClientes = null;
        if (empleados) {
            registrosEmpleados = new ArrayList<>();
            if (bbdd != 4)
                registrosEmpleados = new CargarDatos().cargarRegistrosEmpleados(bbdd);
            else
                registrosEmpleados = new CargarDatosDB4O().cargarRegistrosEmpleados();
        } else {
            registrosClientes = new ArrayList<>();
            if (bbdd != 4)
                registrosClientes = new CargarDatos().cargarRegistrosClientes(bbdd);
            else
                registrosClientes = new CargarDatosDB4O().cargarRegistrosClientes();
        }

        ModeloTableRegistros mtr = new ModeloTableRegistros(registrosEmpleados, registrosClientes);
        table1.setModel(mtr);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resizeColumnWidth(table1);
    }

    public int[] widths = { 50, 80, 130, 512 };

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = widths[column];
            if (column == 3)
                for (int i = 0; i < widths.length - 1; i++) {
                    width -= widths[i];
                }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setBbdd(int bbdd) {
        this.bbdd = bbdd;
    }

    public void setVentanaRegistros(JFrame ventanaRegistros) {
        this.ventanaRegistros = ventanaRegistros;
    }

    public void setVentanaSecundaria(JFrame ventanaSecundaria) {
        this.ventanaSecundaria = ventanaSecundaria;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registros");
        frame.setContentPane(new VentanaRegistros().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
