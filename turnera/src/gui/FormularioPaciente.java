package gui;

import dao.DAOPaciente;
import entidades.Paciente;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPaciente extends JPanel{
    DAOPaciente daopaciente;
    JPanel formularioPaciente;
    JLabel jLabelNombre;
    JTextField  jTextFieldNombre;
    JButton jButtonSend;
    JButton jButtonExit;
    JLabel jLabelApellido;
    JTextField jTextFieldApellido;
    JLabel jLabelDni;
    JTextField jTextFieldDni;
    JLabel jLabelCodObraSocial;
    JTextField jTextFieldCodObraSocial;
    JPanel jPanelBotones;
    PanelManager panel;


    public FormularioPaciente (PanelManager panel){

        this.panel=panel;
        creadorFormularioPaciente();


    }
    public void creadorFormularioPaciente(){
        DAOPaciente daoPaciente = new DAOPaciente();
        formularioPaciente = new JPanel();
        formularioPaciente.setLayout(new GridLayout(5,2));
        jLabelNombre = new JLabel("Nombre");
        jLabelApellido = new JLabel("Apellido");
        jLabelDni = new JLabel("Dni");
        jLabelCodObraSocial = new JLabel("Codigo de obra social");
        jTextFieldNombre = new JTextField();
        jTextFieldApellido = new JTextField();
        jTextFieldDni = new JTextField();
        jTextFieldCodObraSocial = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
        jPanelBotones=new JPanel();

        formularioPaciente.add(jLabelDni);
        formularioPaciente.add(jTextFieldDni);
        formularioPaciente.add(jLabelNombre);
        formularioPaciente.add(jTextFieldNombre);
        formularioPaciente.add(jLabelApellido);
        formularioPaciente.add(jTextFieldApellido);
        formularioPaciente.add(jLabelCodObraSocial);
        formularioPaciente.add(jTextFieldCodObraSocial);
        formularioPaciente.add(jButtonSend);
        formularioPaciente.add(jButtonExit);



        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente = new Paciente();
                paciente.setNombre(jTextFieldNombre.getText());
                paciente.setApellido(jTextFieldApellido.getText());
                paciente.setCodObraSocial(Integer.parseInt(jTextFieldCodObraSocial.getText()));
                paciente.setDni(Integer.parseInt(jTextFieldDni.getText()));

            }
        });

    }


    public JPanel getFormularioPaciente() {
        return formularioPaciente;
    }
}
