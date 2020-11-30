import java.sql.*;
//import java.util.Properties;
//import java.util.Scanner;
import java.util.*;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableStringConverter;

public class DatabaseManagement { // Save as "JdbcSelectTest.java" (JDK 7 and above)
    public static void main(String[] args) {
        /*
         * Connection dbConnection = null;
         * 
         * try { Class.forName("com.mysql.jdbc.Driver"); String url =
         * "jdbc:mysql://deltona.birdnest.org/my_schepisii2_groupproject"; Properties
         * info = new Properties(); info.put("user", "my.schepisii2");
         * info.put("password", "applesauce");
         * 
         * dbConnection = DriverManager.getConnection(url, info);
         * 
         * if (dbConnection != null) {
         * System.out.println("Successfully connected to MySQL database test"); }
         * 
         * } catch (Exception e) { System.out.println(e); }
         */

        Scanner input = new Scanner(System.in);
        String tables[] = { "genre", "movie", "profile", "typeof", "useraccount", "viewmovie" };
        String operations[] = { "select", "insert", "update", "delete" };

        Stack<String> columns = new Stack<String>();
        Stack<String> values = new Stack<String>();

        boolean error = false;
        boolean hasNext = true;

        System.out.println(
                "Here are your available tables:" + "\nGenre\nMovie\nProfile\nTypeOf\nUserAccount\nViewsMovie");

        // Which Table?
        System.out.print("Which table would you like to modify: ");
        String tableToModify = input.nextLine().toLowerCase();

        // checks that entry is valid
        List table = Arrays.asList(tables);
        if (!table.contains(tableToModify)) {
            error = true;
            while (error) {
                System.out.println("\nPlease select a valid table");
                tableToModify = input.nextLine().toLowerCase();
                if (!table.contains(tableToModify)) {
                    error = true;
                } else {
                    error = false;
                }
            }
        }

        // What opperation
        System.out.print("\nWhat operation would you like to perform: ");
        String operation = input.nextLine().toLowerCase();
        List oplist = Arrays.asList(operations);
        if (!oplist.contains(operation)) {
            error = true;
            while (error) {
                System.out.println("\nPlease select a valid operation");
                operation = input.nextLine().toLowerCase();
                if (!oplist.contains(operation)) {
                    error = true;
                } else {
                    error = false;
                }
            }
        }

        switch (operation) {
            // If insert
            case "insert":
                int numColumns = 0;

                System.out.print("Please enter the column names one at a time\nColumn name: ");
                columns.push(input.nextLine());
                numColumns++;

                while (hasNext) {
                    System.out.print("\nColumn name: ");
                    String colName = input.nextLine();
                    if (colName.equals("")) {
                        hasNext = false;
                        break;
                    }
                    columns.push(colName);
                    numColumns++;
                }
                System.out.println("Number of columns " + numColumns);

                System.out.print("Please enter the values names one at a time\nValue: ");
                values.push(input.nextLine());

                while (numColumns != 1) {
                    System.out.print("\nValue: ");
                    values.push(input.nextLine());
                    numColumns--;
                }

                // Call specified table modification funciton
                break;

            // If update
            // Which column to update
            // What value to replace
            // On what condition make update?

            // If delete
            // On what condition
            // Where ? = ?

            // If select
            // While has nextLine() = what want?
        }

        input.close();
    }

    // TODO: insert functions that return an sql statment as a string

    // public static String userAccountModify(String) {}
}