package gui;
import javax.swing.*;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.IDateEvaluator;
import java.awt.*;
import java.util.*;
public class CustomDateEvaluator implements IDateEvaluator {
    private Calendar markedDate;

    public CustomDateEvaluator() {
        markedDate = Calendar.getInstance();
        markedDate.set(2023, Calendar.JUNE, 12); // Establece la fecha que deseas marcar
    }

    public boolean isSpecial(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR) == markedDate.get(Calendar.YEAR) &&
                cal.get(Calendar.MONTH) == markedDate.get(Calendar.MONTH) &&
                cal.get(Calendar.DAY_OF_MONTH) == markedDate.get(Calendar.DAY_OF_MONTH);
    }

    public Color getSpecialForegroundColor() {
        return Color.WHITE; // Cambia el color del texto de la marca
    }

    public Color getSpecialBackroundColor() {
        return Color.RED; // Cambia el color de fondo de la marca
    }

    public String getSpecialTooltip() {
        return "Tiene un turno a las 8"; // Cambia el texto de la marca
    }

    @Override
    public boolean isInvalid(Date date) {
        return false;
    }

    @Override
    public Color getInvalidForegroundColor() {
        return null;
    }

    @Override
    public Color getInvalidBackroundColor() {
        return null;
    }

    @Override
    public String getInvalidTooltip() {
        return null;
    }
}
