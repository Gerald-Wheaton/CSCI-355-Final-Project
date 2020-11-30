// Schepisi, Group 2
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.net.ssl.SSLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class crud {//Create, Read, Update, & Delete on Profile Table

	public static void main(String[] args) {
	String dbURL = "jdbc:mysql://deltona.birdnest.org/my_schepisii2_groupproject?verifyServerCertificate=false&useSSL=true";
	String username = "my.schepisii2";
	String password = "applesauce";
	

	try {
		Connection conn = DriverManager.getConnection(dbURL, username, password);

		if (conn != null) {
			System.out.println("Connected");
		}

		//Creation of user_account necessary to filfull foreign key requirements
		/*String createUser = "INSERT INTO USER_ACCOUNT (id, Email, FirstName, LastName, Phone, StreetAddress, City, State, Zip) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement createUserStatement = conn.prepareStatement(createUser);
		createUserStatement.setInt(1, 1);
		createUserStatement.setString(2, "stuffb@winthrop.edu");
		createUserStatement.setString(3, "Big");
		createUserStatement.setString(4, "Stuff");
		createUserStatement.setString(5, "803-323-2211");
		createUserStatement.setString(6, "701 Oakland Avenue");
		createUserStatement.setString(7, "Rock Hill");
		createUserStatement.setString(8, "SC");
		createUserStatement.setString(9, "29733"); //string because some accounts may have an extention e.g. 12345-6789

		int userRowsInserted = createUserStatement.executeUpdate();
		if (userRowsInserted > 0 ){
			System.out.println("A new user account was inserted successfully!");
		}
*/
		//Create profile
		String createProfile = "INSERT INTO PROFILE (id, Name, AcctID) VALUES (?, ?, (SELECT id FROM USER_ACCOUNT WHERE email=?))";

		PreparedStatement createProfileStatement = conn.prepareStatement(createProfile);
		createProfileStatement.setInt(1,1);
		createProfileStatement.setString(2, "Big Stuff");
		createProfileStatement.setString(3, "stuffb@winthrop.edu");
		
		int profileRowsInserted = createProfileStatement.executeUpdate();
		if (profileRowsInserted > 0) {
			System.out.println("A new profile was inserted successfully!");
		}	

		//Read profile
		String read = "SELECT * FROM PROFILE";

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(read);
		 
		int count = 0;
		 
		while (result.next()){
    			int id = result.getInt("id");
		        String name = result.getString("Name");
			int acctID = result.getInt("AcctID");
			
			String output = "Profile #%d: %s - %s - %s";
		        System.out.println(String.format(output, ++count, id, name, acctID));
		}

		//Update profile
		String update = "UPDATE PROFILE SET name=? WHERE id=?";

		PreparedStatement updateStatement = conn.prepareStatement(update);
		updateStatement.setString(1, "stuffb1");
		updateStatement.setInt(2, 1);

		int rowsUpdated = updateStatement.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println("An existing user was updated successfully!");
		}

		//Delete profile
		String delete = "DELETE FROM PROFILE WHERE id=?";

		PreparedStatement deleteStatement = conn.prepareStatement(delete);
		deleteStatement.setInt(1, 1);

		int rowsDeleted = deleteStatement.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println("A profile was deleted successfully!");
		}

	
			conn.close();
	}catch (SQLException ex) {
		ex.printStackTrace();
	}
	}
}
