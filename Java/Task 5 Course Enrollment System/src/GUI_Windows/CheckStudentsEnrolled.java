package GUI_Windows;

import DataBase.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CheckStudentsEnrolled extends Window {

    private JFrame frame;
    private DatabaseConnection db;
    private DefaultTableModel tableModel;
    private JComboBox<String> input;
    private JTable table;
    private JButton search;

    public CheckStudentsEnrolled() {
        this.frame = new JFrame("Student Enrollment status");
        this.tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addRow(new Object[]{"Student ID", "Student Name"});
        JScrollPane scrollPane = new JScrollPane(table);
        // Add the JScrollPane (containing the JTable) to the JFrame
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.table = new JTable(tableModel);
        this.input = new JComboBox<String>();
        this.search = new JButton("Search");
        this.db = new DatabaseConnection();
        db.connectDatabase();
    }

    @Override
    public void setupUI() {
        frame.setLayout(null);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //set bounds
        input.setBounds(25, 50, 300, 30);
        search.setBounds(350, 50, 115, 30);
        table.setBounds(25, 130, 440, 310);

        // add component
        frame.add(input);
        frame.add(search);
        frame.add(table);

        // config
        input.addItem("Select course code");
        try {
            this.setCourses();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                search.requestFocus();
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

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String)input.getSelectedItem();
                if(selected.equals("Select course code")) {
                    JOptionPane.showMessageDialog(frame, "Please select course code");
                    return;
                }
                try {
                    CallableStatement cs  = db.connection.prepareCall("{CALL GetCourseIDByCode(?, ?)}");
                    cs.setString(1, selected);
                    cs.registerOutParameter(2, Types.INTEGER);
                    cs.execute();
                    int courseId = cs.getInt(2);
                    cs = db.connection.prepareCall("{CALL ListStudentsEnrolledInCourse(?)}");
                    cs.setInt(1, courseId);
                    cs.execute();

                    updateTable(cs.getResultSet());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        frame.setVisible(true);
    }

    public void updateTable(ResultSet rs) throws SQLException {
        // remove older records
        for(int i=1; i<tableModel.getRowCount(); ++i) {
            tableModel.removeRow(i);
        }

        while(rs.next()) {
            tableModel.addRow(new Object[]{rs.getString(1), rs.getString(2)});
        }
    }

    private void setCourses() throws SQLException {
        CallableStatement cs = db.connection.prepareCall("{CALL GetCourseNames()}");
        cs.execute();
        ResultSet rs =  cs.getResultSet();
        while(rs.next()) {
            input.addItem(rs.getString(1));
        }
    }
}

