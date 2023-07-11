package gui;

import entidades.Paciente;
import service.PacienteService;
import service.ServiceException;

import javax.swing.*;
import java.awt.*;

public class FormularioModificarPacienteFinal implements Formulario,DecorarFormulario{
    JLabel jLabelNombre;
    JLabel jLabelApellido;
    JLabel jLabelCodObraSocial;
    JTextField jTextFieldNombre;
    JTextField jTextFieldApellido;
    JTextField jTextFieldCodObraSocial;
    JButton jButtonSend;
    JButton jButtonExit;
    JPanel formularioModificarPacienteFinal;
    PanelManager panel;
    FormularioAdmin formularioAdmin;
    Paciente paciente;
    public FormularioModificarPacienteFinal(PanelManager panel,Paciente paciente){
        this.panel=panel;
        this.paciente=paciente;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        formularioModificarPacienteFinal = new JPanel();
        formularioModificarPacienteFinal.setLayout(new GridLayout(4,2));
        jLabelNombre = new JLabel("Nombre");
        jLabelApellido = new JLabel("Apellido");
        jLabelCodObraSocial = new JLabel("Codigo ObraSocial");
        jTextFieldNombre = new JTextField();
        jTextFieldApellido = new JTextField();
        jTextFieldCodObraSocial = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
    }
    @Override
    public void agregarFormulario(){
        formularioModificarPacienteFinal.add(jLabelNombre);
        formularioModificarPacienteFinal.add(jTextFieldNombre);
        formularioModificarPacienteFinal.add(jLabelApellido);
        formularioModificarPacienteFinal.add(jTextFieldApellido);
        formularioModificarPacienteFinal.add(jLabelCodObraSocial);
        formularioModificarPacienteFinal.add(jTextFieldCodObraSocial);
        formularioModificarPacienteFinal.add(jButtonExit);
        formularioModificarPacienteFinal.add(jButtonSend);
    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(e -> {
            try {
                paciente.setNombre(jTextFieldNombre.getText());
                paciente.setApellido(jTextFieldApellido.getText());
                paciente.setCodObraSocial(Integer.parseInt(jTextFieldCodObraSocial.getText()));
                PacienteService pacienteService = new PacienteService();
                pacienteService.modificar(paciente);
                JOptionPane.showMessageDialog(null,"Paciente modificado");
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormulario());
            } catch (ServiceException ex) {
                JOptionPane.showMessageDialog(null,"Error al modificar un paciente");
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"ingrese un valor valido en el campo CodObraSocial");
            }

        });
        jButtonExit.addActionListener(e -> {
            formularioAdmin = new FormularioAdmin(panel);
            panel.mostrar(formularioAdmin.getFormulario());
        });
    }
    @Override
    public void decorar(){
        formularioModificarPacienteFinal.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioModificarPacienteFinal.setBackground(Color.lightGray);
        formularioModificarPacienteFinal.setPreferredSize(new Dimension(220, 75));
        formularioModificarPacienteFinal.setOpaque(true);
    }
    @Override
    public JPanel getFormulario(){
        return formularioModificarPacienteFinal;
    }

}
