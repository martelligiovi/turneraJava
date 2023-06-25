package gui;

import dao.DAOException;
import dao.DAOPaciente;
import entidades.Paciente;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPaciente extends JPanel{
    DAOPaciente daoPaciente;
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
    PanelManager panel;


    public FormularioPaciente (PanelManager panel){
        this.panel=panel;
        creadorFormularioPaciente();
        agregarFormulario();
        agregarFuncionesBotones();
    }
    private void creadorFormularioPaciente(){
        daoPaciente = new DAOPaciente();
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
    }
    private void agregarFormulario(){
        formularioPaciente.add(jLabelDni);
        formularioPaciente.add(jTextFieldDni);
        formularioPaciente.add(jLabelNombre);
        formularioPaciente.add(jTextFieldNombre);
        formularioPaciente.add(jLabelApellido);
        formularioPaciente.add(jTextFieldApellido);
        formularioPaciente.add(jLabelCodObraSocial);
        formularioPaciente.add(jTextFieldCodObraSocial);
        formularioPaciente.add(jButtonExit);
        formularioPaciente.add(jButtonSend);
    }
    private void agregarFuncionesBotones(){
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioAdmin formularioAdmin = null;
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormularioAdmin());
            }
        });
        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente paciente = new Paciente();
                paciente.setDni(Integer.parseInt(jTextFieldDni.getText()));
                paciente.setNombre(jTextFieldNombre.getText());
                paciente.setApellido(jTextFieldApellido.getText());
                paciente.setCodObraSocial(Integer.parseInt(jTextFieldCodObraSocial.getText()));
                try {
                    daoPaciente.guardar(paciente);
                } catch (DAOException daoException) {
                    daoException.printStackTrace();
                }
                FormularioAdmin formularioAdmin = null;
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormularioAdmin());
            }
        });
    }

    public JPanel getFormularioPaciente() {
        return formularioPaciente;
    }
}
