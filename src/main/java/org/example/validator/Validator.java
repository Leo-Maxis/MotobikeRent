package org.example.validator;

import javax.swing.*;
import java.awt.*;

public class Validator {
    public static boolean isEmpty(JComponent component) {
        if (component instanceof JTextField) {
            JTextField txt = (JTextField) component;
            if (txt.getText().equals("")) {
                txt.setBackground(Color.YELLOW);
                return true;
            }
            else {
                txt.setBackground(Color.WHITE);
            }
        }
        return  false;
    }
    public static boolean isMin(JComponent component, double min) {
        if (component instanceof JTextField) {
            JTextField txt = (JTextField) component;
            try {
                double value = Double.parseDouble(txt.getText());
                if (value >= min) {
                    txt.setBackground(Color.WHITE);
                    return true;
                }
                txt.setBackground(Color.YELLOW);
            } catch (Exception e) {
                txt.setBackground(Color.yellow);
            }
        }
        return  false;
    }
}
