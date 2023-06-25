package gui;

import javax.swing.*;
import java.awt.*;

class ReportFrame extends JFrame {
    private JLabel label;

    public ReportFrame(String legajo, String fecha1, String fecha2, double montoTotal) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(label, BorderLayout.CENTER);
        pack();
    }

}
