package gui;


import dao.DAOCreate;
import dao.DAOException;
import dao.DAOMedico;
import entidades.Medico;
import serrvice.MedicoService;
import serrvice.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws DAOException, ServiceException {
       PanelManager panel = new PanelManager();
        /*DAOMedico daoMedico = new DAOMedico();
        ArrayList<Medico> medicos = new ArrayList<Medico>();
        medicos = daoMedico.buscarTodos();
        for (Medico medico: medicos) {
            System.out.println(medico.getNombre());
        }*/




    }
}
