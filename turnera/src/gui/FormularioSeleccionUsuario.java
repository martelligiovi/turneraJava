package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioSeleccionUsuario extends JPanel{

    JPanel formularioSeleccionUsuario;
    FormularioAdmin formularioAdmin;
    PanelManager panel;
    JButton jButtonAdmin;
    JButton jButtonMedico;
    JButton jButtonPaciente;



    public FormularioSeleccionUsuario (PanelManager panel){

        this.panel=panel;
        creadorFormularioSeleccionUsuario();


    }
    public void creadorFormularioSeleccionUsuario(){
        formularioSeleccionUsuario = new JPanel();
        formularioAdmin= new FormularioAdmin(panel);
        formularioSeleccionUsuario.setLayout(new GridLayout(3,1));
        jButtonAdmin = new JButton("Administrador");
        jButtonMedico = new JButton("Medico");
        jButtonPaciente = new JButton("Paciente");
        formularioSeleccionUsuario.add(jButtonAdmin);
        formularioSeleccionUsuario.add(jButtonMedico);
        formularioSeleccionUsuario.add(jButtonPaciente);




        jButtonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.mostrar(formularioAdmin.getFormularioAdmin());
            }
        });

    }


    public JPanel getformularioSeleccionUsuario() {
        return formularioSeleccionUsuario;
    }
}
