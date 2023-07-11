package gui;

import entidades.Turno;
import service.ServiceException;
import service.TurnoService;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FormularioEliminarTurnoFecha implements Formulario,DecorarFormulario{
    JPanel formularioEliminarTurnoFecha;
    ArrayList<Turno> turnos;
    JButton jButtonSend;
    JButton jButtonExit;
    JLabel jLabelFecha;
    JComboBox jComboBoxFecha;
    TurnoService turnoService;
    PanelManager panel;
    Turno turno;
    FormularioAdmin formularioAdmin;

    public FormularioEliminarTurnoFecha (PanelManager panel, ArrayList<Turno> turnos){
         this.panel=panel;
         this.turnos=turnos;
         creadorFormulario();
         agregarFormulario();
         agregarFuncionesBotones();
         decorar();
    }

    @Override
    public void creadorFormulario(){
        formularioEliminarTurnoFecha = new JPanel();
        formularioEliminarTurnoFecha.setLayout(new GridLayout(2,2));
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
        formularioEliminarTurnoFecha.add(jLabelFecha);
        formularioEliminarTurnoFecha.add(jComboBoxFecha);
        formularioEliminarTurnoFecha.add(jButtonExit);
        formularioEliminarTurnoFecha.add(jButtonSend);
    }

    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(e -> {
            turnoService = new TurnoService();
            //quiero que turno sea el turno sea el primer nodo de turnos y que tenga la fecha que selecciono en el combobox
            turno = turnos.get(0);
            turno.setFecha(jComboBoxFecha.getSelectedItem().toString());
            try {
                turnoService.eliminar(turno);
            } catch (ServiceException ex) {
                JOptionPane.showMessageDialog(null,"Error al eliminar turno ");
                throw new RuntimeException(ex);
            }
            formularioAdmin = new FormularioAdmin(panel);
            panel.mostrar(formularioAdmin.getFormulario());
            JOptionPane.showMessageDialog(null,"Turno eliminado");
        });

        jButtonExit.addActionListener(e -> {
            FormularioAdmin formularioAdmin = new FormularioAdmin(panel);
            panel.mostrar(formularioAdmin.getFormulario());
        });
    }

    @Override
    public void decorar(){
        formularioEliminarTurnoFecha.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioEliminarTurnoFecha.setBackground(Color.lightGray);
        formularioEliminarTurnoFecha.setPreferredSize(new Dimension(220, 80));
        formularioEliminarTurnoFecha.setOpaque(true);
    }

    @Override
    public JPanel getFormulario(){
        return formularioEliminarTurnoFecha;
    }

}
