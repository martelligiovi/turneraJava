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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioEliminarMedico implements Formulario,DecorarFormulario,SetFormatoJTextField{
    JPanel formularioEliminarMedico;
    JButton jButtonSend;
    JButton jButtonExit;
    JLabel jLabelLegajo;
    JTextField jTextFieldLegajo;
    PanelManager panel;
    FormularioAdmin formularioAdmin;
    MedicoService medicoService;

    FormularioEliminarMedico (PanelManager panel){
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }

    @Override
    public void creadorFormulario(){
        formularioEliminarMedico = new JPanel();
        formularioEliminarMedico.setLayout(new GridLayout(2,2));
        jLabelLegajo = new JLabel("Legajo");
        jTextFieldLegajo = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
    }

    @Override
    public void agregarFormulario(){
        formularioEliminarMedico.add(jLabelLegajo);
        formularioEliminarMedico.add(jTextFieldLegajo);
        formularioEliminarMedico.add(jButtonExit);
        formularioEliminarMedico.add(jButtonSend);
        setFormatoJTextField(jTextFieldLegajo);
    }

    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Medico medico = new Medico();
                    medicoService = new MedicoService();
                    medico.setLegajo(Integer.parseInt(jTextFieldLegajo.getText()));
                    medicoService.eliminar(medico);
                    JOptionPane.showMessageDialog(null,"Medico eliminado");
                    formularioAdmin = new FormularioAdmin(panel);
                    panel.mostrar(formularioAdmin.getFormulario());
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null,"Error al eliminar medico");
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Ingrese un numero");
                }
            }
        });

        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormulario());
            }
        });
    }

    @Override
    public void decorar(){
        formularioEliminarMedico.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioEliminarMedico.setBackground(Color.lightGray);
        formularioEliminarMedico.setPreferredSize(new Dimension(220, 80));
        formularioEliminarMedico.setOpaque(true);

    }
    @Override
    public JPanel getFormulario(){
        return formularioEliminarMedico;
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

