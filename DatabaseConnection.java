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
  private Map<String, List<String[]>> actorGraph;
  private final String GET_ACTORS = "SELECT nconst, knownfortitles FROM actors limit 10";

  public DatabaseConnection() {
    actorGraph = new HashMap<String, List<String[]>>();
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://db-315.cse.tamu.edu/605team6", DatabaseLogin.user,
          DatabaseLogin.pswd);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    System.out.println("Opened database successfully");
    // makeGraph();
  }

  // private void makeGraph() {
  // try {
  // Statement stmt = conn.createStatement();
  // ResultSet result = stmt.executeQuery(GET_ACTORS);

  // System.out.println("______________________________________");
  // while (result.next()) {
  // String actorID = result.getString("nconst");
  // String[] movieIDList = result.getString("knownfortitles").split("\\s*,\\s*");
  // System.out.println(movieIDList);
  // }
  // } catch (Exception e) {
  // System.out.println("Error accessing Database." + e.getMessage());
  // }
  // }

  public List<String> getLeastMovies(int startYear, int endYear) {
    List<String> directors = new ArrayList<String>();
    Map<String, Integer> directorNumMovies = new HashMap<String, Integer>();
    String query = "SELECT directors FROM title_crew INNER JOIN title_basics ON title_crew.tconst = title_basics.tconst where title_basics.startYear BETWEEN "
        + startYear + " AND " + endYear;
    try {
      // System.out.println("abc");
      Statement stmt = conn.createStatement();
      ResultSet result = stmt.executeQuery(query);

      System.out.println(query);
      System.out.println("______________________________________");
      while (result.next()) {
        String[] directorList = result.getString("directors").split("\\s*,\\s*");
        for (int k = 0; k < directorList.length; k++) {
          // System.out.println(directorList[k]);
          if (!directorNumMovies.containsKey(directorList[k])) {
            directorNumMovies.put(directorList[k], 1);
          } else {
            directorNumMovies.put(directorList[k], directorNumMovies.get(directorList[k]) + 1);
          }
        }

      }
    } catch (Exception e) {
      System.out.println("Error accessing Database." + e.getMessage());
    }
    int min = Integer.MAX_VALUE;
    for (Map.Entry<String, Integer> entry : directorNumMovies.entrySet()) {
      if (entry.getValue().compareTo(min) < 0) {
        min = entry.getValue();
      }
    }
    for (Map.Entry<String, Integer> entry : directorNumMovies.entrySet()) {
      if (entry.getValue().compareTo(min) == 0) {
        directors.add(getDirectors(entry.getKey()));
        // System.out.println(entry.getValue());
      }
    }

    return directors;
  }

  private String getDirectors(String id) {
    String query = "SELECT primaryname from directors where nconst = \'" + id + "\'";
    String name = "";
    try {
      Statement stmt = conn.createStatement();
      ResultSet result = stmt.executeQuery(query);

      System.out.println(query);
      System.out.println("______________________________________");
      while (result.next()) {
        name = result.getString("primaryname");
      }
    } catch (Exception e) {
      System.out.println("Error accessing Database." + e.getMessage());
    }
    return name;
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

  public void closeConnection() {
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } catch (Exception e) {
      System.out.println("Connection NOT Closed.");
    }
  }

}
