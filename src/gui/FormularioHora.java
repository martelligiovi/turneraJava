package gui;

import entidades.Turno;
import service.ServiceException;
import service.TurnoService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.*;

public class FormularioHora extends JPanel implements Formulario,DecorarFormulario{
    FormularioAgregarTurno formularioAgregarTurno;
    FormularioAdmin formularioAdmin;
    JPanel formularioHora;
    TurnoService turnoService;
    JLabel jLabelHora;
    JComboBox jComboBoxHora;
    JButton jButtonSend;
    JButton jButtonExit;
    PanelManager panel;
    Turno turno;

    public FormularioHora (PanelManager panel,Turno turno) throws ServiceException {
        this.panel=panel;
        this.turno=turno;
        System.out.println("ok");
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }

    @Override
    public void creadorFormulario() throws ServiceException {
        turnoService = new TurnoService();
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
                Turno elemento = new Turno();
                elemento.setLegajoMedico(turno.getLegajoMedico());
                elemento.setDniPaciente(turno.getDniPaciente());
                elemento.setFecha(turno.getFecha().concat(" ").concat(jComboBoxHora.getSelectedItem().toString()));
                elemento.setCosto(turno.getCosto());
                try {
                    turnoService.guardar(elemento);
                    JOptionPane.showMessageDialog(null, "se guardo correctamente");
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormulario());
            }
        });

        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    formularioAgregarTurno = new FormularioAgregarTurno(panel);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                panel.mostrar(formularioAgregarTurno.getFormulario());
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
        formularioHora.setPreferredSize(new Dimension(220, 80));
        formularioHora.setOpaque(true);
    }

}
