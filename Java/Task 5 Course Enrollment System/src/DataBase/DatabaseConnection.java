package DataBase;

import java.sql.*;
public class DatabaseConnection {
    public static Connection connection;

    public static void connectDatabase() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:// localhost:3306/course_management","root","admin");
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from students");
//            while(rs.next()) {
//                System.out.println(rs.getString(2));
//            }

        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public static  void closeConnection() throws SQLException {
        connection.close();
    }
}