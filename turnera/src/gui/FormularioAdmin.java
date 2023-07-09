package gui;

import service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class FormularioAdmin extends JPanel implements Formulario,DecorarFormulario{
    FormularioSeleccionUsuario formularioSeleccionUsuario;
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
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        formularioAdmin = new JPanel();
        formularioAdmin.setLayout(new GridLayout(5,1));
        jButtonRegistrarMedico = new JButton("Registrar Medico");
        jButtonRegistrarPaciente = new JButton("Registrar Paciente");
        jButtonRegistrarTurno = new JButton("Registrar Turno");
        jButtonReportes = new JButton("Reportes");
        jButtonExit = new JButton("Salir");

    }
    @Override
    public void agregarFormulario(){
        formularioAdmin.add(jButtonRegistrarMedico);
        formularioAdmin.add(jButtonRegistrarPaciente);
        formularioAdmin.add(jButtonRegistrarTurno);
        formularioAdmin.add(jButtonReportes);
        formularioAdmin.add(jButtonExit);
    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioSeleccionUsuario = new FormularioSeleccionUsuario(panel);
                panel.mostrar(formularioSeleccionUsuario.getFormulario());
            }
        });
        jButtonRegistrarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioMedico = new FormularioMedico(panel);
                panel.mostrar(formularioMedico.getFormulario());
            }
        });
        jButtonRegistrarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioPaciente = new FormularioPaciente(panel);
                panel.mostrar(formularioPaciente.getFormulario());
            }
        });
        jButtonRegistrarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    formularioTurno = new FormularioTurno(panel);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                panel.mostrar(formularioTurno.getFormulario());
            }
        });
        jButtonReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    formularioReporte = new FormularioReporte(panel);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
                panel.mostrar(formularioReporte.getFormulario());
            }
        });
    }
    @Override
    public JPanel getFormulario(){
        return formularioAdmin;
    }
    @Override
    public void decorar(){
        formularioAdmin.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioAdmin.setBackground(Color.lightGray);
        formularioAdmin.setPreferredSize(new Dimension(220, 175));
        formularioAdmin.setOpaque(true);
    }
}
