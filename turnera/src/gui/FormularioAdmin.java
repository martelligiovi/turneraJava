package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioAdmin extends JPanel{
    FormularioMedico formularioMedico;
    FormularioPaciente formularioPaciente;
    FormularioTurno formularioTurno;
    FormularioReporte formularioReporte;
    JPanel formularioAdmin;
    PanelManager panel;
    JButton jButtonRegistrarMedico;
    JButton jButtonRegistrarPaciente;
    JButton jButtonRegistrarTurno;
    JButton jButtonReportes;
    JButton jButtonExit;
    public FormularioAdmin(PanelManager panel){
        this.panel=panel;
        creadorFormularioAdmin();
        agregarFormularioMedico();
        agregarFuncionesBotones();
    }
    private void creadorFormularioAdmin(){
        formularioAdmin = new JPanel();
        formularioAdmin.setLayout(new GridLayout(5,1));
        jButtonRegistrarMedico = new JButton("Registrar Medico");
        jButtonRegistrarPaciente = new JButton("Registrar Paciente");
        jButtonRegistrarTurno = new JButton("Registrar Turno");
        jButtonReportes = new JButton("Reportes");
        jButtonExit = new JButton("Salir");
    }
    private void agregarFormularioMedico(){
        formularioAdmin.add(jButtonRegistrarMedico);
        formularioAdmin.add(jButtonRegistrarPaciente);
        formularioAdmin.add(jButtonRegistrarTurno);
        formularioAdmin.add(jButtonReportes);
        formularioAdmin.add(jButtonExit);
    }
    private void agregarFuncionesBotones(){
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioSeleccionUsuario formularioSeleccionUsuario = null;
                formularioSeleccionUsuario = new FormularioSeleccionUsuario(panel);
                panel.mostrar(formularioSeleccionUsuario.getformularioSeleccionUsuario());
            }
        });
        jButtonRegistrarMedico.addActionListener(e -> {
            formularioMedico = new FormularioMedico(panel);
            panel.mostrar(formularioMedico.getFormularioMedico());
        });
        jButtonRegistrarPaciente.addActionListener(e -> {
            formularioPaciente = new FormularioPaciente(panel);
            panel.mostrar(formularioPaciente.getFormularioPaciente());
        });
        jButtonRegistrarTurno.addActionListener(e -> {
            formularioTurno = new FormularioTurno(panel);
            panel.mostrar(formularioTurno.getFormularioTurno());
        });
        jButtonReportes.addActionListener(e -> {
            formularioReporte = new FormularioReporte(panel);
            panel.mostrar(formularioReporte.getFormularioReporte());
        });
    }
    public JPanel getFormularioAdmin(){
        return formularioAdmin;
    }
}
