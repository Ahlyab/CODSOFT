package DataBase;

import java.sql.*;
public class DatabaseConnection {
    public Connection connection;
    private static DatabaseConnection dbc;
    private DatabaseConnection() {
        connectDatabase();
    }

    public static DatabaseConnection getInstance() {
        if(dbc == null) {
            dbc = new DatabaseConnection();
        }
        return dbc;
    }

    public  void connectDatabase() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// localhost:3306/course_management","root","admin");

        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}