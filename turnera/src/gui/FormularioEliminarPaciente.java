package gui;

import entidades.Paciente;
import service.PacienteService;
import service.ServiceException;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioEliminarPaciente implements Formulario,DecorarFormulario,SetFormatoJTextField{
    PacienteService medicoService;
    JPanel formularioEliminarPaciente;
    JButton jButtonSend;
    JButton jButtonExit;
    JLabel jLabelDni;
    JTextField jTextFieldDni;
    PanelManager panel;
    FormularioAdmin formularioAdmin;

    FormularioEliminarPaciente (PanelManager panel){
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }

    @Override
    public void creadorFormulario(){
        medicoService = new PacienteService();
        formularioEliminarPaciente = new JPanel();
        formularioEliminarPaciente.setLayout(new GridLayout(2,2));
        jLabelDni = new JLabel("Dni");
        jTextFieldDni = new JTextField();
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
    }

    @Override
    public void agregarFormulario(){
        formularioEliminarPaciente.add(jLabelDni);
        formularioEliminarPaciente.add(jTextFieldDni);
        formularioEliminarPaciente.add(jButtonExit);
        formularioEliminarPaciente.add(jButtonSend);
        setFormatoJTextField(jTextFieldDni);
    }

    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Paciente paciente = new Paciente();
                    paciente.setDni(Integer.parseInt(jTextFieldDni.getText()));
                    medicoService.eliminar(paciente);
                    JOptionPane.showMessageDialog(null,"Paciente eliminado");
                    formularioAdmin = new FormularioAdmin(panel);
                    panel.mostrar(formularioAdmin.getFormulario());
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null,"Error al eliminar paciente");
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
        formularioEliminarPaciente.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioEliminarPaciente.setBackground(Color.lightGray);
        formularioEliminarPaciente.setPreferredSize(new Dimension(220, 80));
        formularioEliminarPaciente.setOpaque(true);
    }

    @Override
    public JPanel getFormulario(){
        return formularioEliminarPaciente;
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