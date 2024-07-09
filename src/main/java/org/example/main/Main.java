package org.example.main;

import org.example.dao.RentDAO;
import org.example.entity.Rent;
import org.example.tabs.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Main {
    private JPanel mainPanel;
    private JMenuItem mnListMotoType;
    private JMenuItem mnListMotobike;
    private JMenuItem mnListCustomer;
    private JMenuItem mnListRent;
    private JTabbedPane tabbedPane1;
    private JTable tbListRenting;
    private JMenuItem mnNew;
    private JTextField txtEndDate;
    private JLabel txtRentId;
    private JLabel txtCustomerName;
    private JLabel txtPhoneNumber;
    private JLabel txtCCCD;
    private JLabel txtAddress;
    private JLabel txtMotobike;
    private JLabel txtDays;
    private JLabel txtTotal;
    private JLabel txtStartDate;
    private JButton cancelButton;
    private JButton payButton;
    private JButton reloadDataButton;

    private DefaultTableModel model = null;

    public Main() {
        initTable();
        loadData();
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
        mnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewRent newRent = new NewRent();
                newRent.getNewRentPanel();
            }
        });
        tbListRenting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tbListRenting.getSelectedRow();
                if (selectedRow != -1) {
                    Object idObj = tbListRenting.getValueAt(selectedRow,0);
                    if (idObj != null) {
                        int id = Integer.parseInt(idObj.toString());
                        loadById(id);
                    }
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtRentId.setText("");
                txtCustomerName.setText("");
                txtCCCD.setText("");
                txtAddress.setText("");
                txtDays.setText("");
                txtTotal.setText("");
                txtStartDate.setText("");
                txtEndDate.setText("");
                txtPhoneNumber.setText("");
                txtMotobike.setText("");
                loadData();
            }
        });
        reloadDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbListRenting.getSelectedRow();
                if (selectedRow == -1 || txtEndDate.getText().equals("null")) {
                    JOptionPane.showMessageDialog(null, " Please chose at least one row want to pay and enter the return date before pay!!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void initTable() {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID","Customer name", "Phone Number", "CCCD", "Address", "Motobike", "Days", "Total", "Start Date", "Return Date"});
        tbListRenting.setModel(model);
    }

    private void loadData() {
        try {
            RentDAO dao = new RentDAO();
            List<Rent> list = dao.findNull();
            model.setRowCount(0);
            for (Rent item : list ) {
                Object[] row = new Object[]{
                        item.getId(),
                        item.getCustomerName(),
                        item.getPhoneNumber(),
                        item.getCccd(),
                        item.getAddress(),
                        item.getMotoName(),
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

    private void loadById(int id) {
        try {
            RentDAO dao = new RentDAO();
            Rent entity = dao.findById(id);
            txtRentId.setText(String.valueOf(entity.getId()));
            txtCustomerName.setText(entity.getCustomerName());
            txtPhoneNumber.setText(entity.getPhoneNumber());
            txtCCCD.setText(entity.getCccd());
            txtAddress.setText(entity.getAddress());
            txtMotobike.setText(entity.getMotoName());
            txtDays.setText(String.valueOf(entity.getDays()));
            txtTotal.setText(String.valueOf(entity.getTotal()));
            txtStartDate.setText(String.valueOf(entity.getStartDate()));
            txtEndDate.setText(String.valueOf(entity.getEndDate()));
        }catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrame");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(850,600);
        //Login form
        Login loginform = new Login();
        loginform.getLoginForm();
    }
}
