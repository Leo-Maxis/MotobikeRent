package org.example.validator;

import org.example.entity.Customer;
import org.example.entity.MotoType;

import javax.swing.*;

public class RentValidator {
    public static String validate(JTextField txtPhoneNumber, JTextField txtCCCD, JTextField txtAddress,
                                  JTextField txtDays, JTextField txtTotal, JTextField txtStartDate, JComboBox<Customer> cboCustomerName, JComboBox<MotoType> cbxMotoType) {
        StringBuilder sb = new StringBuilder();
        if (Validator.isEmpty(cboCustomerName)) {
            sb.append("Name must be entered\n");
        }
        if (Validator.isEmpty(txtPhoneNumber)) {
            sb.append("Phone must be entered\n");
        }
        if (!Validator.isMin(txtTotal, 0)) {
            sb.append("Total must be greater than 0 or invalid number!!!\n");
        }
        if (Validator.isEmpty(txtTotal)) {
            sb.append("Total must be entered\n");
        }
        if (Validator.isEmpty(txtCCCD)) {
            sb.append("CCCD must be entered\n");
        }
        if (Validator.isEmpty(txtAddress)) {
            sb.append("Address must be entered\n");
        }
        if (Validator.isEmpty(txtDays)) {
            sb.append("Days must be entered\n");
        }
        if (Validator.isEmpty(txtStartDate)) {
            sb.append("Start Date must be entered\n");
        }
        return sb.isEmpty() ? null : sb.toString();
    }
}
