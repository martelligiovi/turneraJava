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

public class FormularioHora {
    JPanel formularioHora;
    DAOTurno daoTurno;
    JLabel jLabelHora;
    JComboBox jComboBoxHora;
    JButton jButtonSend;
    JButton jButtonExit;
    PanelManager panel;
    Turno turno;
    public FormularioHora (Turno turno){
        this.turno=turno;
        daoTurno = new DAOTurno();
    }
    public FormularioHora (PanelManager panel,Turno turno){
        this.panel=panel;
        this.turno=turno;
        creadorFormularioHora();
        agregarFormularioHora();
        agregarFuncionesBotones();
    }
    private void creadorFormularioHora(){
        daoTurno = new DAOTurno();
        formularioHora = new JPanel();
        formularioHora.setLayout(new GridLayout(2,2));
        jLabelHora = new JLabel("Hora");
        jComboBoxHora = new JComboBox();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
        ArrayList<String> hs = fillarrayHoras(horariosTomados(this.turno.getFecha(),this.turno.getLegajoMedico()));
        for (String hora : hs) {
            jComboBoxHora.addItem(hora);
        }

    }
    private void agregarFormularioHora(){
        formularioHora.add(jLabelHora);
        formularioHora.add(jComboBoxHora);
        formularioHora.add(jButtonExit);
        formularioHora.add(jButtonSend);
    }
    private void agregarFuncionesBotones(){
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
                FormularioAdmin formularioAdmin = null;
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormularioAdmin());
            }
        });
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioTurno formularioTurno = null;
                formularioTurno = new FormularioTurno(panel);
                panel.mostrar(formularioTurno.getFormularioTurno());
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

    public ArrayList<String> horariosTomados(String fecha, int legajoMedico) {
        ArrayList<String> fechasTomadas = new ArrayList<>();
        ArrayList<String> horariosTomados = new ArrayList<>();
        try {
            fechasTomadas = daoTurno.buscarHorarios(fecha, legajoMedico);
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
    public JPanel getFormularioHora() {
        return formularioHora;
    }


}
