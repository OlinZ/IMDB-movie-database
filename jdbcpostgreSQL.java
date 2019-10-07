import java.sql.*;
import java.util.*;
//import java.sql.DriverManager;
/*
Tim McGuire, adapted from Robert lightfoot
CSCE 315
9-27-2019
 */
public class jdbcpostgreSQL {
  public static List<String> getResults(String query) {
    List<String> results = new ArrayList<String>();
    //Building the connection
     Connection conn = null;
     try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://db-315.cse.tamu.edu/605team6",
           dbSetup.user, dbSetup.pswd);
     } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
     }//end try catch
     System.out.println("Opened database successfully");

     try{
     //create a statement object
       Statement stmt = conn.createStatement();
       //send statement to DBMS
       ResultSet result = stmt.executeQuery(query);

       //OUTPUT
       System.out.println(query);
       System.out.println("______________________________________");
       while (result.next()) {
         results.add(result.getString("primarytitle"));
         results.add(result.getString("tconst"));
       }
   } catch (Exception e){
     System.out.println("Error accessing Database.");
   }

    //closing the connection
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } catch(Exception e) {
      System.out.println("Connection NOT Closed.");
    }//end try catch
    return results;
  }//end main
}//end Class
