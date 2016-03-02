package cardgame.simulation;

/**
 * Created by lawrencew on 3/2/2016.
 */
public interface ListInterface<T> {
    void add(T newEntry);

    boolean add(int newPosition, T newEntry);

    T remove(int givenPosition);

    void clear();

    boolean replace(int givenPosition, T newEntry);

    T getEntry(int givenPosition);

    boolean contains(T anEntry);

    int getLength();

    boolean isEmpty();

    T[] toArray();


}
