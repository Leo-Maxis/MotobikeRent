package org.example.tabs;

import org.example.dao.MotoTypeDAO;
import org.example.entity.MotoType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListMotobike {
    private JTextField txtIdBike;
    private JTextField txtNameBike;
    private JTextField txtYearModelBike;
    private JComboBox cboTypeBike;
    private JFormattedTextField txtPriceBike;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnSave;
    private JButton btnEdit;
    private JButton btnNew;
    private JTable tbListMotobike;
    private JLabel lblimage;
    private JPanel listMotobikePanel;

    private static JFrame listMotobikeFrame = new JFrame();

    public ListMotobike() {
        loadType();
        changButtonState(false,true,false,false);
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIdBike.setText("");
                txtNameBike.setText("");
                txtPriceBike.setText("");
                txtYearModelBike.setText("");
                changButtonState(false,true,false,false);
            }
        });
    }

    private void loadType() {
        try {
            MotoTypeDAO dao = new MotoTypeDAO();
            var list = dao.findAll();
            for (MotoType item : list) {
                cboTypeBike.addItem(item);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changButtonState(boolean edit, boolean save, boolean update, boolean delete) {
        btnEdit.setEnabled(edit);
        btnSave.setEnabled(save);
        btnUpdate.setEnabled(update);
        btnDelete.setEnabled(update);
    }

    public void getListMotobike() {
        listMotobikeFrame.setContentPane(new ListMotobike().listMotobikePanel);
        listMotobikeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listMotobikeFrame.pack();
        listMotobikeFrame.setVisible(true);
        listMotobikeFrame.setLocationRelativeTo(null);
    }

}
