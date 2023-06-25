package gui;

import dao.*;
import entidades.Medico;
import entidades.Paciente;
import entidades.Turno;
import serrvice.ServiceException;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.*;

public class FormularioTurno extends JPanel{
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
    private DAOMedico daoMedico;
    private DAOPaciente daoPaciente;


    public FormularioTurno (PanelManager panel){
        this.panel=panel;
        creadorFormularioTurno();
        agregarFormulario();
        agregarFuncionesBotones();
    }
    private void creadorFormularioTurno(){
        daoMedico = new DAOMedico();
        daoPaciente = new DAOPaciente();
        formularioTurno = new JPanel();
        formularioTurno.setLayout(new GridLayout(5,2));
        jLabelLegajoMedico = new JLabel("legajo midico");
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
    private void agregarFormulario(){
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
    private void agregarFuncionesBotones(){
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioAdmin formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormularioAdmin());
            }
        });

        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Turno turno = new Turno();
                turno.setLegajoMedico(Integer.parseInt(jComboBoxLegajoMedico.getSelectedItem().toString()));
                turno.setDniPaciente(Integer.parseInt(jComboBoxDniPaciente.getSelectedItem().toString()));
                turno.setFecha(jTextFieldFecha.getText());
                turno.setCosto(Double.parseDouble(jTextFieldCosto.getText()));
                FormularioHora formularioHora = new FormularioHora(panel, turno);
                panel.mostrar(formularioHora.getFormularioHora());
            }
        });
    }
    public JPanel getFormularioTurno() {
        return formularioTurno;
    }
    public ArrayList<Medico> fillarrayMedicos(){
        ArrayList<Medico> medicos = new ArrayList<Medico>();
        try {
            medicos = daoMedico.buscarTodos();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return medicos;
    }
    public ArrayList<Paciente> fillarrayPacientes(){
        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
        Paciente pacienteService = new Paciente();
        try {
            pacientes = daoPaciente.buscarTodos();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return pacientes;
    }
    private MaskFormatter createMaskFormatter(String mask) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }

}



