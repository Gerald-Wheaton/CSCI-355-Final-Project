//Philip Nelson, Group 2
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.net.ssl.SSLException;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Time;
public class movieCrud {//Create, Read, Update, & Delete on Profile Table

    static void create(Connection conn){
        Scanner input = new Scanner(System.in);
     
        
    }
    static void read(){

    } 
    static void update(){

    }
    static void delete(){

    }
	public static void main(String[] args) {
	String dbURL = "jdbc:mysql://deltona.birdnest.org/my_schepisii2_groupproject?verifyServerCertificate=false&useSSL=true";
	String username = "my.schepisii2";
    String password = "applesauce";
    int choiceNum = -1;
    Scanner input = new Scanner(System.in);
    System.out.println("What would you like to do? (Enter a number)\n1. Create\n2. Read\n3. Update\n4. Delete");
    String choiceString = input.nextLine();
    try
    {
    choiceNum=Integer.parseInt(choiceString);
    }
    catch(NumberFormatException e)
    {
    //If number is not integer,you wil get exception and exception message will be printed
    System.out.println("Please input a number for your choice");
    }
    

    
    
	try {
		Connection conn = DriverManager.getConnection(dbURL, username, password);

		if (conn != null) {
			System.out.println("Connected");
        }
        switch(choiceNum) {
            case 1:
            String sql = "INSERT INTO MOVIE (Name, YearReleased, Runtime, Description) VALUES (?,?,?,?)";
            PreparedStatement InsertStatement = conn.prepareStatement(sql);
            System.out.println("What is the name of the movie?\n");
            String name=input.nextLine();
            System.out.println("What year was it released?");
            String year=input.nextLine();
            System.out.println("What is the runtime? (hh:mm:ss)");
            String runtime=input.nextLine();
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            Date datetime = new Date();
            try {
            datetime=dateFormat.parse(runtime);
            }
            catch (Exception e)
            {
                System.out.println("Enter a valid timestamp");
            }
            Time formattedRuntime = new java.sql.Time(datetime.getTime());
        
            System.out.println("Enter a description of the movie:");
            String desc=input.nextLine();
            InsertStatement.setString(1, name);
            InsertStatement.setString(2, year);
            InsertStatement.setTime(3, formattedRuntime);
            InsertStatement.setString(4, desc);
            int inserted=InsertStatement.executeUpdate();
            if(inserted>0)
                System.out.println("Inserted new movie");
            break;
            case 2:
            sql = "SELECT * FROM MOVIE";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            System.out.println("ID\tNAME\tYEAR\tRUNTIME \tDESCRIPTION");
            while (result.next()){
                    int id = result.getInt("id");
                    String title = result.getString("name");
                    String releaseYear = result.getString("YearReleased");
					Time timeVar = result.getTime("Runtime");
                    String descrip = result.getString("Description");
                    System.out.println(id+"\t"+title+"\t"+releaseYear+"\t"+timeVar+" \t"+descrip);
				}
                break;
            case 3:
            int idNum = -1;
            sql = "UPDATE MOVIE SET Name=?, YearReleased=?, Runtime=?, Description=? WHERE id=?";
            PreparedStatement UpdateStatement = conn.prepareStatement(sql);
            System.out.println("What is the ID of the movie to be updated?");
            String idString = input.nextLine();
            try
            {
            idNum=Integer.parseInt(idString);
            }
            catch(NumberFormatException e)
            {
            System.out.println("Please input an ID number");
            }
            System.out.println("What is the name of the movie?");
            name=input.nextLine();
            System.out.println("What year was it released?");
            year=input.nextLine();
            System.out.println("What is the runtime? (hh:mm:ss)");
            runtime=input.nextLine();
            dateFormat = new SimpleDateFormat("hh:mm:ss");
            datetime = new Date();
            try {
            datetime=dateFormat.parse(runtime);
            }
            catch (Exception e)
            {
                System.out.println("Enter a valid timestamp");
            }
            formattedRuntime = new java.sql.Time(datetime.getTime());
        
            System.out.println("Enter a description of the movie:");
            desc=input.nextLine();
            UpdateStatement.setString(1, name);
            UpdateStatement.setString(2, year);
            UpdateStatement.setTime(3, formattedRuntime);
            UpdateStatement.setString(4, desc);
            UpdateStatement.setInt(5,idNum);
            int updated=UpdateStatement.executeUpdate();
            if(updated>0)
                System.out.println("Updated movie");
                break;
            case 4:
            idNum = -1;
            sql = "DELETE FROM MOVIE WHERE id=?";
            PreparedStatement DeleteStatement = conn.prepareStatement(sql);
            System.out.println("What is the ID of the movie to be updated?");
            idString = input.nextLine();
            try
            {
            idNum=Integer.parseInt(idString);
            }
            catch(NumberFormatException e)
            {
            System.out.println("Please input an ID number");
            } 
            DeleteStatement.setInt(1,idNum);
            int deleted =  DeleteStatement.executeUpdate();
            if (deleted>0){
                System.out.println("Deleted successfully");
            }

                break;
          }
		
	
			conn.close();
	}catch (SQLException ex) {
        //ex.printStackTrace();
        System.out.println("Couldn't connect");
	}
	}
}
