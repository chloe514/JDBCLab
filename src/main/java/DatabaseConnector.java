import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    // Method to establish a connection to SQLite
    public Connection connect() {
        Connection connection = null;
        try {
            // SQLite connection string
            String url = "jdbc:sqlite:C:/Users/chloe/Desktop/Bookstore1.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Successfully connected to the SQLite database!");
        } catch (SQLException e) {
            System.out.println("Error connecting to the SQLite database.");
            e.printStackTrace();
        }
        return connection;
    }

    // Method to execute a SELECT query and print results
    public void selectAllBooks() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Use the connect() method to get the connection
            connection = this.connect();
            String sql = "SELECT * FROM books"; // Adjust the SQL to match your table structure
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            // Process the results
            while (rs.next()) {
                System.out.println(rs.getString("title") + "\t" +
                        rs.getString("author") + "\t" +
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT statement");
            e.printStackTrace();
        } finally {
            // Properly close the resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method to test the methods
    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        connector.selectAllBooks(); // Calls the selectAllBooks method which internally uses connect
    }
}



