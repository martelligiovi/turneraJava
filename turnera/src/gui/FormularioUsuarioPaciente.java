package gui;


import entidades.Turno;
import service.ServiceException;
import service.TurnoService;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class FormularioUsuarioPaciente extends JPanel implements Formulario,DecorarFormulario{
    PanelManager panel;
    JPanel formularioUsuarioPasiente;
    FormularioSeleccionUsuario formularioSeleccionUsuario;
    FormularioTurnosPaciente formularioTurnosPaciente;
    JLabel jLabelDni;
    JTextField jTextFieldDni;
    JButton jButtonSend;
    JButton jButtonExit;
    TurnoService turnoService;
    public FormularioUsuarioPaciente(PanelManager panel){
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }


    @Override
    public void creadorFormulario() {
        turnoService = new TurnoService();
        formularioUsuarioPasiente = new JPanel();
        formularioUsuarioPasiente.setLayout(new GridLayout(2,2));
        jLabelDni = new JLabel("Ingrese su dni:");
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
        jTextFieldDni = new JFormattedTextField();
        setFormatoJTextFieldDni();
    }

    @Override
    public void agregarFormulario() {
        formularioUsuarioPasiente.add(jLabelDni);
        formularioUsuarioPasiente.add(jTextFieldDni);
        formularioUsuarioPasiente.add(jButtonExit);
        formularioUsuarioPasiente.add(jButtonSend);

    }

    @Override
    public void agregarFuncionesBotones() {
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioSeleccionUsuario = new FormularioSeleccionUsuario(panel);
                panel.mostrar(formularioSeleccionUsuario.getFormulario());
            }
        });
        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Turno> turnos = turnoService.buscarTurnosPaciente(Integer.parseInt(jTextFieldDni.getText()));
                    if (turnos.size() == 0) {
                        JOptionPane.showMessageDialog(null, "No hay turnos disponibles");
                    } else {
                        formularioTurnosPaciente = new FormularioTurnosPaciente(panel, turnos);
                        panel.mostrar(formularioTurnosPaciente.getFormulario());
                    }
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Override
    public JPanel getFormulario() {
        return formularioUsuarioPasiente;
    }
    public NumberFormatter getNumberFormatter(){
        NumberFormatter formatter = new NumberFormatter();
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(99999999); // Máximo 8 dígitos
        return formatter;
    }
    public void setFormatoJTextFieldDni() {
        ((AbstractDocument) jTextFieldDni.getDocument()).setDocumentFilter(new DocumentFilter() {
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
    @Override
    public void decorar(){
        formularioUsuarioPasiente.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioUsuarioPasiente.setBackground(Color.lightGray);
        formularioUsuarioPasiente.setPreferredSize(new Dimension(220, 75));
        formularioUsuarioPasiente.setOpaque(true);
    }


}
