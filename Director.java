import java.util.*;
import java.lang.*;

public class Director implements Comparable {
    public List<String> movies;
    public String id;
    public String name;
    // private DatabaseConnection dbConnection;
    public int size = 0;

    public Director() {
        movies = new ArrayList<String>();
        id = "";
        name = "";
    }

    public Director(String id) {
        this.id = id;
        movies = new ArrayList<String>();
        name = "";

    }

    public int getSize() {
        return size;
    }

    public void addMovie(String name) {
        movies.add(name);
        size++;
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        Director d = (Director) o;
        return this.id.compareTo(d.id);
    }

    @Override
    public String toString() {
        return name + " ----> " + movies.toString() + "\n";
    }

    @Override
    public boolean equals(Object v) {

        boolean retVal = false;

        if (v instanceof Director) {
            Director d = (Director) v;
            retVal = this.id.equals(d.id);
            // System.out.println(this.id + " " + d.id);
        }

        return retVal;
    }
}
