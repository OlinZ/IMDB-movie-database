import java.util.*;
import java.sql.ResultSet;


public class ConnectedActors {
    private DatabaseConnection conn;
    private NormalQueue<Node> queue;
    private String startActor;
    private String endActor;

    public ConnectedActors(String a1, String a2){
        startActor = a1;
        endActor = a2;
        conn = new DatabaseConnection();
        queue = new NormalQueue<Node> ();
    }

    public void findConnection(){
        boolean match = false;
        Node target = new Node();

        queue.add(new Node(null, startActor, ""));

        while(queue.size() > 0 && !match) {
            ArrayList<Node> nodes = connectedActorsQuery(queue.poll());

            queue.addAll(nodes);

            for (int i = 0; i < nodes.size(); i++) {
//                System.out.println(nodes.get(i).getActor());
                if (endActor.equals(nodes.get(i).getActor())) {
                    match = true;
                    target = nodes.get(i);
                    break;
                }
            }
        }

        target.displayPath(); // display the path with the movies
        target.getPath(); // returns an arraylist with the actors and there connecting movies

    }


    private ArrayList<Node> connectedActorsQuery(Node startActor){
        String start = "";
        String connectedActors = "";
        String connectionMovie = "";

        String query = "select min(id) as actor_id, ARRAY_AGG(connected_actors_id) as conected_actor_id, ARRAY_AGG(connecting_movie) as connecting_movie from( select a1.nconst as id, a2.nconst as connected_actors_id,array_to_string(array(select unnest(a1.knownfortitles_arr) INTERSECT select unnest(a2.knownfortitles_arr)),',') as connecting_movie from actors_list as a1 join actors_list as a2 on a1.knownfortitles_arr && a2.knownfortitles_arr and a1.nconst != a2.nconst where a1.nconst = '" + startActor.getActor() + "' limit 50) as temp group by id ;";
        ResultSet result = conn.getResults(query);
        try{
            result.next();
            start = result.getString("actor_id");
            connectedActors = result.getString("conected_actor_id");
            connectionMovie = result.getString("connecting_movie");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        // remove brackets
        connectedActors = connectedActors.substring(1, connectedActors.length()-1);
        connectionMovie = connectionMovie.substring(1, connectionMovie.length()-1);

        ArrayList<String> connectedActorsList = new ArrayList<String>(Arrays.asList(connectedActors.split(",")));
        ArrayList<String> connectedMoviesList = new ArrayList<String>(Arrays.asList(connectionMovie.split(",")));

        // create nodes
        ArrayList<Node> nodes = new ArrayList<Node>(connectedActorsList.size());
        for(int i = 0; i < connectedActorsList.size(); i++){
            nodes.add(new Node(startActor, connectedActorsList.get(i), connectedMoviesList.get(i)));
        }

        return nodes;
      }

      


}