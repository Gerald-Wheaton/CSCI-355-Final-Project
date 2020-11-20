# CSCI-355-Final-Project
Upload your code here for the final project

The idea for this program is that it would be best if each of us writes a separate function that can be copied and pasted into the skeleton program.

Each function will be called inside of a switch statement controlled by what table the user chooses to modify.

The functions should return a string with the SQL statement
  ex: 
  public static String FunctionName(String operation, String tableName, String [] specifiedColumns, String [] specified Values) {
    ...//
    ...//
    return "INSERT INTO <specified_table> (<specified_column1>,<specified_column2>,<specified_column3>,...) VALUES (<specified_value1>,<specified_value2>);"
  }

The switch statement will take the returned string and pass it to the mySQL server/databse
