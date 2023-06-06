package gui;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    public FormularioMedico formularioMedico;
    public FormularioPaciente formularioPaciente;
    JFrame ventana;
    public PanelManager()
    {
        ventana=new JFrame("Clinica");
        ventana.setSize(500,500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        formularioMedico = new FormularioMedico(this);
        mostrar(formularioMedico.getFormularioMedico());
        mostrar(formularioPaciente.getFormularioPaciente());
    }
    public void mostrar(JPanel panel){
        ventana.getContentPane().removeAll();
        ventana.getContentPane().add(BorderLayout.SOUTH, panel);
        ventana.getContentPane().validate();
        ventana.getContentPane().repaint();
        ventana.pack();
    }
}
