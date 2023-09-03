package GUI_Windows;

import DataBase.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterCourse extends Window {

    private JFrame frame;
    private DatabaseConnection db;
    private JComboBox<String> name;
    private JComboBox<String> course;
    private JButton register;

    public RegisterCourse() {
        this.frame = new JFrame("Register course");
        this.name = new JComboBox<String>();
        this.course = new JComboBox<String>();
        this.register = new JButton("Register");
        this.db = new DatabaseConnection();
        db.connectDatabase();

    }

    @Override
    public void setupUI() {
        frame.setLayout(null);
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // set bounds
        name.setBounds(100, 50, 200, 40);
        course.setBounds(100, 140, 200, 40);
        register.setBounds(150, 220, 100,40);

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

        try{
            setCourses();
            setNames();
        }catch (Exception ex){
            throw  new RuntimeException(ex);
        }

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        frame.setVisible(true);
    }

    private void setCourses() throws SQLException {
        CallableStatement cs = db.connection.prepareCall("{CALL GetCourseNames()}");
        cs.execute();
        ResultSet rs =  cs.getResultSet();
        while(rs.next()) {
            course.addItem(rs.getString(1));
        }
    }

    private void setNames() throws SQLException {
        CallableStatement cs = db.connection.prepareCall("{CALL GetStudentNames()}");
        cs.execute();
        ResultSet rs = cs.getResultSet();
        while(rs.next()) {
            name.addItem(rs.getString(1) + ". " + rs.getString(2));
        }
    }
}

