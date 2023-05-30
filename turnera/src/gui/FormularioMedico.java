package gui;

import dao.DAOMedico;
import entidades.Medico;
import serrvice.MedicoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioMedico {
    DAOMedico daomedico;
    JPanel FormularioMedico;
    JLabel jLabelnombre;
    JTextField  jTextFieldNombre;
    JButton jButtonNombre;

     public FormularioMedico (){
         creadorFormularioMedico();
}
    public void creadorFormularioMedico(){
        MedicoService medicoService = new MedicoService();
        JPanel formularioMedico = new JPanel();
        formularioMedico.setLayout(new GridLayout(2,3));
        JFrame frame = new JFrame("Formulario Medico");
        jButtonNombre = new JButton();
        jLabelnombre = new JLabel();
        jTextFieldNombre = new JTextField();
        //quieroo crear  los botones, los labels y textfield de los demas parametros
        JButton jButtonApellido = new JButton();
        JLabel jLabelApellido = new JLabel();
        JTextField jTextFieldApellido = new JTextField();
        JButton jButtonDni = new JButton();
        JLabel jLabelDni = new JLabel();
        JTextField jTextFieldDni = new JTextField();
        JButton jButtonLegajo = new JButton();
        JLabel jLabelLegajo = new JLabel();
        JTextField jTextFieldLegajo = new JTextField();
        jButtonNombre.setText("Guardar");
        formularioMedico.add(jLabelnombre);
        formularioMedico.add(jTextFieldNombre);
        formularioMedico.add(jButtonNombre);
        formularioMedico.add(jLabelApellido);
        formularioMedico.add(jTextFieldApellido);
        formularioMedico.add(jButtonApellido);
        formularioMedico.add(jLabelDni);
        formularioMedico.add(jTextFieldDni);
        formularioMedico.add(jButtonDni);
        formularioMedico.add(jLabelLegajo);
        formularioMedico.add(jTextFieldLegajo);
        formularioMedico.add(jButtonLegajo);
        frame.add(formularioMedico);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        jButtonNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Medico medico = new Medico();
                medico.setNombre(jTextFieldNombre.getText());

            }
        });

    }





}
