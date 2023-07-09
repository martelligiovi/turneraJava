package gui;


import service.PacienteService;
import entidades.Paciente;
import service.ServiceException;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPaciente extends JPanel implements Formulario,DecorarFormulario{
    PacienteService pacienteService;
    JPanel formularioPaciente;
    JLabel jLabelNombre;
    JTextField  jTextFieldNombre;
    JButton jButtonSend;
    JButton jButtonExit;
    JLabel jLabelApellido;
    JTextField jTextFieldApellido;
    JLabel jLabelDni;
    JTextField jTextFieldDni;
    JLabel jLabelCodObraSocial;
    JTextField jTextFieldCodObraSocial;
    PanelManager panel;


    public FormularioPaciente (PanelManager panel){
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        pacienteService = new PacienteService();
        formularioPaciente = new JPanel();
        formularioPaciente.setLayout(new GridLayout(5,2));
        jLabelNombre = new JLabel("Nombre");
        jLabelApellido = new JLabel("Apellido");
        jLabelDni = new JLabel("Dni");
        jLabelCodObraSocial = new JLabel("Codigo de obra social");
        jTextFieldNombre = new JTextField();
        jTextFieldApellido = new JTextField();
        jTextFieldDni = new JTextField();
        jTextFieldCodObraSocial = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
    }
    @Override
    public void agregarFormulario(){
        formularioPaciente.add(jLabelDni);
        formularioPaciente.add(jTextFieldDni);
        formularioPaciente.add(jLabelNombre);
        formularioPaciente.add(jTextFieldNombre);
        formularioPaciente.add(jLabelApellido);
        formularioPaciente.add(jTextFieldApellido);
        formularioPaciente.add(jLabelCodObraSocial);
        formularioPaciente.add(jTextFieldCodObraSocial);
        formularioPaciente.add(jButtonExit);
        formularioPaciente.add(jButtonSend);
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
                String codObraSocialText = jTextFieldCodObraSocial.getText().trim();

                if (dniText.isEmpty() || nombreText.isEmpty() || apellidoText.isEmpty() || codObraSocialText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                } else {
                    try {
                        Paciente paciente = new Paciente();
                        paciente.setDni(Integer.parseInt(jTextFieldDni.getText()));
                        paciente.setNombre(jTextFieldNombre.getText());
                        paciente.setApellido(jTextFieldApellido.getText());
                        paciente.setCodObraSocial(Integer.parseInt(jTextFieldCodObraSocial.getText()));

                        pacienteService.guardar(paciente);
                        JOptionPane.showMessageDialog(null, "Se guardo correctamente");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Error en el formato de los campos numéricos");
                        throw new RuntimeException(ex);
                    } catch (ServiceException ex) {
                        JOptionPane.showMessageDialog(null, "Paciente existente");
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
        return formularioPaciente;
    }
    @Override
    public void decorar(){
        formularioPaciente.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioPaciente.setBackground(Color.lightGray);
        formularioPaciente.setPreferredSize(new Dimension(270, 175));
        formularioPaciente.setOpaque(true);
    }
}
