package org.example.main;

import org.example.tabs.Login;

import javax.swing.*;

public class Main {
    private JTable table1;
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrame");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        //Login form
        Login loginform = new Login();
        loginform.getLoginForm();
    }
}
