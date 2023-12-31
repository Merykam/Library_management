package pack.youcode.library;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static Connection con;

    public static Connection createDBConnection(){

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");


            // Establish a connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saslibrary", "root", "");

            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null; // Return null if the connection fails
        }
    }

}


