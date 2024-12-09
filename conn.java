package medicare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class conn {
    public Statement statement; // For simple queries
    public Connection connection;

    // Constructor to initialize connection
    public conn() {
        try {
            // Connect to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicare", "root", "Aditya@563");

            // Create statement for non-parameterized queries
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();  // Print any exceptions
        }
    }

    // You can use PreparedStatement for parameterized queries like this:
    public PreparedStatement getPreparedStatement(String query) throws Exception {
        return connection.prepareStatement(query);
    }
}
