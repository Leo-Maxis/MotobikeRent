package org.example.tabs;

import org.example.dao.MotoTypeDAO;
import org.example.entity.MotoType;
import org.example.validator.MotoTypeValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
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
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(null, "Do you want to update?", "Confirm Message", JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
                        return;
                    }
                    String valid = MotoTypeValidator.validate(txtNameType);
                    if(valid != null ) {
                        JOptionPane.showMessageDialog(null, valid, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    MotoType entity = new MotoType();
                    entity.setId(Integer.parseInt(txtIdType.getText()));
                    entity.setName(txtNameType.getText());
                    MotoTypeDAO dao = new MotoTypeDAO();
                    entity = dao.update(entity);
                    JOptionPane.showMessageDialog(null, "Type is updated!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    txtIdType.setEditable(false);
                    txtNameType.setEditable(false);
                    changeStatus(true, false, false, true);
                    loadData();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNameType.setEditable(true);
                changeStatus(true, false,true,true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(null, "Do you want to update?", "Confirm Message", JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
                        return;
                    }
                    MotoTypeDAO dao = new MotoTypeDAO();
                    int id = Integer.parseInt(txtIdType.getText());
                    if (dao.delete(id)) {
                        JOptionPane.showMessageDialog(null, "Type is deleted!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else  {
                        JOptionPane.showMessageDialog(null, "Type cannot be delete!!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                    txtIdType.setText("");
                    txtNameType.setText("");
                    changeStatus(false, true, false, false);
                    changFieldStates(true);
                    loadData();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        tbListType.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tbListType.getSelectedRow();
                if (selectedRow != -1) {
                    Object idObj = tbListType.getValueAt(selectedRow,0);
                    if (idObj != null) {
                        int id = Integer.parseInt(idObj.toString());
                        loadById(id);
                        changeStatus(true, false, true, true);
                        changFieldStates(false);
                    }
                }
            }
        });
    }

    private void loadById(int id) {
        try {
            MotoTypeDAO dao = new MotoTypeDAO();
            MotoType entity = dao.findById(id);
            txtIdType.setText("" + entity.getId());
            txtNameType.setText(entity.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
