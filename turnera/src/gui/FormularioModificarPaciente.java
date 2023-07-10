package gui;

import entidades.Paciente;
import entidades.Paciente;
import service.PacienteService;
import service.ServiceException;

import javax.swing.*;
import java.awt.*;

public class FormularioModificarPaciente implements Formulario,DecorarFormulario{
    JPanel formularioModificarPaciente;
    PanelManager panel;
    FormularioAdmin formularioAdmin;
    JLabel jLabelDni;
    JTextField jTextFieldDni;
    JButton jButtonSend;
    JButton jButtonExit;
    FormularioModificarPacienteFinal formularioModificarPacienteFinal;
    Paciente paciente;
    PacienteService pacienteService;

    FormularioModificarPaciente(PanelManager panel){
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        formularioModificarPaciente = new JPanel();
        formularioModificarPaciente.setLayout(new GridLayout(2,2));
        jLabelDni = new JLabel("Dni del paciente a modificar");
        jTextFieldDni = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
    }
    @Override
    public void agregarFormulario(){
        formularioModificarPaciente.add(jLabelDni);
        formularioModificarPaciente.add(jTextFieldDni);
        formularioModificarPaciente.add(jButtonExit);
        formularioModificarPaciente.add(jButtonSend);
    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(e -> {
            pacienteService = new PacienteService();
            paciente = new Paciente();
            try {
                paciente.setDni(Integer.parseInt(jTextFieldDni.getText()));
                paciente = pacienteService.buscar(paciente);
                if (paciente==null){
                    JOptionPane.showMessageDialog(null,"Paciente no encontrado");
                }else {
                    formularioModificarPacienteFinal = new FormularioModificarPacienteFinal(panel,paciente);
                    panel.mostrar(formularioModificarPacienteFinal.getFormulario());
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
        formularioModificarPaciente.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }
    @Override
    public JPanel getFormulario(){
        return formularioModificarPaciente;
    }







}
