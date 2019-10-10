import javax.swing;
public abstract class Results extends JPanel{
    private DatabaseConnection conn;

    public Results(DatabaseConnection conn){
        this.conn = conn;
    }

    public void showResults(){
        
    }

    
}