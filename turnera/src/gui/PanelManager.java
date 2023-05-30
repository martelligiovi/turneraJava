package gui;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    private FormularioMedico formularioMedico;
    JFrame ventana;
    public PanelManager(){
        ventana = new JFrame("Turnera");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formularioMedico = new FormularioMedico(this);
        mostrar(formularioMedico);
    }
    public void mostrar(JPanel panel){
        ventana.getContentPane().removeAll();
        ventana.getContentPane().add(BorderLayout.SOUTH, panel);
        ventana.getContentPane().validate();
        ventana.getContentPane().repaint();
        ventana.pack();
    }
}
