package org.example.main;

import org.example.tabs.ListMotoType;
import org.example.tabs.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class Main {
    private JTable table1;
    private JPanel mainPanel;
    private JMenuItem mnListMotoType;

    public Main() {
        mnListMotoType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListMotoType listMotoType = new ListMotoType();
                listMotoType.getListMotoType();
            }
        });
    }

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
