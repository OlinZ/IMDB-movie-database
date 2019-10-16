import java.util.*;
public class NormalQueue<T>{
    LinkedList<T> queue;

    public NormalQueue(){
        queue = new LinkedList<T>();
    }

    public void add(T node){
        queue.addLast(node);
    }

    public T poll(){
        return queue.pollFirst();
    }

    public T peek(){
        return queue.peekFirst();
    }

    public void addAll(List<T> list){
        queue.addAll(list);
    }

    public int size(){
        return queue.size();
    }
}