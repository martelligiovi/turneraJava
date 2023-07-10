package gui;

import entidades.Turno;
import service.ServiceException;
import service.TurnoService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FormularioModificarTurnoFinal implements Formulario, DecorarFormulario{
    Turno turno;
    JPanel formularioModificarTurnoFinal;
    JLabel jLabelCosto;
    JTextField jTextFieldCosto;
    JButton jButtonSend;
    JButton jButtonExit;
    PanelManager panel;
    FormularioAdmin formularioAdmin;
    FormularioModificarTurno formularioModificarTurno;
    TurnoService turnoService;

    public FormularioModificarTurnoFinal(PanelManager panel,Turno turno) {
        this.panel=panel;
        this.turno=turno;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        formularioModificarTurnoFinal = new JPanel();
        formularioModificarTurnoFinal.setLayout(new GridLayout(2,2));
        jLabelCosto = new JLabel("Costo");
        jTextFieldCosto = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
    }
    @Override
    public void agregarFormulario(){
        formularioModificarTurnoFinal.add(jLabelCosto);
        formularioModificarTurnoFinal.add(jTextFieldCosto);
        formularioModificarTurnoFinal.add(jButtonExit);
        formularioModificarTurnoFinal.add(jButtonSend);
    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(e -> {
            turnoService = new TurnoService();
            try {
                turno.setCosto(Double.parseDouble(jTextFieldCosto.getText()));
                turnoService.modificar(turno);
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormulario());
                JOptionPane.showMessageDialog(null, "se modifico el turno");
            } catch (Exception exception) {
                if (exception instanceof NumberFormatException) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido para el costo");
                } else {
                    exception.printStackTrace();
                }
            }

        });
            jButtonExit.addActionListener(e -> {
                try {
                    formularioModificarTurno = new FormularioModificarTurno(panel);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
                panel.mostrar(formularioModificarTurno.getFormulario());
        });
    }
    @Override
    public void decorar(){
        formularioModificarTurnoFinal.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
    }
    @Override
    public JPanel getFormulario(){
        return formularioModificarTurnoFinal;
    }
}
