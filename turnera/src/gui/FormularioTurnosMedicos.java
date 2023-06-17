package gui;

import dao.DAOException;
import dao.DAOMedico;
import dao.DAOTurno;
import entidades.Turno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FormularioTurnosMedicos {
    DAOTurno daoTurno;
    DefaultTableModel model;
    JPanel formularioTurnosMedicos;
    PanelManager panel;
    ArrayList<Turno> listaTurnos;

    public FormularioTurnosMedicos(PanelManager panel,String fecha,int legajo) throws DAOException {
        this.panel=panel;
        creadorFormularioTurnosMedicos(fecha, legajo);
    }

    public void creadorFormularioTurnosMedicos(String fecha,int legajo) throws DAOException {
        formularioTurnosMedicos = new JPanel();
        daoTurno = new DAOTurno();
        formularioTurnosMedicos.setLayout(new GridLayout(1,1));
        model = new DefaultTableModel();
        model.addColumn("dni paciente");
        model.addColumn("hora");
        model.addColumn("costo");
        ArrayList<Turno> turnos = daoTurno.buscarTurnos(fecha, legajo);
        for (Turno turno : turnos) {
            model.addRow(new Object[]{turno.getDniPaciente(), turno.getHora(), turno.getCosto()});
        }
        formularioTurnosMedicos.add(new JScrollPane(new JTable(model)));
    }
    public JPanel getFormularioTurnosMedicos() {
        return formularioTurnosMedicos;
    }
}