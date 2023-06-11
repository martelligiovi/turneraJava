package gui;

import dao.DAOException;
import dao.DAOTurno;
import entidades.Turno;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioTurno extends JPanel{
    DAOTurno daoturno;
    JPanel formularioTurno;
    JLabel jLabelLegajoMedico;
    JTextField  jTextFieldLegajoMedico;
    JButton jButtonSend;
    JButton jButtonExit;
    JLabel jLabelDniPaciente;
    JTextField jTextFieldDniPaciente;
    JLabel jLabelFecha;
    JTextField jTextFieldFecha;
    JLabel jLabelCosto;
    JTextField jTextFieldCosto;
    PanelManager panel;


    public FormularioTurno (PanelManager panel){

        this.panel=panel;
        creadorFormularioTurno();


    }
    public void creadorFormularioTurno(){
        DAOTurno daoTurno = new DAOTurno();
        formularioTurno = new JPanel();
        formularioTurno.setLayout(new GridLayout(5,2));
        jLabelLegajoMedico = new JLabel("legajo midico");
        jTextFieldLegajoMedico = new JTextField();
        jLabelDniPaciente = new JLabel("Dni paciente");
        jTextFieldDniPaciente = new JTextField();
        jLabelFecha = new JLabel("Fecha");
        jTextFieldFecha = new JTextField();
        jLabelCosto = new JLabel("Costo");
        jTextFieldCosto = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");

        formularioTurno.add(jLabelLegajoMedico);
        formularioTurno.add(jTextFieldLegajoMedico);
        formularioTurno.add(jLabelDniPaciente);
        formularioTurno.add(jTextFieldDniPaciente);
        formularioTurno.add(jLabelFecha);
        formularioTurno.add(jTextFieldFecha);
        formularioTurno.add(jLabelCosto);
        formularioTurno.add(jTextFieldCosto);
        formularioTurno.add(jButtonSend);
        formularioTurno.add(jButtonExit);



        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Turno turno = new Turno();
                turno.setLegajoMedico(Integer.parseInt(jTextFieldLegajoMedico.getText()));
                turno.setDniPaciente(Integer.parseInt(jTextFieldDniPaciente.getText()));
                turno.setFecha(Integer.parseInt(jTextFieldFecha.getText()));
                turno.setCosto(Double.parseDouble(jTextFieldCosto.getText()));
                try {
                    daoTurno.guardar(turno);
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }


    public JPanel getFormularioTurno() {
        return formularioTurno;
    }
}

