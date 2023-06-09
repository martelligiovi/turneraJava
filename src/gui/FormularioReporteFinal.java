package gui;

import entidades.Turno;
import service.ServiceException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormularioReporteFinal extends JPanel implements DecorarFormulario{
    PanelManager panel;
    JPanel formularioReporteFinal;
    ArrayList<Turno> listaTurnos;
    JLabel jLabelTotal;
    JLabel jLabelIntro;
    DefaultTableModel model;
    GridBagConstraints gbc;
    String fecha1;
    String fecha2;

    public FormularioReporteFinal(PanelManager panel, ArrayList<Turno> turnos, String fecha1, String fecha2){
        this.listaTurnos = turnos;
        this.panel = panel;
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
        crearFormularioReporteFinal();
        decorar();
    }

    private void crearFormularioReporteFinal(){
        formularioReporteFinal = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        jLabelIntro = new JLabel("<html><div style=\"font-size:10px; margin-top: -5px; line-height: 0;\">El médico con legajo: <b><span style=\"font-size:14px;\">" + listaTurnos.get(0).getLegajoMedico() + "</b><br><br></span> entre las fechas <b><span style=\"font-size:14px;\">" + this.fecha1 + "</b></span> y <b><span style=\"font-size:14px;\">" + this.fecha2 + "</div></html>");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        formularioReporteFinal.add(jLabelIntro, gbc);
        gbc.insets = new Insets(10, 0, 10, 0);

        model = new DefaultTableModel();
        model.addColumn("dni paciente");
        model.addColumn("fecha");
        model.addColumn("costo");
        Double total = 0.0;
        for (Turno turno : listaTurnos) {
            model.addRow(new Object[]{turno.getDniPaciente(), turno.getFecha(), turno.getCosto()});
            total += turno.getCosto();
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        formularioReporteFinal.add(scrollPane, gbc);

        jLabelTotal = new JLabel("<html><div style=\"font-size:10px; margin-top: -5px;\">El total de dinero cobrado es: <span style=\"font-size:14px; display: inline-block; margin-left: 20px;\">" + total + "</span></div></html>");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formularioReporteFinal.add(jLabelTotal, gbc);

        JButton exitButton = new JButton("Exit");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        formularioReporteFinal.add(exitButton, gbc);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la pestaña FormularioReporte
                FormularioReporte formularioReporte = null;
                try {
                    formularioReporte = new FormularioReporte(panel);
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
                panel.mostrar(formularioReporte.getFormulario());
            }
        });
    }

    public JPanel getFormulario(){
        return formularioReporteFinal;
    }

    @Override
    public void decorar(){
        formularioReporteFinal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formularioReporteFinal.setBackground(Color.lightGray);
        formularioReporteFinal.setOpaque(true);
    }

}

