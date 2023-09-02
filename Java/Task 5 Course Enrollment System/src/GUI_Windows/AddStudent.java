package GUI_Windows;

import DataBase.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
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

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = studentName.getText();
                try {
                    CallableStatement cs =  db.connection.prepareCall("{CALL addStudent(?)}");
                    cs.setString(1, name);
                    if(cs.execute()) {
                        JOptionPane.showMessageDialog(frame, "Successfully added");
                    }else{
                        JOptionPane.showMessageDialog(frame, "Failed to add, Please try again");
                    }
                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
                    JOptionPane.showMessageDialog(frame, "MySQL error");
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                submit.requestFocus();
                db.connectDatabase();
            }
        });

        frame.setVisible(true);
    }
}

