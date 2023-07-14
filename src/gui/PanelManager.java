package gui;

import javax.swing.*;
import java.awt.*;

public class PanelManager{
    public FormularioSeleccionUsuario formularioSeleccionUsuario;
    public FormularioAdmin formularioAdmin;

    JFrame ventana;
    public PanelManager(){
        ventana=new JFrame("Clinica");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formularioSeleccionUsuario = new FormularioSeleccionUsuario(this);
        mostrar(formularioSeleccionUsuario.getFormulario());
        ventana.setVisible(true);
    }
    public void mostrar(JPanel panel){
        ventana.getContentPane().removeAll();
        ventana.getContentPane().add(BorderLayout.SOUTH, panel);
        ventana.getContentPane().validate();
        ventana.getContentPane().repaint();
        ventana.pack();
        ventana.setLocationRelativeTo(null);

    }

    public JFrame getVentana(){
        return ventana;
    }

    public void cerrarVentana(){
        ventana.dispose();
    }
}
