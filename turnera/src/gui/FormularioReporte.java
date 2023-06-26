package gui;

import dao.DAOException;
import dao.DAOMedico;
import dao.DAOTurno;
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

public class FormularioReporte extends JPanel implements Formulario{
    DAOMedico daoMedico;
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
    public DAOTurno daoTurno;

    public FormularioReporte(PanelManager panel) {
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        formularioReporte = new JPanel();
        jButtonSend = new JButton("Buscar");
        jLabelLegajo = new JLabel("Legajo");
        jTextFieldFecha1 = new JFormattedTextField(createMaskFormatter("####/##/##"));
        jTextFieldFecha2 = new JFormattedTextField(createMaskFormatter("####/##/##"));
        jLabelFecha1 = new JLabel("Fecha 1");
        jLabelFecha2 = new JLabel("Fecha 2");
        jButtonExit = new JButton("Salir");
        jComboBoxLegajoMedico = new JComboBox();
        ArrayList<Integer> legajos = fillarrayLegajosMedicos();
        for (Integer m : legajos) {
            jComboBoxLegajoMedico.addItem(m);
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
        jButtonSend.addActionListener(e -> {
            try {
                daoTurno = new DAOTurno();
                listaTurnos = new ArrayList<>();
                ArrayList<Turno> listaTurnos = daoTurno.buscarCobros(jTextFieldFecha1.getText(),jTextFieldFecha2.getText(),(Integer) jComboBoxLegajoMedico.getSelectedItem());
                formularioReporteFinal = new FormularioReporteFinal(panel,listaTurnos,jTextFieldFecha1.getText(),jTextFieldFecha2.getText());
                panel.mostrar(formularioReporteFinal.getFormulario());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "No hay turnos");
                exception.printStackTrace();
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
    public ArrayList<Integer> fillarrayLegajosMedicos(){
        daoMedico = new DAOMedico();
        ArrayList<Medico> medicos = new ArrayList<Medico>();
        ArrayList<Integer> legajos = new ArrayList<Integer>();
        try {
            medicos = daoMedico.buscarTodos();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        for(Medico m : medicos){
            legajos.add(m.getLegajo());
        }
        return legajos;
    }
    public void decorar(){
        formularioReporte.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioReporte.setBackground(Color.lightGray);
        formularioReporte.setSize(1000,1000);
        formularioReporte.setOpaque(true);
    }



}
