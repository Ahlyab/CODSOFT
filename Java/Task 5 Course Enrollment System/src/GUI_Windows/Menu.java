package GUI_Windows;

import CustomGUIComponents.CustomButtons;
import DataBase.DatabaseConnection;
import ExceptionsAndActions.MenuActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Menu extends Window {
    private JFrame frame;
    private CustomButtons addStudent;
    private CustomButtons addCourse;
    private CustomButtons checkStudentsInCourse;
    private CustomButtons checkCourseEnrolledByStudent;
    private CustomButtons registerForCourse;
    private CustomButtons unEnrollCourse;
    private JLabel heading;

    public Menu() {
        frame = new JFrame();
        unEnrollCourse = new CustomButtons("Unenroll course");
        registerForCourse = new CustomButtons("Register for course");
        checkStudentsInCourse = new CustomButtons("Students Enrolled");
        checkCourseEnrolledByStudent = new CustomButtons("Course taken");
        addCourse = new CustomButtons("Add Course");
        addStudent = new CustomButtons("Add Student");
        heading = new JLabel("Select Operation");
    }

    public void setupUI() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Menu - Course Management");
        frame.setSize(600, 700);

        // set bounds

        heading.setSize(200, 200);
        heading.setBounds(200, 20, 200, 200);
        heading.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        addStudent.setBounds(60, 250, 200, 40);
        addCourse.setBounds(310, 250, 200, 40);

        registerForCourse.setBounds(60,350, 200, 40);
        unEnrollCourse.setBounds(310,350, 200, 40);


        checkStudentsInCourse.setBounds(60,450, 200, 40);
        checkCourseEnrolledByStudent.setBounds(310,450, 200, 40);

        // add buttons
        frame.add(addStudent);
        frame.add(addCourse);
        frame.add(checkCourseEnrolledByStudent);
        frame.add(checkStudentsInCourse);
        frame.add(registerForCourse);
        frame.add(this.unEnrollCourse);
        frame.add(heading);

        // adding actionListeners
        addStudent.addActionListener(new MenuActionListener(new AddStudent()));
        addCourse.addActionListener(new MenuActionListener(new AddCourse()));
        checkStudentsInCourse.addActionListener(new MenuActionListener(new CheckStudentsEnrolled()));
        checkCourseEnrolledByStudent.addActionListener(new MenuActionListener(new CourseTaken()));
        registerForCourse.addActionListener(new MenuActionListener(new RegisterCourse()));
        unEnrollCourse.addActionListener(new MenuActionListener(new UnEnrollCourse()));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    DatabaseConnection.getInstance().closeConnection();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                super.windowClosed(e);
            }
        });

        frame.setLayout(null);
        frame.setVisible(true);
    }

}

