package ExceptionsAndActions;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InputFocusListener extends FocusAdapter {

    private String placeholder;
    private JTextComponent inputField;

    public InputFocusListener(JTextComponent inputField, String placeholder) {
        this.placeholder = placeholder;
        this.inputField = inputField;
    }

    @Override
    public void focusGained(FocusEvent e) {
        inputField.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(inputField.getText().isEmpty()) {
            inputField.setText(placeholder);
        }
    }
}

