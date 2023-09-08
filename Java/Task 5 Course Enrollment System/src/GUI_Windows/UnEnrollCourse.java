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
import java.sql.Types;

public class UnEnrollCourse extends Window {
    private JFrame frame;
    private DatabaseConnection db;
    private JComboBox<String> name;
    private JComboBox<String> course;
    private JButton unEnroll;

    public UnEnrollCourse() {
        this.frame = new JFrame("UnEnroll course");
        this.name = new JComboBox<String>();
        this.course = new JComboBox<String>();
        this.unEnroll = new JButton("UnEnroll");
        this.db =  DatabaseConnection.getInstance();

    }

    @Override
    public void setupUI() {
        frame.setLayout(null);
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // set bounds
        name.setBounds(100, 50, 200, 40);
        course.setBounds(100, 140, 200, 40);
        unEnroll.setBounds(150, 220, 100,40);

        // add components
        frame.add(name);
        frame.add(course);
        frame.add(unEnroll);

        // config
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                unEnroll.requestFocus();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
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

        unEnroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCode = (String) course.getSelectedItem();
                String studentName = (String) name.getSelectedItem();
                if(courseCode.equals("Select course") || studentName.equals("Select name")){
                    JOptionPane.showMessageDialog(frame,"Make sure you have selected student and course name");
                    return;
                }


                String id = studentName.split("\\.")[0];

                try {
                    CallableStatement cs = db.connection.prepareCall("{CALL GetCourseIDByCode(?, ?)}");
                    cs.setString(1, courseCode);
                    cs.registerOutParameter(2, Types.INTEGER);
                    cs.execute();
                    int courseId = cs.getInt(2);
                    cs = db.connection.prepareCall("{CALL RemoveCourseRegistration(?, ?, ?)}");
                    cs.setInt(1, Integer.parseInt(id));
                    cs.setInt(2, courseId);
                    cs.registerOutParameter("removalStatus", Types.VARCHAR);
                    cs.execute();
                    JOptionPane.showMessageDialog(frame, cs.getString("removalStatus"));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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
