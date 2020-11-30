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
        String sqlStatement = "SELECT * FROM GENRE";

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
                System.out.print("\nPlease select a valid table ");
                tableToModify = input.nextLine().toLowerCase();
                if (!table.contains(tableToModify)) {
                    error = true;
                } else {
                    error = false;
                }
            }
        }

        // What opperation
        System.out.print("\nWhat operation would you like to perform on table '" + tableToModify + "': ");
        String operation = input.nextLine().toLowerCase();
        List opList = Arrays.asList(operations);
        if (!opList.contains(operation)) {
            error = true;
            while (error) {
                System.out.print("\nPlease select a valid operation ");
                operation = input.nextLine().toLowerCase();
                if (!opList.contains(operation)) {
                    error = true;
                } else {
                    error = false;
                }
            }
        }

        switch (operation) {
            // since INSERT and UPDATE require the same values I just smashed them together
            case "insert":
            case "update":
                hasNext = true;
                int numColumns = 0;

                System.out.print("Please enter the columns you want to modify one at a time\nColumn name: ");
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
                break;

            case "delete":
                break;

            case "select":
                hasNext = true;
                System.out.print("Please enter the columns one at a time that you would like to select from "
                        + tableToModify + "\nColumn name: ");
                columns.push(input.nextLine());

                while (hasNext) {
                    System.out.print("\nColumn name: ");
                    String colName = input.nextLine();
                    if (colName.equals("")) {
                        hasNext = false;
                        break;
                    }
                    columns.push(colName);
                }
                break;
        }

        switch (tableToModify) {
            case "genre":
                // sqlStatement = genreModificationFunction (operation, columns, values)
                // execute
                break;
            case "movie":
                // sqlStatement = genreModificationFunction (operation, columns, values)
                break;
            case "profile":
                // sqlStatement = genreModificationFunction (operation, columns, values)
                break;
            case "typeof":
                // sqlStatement = genreModificationFunction (operation, columns, values)
                break;
            case "useraccount":
                // sqlStatement = genreModificationFunction (operation, columns, values)
                break;
            case "viewmovie":
                // sqlStatement = genreModificationFunction (operation, columns, values)
                break;
        }

        input.close();
    }

    // TODO: declare functions that return an sql statment as a string
}