package org.example.validator;

import org.example.entity.MotoType;

import javax.swing.*;

public class CustomerValidator {
    public static String validate(JTextField txtName, JTextField txtPhoneNumber, JTextField txtCount) {
        StringBuilder sb = new StringBuilder();
        if (Validator.isEmpty(txtName)) {
            sb.append("Name must be entered\n");
        }
        if (Validator.isEmpty(txtPhoneNumber)) {
            sb.append("Phone number must be entered\n");
        }
        if (!Validator.isMin(txtCount, 0)) {
            sb.append("Count must be greater than 0 or invalid number!!!\n");
        }
        if (Validator.isEmpty(txtCount)) {
            sb.append("Count must be entered\n");
        }
        return sb.isEmpty() ? null : sb.toString();
    }
}
