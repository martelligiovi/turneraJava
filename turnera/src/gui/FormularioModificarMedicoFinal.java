package gui;

import entidades.Medico;
import service.MedicoService;
import service.ServiceException;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class FormularioModificarMedicoFinal implements Formulario,DecorarFormulario,SetFormatoJTextField{
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
        setFormatoJTextField(jTextFieldDni);
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
        formularioModificarMedicoFinal.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioModificarMedicoFinal.setBackground(Color.lightGray);
        formularioModificarMedicoFinal.setPreferredSize(new Dimension(220, 160));
        formularioModificarMedicoFinal.setOpaque(true);
    }

    @Override
    public JPanel getFormulario(){
        return formularioModificarMedicoFinal;
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
