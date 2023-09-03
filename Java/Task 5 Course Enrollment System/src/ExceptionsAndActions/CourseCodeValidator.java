package ExceptionsAndActions;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CourseCodeValidator implements FocusListener {

    private JTextField textField;
    private String placeholder;

    public CourseCodeValidator(JTextField textField, String placeholder) {
        this.textField = textField;
        this.placeholder = placeholder;
    }



    public void warn() {
        if (textField.getText().length() > 9){
            JOptionPane.showMessageDialog(null,
                    "Error: course code can't be greater than 8 characters", "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            textField.setText(placeholder);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        textField.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(textField.getText().isEmpty()) {
            textField.setText(placeholder);
        }
        if(!textField.getText().equals(placeholder)){
            warn();
        }
    }
}
