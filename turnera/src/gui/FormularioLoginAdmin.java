package gui;

import service.AdminService;
import javax.swing.*;
import java.awt.*;

public class FormularioLoginAdmin {
    JPanel formularioLoginAdmin;
    PanelManager panel;
    JButton jButtonLoginAdmin;
    JButton jButtonExit;
    JTextField jTextFieldUser;
    JPasswordField jPasswordField;
    JLabel jLabelUser;
    JLabel jLabelPassword;
    AdminService adminService;

    public FormularioLoginAdmin(PanelManager panel){
        this.panel=panel;
        creadorFormulario();
        agregarFormulario();
        agregarFuncionesBotones();
        decorar();
    }

    public void creadorFormulario(){
        formularioLoginAdmin = new JPanel();
        formularioLoginAdmin.setLayout(new GridLayout(3,2));
        jButtonLoginAdmin = new JButton("Ingresar");
        jButtonExit = new JButton("Salir");
        jTextFieldUser = new JTextField();
        jPasswordField = new JPasswordField();
        jLabelUser = new JLabel("Usuario:");
        jLabelPassword = new JLabel("Contraseña:");
    }

    public void agregarFormulario(){
        formularioLoginAdmin.add(jLabelUser);
        formularioLoginAdmin.add(jTextFieldUser);
        formularioLoginAdmin.add(jLabelPassword);
        formularioLoginAdmin.add(jPasswordField);
        formularioLoginAdmin.add(jButtonExit);
        formularioLoginAdmin.add(jButtonLoginAdmin);
    }

    public void agregarFuncionesBotones(){
        jButtonExit.addActionListener(e -> {
            FormularioSeleccionUsuario formularioSeleccionUsuario = new FormularioSeleccionUsuario(panel);
            panel.mostrar(formularioSeleccionUsuario.getFormulario());
        });

        jButtonLoginAdmin.addActionListener(e -> {
            adminService = new AdminService();
            String user = jTextFieldUser.getText();
            String password = String.valueOf(jPasswordField.getPassword());
            try {
                if(adminService.login(user,password)){
                    FormularioAdmin formularioAdmin = new FormularioAdmin(panel);
                    panel.mostrar(formularioAdmin.getFormulario());
                }else{
                    JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos");
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,exception.getMessage());
            }
        });
    }

    public void decorar(){
        formularioLoginAdmin.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formularioLoginAdmin.setBackground(Color.lightGray);
        formularioLoginAdmin.setPreferredSize(new Dimension(220, 120));
        formularioLoginAdmin.setOpaque(true);
    }

    public JPanel getFormulario(){
        return formularioLoginAdmin;
    }

}
