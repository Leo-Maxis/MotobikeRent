package org.example.tabs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewRent {
    private JTextField txtId;
    private JTextField txtCustomerId;
    private JTextField txtCustomerName;
    private JTextField txtPhoneNumber;
    private JTextField txtCCCD;
    private JTextField txtAddress;
    private JComboBox cbMotobikeName;
    private JTextField txtDays;
    private JFormattedTextField txtTotal;
    private JTextField txtStartDate;
    private JTextField txtReturnDate;
    private JButton btnCancel;
    private JButton btnSave;
    private JCheckBox alreadyCustomerCheckBox;
    private JButton btnEdit;
    private JPanel newRentPanel;

    private static JFrame newRentFrame = new JFrame("Add New Rent");

    public NewRent() {
        changeButtonState(true, false, false);
        changeFieldStatus(false, true);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtCustomerId.setText("");
                txtCustomerName.setText("");
                txtPhoneNumber.setText("");
                txtCCCD.setText("");
                txtAddress.setText("");
                txtDays.setText("");
                txtTotal.setText("");
                txtStartDate.setText("");
                txtReturnDate.setText("");
                alreadyCustomerCheckBox.setSelected(false);
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeButtonState(true, true, true);
                changeFieldStatus(true, true);
            }
        });
    }

    private void changeButtonState(boolean edit, boolean save, boolean cancel) {
        btnEdit.setEnabled(edit);
        btnSave.setEnabled(save);
        btnCancel.setEnabled(cancel);
    }

    private void changeFieldStatus(boolean isEditTabel, boolean isEnable) {
        txtId.setEnabled(false);
        txtCustomerId.setEnabled(isEnable);
        txtCustomerName.setEnabled(isEnable);
        txtPhoneNumber.setEnabled(isEnable);
        txtCCCD.setEnabled(isEnable);
        txtAddress.setEnabled(isEnable);
        txtDays.setEnabled(isEnable);
        txtTotal.setEnabled(isEnable);
        txtStartDate.setEnabled(isEnable);
        txtReturnDate.setEnabled(isEnable);

        txtId.setEditable(false);
        txtCustomerId.setEditable(isEditTabel);
        txtCustomerName.setEditable(isEditTabel);
        txtPhoneNumber.setEditable(isEditTabel);
        txtCCCD.setEditable(isEditTabel);
        txtAddress.setEditable(isEditTabel);
        txtDays.setEditable(isEditTabel);
        txtTotal.setEditable(isEditTabel);
        txtStartDate.setEditable(isEditTabel);
        txtReturnDate.setEditable(isEditTabel);

    }

    public void getNewRentPanel() {
        newRentFrame.setContentPane(new NewRent().newRentPanel);
        newRentFrame.setSize(700, 600);
        newRentFrame.setLocationRelativeTo(null);
        newRentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newRentFrame.pack();
        newRentFrame.setVisible(true);
    }
}
