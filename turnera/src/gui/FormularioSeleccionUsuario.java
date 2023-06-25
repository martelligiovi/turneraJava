package gui;

import dao.DAOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioSeleccionUsuario extends JPanel{

    JPanel formularioSeleccionUsuario;
    FormularioAdmin formularioAdmin;
    FormularioUsuarioMedico formularioUsuarioMedico;
    PanelManager panel;
    JButton jButtonAdmin;
    JButton jButtonMedico;
    JButton jButtonPaciente;
    public FormularioSeleccionUsuario (PanelManager panel){
        this.panel=panel;
        creadorFormularioSeleccionUsuario();
        agregarFormulario();
        agregagarFuncionesBotones();
    }
    public void creadorFormularioSeleccionUsuario(){
        formularioSeleccionUsuario = new JPanel();
        formularioSeleccionUsuario.setLayout(new GridLayout(3,1));
        jButtonAdmin = new JButton("Administrador");
        jButtonMedico = new JButton("Medico");
        jButtonPaciente = new JButton("Paciente");
    }
    private void agregarFormulario(){
        formularioSeleccionUsuario.add(jButtonAdmin);
        formularioSeleccionUsuario.add(jButtonMedico);
        formularioSeleccionUsuario.add(jButtonPaciente);
    }
    private void agregagarFuncionesBotones(){
        jButtonMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioUsuarioMedico = new FormularioUsuarioMedico(panel);
                panel.mostrar(formularioUsuarioMedico.getFormularioUsuarioMedico());
            }
        });
        jButtonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormularioAdmin());
            }
        });
    }


    public JPanel getformularioSeleccionUsuario() {
        return formularioSeleccionUsuario;
    }
}

