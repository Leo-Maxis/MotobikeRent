package org.example.tabs;

import org.example.dao.CustomerDAO;
import org.example.entity.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ListCustomer {
    private JTable tbListCustomer;
    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblPhoneNumber;
    private JLabel lblCount;
    private JPanel customerPanel;

    private DefaultTableModel model = null;
    private static JFrame listCustomerFrame = new JFrame("List Customer");

    public ListCustomer() {
        initTable();
        loadData();
        tbListCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
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

    public void getListCustomerFrame() {
        listCustomerFrame.setContentPane(new ListCustomer().customerPanel);
        listCustomerFrame.setLocationRelativeTo(null);
        listCustomerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listCustomerFrame.pack();
        listCustomerFrame.setVisible(true);
        listCustomerFrame.setSize(700,600);
    }
}
