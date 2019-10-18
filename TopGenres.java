import java.util.*;
import java.sql.ResultSet;
import javax.swing.*;

public class TopGenres implements Results{
    private String genre;
    private String startYear;
    private String endYear;
    private DatabaseConnection conn;
    private ArrayList<ArrayList<String>> table;
    
    public TopGenres(DatabaseConnection c, String g, String s, String e){
        conn = c;
        genre = g;
        startYear = s;
        endYear = e;
        table = new ArrayList<ArrayList<String>>();

        getResults();

    }

    public void getResults(){

        try {
            String query = "select tb.primarytitle, tb.startyear, tb.runtimeminutes, tr.averagerating from title_basics_list as tb join title_ratings as tr on tb.tconst = tr.tconst where tb.startyear between "+ startYear+" and "+endYear+" and tb.genres && array['"+ genre +"'] and tb.runtimeminutes is not null order by tr.averagerating desc;";
            ResultSet rs = conn.getResults(query);
            ArrayList<String> primarytitle = new ArrayList<String>();
            ArrayList<String> startyear = new ArrayList<String>();
            ArrayList<String> runtime = new ArrayList<String>();
            ArrayList<String> rating = new ArrayList<String>();

            while(rs.next()){
                primarytitle.add(rs.getString("primarytitle"));
                startyear.add(rs.getString("startyear"));
                runtime.add(rs.getString("runtimeminutes"));
                rating.add(rs.getString("averagerating"));
            }
            table.add(primarytitle);
            table.add(startyear);
            table.add(runtime);
            table.add(rating);
            
        } catch (Exception e) {
            System.out.println("Error in the query for genres. " + e.getMessage());
        }
    }

    public void print(){
        for(int r = 0; r < table.size(); r++){
            for(int c = 0; c < table.get(0).size(); c++){
                System.out.print(table.get(r).get(c) + " " );
            }
            System.out.println();
        }
    }

    public JPanel display(){
        JPanel out = new JPanel();
        JLabel label = new JLabel("Testing");
        out.add(label);
        return out;
    }


}