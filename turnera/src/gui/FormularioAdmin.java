package gui;

import javax.swing.*;
import java.awt.*;

public class FormularioAdmin {
    FormularioMedico formularioMedico;
    FormularioPaciente formularioPaciente;
    FormularioTurno formularioTurno;
    JPanel formularioAdmin;
    PanelManager panel;
    JButton jButtonRegistrarMedico;
    JButton jButtonRegistrarPaciente;
    JButton jButtonRegistrarTurno;
    public FormularioAdmin(PanelManager panel){
        this.panel=panel;
        creadorFormularioAdmin();
    }
    public void creadorFormularioAdmin(){
        formularioAdmin = new JPanel();
        formularioMedico = new FormularioMedico(panel);
        formularioPaciente = new FormularioPaciente(panel);
        formularioTurno = new FormularioTurno(panel);
        formularioAdmin.setLayout(new GridLayout(3,1));
        jButtonRegistrarMedico = new JButton("Registrar Medico");
        jButtonRegistrarPaciente = new JButton("Registrar Paciente");
        jButtonRegistrarTurno = new JButton("Registrar Turno");
        formularioAdmin.add(jButtonRegistrarMedico);
        formularioAdmin.add(jButtonRegistrarPaciente);
        formularioAdmin.add(jButtonRegistrarTurno);
        jButtonRegistrarMedico.addActionListener(e -> {
            panel.mostrar(formularioMedico.getFormularioMedico());
        });
        jButtonRegistrarPaciente.addActionListener(e -> {
            panel.mostrar(formularioPaciente.getFormularioPaciente());
        });
        jButtonRegistrarTurno.addActionListener(e -> {
            panel.mostrar(formularioTurno.getFormularioTurno());
        });


    }
    public JPanel getFormularioAdmin(){
        return formularioAdmin;
    }










}
