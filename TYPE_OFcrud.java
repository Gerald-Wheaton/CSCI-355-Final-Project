import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.net.ssl.SSLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class TYPE_OFcrud{//Create, Read, Update, & Delete on TYPE_OF Table

	public static void main(String[] args) {
	String dbURL = "jdbc:mysql://deltona.birdnest.org/my_schepisii2_groupproject?verifyServerCertificate=false&useSSL=true";
	String username = "my.schepisii2";
	String password = "applesauce";
	

		try {
		Connection conn = DriverManager.getConnection(dbURL, username, password);

		if (conn != null) {
			System.out.println("Connected");
		}

		
		
			//create			
			String sql = "INSERT INTO TYPE_OF (MovieID, GenreID) VALUES ((SELECT id FROM MOVIE WHERE id=?), (SELECT id FROM GENRE WHERE id=?)";
		 
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, "42069");
				statement.setString(2, "12345");
				 
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("A new entry was inserted successfully!");
				}
		
			//read
			String sql = "SELECT * FROM TYPE_OF";
			 
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(sql);
				 
				while (result.next()){
					int MovieID = result.getInt("MovieID");
					int GenreID = result.getInt("GenreID");
			
					System.out.println("Movie ID: " + MovieID + "Genre ID: " + GenreID);
				}

			//update
			String sql = "UPDATE TYPE_OF SET GenreID=? WHERE MovieID=?";
			 
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, "42069");
				statement.setString(2, "54321");
				 
				int rowsUpdated = statement.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println("An existing entry was updated successfully!");
				}


			//delete	
			String sql = "DELETE FROM TYPE_OF WHERE MovieID=?";
			 
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, "42069");
				 
				int rowsDeleted = statement.executeUpdate();
				if (rowsDeleted > 0) {
					System.out.println("An entry was deleted successfully!");
				}

	
			conn.close();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}