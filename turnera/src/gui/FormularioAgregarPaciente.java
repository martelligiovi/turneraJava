package gui;

import service.PacienteService;
import entidades.Paciente;
import service.ServiceException;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioAgregarPaciente extends JPanel implements Formulario,DecorarFormulario,SetFormatoJTextField{
    PacienteService pacienteService;
    JPanel formularioAgregarPaciente;
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


    public FormularioAgregarPaciente (PanelManager panel){
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }

    @Override
    public void creadorFormulario(){
        pacienteService = new PacienteService();
        formularioAgregarPaciente = new JPanel();
        formularioAgregarPaciente.setLayout(new GridLayout(5,2));
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
        formularioAgregarPaciente.add(jLabelDni);
        formularioAgregarPaciente.add(jTextFieldDni);
        formularioAgregarPaciente.add(jLabelNombre);
        formularioAgregarPaciente.add(jTextFieldNombre);
        formularioAgregarPaciente.add(jLabelApellido);
        formularioAgregarPaciente.add(jTextFieldApellido);
        formularioAgregarPaciente.add(jLabelCodObraSocial);
        formularioAgregarPaciente.add(jTextFieldCodObraSocial);
        formularioAgregarPaciente.add(jButtonExit);
        formularioAgregarPaciente.add(jButtonSend);
        setFormatoJTextField(jTextFieldDni);
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
        return formularioAgregarPaciente;
    }
    @Override
    public void decorar(){
        formularioAgregarPaciente.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioAgregarPaciente.setBackground(Color.lightGray);
        formularioAgregarPaciente.setPreferredSize(new Dimension(270, 175));
        formularioAgregarPaciente.setOpaque(true);
    }
    @Override
    public void setFormatoJTextField(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (newText.matches("\\d{0,8}")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
}
