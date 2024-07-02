package org.example.tabs;

import org.example.dao.MotoTypeDAO;
import org.example.dao.MotobikeDAO;
import org.example.entity.MotoType;
import org.example.entity.Motobike;
import org.example.validator.MotobikeValidator;

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
        changeButtonState(false,true,false,false);
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIdBike.setText("");
                txtNameBike.setText("");
                txtPriceBike.setText("");
                txtYearModelBike.setText("");
                changeButtonState(false,true,false,false);
                changeFieldStates(true);
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFieldStates(true);
                changeButtonState(true,false,true,true);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valid = MotobikeValidator.validate(txtNameBike, txtYearModelBike, txtPriceBike, cboTypeBike);
                if (valid != null) {
                    JOptionPane.showMessageDialog(null, valid, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Motobike entity = new Motobike();
                    entity.setName(txtNameBike.getText());
                    entity.setYearModel(Integer.parseInt(txtYearModelBike.getText()));
                    entity.setPrice(Double.parseDouble(txtPriceBike.getText()));
                    MotoType motoType = (MotoType) cboTypeBike.getSelectedItem();
                    entity.setMotoType(motoType.getId());
                    entity.setTypeName(motoType.getName());
                    MotobikeDAO dao = new MotobikeDAO();
                    entity = dao.insert(entity);
                    txtIdBike.setText("" + entity.getId());
                    JOptionPane.showMessageDialog(null, "Motobike is saved!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    changeButtonState(true, false,true,true);
                    changeFieldStates(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
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

    private void changeButtonState(boolean edit, boolean save, boolean update, boolean delete) {
        btnEdit.setEnabled(edit);
        btnSave.setEnabled(save);
        btnUpdate.setEnabled(update);
        btnDelete.setEnabled(update);
    }
    private void changeFieldStates(boolean isEditable) {
        txtNameBike.setEditable(isEditable);
        txtPriceBike.setEditable(isEditable);
        cboTypeBike.setEditable(isEditable);
        txtYearModelBike.setEditable(isEditable);
    }

    public void getListMotobike() {
        listMotobikeFrame.setContentPane(new ListMotobike().listMotobikePanel);
        listMotobikeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listMotobikeFrame.pack();
        listMotobikeFrame.setVisible(true);
        listMotobikeFrame.setLocationRelativeTo(null);
    }

}
