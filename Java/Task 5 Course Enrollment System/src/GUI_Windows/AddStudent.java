package GUI_Windows;

import DataBase.DatabaseConnection;
import ExceptionsAndActions.InputFocusListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class AddStudent extends Window {

    private JFrame frame;

    private DatabaseConnection db;
    private JLabel heading;
    private JTextField studentName;
    private JButton submit;

    public AddStudent() {
        frame = new JFrame("Add Student");
        heading = new JLabel("Add Student");
        studentName = new JTextField();
        submit = new JButton("Add");
        db = new DatabaseConnection();
        db.connectDatabase();

    }

    @Override
    public void setupUI() {
        frame.setLayout(null);
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

        studentName.addFocusListener(new InputFocusListener(studentName, "Enter student name"));

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = studentName.getText();
                try {
                    CallableStatement cs =  db.connection.prepareCall("{CALL addStudent(?)}");
                    cs.setString(1, name);
                    cs.execute();
                    JOptionPane.showMessageDialog(frame, "Successfully added");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "MySQL error : " + ex.getMessage());
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
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

