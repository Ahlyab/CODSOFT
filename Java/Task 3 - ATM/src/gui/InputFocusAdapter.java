package gui;
import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InputFocusAdapter extends FocusAdapter {
    private JTextField input;
    private String message;

    protected InputFocusAdapter(JTextField in, String msg) {
        super();
        input = in;
        message = msg;
    }

    @Override
    public void focusGained(FocusEvent e) {
        input.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(input.getText().length() == 0) {
            input.setText(message);
        }
    }
}
