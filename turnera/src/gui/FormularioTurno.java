package gui;

import entidades.Medico;
import entidades.Paciente;
import entidades.Turno;
import service.MedicoService;
import service.PacienteService;
import service.ServiceException;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.*;

public class FormularioTurno extends JPanel implements Formulario,DecorarFormulario{
    JPanel formularioTurno;
    FormularioHora formularioHora;
    JLabel jLabelLegajoMedico;
    JComboBox  jComboBoxLegajoMedico;
    JButton jButtonSend;
    JButton jButtonExit;
    JLabel jLabelDniPaciente;
    JComboBox jComboBoxDniPaciente;
    JLabel jLabelFecha;
    JFormattedTextField jTextFieldFecha;
    JLabel jLabelCosto;
    JTextField jTextFieldCosto;
    PanelManager panel;
    MedicoService medicoService;
    PacienteService pacienteService;
    Turno turno;


    public FormularioTurno (PanelManager panel) throws ServiceException, ParseException {
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario() throws ParseException, ServiceException {
        medicoService = new MedicoService();
        pacienteService = new PacienteService();
        formularioTurno = new JPanel();
        formularioTurno.setLayout(new GridLayout(5,2));
        jLabelLegajoMedico = new JLabel("legajo medico");
        jComboBoxLegajoMedico = new JComboBox();
        jLabelDniPaciente = new JLabel("Dni paciente");
        jComboBoxDniPaciente = new JComboBox();
        jLabelFecha = new JLabel("Fecha");
        jTextFieldFecha = new JFormattedTextField(createMaskFormatter("####/##/##"));
        jLabelCosto = new JLabel("Costo");
        jTextFieldCosto = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
        jTextFieldFecha.setColumns(10);
        jTextFieldFecha.setFocusLostBehavior(JFormattedTextField.COMMIT);
        jTextFieldFecha.setText("yyyy/MM/dd");
        ArrayList<Medico> medicos = fillarrayMedicos();
        for (Medico m : medicos) {
            jComboBoxLegajoMedico.addItem(m.getLegajo());
        }
        ArrayList<Paciente> pacientes = fillarrayPacientes();
        jComboBoxDniPaciente = new JComboBox();
        for (Paciente p : pacientes) {
            jComboBoxDniPaciente.addItem(p.getDni());
        }
    }
    @Override
    public void agregarFormulario(){
        formularioTurno.add(jLabelLegajoMedico);
        formularioTurno.add(jComboBoxLegajoMedico);
        formularioTurno.add(jLabelDniPaciente);
        formularioTurno.add(jComboBoxDniPaciente);
        formularioTurno.add(jLabelFecha);
        formularioTurno.add(jTextFieldFecha);
        formularioTurno.add(jLabelCosto);
        formularioTurno.add(jTextFieldCosto);
        formularioTurno.add(jButtonExit);
        formularioTurno.add(jButtonSend);
    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioAdmin formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormulario());
            }
        });

        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turno = new Turno();
                turno.setLegajoMedico(Integer.parseInt(jComboBoxLegajoMedico.getSelectedItem().toString()));
                turno.setDniPaciente(Integer.parseInt(jComboBoxDniPaciente.getSelectedItem().toString()));
                turno.setFecha(jTextFieldFecha.getText());
                turno.setCosto(Double.parseDouble(jTextFieldCosto.getText()));
                try {
                    formularioHora = new FormularioHora(panel, turno);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
                panel.mostrar(formularioHora.getFormulario());
            }
        });
    }
    @Override
    public JPanel getFormulario() {return formularioTurno;}
    public ArrayList<Medico> fillarrayMedicos() throws ServiceException {
        ArrayList<Medico> medicos = new ArrayList<Medico>();
        medicos = medicoService.buscarTodos();
        return medicos;
    }
    public ArrayList<Paciente> fillarrayPacientes() throws ServiceException {
        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
        Paciente paciente = new Paciente();
        pacientes = pacienteService.buscarTodos();
        return pacientes;
    }
    public MaskFormatter createMaskFormatter(String mask){
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(mask);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        formatter.setPlaceholderCharacter('_');
        return formatter;
    }
    @Override
    public void decorar(){
        formularioTurno.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioTurno.setBackground(Color.lightGray);
        formularioTurno.setPreferredSize(new Dimension(220, 175));
        formularioTurno.setOpaque(true);
    }

}



