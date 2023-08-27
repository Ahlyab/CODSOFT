package gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ATM_GUI {
    private JFrame frame;
    private JTextField name;
    private JTextField pin;
    private JButton login;

    ATM_GUI() {
        frame = new JFrame();
        name = new JTextField();
        pin = new JTextField();
        login = new JButton("Login");

    }

    private void setupUI(){
        frame.setTitle("ATM login");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        name.setBounds(100, 100, 200, 30);
        pin.setBounds(100, 150, 200, 30);
        login.setBounds(150, 200, 100, 30);

        frame.add(name);
        frame.add(pin);
        frame.add(login);
        frame.setLayout(null);

        name.addFocusListener(new InputFocusAdapter(name, "Enter account name"));
        pin.addFocusListener(new InputFocusAdapter(pin, "Enter 4 digit pin"));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                name.requestFocus();
                pin.requestFocus();
                login.requestFocus();
            }
        });
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ATM_GUI().setupUI();
    }
}
