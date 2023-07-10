package gui;

import service.TurnoService;
import entidades.Turno;
import service.ServiceException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FormularioUsuarioMedico extends JPanel implements Formulario,DecorarFormulario{
    PanelManager panel;
    JPanel formularioUsuarioMedico;
    FormularioSeleccionUsuario formularioSeleccionUsuario;
    FormularioTurnosMedicos formularioTurnosMedicos;
    TurnoService turnoService;
    JLabel jLabelLegajo;
    JTextField jTextFieldLegajo;
    JLabel jLabelFecha;
    JFormattedTextField jTextFieldFecha;
    JButton jButtonSend;
    JButton jButtonExit;

    public FormularioUsuarioMedico(PanelManager panel){
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }
    @Override
    public void creadorFormulario(){
        turnoService = new TurnoService();
        formularioUsuarioMedico = new JPanel();
        formularioUsuarioMedico.setLayout(new GridLayout(3,2));
        jLabelLegajo = new JLabel("ingrese su legajo");
        jTextFieldLegajo = new JTextField();
        jLabelFecha = new JLabel("Fecha");
        jTextFieldFecha = new JFormattedTextField(createMaskFormatter("####/##/##"));
        jButtonSend = new JButton("Enviar");
        jButtonExit = new JButton("Salir");
    }
    @Override
    public void agregarFormulario(){
        formularioUsuarioMedico.add(jLabelLegajo);
        formularioUsuarioMedico.add(jTextFieldLegajo);
        formularioUsuarioMedico.add(jLabelFecha);
        formularioUsuarioMedico.add(jTextFieldFecha);
        formularioUsuarioMedico.add(jButtonExit);
        formularioUsuarioMedico.add(jButtonSend);
    }
    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechaText = jTextFieldFecha.getText().trim();
                String legajoText = jTextFieldLegajo.getText().trim();

                // Verificar que se haya ingresado una fecha válida
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                try {
                    LocalDate fecha = LocalDate.parse(fechaText, dateFormatter);
                    ArrayList<Turno> turnos = turnoService.buscarTurnosMedico(fechaText, Integer.parseInt(legajoText));

                    if (turnos.size() == 0) {
                        JOptionPane.showMessageDialog(null, "No hay turnos disponibles");
                    } else {
                        formularioTurnosMedicos = new FormularioTurnosMedicos(panel, turnos);
                        panel.mostrar(formularioTurnosMedicos.getFormulario());
                    }
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto.\n Use el formato yyyy/MM/dd");
                }
            }
        });
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioSeleccionUsuario = new FormularioSeleccionUsuario(panel);
                panel.mostrar(formularioSeleccionUsuario.getFormulario());
            }
        });
    }
    @Override
    public JPanel getFormulario() {
        return formularioUsuarioMedico;
    }
    public MaskFormatter createMaskFormatter(String mask) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }
    @Override
    public void decorar(){
        formularioUsuarioMedico.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioUsuarioMedico.setBackground(Color.lightGray);
        formularioUsuarioMedico.setPreferredSize(new Dimension(220, 105));
        formularioUsuarioMedico.setOpaque(true);
    }


}
