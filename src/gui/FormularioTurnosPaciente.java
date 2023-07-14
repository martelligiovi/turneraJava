package gui;

import service.ServiceException;
import service.TurnoService;
import entidades.Turno;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FormularioTurnosPaciente implements DecorarFormulario{
    TurnoService turnoService;
    DefaultTableModel model;
    JPanel formularioTurnosPaciente;
    PanelManager panel;
    ArrayList<Turno> turnos;

    public FormularioTurnosPaciente(PanelManager panel,ArrayList<Turno> turnos) throws ServiceException{
        this.panel=panel;
        this.turnos=turnos;
        creadorFormulario();
        decorar();
    }

    public void creadorFormulario() throws ServiceException{
        formularioTurnosPaciente = new JPanel();
        turnoService = new TurnoService();
        formularioTurnosPaciente.setLayout(new GridLayout(1,1));
        model = new DefaultTableModel();
        model.addColumn("legajo medico");
        model.addColumn("turno");
        model.addColumn("costo");
        for (Turno turno : this.turnos){
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
