package gui;
import dao.DAOTurno;
import entidades.Turno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormularioUsuarioMedico {
    PanelManager panel;
    JPanel formularioUsuarioMedico;
    DAOTurno daoTurno;
    JLabel jLabelLegajo;
    JTextField jTextFieldLegajo;
    JButton jButtonSend;

    public FormularioUsuarioMedico(PanelManager panel){
        this.panel=panel;
        creadorFormularioUsuarioMedico();
    }
    public void creadorFormularioUsuarioMedico(){
        daoTurno = new DAOTurno();
        formularioUsuarioMedico = new JPanel();
        formularioUsuarioMedico.setLayout(new GridLayout(2,2));
        jLabelLegajo = new JLabel("ingrese su legajo");
        jTextFieldLegajo = new JTextField();
        jButtonSend = new JButton("Enviar");
        formularioUsuarioMedico.add(jLabelLegajo);
        formularioUsuarioMedico.add(jTextFieldLegajo);
        JPanel emptyPanel = new JPanel();
        formularioUsuarioMedico.add(emptyPanel);
        formularioUsuarioMedico.add(jButtonSend);
        emptyPanel.setVisible(false);

        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String legajo = jTextFieldLegajo.getText();
                ArrayList<Turno> turnos = daoTurno.buscarTodos(legajo);

                // Realiza la acción deseada con el ArrayList de turnos
                // Por ejemplo, mostrar los turnos en una ventana emergente
                StringBuilder message = new StringBuilder();
                for (Turno turno : turnos) {
                    message.append(turno.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(formularioUsuarioMedico, message.toString(), "Turnos del médico", JOptionPane.INFORMATION_MESSAGE);
            */}
        });










    }

    public JPanel getFormularioUsuarioMedico() {
        return formularioUsuarioMedico;
    }



}
