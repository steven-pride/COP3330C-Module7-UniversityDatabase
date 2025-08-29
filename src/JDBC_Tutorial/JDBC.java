/*
Steven Pride
COP3330C - 26824
03/18/2025

Problem Description:
Demonstrate the use of JDBC and SQLite to connect to databases
Demonstrate the use of SQL Helper to generate DBHelper code.

Inputs:
No direct user input.
Select queries will be hard coded.

Outputs:
1. 15 Rows will be written out
2. 1 Row will be written out with Student_ID 10202, First Name John, Last Name Doe
 */

package JDBC_Tutorial;

//Required imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Part 2.1 application class
 */
public class JDBC {
    /**
     * Main method for the application
     * @param args an array of strings, unused.
     */
    public static void main(String [] args) throws SQLException {
        String uname = "richard-simplilearn";
        String password = "mypassword";
        String query1 = "Select * from EngineeringStudents";
        String query2 = "Select Student_ID, Department, First_Name, Last_Name, PassOutYear, UniversityRank from EngineeringStudents where Student_ID = 10202";
        String url = "jdbc:sqlite:./db/University.db";

        try{
            Connection con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query1);

            while(result.next()){
                String UniversityData = "";
                for(int i = 1; i <= 6; i++){
                    UniversityData += result.getString(i) + ":";
                }
                System.out.println(UniversityData);
            }

            result = statement.executeQuery(query2);

            while(result.next()){
                String UniversityData = "";
                for(int i = 1; i <= 6; i++){
                    UniversityData += result.getString(i) + ":";
                }
                System.out.println(UniversityData);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
