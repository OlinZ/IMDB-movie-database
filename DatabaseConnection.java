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

  public DatabaseConnection() {
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

  public List<Director> getLeastMovies(int startYear, int endYear, String excludeActor) {
    List<Director> directors = new ArrayList<Director>();
    List<Director> minDirectors = new ArrayList<Director>();
    // Map<String, Integer> directorNumMovies = new HashMap<String, Integer>();
    String query = "SELECT primarytitle, directors FROM title_crew INNER JOIN title_basics ON title_crew.tconst = title_basics.tconst where title_basics.startYear BETWEEN "
        + startYear + " AND " + endYear + " AND directors IS NOT NULL";
    try {
      Statement stmt = conn.createStatement();
      ResultSet result = stmt.executeQuery(query);
      // while (result.next()) {
      // System.out.println(result.getString("tconst"));
      // }

      System.out.println(query);
      System.out.println("______________________________________");
      // int count = 0;
      while (result.next()) {
        // count++;
        String movieName = result.getString("primarytitle");
        String directorID = result.getString("directors");
        System.out.println(directorID);
        // System.out.println(movieName);
        if (directorID != null) {
          String[] directorList = directorID.split(",");
          Director d;
          // String m = "";
          for (int k = 0; k < directorList.length; k++) {
            d = new Director(directorList[k]);
            d.name = getDirectors(directorList[k]);
            // m = getMovieName(movieName);
            if (directors.contains(d)) {
              directors.get(directors.indexOf(d)).addMovie(movieName);
              System.out.println("dupe");
            } else {
              d.addMovie(movieName);
              directors.add(d);
            }
            // if (!directorNumMovies.containsKey(directorList[k])) {
            // directorNumMovies.put(directorList[k], 1);
            // } else {
            // directorNumMovies.put(directorList[k],
            // directorNumMovies.get(directorList[k])
            // + 1);
            // }
          }

        }
      }

      // System.out.println(count);
    } catch (Exception e) {
      System.out.println("Error accessing Database." + e.getMessage());
    }
    // System.out.println(directors);

    System.out.println("done " + directors.size());
    Collections.sort(directors, new SortBySize());
    System.out.println("Sorted");
    int min = directors.get(0).getSize();
    int i = 0;
    while (i < directors.size() && directors.get(i).getSize() == min) {
      System.out.println(directors.get(i).getSize());
      minDirectors.add(directors.get(i));
      i++;
    }
    System.out.println(minDirectors.size());

    // int min = Integer.MAX_VALUE;
    // for (Director d : directors) {
    // if (d.getSize() < min) {
    // min = d.getSize();
    // }
    // }

    // for (Director d : directors) {
    // if (d.getSize() == min && !d.name.equals("")) {
    // minDirectors.add(d);
    // }
    // }

    return minDirectors;
  }

  public String getDirectors(String id) {
    String query = "SELECT primaryname from directors where nconst = \'" + id + "\'";
    String name = "";
    try {
      Statement stmt = conn.createStatement();
      ResultSet result = stmt.executeQuery(query);

      // System.out.println(query);
      // System.out.println("______________________________________");
      while (result.next()) {
        name = result.getString("primaryname");
      }
    } catch (Exception e) {
      System.out.println("Error accessing Database." + e.getMessage());
    }
    return name;
  }

  public List<String> getMovies(String id) {
    List<String> movies = new ArrayList<String>();
    String query = "SELECT tconst FROM title_crew WHERE directors LIKE \'%" + id + "%\'";
    try {
      Statement stmt = conn.createStatement();
      ResultSet result = stmt.executeQuery(query);
      while (result.next()) {
        movies.add(result.getString("tconst"));
      }
    } catch (Exception e) {

      System.out.println("Error accessing Database." + e.getMessage());
    }
    for (int k = 0; k < movies.size(); k++) {
      movies.set(k, getMovieName(movies.get(k)));
    }
    return movies;
  }

  private String getMovieName(String id) {
    String query = "SELECT primarytitle from title_basics where tconst = \'" + id + "\'";
    String name = "";
    try {
      Statement stmt = conn.createStatement();
      ResultSet result = stmt.executeQuery(query);

      // System.out.println(query);
      // System.out.println("______________________________________");
      while (result.next()) {
        name = result.getString("primarytitle");
      }
    } catch (Exception e) {
      System.out.println("Error accessing Database." + e.getMessage());
    }
    return name;
  }

  public ResultSet getResults(String query) {
    ResultSet result;
    try {
      Statement stmt = conn.createStatement();
      result = stmt.executeQuery(query);
      // System.out.println(query);
      return result;
    } catch (Exception e) {
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
