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
    private JButton add;

    public AddCourse() {
        frame = new JFrame("Add Course");
        heading = new JLabel("Add Course");
        courseCode = new JTextField();
        title = new JTextField();
        description = new JTextField();
        capcity = new JComboBox<Integer>();
        schedule = new JTextField();
        add = new JButton("Add");
        db = DatabaseConnection.getInstance();

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
        add.setBounds(200, 350, 100, 40);

        // adding components
        frame.add(heading);
        frame.add(courseCode);
        frame.add(title);
        frame.add(description);
        frame.add(capcity);
        frame.add(schedule);
        frame.add(add);

        // configs
        capcity.addItem(50);
        capcity.addItem(100);

        courseCode.addFocusListener(new CourseCodeValidator(courseCode, "Enter course code"));
        title.addFocusListener(new InputFocusListener(title, "Enter course title"));
        description.addFocusListener(new InputFocusListener(description, "Course Description"));
        schedule.addFocusListener(new InputFocusListener(schedule, "schedule e-g : Mon/Wed 9:00 AM - 10:30 AM"));


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String _title = title.getText();
                String _cc = courseCode.getText();
                int _capacity = capcity.getItemAt(capcity.getSelectedIndex());
                String _description = description.getText();
                String _schedule = schedule.getText();

                if(_title.isEmpty() || _cc.isEmpty() || _description.isEmpty() || _schedule.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Make sure all fields are filled!");
                    return;
                }

                try {
                    CallableStatement cs = db.connection.prepareCall("{CALL AddCourse(?,?,?,?,?)}");
                    cs.setString(1, _cc);
                    cs.setString(2, _title);
                    cs.setString(3, _description);
                    cs.setInt(4, _capacity);
                    cs.setString(5, _schedule);
                    cs.execute();
                    JOptionPane.showMessageDialog(frame, "Successfully added");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to add");

                }
            }
        });


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                title.requestFocus();
                description.requestFocus();
                courseCode.requestFocus();
                schedule.requestFocus();
                add.requestFocus();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
        frame.setVisible(true);
    }
}

