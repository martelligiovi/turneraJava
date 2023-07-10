package gui;

import service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class FormularioAdmin extends JPanel implements Formulario,DecorarFormulario{
    FormularioSeleccionUsuario formularioSeleccionUsuario;
    FormularioAgregarMedico formularioAgregarMedico;
    FormularioAgregarPaciente formularioAgregarPaciente;
    FormularioAgregarTurno formularioAgregarTurno;
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
    FormularioModificarTurno formularioModificarTurno;
    JButton jButtonModificarPaciente;
    FormularioModificarPaciente formularioModificarPaciente;
    JButton jButtonModificarMedico;
    FormularioModificarMedico formularioModificarMedico;
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
                formularioAgregarMedico = new FormularioAgregarMedico(panel);
                panel.mostrar(formularioAgregarMedico.getFormulario());
            }
        });
        jButtonRegistrarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioAgregarPaciente = new FormularioAgregarPaciente(panel);
                panel.mostrar(formularioAgregarPaciente.getFormulario());
            }
        });
        jButtonRegistrarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    formularioAgregarTurno = new FormularioAgregarTurno(panel);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                panel.mostrar(formularioAgregarTurno.getFormulario());
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
        jButtonModificarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioModificarMedico = new FormularioModificarMedico(panel);
                panel.mostrar(formularioModificarMedico.getFormulario());
            }
        });
        jButtonModificarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioModificarPaciente = new FormularioModificarPaciente(panel);
                panel.mostrar(formularioModificarPaciente.getFormulario());
            }
        });
        jButtonModificarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    formularioModificarTurno = new FormularioModificarTurno(panel);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
                panel.mostrar(formularioModificarTurno.getFormulario());
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
