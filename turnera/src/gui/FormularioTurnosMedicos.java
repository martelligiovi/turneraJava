package gui;

import service.ServiceException;
import service.TurnoService;
import entidades.Turno;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FormularioTurnosMedicos implements DecorarFormulario {
    DefaultTableModel model;
    JPanel formularioTurnosMedicos;
    PanelManager panel;
    ArrayList<Turno> turnos;

    public FormularioTurnosMedicos(PanelManager panel,ArrayList<Turno> turnos) throws ServiceException {
        this.panel=panel;
        this.turnos=turnos;
        creadorFormularioTurnosMedicos();
        decorar();
    }

    public void creadorFormularioTurnosMedicos() throws ServiceException {
        formularioTurnosMedicos = new JPanel();
        formularioTurnosMedicos.setLayout(new GridLayout(1,1));
        model = new DefaultTableModel();
        model.addColumn("dni paciente");
        model.addColumn("hora");
        model.addColumn("costo");
        for (Turno turno : this.turnos) {
            model.addRow(new Object[]{turno.getDniPaciente(), turno.getHora(), turno.getCosto()});
        }
        formularioTurnosMedicos.add(new JScrollPane(new JTable(model)));
    }
    public JPanel getFormulario() {
        return formularioTurnosMedicos;
    }
    @Override
    public void decorar(){
        formularioTurnosMedicos.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioTurnosMedicos.setBackground(Color.lightGray);
        formularioTurnosMedicos.setOpaque(true);
    }
}
