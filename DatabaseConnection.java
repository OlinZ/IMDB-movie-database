import java.sql.*;
import java.util.*;
//import java.sql.DriverManager;
/*
Tim McGuire, adapted from Robert lightfoot
CSCE 315
9-27-2019
 */
public class DatabaseConnection {
  private Connection conn = null;

  public DatabaseConnection(){
    try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://db-315.cse.tamu.edu/605team6",
           DatabaseLogin.user, DatabaseLogin.pswd);
     } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
     }
     System.out.println("Opened database successfully");
  }

  public ResultSet getResults(String query) {     
    ResultSet result;
     try{
       Statement stmt = conn.createStatement();
        result = stmt.executeQuery(query);       
        // System.out.println(query);
        return result;
   } catch (Exception e){
     System.out.println("Error accessing Database." + e.getMessage());
     return null;

   }
  }

  public void closeConnection(){
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } catch(Exception e) {
      System.out.println("Connection NOT Closed.");
    }
  }

}
