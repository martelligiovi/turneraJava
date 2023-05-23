package gui;


import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginFrame frame = new LoginFrame();
                frame.setVisible(true);
                frame.setSize(400, 300); // Ajusta el tamaño de la ventana emergente
                frame.setLocationRelativeTo(null); // Centra la ventana emergente
            }
        });
    }
}
