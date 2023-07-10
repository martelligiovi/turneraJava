package gui;

import service.MedicoService;
import service.ServiceException;
import service.TurnoService;
import entidades.Medico;
import entidades.Paciente;
import entidades.Turno;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class FormularioReporte extends JPanel implements Formulario,DecorarFormulario{
    MedicoService medicoService;
    ArrayList<Turno> listaTurnos;
    FormularioReporteFinal formularioReporteFinal;
    FormularioAdmin formularioAdmin;
    JPanel formularioReporte;
    PanelManager panel;
    JFormattedTextField jTextFieldFecha1;
    JLabel jLabelFecha1;
    JFormattedTextField jTextFieldFecha2;
    JLabel jLabelFecha2;
    JComboBox jComboBoxLegajoMedico;
    JLabel jLabelLegajo;
    JButton jButtonSend;
    JButton jButtonExit;
    TurnoService turnoService;

    public FormularioReporte(PanelManager panel) throws ServiceException {
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario() throws ServiceException {
        formularioReporte = new JPanel();
        jButtonSend = new JButton("Buscar");
        jLabelLegajo = new JLabel("Legajo");
        jTextFieldFecha1 = new JFormattedTextField(createMaskFormatter("####/##/##"));
        jTextFieldFecha2 = new JFormattedTextField(createMaskFormatter("####/##/##"));
        jLabelFecha1 = new JLabel("Fecha 1");
        jLabelFecha2 = new JLabel("Fecha 2");
        jButtonExit = new JButton("Salir");
        jComboBoxLegajoMedico = new JComboBox();
        ArrayList<Medico> medicos = fillarrayMedicos();
        for (Medico m : medicos) {
            jComboBoxLegajoMedico.addItem(m.getLegajo() + "-" + m.getNombre() + " " + m.getApellido());
        }
        formularioReporte.setLayout(new GridLayout(4,2));
    }
    @Override
    public void agregarFormulario(){
        formularioReporte.add(jLabelLegajo);
        formularioReporte.add(jComboBoxLegajoMedico);
        formularioReporte.add(jLabelFecha1);
        formularioReporte.add(jTextFieldFecha1);
        formularioReporte.add(jLabelFecha2);
        formularioReporte.add(jTextFieldFecha2);
        formularioReporte.add(jButtonExit);
        formularioReporte.add(jButtonSend);
    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    turnoService = new TurnoService();
                    listaTurnos = new ArrayList<>();
                    ArrayList<Turno> listaTurnos = turnoService.buscarCobros(jTextFieldFecha1.getText(),jTextFieldFecha2.getText(),(Integer) jComboBoxLegajoMedico.getSelectedItem());
                    formularioReporteFinal = new FormularioReporteFinal(panel,listaTurnos,jTextFieldFecha1.getText(),jTextFieldFecha2.getText());
                    panel.mostrar(formularioReporteFinal.getFormulario());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "No hay turnos");
                    exception.printStackTrace();
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
    public JPanel getFormulario() {
        return formularioReporte;
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
    public ArrayList<Medico> fillarrayMedicos() throws ServiceException {
        ArrayList<Medico> medicos = new ArrayList<Medico>();
        medicoService = new MedicoService();
        medicos = medicoService.buscarTodos();
        return medicos;
    }

    @Override
    public void decorar(){
        formularioReporte.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioReporte.setBackground(Color.lightGray);
        formularioReporte.setPreferredSize(new Dimension(450, 140));
        formularioReporte.setOpaque(true);
    }



}
