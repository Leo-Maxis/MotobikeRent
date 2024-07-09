package org.example.tabs;

import org.example.dao.CustomerDAO;
import org.example.dao.MotobikeDAO;
import org.example.dao.RentDAO;
import org.example.entity.Customer;
import org.example.entity.Motobike;
import org.example.entity.Rent;
import org.example.util.DateUtil;
import org.example.validator.RentValidator;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class NewRent {
    private JTextField txtId;
    private JTextField txtCustomerId;
    private JTextField txtCCCD;
    private JTextField txtAddress;
    private JComboBox cbMotobikeName;
    private JTextField txtDays;
    private JFormattedTextField txtTotal;
    private JButton btnCancel;
    private JButton btnSave;
    private JCheckBox cbAlreadyCustomer;
    private JButton btnEdit;
    private JPanel newRentPanel;
    private JComboBox<Customer> cboCustomerName;
    private JTextField txtPhoneNumber;
    private JFormattedTextField ftfStartDate;
    private JFormattedTextField ftfReturnDate;

    private static JFrame newRentFrame = new JFrame("Add New Rent");

    public NewRent() {
        loadType();
        changeButtonState(true, false, false);
        changeFieldStatus(false, true);
        cbAlreadyCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbAlreadyCustomer.isSelected()) {
                    loadCboCustomer();
                    Customer customer = (Customer) cboCustomerName.getSelectedItem();
                    int id = customer.getId();
                    loadIdCustomer(id);
                    cboCustomerName.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Customer customer = (Customer) cboCustomerName.getSelectedItem();
                            int id = customer.getId();
                            loadIdCustomer(id);
                        }
                    });
                }
                else {
                    cboCustomerName.removeAllItems();
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtCustomerId.setText("");
                cboCustomerName.setSelectedItem(null);
                txtCCCD.setText("");
                txtAddress.setText("");
                txtDays.setText("");
                txtTotal.setText("");
                ftfStartDate.setText("");
                ftfReturnDate.setText("");
                cbAlreadyCustomer.setSelected(false);
                changeButtonState(true, false, false);
                changeFieldStatus(false, true);
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeButtonState(true, true, true);
                changeFieldStatus(true, true);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String valid = RentValidator.validate(txtPhoneNumber, txtCCCD, txtAddress, txtDays, txtTotal, ftfStartDate, cboCustomerName, cbMotobikeName);
                    if (valid != null) {
                        JOptionPane.showMessageDialog(null, valid, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (cbAlreadyCustomer.isSelected()) {
                        updateCustomer();
                        insertRent();
                    } else {
                        insertCustomer();
                        insertRent();
                    }
                    JOptionPane.showMessageDialog(null, "Rent was saved!!!", "Infomation", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                changeFieldStatus(false, false);
                changeButtonState(true, false, true);
            }
        });
    }
    private void loadType() {
        try {
            MotobikeDAO dao = new MotobikeDAO();
            var list = dao.findAll();
            for (Motobike item : list) {
                cbMotobikeName.addItem(item);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadIdCustomer(int id) {
        try {
            CustomerDAO dao = new CustomerDAO();
            Customer entity = dao.findById(id);
            txtCustomerId.setText(String.valueOf(entity.getId()));
            txtPhoneNumber.setText(entity.getPhoneNumber());
        }catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadCboCustomer() {
        try {
            CustomerDAO dao = new CustomerDAO();
            var list = dao.findAll();
            for (Customer item : list) {
                cboCustomerName.addItem(item);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertCustomer() {
        String valid = RentValidator.validate(txtPhoneNumber, txtCCCD, txtAddress, txtDays, txtTotal, ftfStartDate, cboCustomerName, cbMotobikeName);
        if (valid != null) {
            JOptionPane.showMessageDialog(null, valid, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Customer entity = new Customer();
            String customerName = ((JTextField)cboCustomerName.getEditor().getEditorComponent()).getText();
            entity.setName(customerName);
            entity.setPhoneNumber(txtPhoneNumber.getText());
            entity.setCount(1);
            CustomerDAO dao = new CustomerDAO();
            entity = dao.insert(entity);
            txtCustomerId.setText("" + entity.getId());
            JOptionPane.showMessageDialog(null, "New customer was saved!!", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCustomer() {
        String valid = RentValidator.validate(txtPhoneNumber, txtCCCD, txtAddress, txtDays, txtTotal, ftfStartDate, cboCustomerName, cbMotobikeName);
        if (valid != null) {
            JOptionPane.showMessageDialog(null, valid, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Customer entity = new Customer();
            entity.setId(Integer.parseInt(txtCustomerId.getText()));
            CustomerDAO dao = new CustomerDAO();
            dao.updateCount(entity);
        }catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void insertRent() {
        String valid = RentValidator.validate(txtPhoneNumber, txtCCCD, txtAddress, txtDays, txtTotal, ftfStartDate, cboCustomerName, cbMotobikeName);
        if (valid != null) {
            JOptionPane.showMessageDialog(null, valid, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Rent entity = new Rent();
            String customerName = ((JTextField)cboCustomerName.getEditor().getEditorComponent()).getText();
            entity.setCustomerId(Integer.parseInt(txtCustomerId.getText()));
            entity.setCustomerName(customerName);
            entity.setPhoneNumber(txtPhoneNumber.getText());
            entity.setCccd(txtCCCD.getText());
            entity.setAddress(txtAddress.getText());
            Motobike motobike = (Motobike) cbMotobikeName.getSelectedItem();
            entity.setMotoId(motobike.getId());
            entity.setMotoName(motobike.getName());
            entity.setDays(Integer.parseInt(txtDays.getText()));
            entity.setTotal(Double.parseDouble(txtTotal.getText()));
            DateUtil dateUtil = new DateUtil();
            entity.setStartDate(dateUtil.toDate(ftfStartDate.getText()));
//            entity.setEndDate(Objects.equals(ftfReturnDate.getText(), "") ? null:dateUtil.toDate(ftfReturnDate.getText()));
            RentDAO dao = new RentDAO();
            entity = dao.insert(entity);
            txtId.setText("" + entity.getId());
            JOptionPane.showMessageDialog(null, "New rent was saved!!", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeButtonState(boolean edit, boolean save, boolean cancel) {
        btnEdit.setEnabled(edit);
        btnSave.setEnabled(save);
        btnCancel.setEnabled(cancel);
    }

    private void changeFieldStatus(boolean isEditTabel, boolean isEnable) {
        txtId.setEnabled(false);
        txtCustomerId.setEnabled(isEnable);
        cboCustomerName.setEnabled(isEnable);
        txtCCCD.setEnabled(isEnable);
        txtAddress.setEnabled(isEnable);
        txtDays.setEnabled(isEnable);
        txtTotal.setEnabled(isEnable);
        ftfStartDate.setEnabled(isEnable);
        ftfReturnDate.setEnabled(isEnable);
        cbMotobikeName.setEnabled(isEnable);
        cbAlreadyCustomer.setEnabled(isEnable);
        txtPhoneNumber.setEditable(isEnable);

        txtId.setEditable(false);
        txtCustomerId.setEditable(false);
        cboCustomerName.setEditable(isEditTabel);
        txtCCCD.setEditable(isEditTabel);
        txtAddress.setEditable(isEditTabel);
        txtDays.setEditable(isEditTabel);
        txtTotal.setEditable(isEditTabel);
        ftfStartDate.setEditable(isEditTabel);
        ftfReturnDate.setEditable(isEditTabel);
        txtPhoneNumber.setEditable(isEditTabel);
        cbMotobikeName.setEditable(false);

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
