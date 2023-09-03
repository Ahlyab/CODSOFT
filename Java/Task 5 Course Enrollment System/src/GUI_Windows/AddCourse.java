package GUI_Windows;

import DataBase.DatabaseConnection;
import ExceptionsAndActions.CourseCodeValidator;
import ExceptionsAndActions.InputFocusListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class AddCourse extends Window {

    private JFrame frame;

    private DatabaseConnection db;
    private JLabel heading;
    private JTextField courseCode;
    private JTextField title;
    private JTextField description;
    private JComboBox<Integer> capcity;
    private JTextField schedule;
    private JButton submit;

    public AddCourse() {
        frame = new JFrame("Add Course");
        heading = new JLabel("Add Course");
        courseCode = new JTextField();
        title = new JTextField();
        description = new JTextField();
        capcity = new JComboBox<Integer>();
        schedule = new JTextField();
        submit = new JButton("Add");
        db = new DatabaseConnection();
        db.connectDatabase();
    }

    @Override
    public void setupUI() {
        frame.setLayout(null);
        frame.setSize(450,500);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // set bounds
        heading.setBounds(125, 50, 200, 50);
        heading.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        title.setBounds(100, 150, 250,30);
        courseCode.setBounds(100, 200, 125,30);
        capcity.setBounds(250, 200, 100, 30);
        description.setBounds(100, 250, 250,30);
        schedule.setBounds(100, 300, 250, 30);
        submit.setBounds(200, 350, 100, 40);

        // adding components
        frame.add(heading);
        frame.add(courseCode);
        frame.add(title);
        frame.add(description);
        frame.add(capcity);
        frame.add(schedule);
        frame.add(submit);

        // configs
        capcity.addItem(50);
        capcity.addItem(100);

        courseCode.addFocusListener(new CourseCodeValidator(courseCode, "Enter course code"));
        title.addFocusListener(new InputFocusListener(title, "Enter course title"));
        description.addFocusListener(new InputFocusListener(description, "Course Description"));
        schedule.addFocusListener(new InputFocusListener(schedule, "schedule e-g : Mon/Wed 9:00 AM - 10:30 AM"));

//        courseCode.getDocument().addDocumentListener(new CourseCodeValidator(courseCode));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                title.requestFocus();
                description.requestFocus();
                courseCode.requestFocus();
                schedule.requestFocus();
                submit.requestFocus();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    db.closeConnection();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                super.windowClosed(e);
            }
        });
        frame.setVisible(true);
    }
}

