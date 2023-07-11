package gui;

import entidades.Turno;
import service.ServiceException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class FormularioReporteAdicionalFinal implements DecorarFormulario {
    DefaultTableModel model;
    JPanel formularioReporteAdicionalFinal;
    PanelManager panel;
    ArrayList<Turno> turnos;

    public FormularioReporteAdicionalFinal(PanelManager panel,ArrayList<Turno> turnos) throws ServiceException {
        this.panel=panel;
        this.turnos=turnos;
        creadorFormularioReporteAdicionalFinal();
        decorar();
    }

    public void creadorFormularioReporteAdicionalFinal() throws ServiceException {
        formularioReporteAdicionalFinal = new JPanel();
        formularioReporteAdicionalFinal.setLayout(new GridLayout(1,1));
        model = new DefaultTableModel();
        model.addColumn("legajo medico");
        model.addColumn("nombre medico");
        model.addColumn("apellido medico");
        model.addColumn("ganancia");
        for (Turno turno : this.turnos) {
            model.addRow(new Object[]{turno.getLegajoMedico(),turno.getNombreMedico(),turno.getApellidoMedico(),turno.getCosto()});
        }
        formularioReporteAdicionalFinal.add(new JScrollPane(new JTable(model)));
    }
    @Override
    public void decorar(){
        formularioReporteAdicionalFinal.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioReporteAdicionalFinal.setBackground(Color.lightGray);
        formularioReporteAdicionalFinal.setOpaque(true);
    }
    public JPanel getFormulario() {
        return formularioReporteAdicionalFinal;
    }
}