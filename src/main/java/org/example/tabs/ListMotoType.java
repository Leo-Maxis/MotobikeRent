package org.example.tabs;

import org.example.dao.MotoTypeDAO;
import org.example.entity.MotoType;
import org.example.validator.MotoTypeValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListMotoType {
    private JTextField txtIdType;
    private JTextField txtNameType;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnSave;
    private JButton btnEdit;
    private JButton btnNew;
    private JLabel lblIamge;
    private JTable tbListType;
    private JPanel motoTypePanel;

    private static JFrame listMotoType = new JFrame();
    private DefaultTableModel model = null;

    private void newButton() {
        txtIdType.setText("");
        txtNameType.setText("");
    }

    private void changeStatus(boolean edit, boolean save, boolean update, boolean delete) {
        btnEdit.setEnabled(edit);
        btnSave.setEnabled(save);
        btnUpdate.setEnabled(update);
        btnDelete.setEnabled(delete);
    }
    private void changFieldStates(boolean isEditable) {
        txtNameType.setEditable(isEditable);
    }

    public ListMotoType() {
        initTable();
        loadData();
        changeStatus(false, true, false, false);
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newButton();
                changeStatus(false, true, false, false);
                changFieldStates(true);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valid = MotoTypeValidator.validate(txtNameType);
                if (valid != null) {
                    JOptionPane.showMessageDialog(null, valid, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    MotoType entity = new MotoType();
                    entity.setName(txtNameType.getText());
                    MotoTypeDAO dao = new MotoTypeDAO();
                    entity = dao.insert(entity);
                    txtIdType.setText(entity.getId() + "");
                    JOptionPane.showMessageDialog(null, "Type is saved!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    txtIdType.setEditable(false);
                    txtNameType.setEditable(false);
                    changeStatus(true, false, true, true);
                    loadData();
                } catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void initTable() {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {"ID", "Name"});
        tbListType.setModel(model);
    }
    private void loadData() {
        try {
            MotoTypeDAO dao = new MotoTypeDAO();
            List<MotoType> list = dao.findAll();
            model.setRowCount(0);
            for (MotoType item : list) {
                Object[] row = new Object[] {
                        item.getId(),
                        item.getName(),
                };
                model.addRow(row);
            }
            model.fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getListMotoType() {
        JFrame motoTypeFrame = new JFrame("List Motobike Type Frame");
        motoTypeFrame.setLocationRelativeTo(null);
        motoTypeFrame.setContentPane(new ListMotoType().motoTypePanel);
        motoTypeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        motoTypeFrame.pack();
        motoTypeFrame.setVisible(true);
    }
}
