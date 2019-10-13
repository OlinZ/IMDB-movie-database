import java.util.*;

public class Node implements Comparable<Node>{

    private Node prev;
    private String actor;
    private String movie;

    public Node(){
        prev = null;
        actor = "";
        movie = "";
    }

    public Node(Node prev, String actor, String movie){
        this.prev = prev;
        this.actor = actor;
        this.movie = movie;
    }

    public ArrayList<Node> createList(Node parent, ArrayList<String> actors, ArrayList<String> movies){
        ArrayList<Node> nodes = new ArrayList<Node>(actors.size());
        for(int i = 0; i < actors.size(); i++){
            Node temp = new Node(parent, actors.get(i), movies.get(i));
            nodes.add(temp);
        }
        return nodes;
    }

    public void displayPath(){
        Node iter = this;

        while(iter.prev != null){
            System.out.println(iter.getActor());
            System.out.println(iter.getMovie());
            iter = iter.prev;
        }
        System.out.println(iter.actor);
        iter = iter.prev;

    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Node{" +
                "actor='" + actor + '\'' +
                '}';
    }

    public boolean equals(Node node) {
        return actor.equals(node.actor) && movie.equals(node.movie);
    }

    public int compareTo(Node node){
        return actor.compareTo(node.getActor());
    }


    public String getMovie() {
        return movie;
    }

    public Node getPrev() {
        return prev;
    }

    public String getActor() {
        return actor;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setactor(String actor) {
        this.actor = actor;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }


}