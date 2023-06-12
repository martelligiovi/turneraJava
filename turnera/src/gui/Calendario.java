package gui;
import javax.swing.*;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.IDateEvaluator;
import java.awt.*;
import java.util.*;

public class Calendario extends JFrame {
    public Calendario() {
        setTitle("Calendario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JCalendar calendar = new JCalendar();
        calendar.setDecorationBackgroundColor(Color.WHITE); // Cambia el color de fondo de la marca
        calendar.getDayChooser().addDateEvaluator(new CustomDateEvaluator()); // Agrega el evaluador de fechas personalizado
        getContentPane().add(calendar);

        pack();
        setLocationRelativeTo(null);
    }}

// Im