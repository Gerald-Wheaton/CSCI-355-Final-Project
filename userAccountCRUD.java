
// Gerald, Group 2
import javax.net.ssl.SSLException;
import java.sql.*;

import java.util.*;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableStringConverter;

class userAccountCRUD {
  public static void main(String[] args) {
    String dbURL = "jdbc:mysql://deltona.birdnest.org/my_schepisii2_groupproject?verifyServerCertificate=false&useSSL=true";
    String username = "my.schepisii2";
    String password = "applesauce";

    try {
      Connection conn = DriverManager.getConnection(dbURL, username, password);

      if (conn != null) {
        System.out.println("Connected");
      }

      String operations[] = { "create", "insert", "read", "select", "update", "delete" };
      String columns = "";
      String values = "";
      String sqlStatement = "";
      String condition = "";
      String[] tableColumns = new String[] { "Email", "FirstName", "LastName", "Phone", "StreetAddress", "City",
          "State", "Zip" };

      boolean error = false;

      Scanner input = new Scanner(System.in);

      // create
      sqlStatement = "INSERT INTO USER_ACCOUNT (Email, FirstName, LastName, Phone, StreetAddress, City, State, Zip) VALUES (\"gerald20@place.org\", \"Gerald\", \"Wheaton\", \"8907657788\", \"123 Place Ave\", \"Bigger Place\", \"SC\", \"34567\");";
      PreparedStatement insertStatement = conn.prepareStatement(sqlStatement);
      int didInsert = insertStatement.executeUpdate();
      if (didInsert > 0) {
        System.out.println("CREATE was completed" + "\n");
      }

      // read
      sqlStatement = "SELECT * FROM USER_ACCOUNT;";
      Statement statement = conn.createStatement();
      ResultSet result = statement.executeQuery(sqlStatement);

      int count = 0;

      while (result.next()) {
        String email = result.getString("Email");
        String firstName = result.getString("FirstName");
        String lastName = result.getString("LastName");
        String phone = result.getString("Phone");
        String street = result.getString("StreetAddress");
        String city = result.getString("City");
        String state = result.getString("State");
        String zip = result.getString("Zip");
        String results = "USER_ACCOUNT #%d: %s, %s, %s, %s, %s, %s, %s, %s";
        System.out.println(String.format(results, ++count, email, firstName, lastName, phone, state, city, state, zip));

      }

      // update
      sqlStatement = "UPDATE USER_ACCOUNT SET Email = \"steve@newemail.org\" WHERE id = 1;";
      PreparedStatement updateStatement = conn.prepareStatement(sqlStatement);
      int didUpdate = updateStatement.executeUpdate();
      if (didInsert > 0) {
        System.out.println("UPDATE was completed" + "\n");
      }

      // delete
      sqlStatement = "DELETE FROM USER_ACCOUNT WHERE id = 1;";
      PreparedStatement deleteStatement = conn.prepareStatement(sqlStatement);
      int didDelete = deleteStatement.executeUpdate();
      if (didDelete > 0) {
        System.out.println("DELETE was completed" + "\n");
      }

      System.out.println("\n");

      input.close();

      conn.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
