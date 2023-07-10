package gui;

import entidades.Medico;
import entidades.Paciente;
import entidades.Turno;

import service.*;


import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class FormularioModificarTurno implements Formulario, DecorarFormulario {
    TurnoService turnoService;
    MedicoService medicoService;
    PacienteService pacienteService;
    JPanel formularioModificarTurno;
    JButton jButtonSend;
    JButton jButtonExit;
    JComboBox jComboBoxLegajoMedico;
    JComboBox jComboBoxDniPaciente;
    JLabel jLabelDni;
    JLabel jLabelLegajo;
    PanelManager panel;
    FormularioAdmin formularioAdmin;
    FormularioModificarTurnoFecha formularioModificarTurnoFecha;
    ArrayList<Turno> turnos;
    FormularioModificarTurno (PanelManager panel) throws ServiceException {
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario() throws ServiceException {
        turnoService = new TurnoService();
        formularioModificarTurno = new JPanel();
        formularioModificarTurno.setLayout(new GridLayout(3,2));
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
        jComboBoxDniPaciente = new JComboBox();
        jComboBoxLegajoMedico = new JComboBox();
        jLabelDni = new JLabel("Dni paciente");
        jLabelLegajo = new JLabel("Legajo medico");
        ArrayList<Medico> medicos = fillarrayMedicos();
        for (Medico m : medicos) {
            jComboBoxLegajoMedico.addItem(m.getLegajo() + "-" + m.getNombre() + " " + m.getApellido());
        }
        ArrayList<Paciente> pacientes = fillarrayPacientes();
        jComboBoxDniPaciente = new JComboBox();
        for (Paciente p : pacientes) {
            jComboBoxDniPaciente.addItem(p.getDni() + "-" + p.getNombre() + " " + p.getApellido());
        }
    }
    @Override
    public void agregarFormulario(){
        formularioModificarTurno.add(jLabelDni);
        formularioModificarTurno.add(jComboBoxDniPaciente);
        formularioModificarTurno.add(jLabelLegajo);
        formularioModificarTurno.add(jComboBoxLegajoMedico);
        formularioModificarTurno.add(jButtonExit);
        formularioModificarTurno.add(jButtonSend);

    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Turno turno = new Turno();
                    turno.setDniPaciente(Integer.parseInt(jComboBoxDniPaciente.getSelectedItem().toString().split("-")[0]));
                    turno.setLegajoMedico(Integer.parseInt(jComboBoxLegajoMedico.getSelectedItem().toString().split("-")[0]));
                    turnos=new ArrayList<Turno>(turnoService.buscarTurnosPorPacienteYMedico(turno));
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null,"Error al seleccionar el turno");
                }
                if (turnos.size()==0){
                    JOptionPane.showMessageDialog(null,"No hay turnos para modificar");
                }else{
                   formularioModificarTurnoFecha = new FormularioModificarTurnoFecha(panel,turnos);
                   panel.mostrar(formularioModificarTurnoFecha.getFormulario());
                }
            }
        });
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormulario());
            }
        });

    }
    @Override
    public void decorar(){
        formularioModificarTurno.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }
    @Override
    public JPanel getFormulario(){
        return formularioModificarTurno;
    }
    public ArrayList<Paciente> fillarrayPacientes() throws ServiceException {
        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
        pacienteService = new PacienteService();
        pacientes = pacienteService.buscarTodos();
        return pacientes;
    }
    public ArrayList<Medico> fillarrayMedicos() throws ServiceException {
        ArrayList<Medico> medicos = new ArrayList<Medico>();
        medicoService = new MedicoService();
        medicos = medicoService.buscarTodos();
        return medicos;
    }

}

