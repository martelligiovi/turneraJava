package gui;

import dao.DAOException;
import dao.DAOTurno;
import entidades.Turno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FormularioTurnosPaciente implements DecorarFormulario{
    DAOTurno daoTurno;
    DefaultTableModel model;
    JPanel formularioTurnosPaciente;
    PanelManager panel;
    ArrayList<Turno> listaTurnos;
    public FormularioTurnosPaciente(PanelManager panel,int dni) throws DAOException {
        this.panel=panel;
        creadorFormulario(dni);
        decorar();
    }
    public void creadorFormulario(int dni) throws DAOException {
        formularioTurnosPaciente = new JPanel();
        daoTurno = new DAOTurno();
        formularioTurnosPaciente.setLayout(new GridLayout(1,1));
        model = new DefaultTableModel();
        model.addColumn("legajo medico");
        model.addColumn("turno");
        model.addColumn("costo");
        ArrayList<Turno> turnos = daoTurno.buscarTurnosPaciente(dni);
        for (Turno turno : turnos) {
            model.addRow(new Object[]{turno.getLegajoMedico(), turno.getFecha(), turno.getCosto()});
        }
        formularioTurnosPaciente.add(new JScrollPane(new JTable(model)));
    }
    public JPanel getFormulario() {
        return formularioTurnosPaciente;
    }
    @Override
    public void decorar(){
        formularioTurnosPaciente.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioTurnosPaciente.setBackground(Color.lightGray);
        formularioTurnosPaciente.setOpaque(true);
    }
}
