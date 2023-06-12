package gui;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    public FormularioSeleccionUsuario formularioSeleccionUsuario;
    public FormularioAdmin formularioAdmin;

    JFrame ventana;
    public PanelManager()
    {
        ventana=new JFrame("Clinica");
        ventana.setSize(500,500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        formularioSeleccionUsuario = new FormularioSeleccionUsuario(this);
        mostrar(formularioSeleccionUsuario.getformularioSeleccionUsuario());
    }
    public void mostrar(JPanel panel){
        ventana.getContentPane().removeAll();
        ventana.getContentPane().add(BorderLayout.SOUTH, panel);
        ventana.getContentPane().validate();
        ventana.getContentPane().repaint();
        ventana.pack();
    }
    //hacer el mostrarPanelAdmin
    public void mostrarFormularioAdmin(){
        JPanel formularioAdmin = new FormularioAdmin(this);
        ventana.getContentPane().removeAll();
        ventana.getContentPane().add(BorderLayout.SOUTH, formularioAdmin);
        ventana.getContentPane().validate();
        ventana.getContentPane().repaint();
        ventana.pack();
    }



}
