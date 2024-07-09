package org.example.tabs;

import org.example.dao.LoginDAO;
import org.example.entity.UserLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

public class Login {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton cancelButton;
    private JButton loginButton;
    private JCheckBox rememberMeCheckBox;
    private JPanel loginPanel;

    private static JFrame login = new JFrame();

    public Login() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String password = "";
                    for(char c: txtPassword.getPassword()){
                        password+= c;
                    }
                    if (txtUsername.getText().equals("") || password.equals("")) {
                        JOptionPane.showMessageDialog(null, "Username or password cannot be empty!!!", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else {

                        loginUser(txtUsername.getText(), password);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loginUser(String username, String password) throws SQLException, ClassNotFoundException {
        try {
            LoginDAO dao = new LoginDAO();
            UserLogin entity = dao.loginuser(username, password);
            if (entity == null) {
                JOptionPane.showMessageDialog(null, "Username or password not correct", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                login.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getLoginForm() {
        JDialog loginForm = new JDialog(login,"Login", Dialog.ModalityType.APPLICATION_MODAL);
        loginForm.getContentPane().add(loginPanel);
        loginForm.pack();
        loginForm.setVisible(true);
        loginForm.setLocationRelativeTo(null);
    }
}
