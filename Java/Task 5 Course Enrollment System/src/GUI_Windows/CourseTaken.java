package GUI_Windows;

import DataBase.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CourseTaken extends Window {

    private JFrame frame;
    private DatabaseConnection db;
    private DefaultTableModel tableModel;
    private JComboBox<String> input;
    private JTable table;
    private JButton search;
    private JScrollPane scrollPane;

    public CourseTaken() {
        this.frame = new JFrame("Course Enrollment status");
        this.tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Course Code");
        tableModel.addColumn("Title");
        tableModel.addColumn("Description");
        tableModel.addColumn("Schedule");
        tableModel.addRow(new Object[]{"Course ID", "Course Code", "Title", "Description", "Schedule"});
        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.createHorizontalScrollBar();
        this.table = new JTable(tableModel);
//        table.setAutoscrolls(true);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // copied and not working
        // Add the JScrollPane (containing the JTable) to the JFrame
//        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.input = new JComboBox<String>();
        this.search = new JButton("Search");
        this.db = new DatabaseConnection();
        db.connectDatabase();
    }

    @Override
    public void setupUI() {
        frame.setLayout(null);
        frame.setSize(700,500);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //set bounds
        input.setBounds(25, 50, 300, 30);
        search.setBounds(350, 50, 115, 30);
        table.setBounds(25, 130, 640, 310);


        // add component
        frame.add(input);
        frame.add(search);
        frame.add(table);
        frame.add(scrollPane);

        // config
        input.addItem("Select Student Name");
        try {
            this.setStudentNames();
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
                if(selected.equals("Select Student Name")) {
                    JOptionPane.showMessageDialog(frame, "Please Select Student Name");
                    return;
                }
                try {
                    int id = Integer.parseInt(selected.split("\\.")[0]);
                    CallableStatement cs = db.connection.prepareCall("{CALL ListCoursesEnrolledByStudent(?)}");
                    cs.setInt(1, id);
                    ResultSet rs = cs.executeQuery();
                    updateTable(rs);
                    resizeColumnWidth(table);

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
            tableModel.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
        }
    }

    private void setStudentNames() throws SQLException {
        CallableStatement cs = db.connection.prepareCall("{CALL GetStudentNames()}");
        cs.execute();
        ResultSet rs =  cs.getResultSet();
        while(rs.next()) {
            input.addItem(rs.getString(1) + ". " + rs.getString(2));
        }
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
