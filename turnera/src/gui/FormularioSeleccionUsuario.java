package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioSeleccionUsuario extends JPanel implements Formulario,DecorarFormulario{
    JPanel formularioSeleccionUsuario;
    FormularioLoginAdmin formularioLoginAdmin;
    FormularioUsuarioMedico formularioUsuarioMedico;
    FormularioUsuarioPaciente formularioUsuarioPaciente;
    PanelManager panel;
    JButton jButtonAdmin;
    JButton jButtonMedico;
    JButton jButtonPaciente;

    public FormularioSeleccionUsuario (PanelManager panel){
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }

    @Override
    public void creadorFormulario(){
        formularioSeleccionUsuario = new JPanel();
        formularioSeleccionUsuario.setLayout(new GridLayout(3,1));
        jButtonAdmin = new JButton("Administrador");
        jButtonMedico = new JButton("Medico");
        jButtonPaciente = new JButton("Paciente");
    }

    @Override
    public void agregarFormulario(){
        formularioSeleccionUsuario.add(jButtonAdmin);
        formularioSeleccionUsuario.add(jButtonMedico);
        formularioSeleccionUsuario.add(jButtonPaciente);
    }

    @Override
    public void agregarFuncionesBotones(){
        jButtonMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioUsuarioMedico = new FormularioUsuarioMedico(panel);
                panel.mostrar(formularioUsuarioMedico.getFormulario());
            }
        });
        jButtonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioLoginAdmin = new FormularioLoginAdmin(panel);
                panel.mostrar(formularioLoginAdmin.getFormulario());
            }
        });
        jButtonPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioUsuarioPaciente = new FormularioUsuarioPaciente(panel);
                panel.mostrar(formularioUsuarioPaciente.getFormulario());
            }
        });
    }

    @Override
    public JPanel getFormulario(){
        return formularioSeleccionUsuario;
    }
    @Override
    public void decorar(){
        formularioSeleccionUsuario.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioSeleccionUsuario.setBackground(Color.lightGray);
        formularioSeleccionUsuario.setPreferredSize(new Dimension(220, 120));
        formularioSeleccionUsuario.setOpaque(true);
    }

}

