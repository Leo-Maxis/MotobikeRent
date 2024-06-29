package org.example.tabs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    public void getLoginForm() {
        JDialog loginForm = new JDialog(login,"Login", Dialog.ModalityType.APPLICATION_MODAL);
        loginForm.getContentPane().add(loginPanel);
        loginForm.pack();
        loginForm.setVisible(true);
        loginForm.setLocationRelativeTo(null);
    }


}
