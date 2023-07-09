package gui;

import dao.*;
import entidades.Turno;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class FormularioHora extends JPanel implements Formulario,DecorarFormulario{
    FormularioTurno formularioTurno;
    FormularioAdmin formularioAdmin;
    JPanel formularioHora;
    DAOTurno daoTurno;
    JLabel jLabelHora;
    JComboBox jComboBoxHora;
    JButton jButtonSend;
    JButton jButtonExit;
    PanelManager panel;
    Turno turno;
    public FormularioHora (PanelManager panel,Turno turno){
        this.panel=panel;
        this.turno=turno;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        daoTurno = new DAOTurno();
        formularioHora = new JPanel();
        formularioHora.setLayout(new GridLayout(2,2));
        jLabelHora = new JLabel("Hora");
        jComboBoxHora = new JComboBox();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
        ArrayList<String> hs = turno.fillarrayHoras();
        for (String hora : hs) {
            jComboBoxHora.addItem(hora);
        }
    }
    @Override
    public void agregarFormulario(){
        formularioHora.add(jLabelHora);
        formularioHora.add(jComboBoxHora);
        formularioHora.add(jButtonExit);
        formularioHora.add(jButtonSend);
    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Turno turnoDao = new Turno();
                turnoDao.setLegajoMedico(turno.getLegajoMedico());
                turnoDao.setDniPaciente(turno.getDniPaciente());
                turnoDao.setFecha(turno.getFecha().concat(" ").concat(jComboBoxHora.getSelectedItem().toString()));
                turnoDao.setCosto(turno.getCosto());
                try {
                    daoTurno.guardar(turnoDao);
                } catch (DAOException ex) {
                    ex.printStackTrace();
                }
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormulario());
            }
        });
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioTurno = new FormularioTurno(panel);
                panel.mostrar(formularioTurno.getFormulario());
            }
        });
    }
    public DefaultComboBoxModel<String> model(ArrayList<String> horarios) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addAll(horarios); // Agrega todos los elementos del array a la
        return model;
    }
    @Override
    public JPanel getFormulario() {
        return formularioHora;
    }
    @Override
    public void decorar(){
        formularioHora.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioHora.setBackground(Color.lightGray);
        formularioHora.setPreferredSize(new Dimension(220, 70));
        formularioHora.setOpaque(true);
    }

}
