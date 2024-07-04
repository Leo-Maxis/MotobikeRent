package org.example.tabs;

import org.example.dao.RentDAO;
import org.example.entity.Rent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListRent {
    private JTable tbListRent;
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
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnEdit;
    private JPanel listRentPanel;

    private DefaultTableModel model = null;
    private static JFrame listRentFrame = new JFrame("List Rent");

    public ListRent() {
        initTable();
        loadData();
        changeButtonStatus(false,false,false);
        changeFieldStatus(false, false);
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFieldStatus(true, true);
                changeButtonStatus(true, true, true);
            }
        });
    }

    private void initTable() {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID","Customer name", "Phone Number", "CCCD", "Address", "Motobike", "Days", "Total", "Start Date", "Return Date"});
        tbListRent.setModel(model);
    }

    private void loadData() {
        try {
            RentDAO dao = new RentDAO();
            List<Rent> list = dao.findAll();
            model.setRowCount(0);
            for (Rent item : list ) {
                Object[] row = new Object[]{
                        item.getId(),
                        item.getCustomerName(),
                        item.getPhoneNumber(),
                        item.getCccd(),
                        item.getAddress(),
                        item.getMotobikeName(),
                        item.getDays(),
                        item.getTotal(),
                        item.getStartDate(),
                        item.getEndDate()
                };
                model.addRow(row);
            }
            model.fireTableDataChanged();
        }catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeButtonStatus(boolean edit, boolean update, boolean delete) {
        btnEdit.setEnabled(edit);
        btnUpdate.setEnabled(update);
        btnDelete.setEnabled(delete);
    }

    private void changeFieldStatus(boolean isEditable, boolean isEnabled) {
        txtId.setEditable(false);
        txtId.setEnabled(false);
        txtCustomerId.setEnabled(isEnabled);
        txtCustomerId.setEditable(isEditable);
        txtCustomerName.setEnabled(isEnabled);
        txtCustomerName.setEditable(isEditable);
        txtPhoneNumber.setEnabled(isEnabled);
        txtPhoneNumber.setEditable(isEditable);
        txtCCCD.setEnabled(isEnabled);
        txtCCCD.setEditable(isEditable);
        txtAddress.setEnabled(isEnabled);
        txtAddress.setEditable(isEditable);
        cbMotobikeName.setEnabled(isEnabled);
        cbMotobikeName.setEditable(isEditable);
        txtDays.setEnabled(isEnabled);
        txtDays.setEditable(isEditable);
        txtTotal.setEnabled(isEnabled);
        txtTotal.setEditable(isEditable);
        txtStartDate.setEnabled(isEnabled);
        txtStartDate.setEditable(isEditable);
        txtReturnDate.setEnabled(isEnabled);
        txtReturnDate.setEditable(isEditable);
    }

    public void getListRentPanel() {
        listRentFrame.setContentPane(new ListRent().listRentPanel);
        listRentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listRentFrame.setLocationRelativeTo(null);
        listRentFrame.setSize(700,600);
        listRentFrame.pack();
        listRentFrame.setVisible(true);
    }
}
