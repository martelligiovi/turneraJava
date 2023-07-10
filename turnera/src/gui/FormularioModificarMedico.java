package gui;

import entidades.Medico;
import entidades.Paciente;
import service.MedicoService;
import service.ServiceException;

import javax.swing.*;
import java.awt.*;

public class FormularioModificarMedico implements Formulario,DecorarFormulario{
    JPanel formularioModificarMedico;
    PanelManager panel;
    FormularioAdmin formularioAdmin;
    JLabel jLabelLegajo;
    JTextField jTextFieldLegajo;
    JButton jButtonSend;
    JButton jButtonExit;
    FormularioModificarMedicoFinal formularioModificarMedicoFinal;
    Medico medico;
    MedicoService medicoService;

    FormularioModificarMedico(PanelManager panel){
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        formularioModificarMedico = new JPanel();
        formularioModificarMedico.setLayout(new GridLayout(2,2));
        jLabelLegajo = new JLabel("Legajo del medico a modificar");
        jTextFieldLegajo = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
    }
    @Override
    public void agregarFormulario(){
        formularioModificarMedico.add(jLabelLegajo);
        formularioModificarMedico.add(jTextFieldLegajo);
        formularioModificarMedico.add(jButtonExit);
        formularioModificarMedico.add(jButtonSend);
    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(e -> {
            medicoService = new MedicoService();
            medico = new Medico();
            try {
                medico.setLegajo(Integer.parseInt(jTextFieldLegajo.getText()));
                medico = medicoService.buscar(medico);
                if (medico==null){
                    JOptionPane.showMessageDialog(null,"Medico no encontrado");
                }else {
                    formularioModificarMedicoFinal = new FormularioModificarMedicoFinal(panel,medico);
                    panel.mostrar(formularioModificarMedicoFinal.getFormulario());
                }
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Ingrese un valor valido");
            }


        });
        jButtonExit.addActionListener(e -> {
            formularioAdmin = new FormularioAdmin(panel);
            panel.mostrar(formularioAdmin.getFormulario());
        });
    }
    @Override
    public void decorar(){
        formularioModificarMedico.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }
    @Override
    public JPanel getFormulario(){
        return formularioModificarMedico;
    }







}
