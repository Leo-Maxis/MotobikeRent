package org.example.validator;

import javax.swing.*;

public class MotoTypeValidator {
    public static String validate(JTextField txtName) {
        StringBuilder sb = new StringBuilder();
        if (Validator.isEmpty(txtName)) {
            sb.append("Name must be entered");
        }
        return sb.isEmpty() ? null : sb.toString();
    }
}
