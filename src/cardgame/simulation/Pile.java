package cardgame.simulation;

/**
 * Created by lawrencew on 3/2/2016.
 */
public class Pile<T> implements ListInterface<T>{
    
    private T[] list;
    private int numberOfEntries;

    public void add(T newEntry) {

    }

    public boolean add(int newPosition, T newEntry) {
        return false;
    }

    @Override
    public T remove(int givenPosition) {
        return null;
    }

    @Override
    public void clear() {

    }

    public boolean replace(int givenPosition, T newEntry) {
        return false;
    }

    @Override
    public T getEntry(int givenPosition) {
        return null;
    }

    public boolean contains(T anEntry) {
        return false;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public T[] toArray() {
        Object[] obj = new Object[10];
        return (T[])obj;
    }


}
