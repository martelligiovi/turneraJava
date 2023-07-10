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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                try {
                    turno = new Turno();
                    turno.setLegajoMedico(Integer.parseInt(jComboBoxLegajoMedico.getSelectedItem().toString().split("-")[0]));
                    turno.setDniPaciente(Integer.parseInt(jComboBoxDniPaciente.getSelectedItem().toString().split("-")[0]));


                    String fechaText = jTextFieldFecha.getText().trim();
                    String costoText = jTextFieldCosto.getText().trim();

                    if (costoText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese el costo");
                    } else {
                        LocalDate currentDate = LocalDate.now();
                        LocalDate selectedDate = LocalDate.parse(fechaText, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

                        if (selectedDate.isAfter(currentDate)) {
                            turno.setFecha(fechaText);
                            turno.setCosto(Double.parseDouble(costoText));
                            formularioHora = new FormularioHora(panel, turno);
                            panel.mostrar(formularioHora.getFormulario());
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha posterior a la fecha actual");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el formato de los campos numéricos");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el servicio: " + ex.getMessage());
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el formato de la fecha");
                }
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
        formularioTurno.setPreferredSize(new Dimension(420, 175));
        formularioTurno.setOpaque(true);
    }

}



