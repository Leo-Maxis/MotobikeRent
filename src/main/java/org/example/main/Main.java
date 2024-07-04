package org.example.main;

import org.example.tabs.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JPanel mainPanel;
    private JMenuItem mnListMotoType;
    private JMenuItem mnListMotobike;
    private JMenuItem mnListCustomer;
    private JMenuItem mnListRent;
    private JTabbedPane tabbedPane1;
    private JTable tbListRenting;

    public Main() {
        mnListMotoType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListMotoType listMotoType = new ListMotoType();
                listMotoType.getListMotoType();
            }
        });
        mnListMotobike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListMotobike listMotobike = new ListMotobike();
                listMotobike.getListMotobike();
            }
        });
        mnListCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListCustomer listCustomer = new ListCustomer();
                listCustomer.getListCustomerFrame();
            }
        });
        mnListRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListRent listRent = new ListRent();
                listRent.getListRentPanel();
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
        frame.setSize(700,600);
        //Login form
        Login loginform = new Login();
        loginform.getLoginForm();
    }
}
