package gui;

import entidades.Medico;
import service.MedicoService;
import service.ServiceException;

import javax.swing.*;
import java.awt.*;

public class FormularioModificarMedicoFinal implements Formulario,DecorarFormulario{
    JLabel jLabelNombre;
    JLabel jLabelApellido;
    JLabel jLabelDni;
    JTextField jTextFieldNombre;
    JTextField jTextFieldApellido;
    JTextField jTextFieldDni;
    JButton jButtonSend;
    JButton jButtonExit;
    JPanel formularioModificarMedicoFinal;
    PanelManager panel;
    FormularioAdmin formularioAdmin;
    Medico medico;
    public FormularioModificarMedicoFinal(PanelManager panel,Medico medico){
        this.panel=panel;
        this.medico=medico;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        formularioModificarMedicoFinal = new JPanel();
        formularioModificarMedicoFinal.setLayout(new GridLayout(4,2));
        jLabelNombre = new JLabel("Nombre");
        jLabelApellido = new JLabel("Apellido");
        jLabelDni = new JLabel("Dni");
        jTextFieldNombre = new JTextField();
        jTextFieldApellido = new JTextField();
        jTextFieldDni = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
    }
    @Override
    public void agregarFormulario(){
        formularioModificarMedicoFinal.add(jLabelNombre);
        formularioModificarMedicoFinal.add(jTextFieldNombre);
        formularioModificarMedicoFinal.add(jLabelApellido);
        formularioModificarMedicoFinal.add(jTextFieldApellido);
        formularioModificarMedicoFinal.add(jLabelDni);
        formularioModificarMedicoFinal.add(jTextFieldDni);
        formularioModificarMedicoFinal.add(jButtonExit);
        formularioModificarMedicoFinal.add(jButtonSend);
    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(e -> {
            try {
                medico.setNombre(jTextFieldNombre.getText());
                medico.setApellido(jTextFieldApellido.getText());
                medico.setDni(Integer.parseInt(jTextFieldDni.getText()));
                MedicoService medicoService = new MedicoService();
                medicoService.modificar(medico);
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormulario());
                JOptionPane.showMessageDialog(null,"se modifico el medico");
            } catch (ServiceException ex) {
                JOptionPane.showMessageDialog(null,"Error al modificar un medico");
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"ingrese un valor valido en el campo dni");
            }
        });
        jButtonExit.addActionListener(e -> {
            formularioAdmin = new FormularioAdmin(panel);
            panel.mostrar(formularioAdmin.getFormulario());
        });
    }
    @Override
    public void decorar(){
        formularioModificarMedicoFinal.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }
    @Override
    public JPanel getFormulario(){
        return formularioModificarMedicoFinal;
    }

}
