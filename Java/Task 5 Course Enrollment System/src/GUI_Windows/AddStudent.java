package GUI_Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddStudent extends Window {

    private JFrame frame = new JFrame();
    private JLabel heading;
    private JTextField studentName;
    private JButton submit;

    public AddStudent() {
        frame = new JFrame("Add Student");
        heading = new JLabel("Add Student");
        studentName = new JTextField();
        submit = new JButton("Add");
    }

    @Override
    public void setupUI() {
        frame.setLayout(null);
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // set bounds
        heading.setBounds(150, 50, 200, 50);
        heading.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        studentName.setBounds(150, 150, 200,30);
        submit.setBounds(200, 240, 100, 40);

        // adding components
        frame.add(heading);
        frame.add(studentName);
        frame.add(submit);

        // configs

        studentName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                studentName.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(studentName.getText().isEmpty()) {
                    studentName.setText("Enter student name");
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                submit.requestFocus();
            }
        });
        
        frame.setVisible(true);
    }
}

