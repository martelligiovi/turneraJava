package gui;

import dao.DAOPaciente;
import entidades.Paciente;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPaciente extends JPanel{
    DAOPaciente daomedico;
    JPanel formularioPaciente;
    JLabel jLabelNombre;
    JTextField  jTextFieldNombre;
    JButton jButtonSend;
    JButton jButtonExit;
    JLabel jLabelApellido;
    JTextField jTextFieldApellido;
    JLabel jLabelDni;
    JTextField jTextFieldDni;
    JLabel jLabelLegajo;
    JTextField jTextFieldLegajo;
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
        jLabelLegajo = new JLabel("Legajo");
        jTextFieldNombre = new JTextField();
        jTextFieldApellido = new JTextField();
        jTextFieldDni = new JTextField();
        jTextFieldLegajo = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
        jPanelBotones=new JPanel();

        formularioPaciente.add(jLabelNombre);
        formularioPaciente.add(jTextFieldNombre);
        formularioPaciente.add(jLabelApellido);
        formularioPaciente.add(jTextFieldApellido);
        formularioPaciente.add(jLabelDni);
        formularioPaciente.add(jTextFieldDni);
        formularioPaciente.add(jLabelLegajo);
        formularioPaciente.add(jTextFieldLegajo);
        formularioPaciente.add(jButtonSend);
        formularioPaciente.add(jButtonExit);



        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente medico = new Paciente();
                medico.setNombre(jTextFieldNombre.getText());
                medico.setApellido(jTextFieldApellido.getText());
                medico.setDni(Integer.parseInt(jTextFieldDni.getText()));

            }
        });

    }


    public JPanel getFormularioPaciente() {
        return formularioPaciente;
    }
}
