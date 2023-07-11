package gui;

import service.ServiceException;
import service.TurnoService;
import entidades.Turno;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class FormularioReporteAdicional implements Formulario,DecorarFormulario{
    JPanel formularioReporteAdicional;
    JButton jButtonSend;
    JFormattedTextField jTextFieldFecha1;
    JFormattedTextField jTextFieldFecha2;
    JLabel jLabelFecha1;
    JLabel jLabelFecha2;
    JButton jButtonExit;
    PanelManager panel;
    FormularioAdmin formularioAdmin;
    FormularioReporteAdicionalFinal formularioReporteAdicionalFinal;
    ArrayList<Turno> listaTurnos;

    public FormularioReporteAdicional(PanelManager panel) throws ServiceException{
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }

    @Override
    public void creadorFormulario() throws ServiceException{
        formularioReporteAdicional = new JPanel();
        jButtonSend = new JButton("Buscar");
        jTextFieldFecha1 = new JFormattedTextField(createMaskFormatter("####/##/##"));
        jTextFieldFecha2 = new JFormattedTextField(createMaskFormatter("####/##/##"));
        jLabelFecha1 = new JLabel("Fecha 1");
        jLabelFecha2 = new JLabel("Fecha 2");
        jButtonExit = new JButton("Salir");
        formularioReporteAdicional.setLayout(new GridLayout(3,2));
    }

    @Override
    public void agregarFormulario(){
        formularioReporteAdicional.add(jLabelFecha1);
        formularioReporteAdicional.add(jTextFieldFecha1);
        formularioReporteAdicional.add(jLabelFecha2);
        formularioReporteAdicional.add(jTextFieldFecha2);
        formularioReporteAdicional.add(jButtonSend);
        formularioReporteAdicional.add(jButtonExit);
    }

    @Override
    public void agregarFuncionesBotones(){
        jButtonSend.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                listaTurnos = new ArrayList<>();
                TurnoService turnoService = new TurnoService();
                try{
                    listaTurnos = turnoService.calcularSumaCobrosPorRango(jTextFieldFecha1.getText(),jTextFieldFecha2.getText());
                    formularioReporteAdicionalFinal = new FormularioReporteAdicionalFinal(panel,listaTurnos);
                    panel.mostrar(formularioReporteAdicionalFinal.getFormulario());
                } catch (ServiceException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
        jButtonExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                formularioAdmin = new FormularioAdmin(panel);
                panel.mostrar(formularioAdmin.getFormulario());
            }
        });
    }

    @Override
    public void decorar(){
        formularioReporteAdicional.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioReporteAdicional.setBackground(Color.lightGray);
        formularioReporteAdicional.setPreferredSize(new Dimension(220, 120));
        formularioReporteAdicional.setOpaque(true);
    }

    @Override
    public JPanel getFormulario(){
        return formularioReporteAdicional;
    }

    private MaskFormatter createMaskFormatter(String mask){
        MaskFormatter formatter = null;
        try{
            formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException e){
            e.printStackTrace();
        }return formatter;
    }

}
