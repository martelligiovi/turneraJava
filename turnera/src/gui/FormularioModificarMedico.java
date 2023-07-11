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

public class FormularioModificarMedico implements Formulario,DecorarFormulario,SetFormatoJTextField{
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
        setFormatoJTextField(jTextFieldLegajo);
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
            }
        });

        jButtonExit.addActionListener(e -> {
            formularioAdmin = new FormularioAdmin(panel);
            panel.mostrar(formularioAdmin.getFormulario());
        });
    }

    @Override
    public void decorar(){
        formularioModificarMedico.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioModificarMedico.setBackground(Color.lightGray);
        formularioModificarMedico.setPreferredSize(new Dimension(220, 80));
        formularioModificarMedico.setOpaque(true);
    }

    @Override
    public JPanel getFormulario(){
        return formularioModificarMedico;
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
