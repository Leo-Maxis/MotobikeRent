package org.example.tabs;

import org.example.dao.MotoTypeDAO;
import org.example.dao.MotobikeDAO;
import org.example.entity.MotoType;
import org.example.entity.Motobike;
import org.example.validator.MotobikeValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListMotobike {
    private JTextField txtIdBike;
    private JTextField txtNameBike;
    private JTextField txtYearModelBike;
    private JComboBox<MotoType> cboTypeBike;
    private JFormattedTextField txtPriceBike;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnSave;
    private JButton btnEdit;
    private JButton btnNew;
    private JTable tbListMotobike;
    private JLabel lblimage;
    private JPanel listMotobikePanel;

    private DefaultTableModel model = null;

    private static JFrame listMotobikeFrame = new JFrame();

    public ListMotobike() {
        initTable();
        loadData();
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
                    loadData();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(null, "Do you want to update?", "Confirm Message", JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
                        return;
                    }
                    String valid = MotobikeValidator.validate(txtNameBike, txtYearModelBike, txtPriceBike, cboTypeBike);
                    if (valid != null) {
                        JOptionPane.showMessageDialog(null, valid, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Motobike entity = new Motobike();
                    entity.setName(txtNameBike.getText());
                    entity.setYearModel(Integer.parseInt(txtYearModelBike.getText()));
                    entity.setPrice(Double.parseDouble(txtPriceBike.getText()));
                    MotoType motoType = (MotoType) cboTypeBike.getSelectedItem();
                    entity.setMotoType(motoType.getId());
                    entity.setTypeName(motoType.getName());
                    entity.setId(Integer.parseInt(txtIdBike.getText()));
                    MotobikeDAO dao = new MotobikeDAO();
                    var result = dao.update(entity);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Motobike is updated!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Motobike cannot br updated!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    changeButtonState(true,false, false, false);
                    changeFieldStates(false);
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
                    if (JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Confirm Message", JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
                        return;
                    }
                    int id = Integer.parseInt(txtIdBike.getText());
                    MotobikeDAO dao = new MotobikeDAO();
                    var result = dao.delete(id);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Motobike is deleted!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        txtIdBike.setText("");
                        txtNameBike.setText("");
                        txtPriceBike.setText("");
                        txtYearModelBike.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Motobike cannot be deleted!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                    changeFieldStates(false);
                    changeButtonState(false, true, false, false);
                    loadData();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        tbListMotobike.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tbListMotobike.getSelectedRow();
                if (selectedRow != -1) {
                    Object idOjb = tbListMotobike.getValueAt(selectedRow, 0);
                    if (idOjb != null) {
                        int id = Integer.parseInt(idOjb.toString());
                        loadById(id);
                    }
                }
            }
        });
    }

    private void loadById(int id) {
        try {
            MotobikeDAO dao = new MotobikeDAO();
            Motobike entity = dao.findById(id);
            txtIdBike.setText("" + entity.getId());
            txtNameBike.setText(entity.getName());
            txtYearModelBike.setText(String.valueOf(entity.getYearModel()));
            txtPriceBike.setText(String.valueOf(entity.getPrice()));
            for (int i = 0; i < cboTypeBike.getItemCount(); i++) {
                var item = cboTypeBike.getItemAt(i);
                if (item.getId() == entity.getMotoType()) {
                    cboTypeBike.setSelectedItem(item);
                    break;
                }
            }
            changeButtonState(true, false,true,true);
            changeFieldStates(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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

    private void initTable() {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Name", "Year Model", "Type ID", "Type Name", "Price"});
        tbListMotobike.setModel(model);
    }

    private void loadData() {
        try {
            MotobikeDAO dao = new MotobikeDAO();
            List<Motobike> list = dao.findAll();
            model.setRowCount(0);
            for (Motobike item : list) {
                Object[] row = new Object[] {
                        item.getId(),
                        item.getName(),
                        item.getYearModel(),
                        item.getMotoType(),
                        item.getTypeName(),
                        item.getPrice(),
                };
                model.addRow(row);
            }
            model.fireTableDataChanged();
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
        cboTypeBike.setEnabled(isEditable);
        txtYearModelBike.setEditable(isEditable);
    }

    public void getListMotobike() {
        listMotobikeFrame.setContentPane(new ListMotobike().listMotobikePanel);
        listMotobikeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listMotobikeFrame.pack();
        listMotobikeFrame.setVisible(true);
        listMotobikeFrame.setLocationRelativeTo(null);
        listMotobikeFrame.setSize(700,600);
    }

}
