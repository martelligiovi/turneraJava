package gui;

import entidades.Medico;
import service.MedicoService;
import service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioMedico extends JPanel implements Formulario,DecorarFormulario{

    MedicoService medicoService;
    JPanel formularioMedico;
    JLabel jLabelNombre;
    JTextField  jTextFieldNombre;
    JButton jButtonSend;
    JButton jButtonExit;
    JLabel jLabelApellido;
    JTextField jTextFieldApellido;
    JLabel jLabelDni;
    JTextField jTextFieldDni;
    JLabel jLabelLegajo;
    JTextField jTextFieldLegajo;
    PanelManager panel;

    public FormularioMedico (PanelManager panel){
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        medicoService = new MedicoService();
        formularioMedico = new JPanel();
        formularioMedico.setLayout(new GridLayout(5,2));
        jLabelNombre = new JLabel("Nombre");
        jLabelApellido = new JLabel("Apellido");
        jLabelDni = new JLabel("Dni");
        jLabelLegajo = new JLabel("Legajo");
        jTextFieldNombre = new JTextField();
        jTextFieldApellido = new JTextField();
        jTextFieldDni = new JTextField();
        jTextFieldLegajo = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
    }
    @Override
    public void agregarFormulario(){
        formularioMedico.add(jLabelDni);
        formularioMedico.add(jTextFieldDni);
        formularioMedico.add(jLabelNombre);
        formularioMedico.add(jTextFieldNombre);
        formularioMedico.add(jLabelApellido);
        formularioMedico.add(jTextFieldApellido);
        formularioMedico.add(jLabelLegajo);
        formularioMedico.add(jTextFieldLegajo);
        formularioMedico.add(jButtonExit);
        formularioMedico.add(jButtonSend);

    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioAdmin formularioAdmin = null;
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormulario());
            }
        });
        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dniText = jTextFieldDni.getText().trim();
                String nombreText = jTextFieldNombre.getText().trim();
                String apellidoText = jTextFieldApellido.getText().trim();
                String legajoText = jTextFieldLegajo.getText().trim();

                if (dniText.isEmpty() || nombreText.isEmpty() || apellidoText.isEmpty() || legajoText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                } else {
                    try {
                        Medico medico = new Medico();
                        medico.setDni(Integer.parseInt(dniText));
                        medico.setNombre(nombreText);
                        medico.setApellido(apellidoText);
                        medico.setLegajo(Integer.parseInt(legajoText));

                        medicoService.guardar(medico);
                        JOptionPane.showMessageDialog(null, "Se guardó correctamente");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Error en el formato de los campos numéricos");
                        throw new RuntimeException(ex);
                    } catch (ServiceException ex) {
                        JOptionPane.showMessageDialog(null, "Medico existente");
                        throw new RuntimeException(ex);
                    }
                    FormularioAdmin formularioAdmin = null;
                    formularioAdmin = new FormularioAdmin(panel);
                    panel.mostrar(formularioAdmin.getFormulario());
                }

            }
        });
    }


    public JPanel getFormulario() {
        return formularioMedico;
    }
    @Override
    public void decorar(){
        formularioMedico.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioMedico.setBackground(Color.lightGray);
        formularioMedico.setPreferredSize(new Dimension(220, 175));
        formularioMedico.setOpaque(true);
    }
}
