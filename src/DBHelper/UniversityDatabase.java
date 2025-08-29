/*
Steven Pride
COP3330C - 26824
03/18/2025

Problem Description:
Demonstrate the use of JDBC and SQLite to connect to databases
Demonstrate the use of SQL Helper to generate DBHelper code.

Inputs:
No direct user input.
Select, insert, update, and delete queries will be hard coded.

Outputs:
1. Added two new rows
2. 17 Rows will be written out, the new rows are Student_ID 10220 and 10221
3. Deleted two rows
4. 15 Rows will be written out, the rows deleted are Student_ID 10220 and 10221
5. Selected students from EngineeringStudents where their University Rank is between 60 and 800.
6. 5 Rows will be written out, Student_IDs 10202, 10205, 10208, 10211, 10215
7. Updated three rows
8. 15 rows will be written out,
   Student_ID 10205 will have the Department updated to ENGLISH
   Student_ID 10208 will have the PassOutYear updated to 2019
   Student_ID 10210 will have the Last_name updated to Alexa
 */

package DBHelper;

//Required imports
import javax.swing.table.DefaultTableModel;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Part 3 application class
 */
public class UniversityDatabase {
    /**
     * Main method for the application
     * @param args an array of strings, unused.
     */
    public static void main(String [] args) {
        //create an instance of our database class, EngineeringStudents
        EngineeringStudents uniDb = new EngineeringStudents();

        /*create a 2D array of arrayList to hold the results of a query
          (this can hold columns and rows of any type)*/
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

        //1. Add two new rows to the database, and print a message confirming that a new row of data was added.
        System.out.println("1. ***********************************************************\n");
        uniDb.insert(10220, "CTSD", "Steven", "Pride", 2026, 342);
        uniDb.insert(10221,  "MUSIC", "John", "Fieldstone", 2023, 1362);
        System.out.println("Added two new rows.");

        //2. Print the database, as a 2D ArrayList.
        System.out.println("\n2. ***********************************************************\n");
        data = uniDb.getExecuteResult("select * from EngineeringStudents");
        for(List<Object> record: data){
            System.out.println(record.toString());
        }

        //3. Delete two rows from the databases - you may pick which ones.  Display a message confirming that a row of data was deleted.
        System.out.println("\n3. ***********************************************************\n");
        uniDb.delete(uniDb.Student_ID, "10220");
        uniDb.delete(uniDb.Student_ID, "10221");
        System.out.println("Deleted two rows.");

        //4. Print the database, as a DefaultTableModel.
        System.out.println("\n4. ***********************************************************\n");
        //perform a specific query on the database returning DefaultTableModel
        DefaultTableModel table = new DefaultTableModel();
        table = uniDb.selectToTable("Student_ID, Department, First_name, Last_name, PassOutYear, UniversityRank",
                null, null, null, null);

        for(int row = 0; row < table.getRowCount(); row++){
            for(int column = 0; column < table.getColumnCount(); column++){
                System.out.print(table.getValueAt(row, column).toString() + " | ");
            }
            System.out.println();
        }

        //5. Execute a query to search for a certain student or group of students - you may select the search criteria, but it must differ from the example shown in the videos above.
        System.out.println("\n5. ***********************************************************\n");
        data = uniDb.getExecuteResult("select Student_ID, Department, First_name, Last_name, PassOutYear, UniversityRank from EngineeringStudents where UniversityRank >= 60 and UniversityRank <=800");
        System.out.println("Selected students from EngineeringStudents where their University Rank is between 60 and 800.");

        //6. Print the results of your query to the screen, as a 2D ArrayList.
        System.out.println("\n6. ***********************************************************\n");
        for(List<Object> record: data){
            System.out.println(record.toString());
        }

        //7. Update three values within the table - you may pick which values.
        System.out.println("\n7. ***********************************************************\n");
        uniDb.update(uniDb.Last_Name, "Alexa", uniDb.Student_ID, "10210");
        uniDb.update(uniDb.Department, "ENGLISH", uniDb.Student_ID, "10205");
        uniDb.update(uniDb.PassOutYear, "2019", uniDb.Student_ID, "10208");
        System.out.println("Updated three rows.");

        //8. Print the database, as a DefaultTableModel.
        System.out.println("\n8. ***********************************************************\n");
        table = uniDb.selectToTable(null, null, null, null, null);

        for(int row = 0; row < table.getRowCount(); row++){
            for(int column = 0; column < table.getColumnCount(); column++){
                System.out.print(table.getValueAt(row, column).toString() + " | ");
            }
            System.out.println();
        }
    }
}
