import java.sql.*;
import java.util.Scanner;

public class DatabaseManagement { // Save as "JdbcSelectTest.java" (JDK 7 and above)
    public static void main(String[] args) {
        try (
                // Allocation of a database object 'Connection'
                Connection conn = DriverManager
                        .getConnection("jdbc:mysql://deltona.birdnest.org:3306/my_schepisii2_groupproject?useSSL=false"
                                + "user=my.schepisii2&password=applesauce");
                Statement stmt = conn.createStatement();) { // body of code where statements can be made

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter your SQL statement below:");
            String strSelect = sc.next();// assign the sql statement strinfg to this variable
            System.out.println("The SQL statement is: " + strSelect + "\n");

            ResultSet resultSet = stmt.executeQuery(strSelect);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // TODO: insert functions that return a sql statment as a string
}