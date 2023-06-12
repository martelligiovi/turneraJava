package gui;

import dao.DAOMedico;
import entidades.Medico;
import serrvice.MedicoService;
import serrvice.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioMedico extends JPanel{

    MedicoService medicoService;
    JPanel formularioMedico;
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


    public FormularioMedico (PanelManager panel){

        this.panel=panel;
        creadorFormularioMedico();


}
    public void creadorFormularioMedico(){
        medicoService = new MedicoService();
        formularioMedico = new JPanel();
        formularioMedico.setLayout(new GridLayout(5,2));
        jLabelNombre = new JLabel("Nombre");
        jLabelApellido = new JLabel("Apellido");
        jLabelDni = new JLabel("Dni");
        jLabelLegajo = new JLabel("Legajo");
        jTextFieldNombre = new JTextField();
        jTextFieldApellido = new JTextField();
        //quiero usar un jtextfield que solo acepte numeros y que no se pueda escribir mas de 8 numeros
        jTextFieldDni = new JTextField();
        jTextFieldLegajo = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
        jPanelBotones=new JPanel();


        formularioMedico.add(jLabelDni);
        formularioMedico.add(jTextFieldDni);
        formularioMedico.add(jLabelNombre);
        formularioMedico.add(jTextFieldNombre);
        formularioMedico.add(jLabelApellido);
        formularioMedico.add(jTextFieldApellido);
        formularioMedico.add(jLabelLegajo);
        formularioMedico.add(jTextFieldLegajo);
        formularioMedico.add(jButtonSend);
        formularioMedico.add(jButtonExit);

        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioAdmin formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormularioAdmin());
            }
        });


        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Medico medico = new Medico();
                medico.setDni(Integer.parseInt(jTextFieldDni.getText()));
                medico.setNombre(jTextFieldNombre.getText());
                medico.setApellido(jTextFieldApellido.getText());
                medico.setLegajo(Integer.parseInt(jTextFieldLegajo.getText()));
                try {
                    medicoService.guardarMedico(medico);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }


    public JPanel getFormularioMedico() {
        return formularioMedico;
    }
}
