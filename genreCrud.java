//ALEX KIRBY
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.net.ssl.SSLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class genreCrud{
  public static void main(){


  String dbURL = "jdbc:mysql://deltona.birdnest.org/my_schepisii2_groupproject?verifyServerCertificate=false&useSSL=true";
	String username = "my.schepisii2";
	String password = "applesauce";
	

  try {
    	Connection conn = DriverManager.getConnection(dbURL, username, password);

		if (conn != null) {
			System.out.println("Connected");
		}
    Scanner input = new Scanner(System.in);
    System.Out.println ("Choose the number for the operation to perform. \n\n 1.Create 2.Read 3.Update 4.Delete\n");
    int userInput = input.nextInt();
    String info;
    int infoN;
	  

    switch(userInput) {
      case 1:
        String sql = "INSERT INTO Genre (id, Genre) VALUES (?,?)";
        PreparedStatement InsertStatement = conn.prepareStatement(sql);
        System.Out.println ("Input the Genre\n");
        info = input.next();
        Insertstatement.setString(1, "");
        Insertstatement.setString(2, info);
        input.close();
        int rowsInserted = Insertstatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new genre was inserted\n");
        }

      break;
      case 2:
        String Selectsql = "SELECT * FROM Genre";
 
        Statement SelectStatement = conn.createStatement();
        ResultSet result = SelectStatement.executeQuery(Selectsql);
 
        int count = 0;
 
        while (result.next()){
          String id = result.getString(1);
          String genre = result.getString(2);
         
 
          String output = "Genre #%d:%s - %s";
          System.out.println(String.format(output,++count, id, genre));
        }   
      break;
      case 3:
        String Updatesql = "UPDATE Genre SET Genre=? WHERE id=?";
 
        PreparedStatement Updatestatement = conn.prepareStatement(Updatesql);
        System.Out.println ("What is the genre id to update?\n");
        info = input.next();
	 try
            {
            infoN=Integer.parseInt(info);
            }
         catch(NumberFormatException e)
            {
            System.out.println("Please input an ID number");
            }
        Updatestatement.setString(2, infoN);
        System.Out.println ("Update genre\n");
        info = input.next();
        Updatestatement.setString(1, info);
  
 
        int rowsUpdated = Updatestatement.executeUpdate();
        if (rowsUpdated > 0) {
          System.out.println("The genre was updated\n");
        }
      break;
      case 4:
        String Deletesql = "DELETE FROM Genre WHERE id=?";
 
        PreparedStatement Deletestatement = conn.prepareStatement(Deletesql);
        System.Out.println ("What is the genre id of the genre to delete?\n");
        info = input.next();
	 try
	    {
            infoN=Integer.parseInt(info);
            }
         catch(NumberFormatException e)
            {
            System.out.println("Please input an ID number");
            } 
        Deletestatement.setString(1, infoN);
 
        int rowsDeleted = Deletestatement.executeUpdate();
        if (rowsDeleted > 0) {
          System.out.println("the genre was deleted\n");
        }
      break;
      default:
          System.Out.println ("Operation was not selected");
      break; 
      conn.close();
    }
  }catch (SQLException ex) {
    ex.printStackTrace();
  }
}
}
