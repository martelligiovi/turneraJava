package gui;
import dao.DAOException;
import dao.DAOTurno;
import entidades.Turno;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class FormularioUsuarioMedico {
    PanelManager panel;
    JPanel formularioUsuarioMedico;
    DAOTurno daoTurno;
    JLabel jLabelLegajo;
    JTextField jTextFieldLegajo;
    JLabel jLabelFecha;
    JFormattedTextField jTextFieldFecha;
    JButton jButtonSend;
    JButton jButtonExit;

    public FormularioUsuarioMedico(PanelManager panel){
        this.panel=panel;
        creadorFormularioUsuarioMedico();
    }
    public void creadorFormularioUsuarioMedico(){
        daoTurno = new DAOTurno();
        formularioUsuarioMedico = new JPanel();
        formularioUsuarioMedico.setLayout(new GridLayout(3,2));
        jLabelLegajo = new JLabel("ingrese su legajo");
        jTextFieldLegajo = new JTextField();
        jLabelFecha = new JLabel("Fecha");
        jTextFieldFecha = new JFormattedTextField(createMaskFormatter("####/##/##"));
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
        formularioUsuarioMedico.add(jLabelLegajo);
        formularioUsuarioMedico.add(jTextFieldLegajo);
        formularioUsuarioMedico.add(jLabelFecha);
        formularioUsuarioMedico.add(jTextFieldFecha);
        formularioUsuarioMedico.add(jButtonExit);
        formularioUsuarioMedico.add(jButtonSend);


        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioTurnosMedicos formularioTurnosMedicos = null;
                try {
                    formularioTurnosMedicos = new FormularioTurnosMedicos(panel,jTextFieldFecha.getText(),Integer.parseInt(jTextFieldLegajo.getText()));
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
                panel.mostrar(formularioTurnosMedicos.getFormularioTurnosMedicos());
                }
        });
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioAdmin formularioAdmin = null;
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormularioAdmin());
            }
        });
    }

    public JPanel getFormularioUsuarioMedico() {
        return formularioUsuarioMedico;
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
