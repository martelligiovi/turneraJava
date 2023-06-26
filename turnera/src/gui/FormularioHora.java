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
        ArrayList<String> hs = fillarrayHoras(horariosTomados(this.turno.getFecha(),this.turno.getLegajoMedico(),this.turno.getDniPaciente()));
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
    public ArrayList<String> fillarrayHoras(ArrayList<String> horariosTomados){
        int horaInicial = 10; // Hora inicial (10:00)
        ArrayList<String> horariosTurnos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            horariosTurnos.add(String.format("%02d:00", horaInicial));
            horariosTurnos.add(String.format("%02d:30", horaInicial));
            horaInicial++;
        }
        horariosTurnos.removeAll(horariosTomados);
        return horariosTurnos;
    }

    public ArrayList<String> horariosTomados(String fecha, int legajoMedico, int dniPaciente) {
        ArrayList<String> fechasTomadas = new ArrayList<>();
        ArrayList<String> horariosTomados = new ArrayList<>();
        try {
            fechasTomadas = daoTurno.buscarHorariosPorMedico(fecha, legajoMedico);
            System.out.println(fechasTomadas);
            fechasTomadas.addAll(daoTurno.buscarHorariosPorPaciente(fecha,dniPaciente));
            System.out.println(fechasTomadas);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        for (String fechaHora : fechasTomadas) {
            String[] partes = fechaHora.split(" ");
            String hora = partes[1]; // Segunda parte es la hora
            horariosTomados.add(hora);
        }
        return horariosTomados;
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
