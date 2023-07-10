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
    JButton jButonEliminarTurno;
    FormularioEliminarTurno formularioEliminarTurno;
    JButton jButtonEliminarPaciente;
    FormularioEliminarPaciente formularioEliminarPaciente;
    JButton jButtonEliminarMedico;
    FormularioEliminarMedico formularioEliminarMedico;
    JButton jButtonModificarTurno;
    JButton jButtonModificarPaciente;
    JButton jButtonModificarMedico;
    JButton jButtonReportes;
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
        formularioAdmin.setLayout(new GridLayout(4, 3));
        jButtonRegistrarMedico = new JButton("Registrar Medico");
        jButtonRegistrarPaciente = new JButton("Registrar Paciente");
        jButtonRegistrarTurno = new JButton("Registrar Turno");
        jButtonEliminarMedico = new JButton("Eliminar Medico");
        jButtonEliminarPaciente = new JButton("Eliminar Paciente");
        jButonEliminarTurno = new JButton("Eliminar Turno");
        jButtonModificarMedico = new JButton("Modificar Medico");
        jButtonModificarPaciente = new JButton("Modificar Paciente");
        jButtonModificarTurno = new JButton("Modificar Turno");
        jButtonReportes = new JButton("Reportes");


    }
    @Override
    public void agregarFormulario(){
        formularioAdmin.add(jButtonRegistrarMedico);
        formularioAdmin.add(jButtonRegistrarPaciente);
        formularioAdmin.add(jButtonRegistrarTurno);
        formularioAdmin.add(jButtonEliminarMedico);
        formularioAdmin.add(jButtonEliminarPaciente);
        formularioAdmin.add(jButonEliminarTurno);
        formularioAdmin.add(jButtonModificarMedico);
        formularioAdmin.add(jButtonModificarPaciente);
        formularioAdmin.add(jButtonModificarTurno);
        //quiero agregar una objeto invisible para que se vea bien
        formularioAdmin.add(new JLabel(""));
        formularioAdmin.add(jButtonReportes);

    }
    @Override
    public void agregarFuncionesBotones(){
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
        jButtonEliminarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioEliminarMedico = new FormularioEliminarMedico(panel);
                panel.mostrar(formularioEliminarMedico.getFormulario());
            }
        });
        jButtonEliminarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioEliminarPaciente = new FormularioEliminarPaciente(panel);
                panel.mostrar(formularioEliminarPaciente.getFormulario());
            }
        });
        jButonEliminarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    formularioEliminarTurno = new FormularioEliminarTurno(panel);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
                panel.mostrar(formularioEliminarTurno.getFormulario());
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
        formularioAdmin.setPreferredSize(new Dimension(450, 175));
        formularioAdmin.setOpaque(true);
    }
}
