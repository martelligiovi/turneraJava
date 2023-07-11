package gui;

import entidades.Medico;
import entidades.Paciente;
import entidades.Turno;
import service.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormularioEliminarTurno implements Formulario, DecorarFormulario{
    TurnoService turnoService;
    MedicoService medicoService;
    PacienteService pacienteService;
    JPanel formularioEliminarTurno;
    JButton jButtonSend;
    JButton jButtonExit;
    JComboBox jComboBoxLegajoMedico;
    JComboBox jComboBoxDniPaciente;
    JLabel jLabelDni;
    JLabel jLabelLegajo;
    PanelManager panel;
    FormularioAdmin formularioAdmin;
    FormularioEliminarTurnoFecha formularioEliminarTurnoFecha;
    ArrayList<Turno> turnos;

    FormularioEliminarTurno (PanelManager panel) throws ServiceException {
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }

    @Override
    public void creadorFormulario() throws ServiceException {
        turnoService = new TurnoService();
        formularioEliminarTurno = new JPanel();
        formularioEliminarTurno.setLayout(new GridLayout(3,2));
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
        formularioEliminarTurno.add(jLabelDni);
        formularioEliminarTurno.add(jComboBoxDniPaciente);
        formularioEliminarTurno.add(jLabelLegajo);
        formularioEliminarTurno.add(jComboBoxLegajoMedico);
        formularioEliminarTurno.add(jButtonExit);
        formularioEliminarTurno.add(jButtonSend);
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
                    JOptionPane.showMessageDialog(null,"No hay turnos para eliminar");
                }else{
                    formularioEliminarTurnoFecha = new FormularioEliminarTurnoFecha(panel,turnos);
                    panel.mostrar(formularioEliminarTurnoFecha.getFormulario());
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
        formularioEliminarTurno.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioEliminarTurno.setBackground(Color.lightGray);
        formularioEliminarTurno.setPreferredSize(new Dimension(450, 120));
        formularioEliminarTurno.setOpaque(true);
    }

    @Override
    public JPanel getFormulario(){
        return formularioEliminarTurno;
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
