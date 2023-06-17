package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioAdmin extends JPanel{
    FormularioMedico formularioMedico;
    FormularioPaciente formularioPaciente;
    FormularioTurno formularioTurno;
    JPanel formularioAdmin;
    PanelManager panel;
    JButton jButtonRegistrarMedico;
    JButton jButtonRegistrarPaciente;
    JButton jButtonRegistrarTurno;
    JButton jButtonExit;
    public FormularioAdmin(PanelManager panel){
        this.panel=panel;
        creadorFormularioAdmin();
    }
    public void creadorFormularioAdmin(){
        formularioAdmin = new JPanel();
        formularioMedico = new FormularioMedico(panel);
        formularioPaciente = new FormularioPaciente(panel);
        formularioTurno = new FormularioTurno(panel);
        formularioAdmin.setLayout(new GridLayout(4,1));
        jButtonRegistrarMedico = new JButton("Registrar Medico");
        jButtonRegistrarPaciente = new JButton("Registrar Paciente");
        jButtonRegistrarTurno = new JButton("Registrar Turno");
        jButtonExit = new JButton("Salir");
        formularioAdmin.add(jButtonRegistrarMedico);
        formularioAdmin.add(jButtonRegistrarPaciente);
        formularioAdmin.add(jButtonRegistrarTurno);
        formularioAdmin.add(jButtonExit);

        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioSeleccionUsuario formularioSeleccionUsuario = null;
                formularioSeleccionUsuario = new FormularioSeleccionUsuario(panel);
                panel.mostrar(formularioSeleccionUsuario.getformularioSeleccionUsuario());
            }
        });





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
