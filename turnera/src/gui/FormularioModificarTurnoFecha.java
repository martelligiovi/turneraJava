package gui;

import entidades.Turno;
import service.ServiceException;
import service.TurnoService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FormularioModificarTurnoFecha implements Formulario,DecorarFormulario{
    JPanel formularioModificarTurnoFecha;
    ArrayList<Turno> turnos;
    JButton jButtonSend;
    JButton jButtonExit;
    JLabel jLabelFecha;
    JComboBox jComboBoxFecha;
    TurnoService turnoService;
    PanelManager panel;
    Turno turno;
    FormularioModificarTurnoFinal formularioModificarTurnoFinal;
    FormularioModificarTurno formularioModificarTurno;

    public FormularioModificarTurnoFecha (PanelManager panel, ArrayList<Turno> turnos){
        this.panel=panel;
        this.turnos=turnos;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        formularioModificarTurnoFecha = new JPanel();
        formularioModificarTurnoFecha.setLayout(new GridLayout(2,2));
        jLabelFecha = new JLabel("Fecha");
        jComboBoxFecha = new JComboBox();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
        for (Turno turno : turnos) {
            jComboBoxFecha.addItem(turno.getFecha());
        }
    }
    @Override
    public void agregarFormulario(){
        formularioModificarTurnoFecha.add(jLabelFecha);
        formularioModificarTurnoFecha.add(jComboBoxFecha);
        formularioModificarTurnoFecha.add(jButtonExit);
        formularioModificarTurnoFecha.add(jButtonSend);
    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(e -> {
            turnoService = new TurnoService();
            turno = turnos.get(0);
            turno.setFecha(jComboBoxFecha.getSelectedItem().toString());
            formularioModificarTurnoFinal = new FormularioModificarTurnoFinal(panel,turno);
            panel.mostrar(formularioModificarTurnoFinal.getFormulario());


        });
        jButtonExit.addActionListener(e -> {
            FormularioAdmin formularioAdmin = new FormularioAdmin(panel);
            panel.mostrar(formularioAdmin.getFormulario());
        });
    }
    @Override
    public void decorar(){
        formularioModificarTurnoFecha.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }
    @Override
    public JPanel getFormulario(){
        return formularioModificarTurnoFecha;
    }

}

