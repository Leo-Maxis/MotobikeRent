package org.example.tabs;

import org.example.dao.CustomerDAO;
import org.example.entity.Customer;
import org.example.validator.CustomerValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListCustomer {
    private JTable tbListCustomer;
    private JPanel customerPanel;
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtPhoneNumber;
    private JTextField txtCount;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnEdit;

    private DefaultTableModel model = null;
    private static JFrame listCustomerFrame = new JFrame("List Customer");

    public ListCustomer() {
        initTable();
        loadData();
        changeFieldStatus(false, false);
        changeButtonStatus(false, false, false);
        tbListCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tbListCustomer.getSelectedRow();
                if (selectedRow != -1) {
                    Object idObj = tbListCustomer.getValueAt(selectedRow,0);
                    if(idObj != null) {
                        int id = Integer.parseInt(idObj.toString());
                        loadById(id);
                    }
                }
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeButtonStatus(true, true, true);
                changeFieldStatus(true, true);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(null, "Do you want to update?", "Confirm Message", JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
                        return;
                    }
                    String valid = CustomerValidator.validate(txtName, txtPhoneNumber, txtCount);
                    if (valid != null) {
                        JOptionPane.showMessageDialog(null, valid, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Customer entity = new Customer();
                    entity.setName(txtName.getText());
                    entity.setPhoneNumber(txtPhoneNumber.getText());
                    entity.setCount(Integer.parseInt(txtCount.getText()));
                    entity.setId(Integer.parseInt(txtID.getText()));
                    CustomerDAO dao = new CustomerDAO();
                    var result = dao.update(entity);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Customer is updated!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Customer cannot be update!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                    changeFieldStatus(false,false);
                    changeButtonStatus(true, true, true);
                    loadData();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(null, "Do you want to update?", "Confirm Message", JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
                        return;
                    }
                    String valid = CustomerValidator.validate(txtName, txtPhoneNumber, txtCount);
                    if (valid != null) {
                        JOptionPane.showMessageDialog(null, valid, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt(txtID.getText());
                    CustomerDAO dao = new CustomerDAO();
                    var result = dao.delete(id);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Customer was deleted!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Customer cannot be delete!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                    changeButtonStatus(false, false, false);
                    changeFieldStatus(false, false);
                    loadData();
                    txtID.setText("");
                    txtName.setText("");
                    txtCount.setText("");
                    txtPhoneNumber.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private void changeFieldStatus(boolean isEditable, boolean isEnabled) {
        txtID.setEditable(false);
        txtName.setEditable(isEditable);
        txtPhoneNumber.setEditable(isEditable);
        txtCount.setEditable(isEditable);
        txtID.setEnabled(true);
        txtName.setEnabled(isEnabled);
        txtPhoneNumber.setEnabled(isEnabled);
        txtCount.setEnabled(isEnabled);
    }

    private void changeButtonStatus(boolean edit, boolean update, boolean delete) {
        btnEdit.setEnabled(edit);
        btnUpdate.setEnabled(update);
        btnDelete.setEnabled(delete);
    }

    private void initTable(){
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Name", "Phone Number", "Count"});
        tbListCustomer.setModel(model);
    }

    private void loadData() {
        try {
            CustomerDAO dao = new CustomerDAO();
            List<Customer> list = dao.findAll();
            model.setRowCount(0);
            for (Customer item : list) {
                Object[] row = new Object[] {
                        item.getId(),
                        item.getName(),
                        item.getPhoneNumber(),
                        item.getCount()
                };
                model.addRow(row);
            }
            model.fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadById(int id) {
        try {
            CustomerDAO dao = new CustomerDAO();
            Customer entity = dao.findById(id);
            txtID.setText(String.valueOf(entity.getId()));
            txtName.setText(entity.getName());
            txtPhoneNumber.setText(entity.getPhoneNumber());
            txtCount.setText(String.valueOf(entity.getCount()));
            changeFieldStatus(false, true);
            changeButtonStatus(true, true, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getListCustomerFrame() {
        listCustomerFrame.setContentPane(new ListCustomer().customerPanel);
        listCustomerFrame.setLocationRelativeTo(null);
        listCustomerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listCustomerFrame.pack();
        listCustomerFrame.setVisible(true);
        listCustomerFrame.setSize(700,600);
    }

}
