import java.util.Comparator;

public class SortBySize implements Comparator<Director> {
    public int compare(Director a, Director b) {
        return a.getSize() - b.getSize();
    }
}