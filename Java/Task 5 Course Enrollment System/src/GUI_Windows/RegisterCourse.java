package GUI_Windows;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterCourse extends Window {

    private JFrame frame;
    private JComboBox<String> name;
    private JComboBox<String> course;
    private JButton register;

    public RegisterCourse() {
        this.frame = new JFrame("Register course");
        this.name = new JComboBox<String>();
        this.course = new JComboBox<String>();
        this.register = new JButton("Register");

    }

    @Override
    public void setupUI() {
        frame.setLayout(null);
        frame.setSize(600,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // set bounds
        name.setBounds(50, 50, 200, 40);
        course.setBounds(300, 50, 200, 40);
        register.setBounds(250, 140, 100,40);

        // add components
        frame.add(name);
        frame.add(course);
        frame.add(register);

        // config
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                register.requestFocus();
            }
        });

        name.addItem("Select name");
        course.addItem("Select course");

        frame.setVisible(true);
    }
}

