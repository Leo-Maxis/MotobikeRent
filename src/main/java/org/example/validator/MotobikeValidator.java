package org.example.validator;

import org.example.entity.MotoType;

import javax.swing.*;

public class MotobikeValidator {
    public static String validate(JTextField txtName, JTextField txtYearModel, JTextField txtPrice, JComboBox<MotoType> cbxType) {
        StringBuilder sb = new StringBuilder();
        if (Validator.isEmpty(txtName)) {
            sb.append("Name must be entered\n");
        }
        if (Validator.isEmpty(txtYearModel)) {
            sb.append("Year must be entered\n");
        }
        if (!Validator.isMin(txtPrice, 0)) {
            sb.append("Price must be greater than 0 or invalid number!!!\n");
        }
        if (Validator.isEmpty(txtPrice)) {
            sb.append("Price must be entered\n");
        }
        return sb.isEmpty() ? null : sb.toString();
    }

}
