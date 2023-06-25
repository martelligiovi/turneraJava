package gui;

import dao.DAOException;
import dao.DAOTurno;
import entidades.Turno;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class FormularioReporte {
    ArrayList<Turno> listaTurnos;
    FormularioReporteFinal formularioReporteFinal;
    JPanel formularioReporte;
    PanelManager panel;
    JFormattedTextField jTextFieldFecha1;
    JLabel jLabelFecha1;
    JFormattedTextField jTextFieldFecha2;
    JLabel jLabelFecha2;
    JTextField jTextFieldLegajo;
    JLabel jLabelLegajo;
    JButton jButtonBuscar;
    private DAOTurno daoTurno;

    public FormularioReporte(PanelManager panel) {
        this.panel=panel;
        creadorFormularioReporte();
    }
    public void creadorFormularioReporte(){
        formularioReporte = new JPanel();
        formularioReporte.setLayout(new GridLayout(4,2));
        jLabelLegajo = new JLabel("Legajo");
        jTextFieldLegajo = new JTextField();
        jTextFieldFecha1 = new JFormattedTextField(createMaskFormatter("####/##/##"));
        jTextFieldFecha2 = new JFormattedTextField(createMaskFormatter("####/##/##"));
        jLabelFecha1 = new JLabel("Fecha 1");
        jLabelFecha2 = new JLabel("Fecha 2");
        formularioReporte.add(jLabelLegajo);
        formularioReporte.add(jTextFieldLegajo);
        formularioReporte.add(jLabelFecha1);
        formularioReporte.add(jTextFieldFecha1);
        formularioReporte.add(jLabelFecha2);
        formularioReporte.add(jTextFieldFecha2);
        jButtonBuscar = new JButton("Buscar");
        formularioReporte.add(jButtonBuscar);
        jButtonBuscar.addActionListener(e -> {
            try {
                daoTurno = new DAOTurno();
                listaTurnos = new ArrayList<>();
                ArrayList<Turno> listaTurnos = daoTurno.buscarCobros(jTextFieldFecha1.getText(),jTextFieldFecha2.getText(),Integer.parseInt(jTextFieldLegajo.getText()));
                formularioReporteFinal = new FormularioReporteFinal(panel,listaTurnos,jTextFieldFecha1.getText(),jTextFieldFecha2.getText());
                panel.mostrar(formularioReporteFinal.getFormularioReporteFinal());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    public JPanel getFormularioReporte() {
        return formularioReporte;
    }
    private MaskFormatter createMaskFormatter(String mask) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }





}
