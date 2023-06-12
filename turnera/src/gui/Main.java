package gui;


import dao.DAOCreate;
import dao.DAOException;
import dao.DAOMedico;
import entidades.Medico;
import serrvice.MedicoService;
import serrvice.ServiceException;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JCalendar;


public class Main {
    public static void main(String[] args) throws DAOException, ServiceException {
        //es el programa de verdad
       PanelManager panel = new PanelManager();

        //prueba de calendario
        /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Calendario gui = new Calendario();
                gui.setVisible(true);
            }
        });*/







    }
}
