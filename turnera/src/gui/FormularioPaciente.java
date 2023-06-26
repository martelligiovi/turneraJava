package gui;

import dao.DAOException;
import dao.DAOPaciente;
import entidades.Paciente;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPaciente extends JPanel implements Formulario{
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
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
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
    @Override
    public void agregarFormulario(){
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
    @Override
    public void agregarFuncionesBotones(){
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioAdmin formularioAdmin = null;
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormulario());
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
                panel.mostrar(formularioAdmin.getFormulario());
            }
        });
    }

    public JPanel getFormulario() {
        return formularioPaciente;
    }
    public void decorar(){
        formularioPaciente.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioPaciente.setBackground(Color.lightGray);
        formularioPaciente.setSize(1000,1000);
        formularioPaciente.setOpaque(true);
    }
}
