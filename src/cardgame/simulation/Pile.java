package cardgame.simulation;

import cardgame.adt.ListInterface;

/**
 * Created by lawrencew on 3/2/2016.
 */
public class Pile<T> implements ListInterface<T> {

    private Node head;
    private Node tail;
    private int numberOfEntries;

    public Pile() {
        //tail = new Node();
    }

    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        tail.setNext(newNode);
        tail = newNode;
        numberOfEntries++;
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

    private class Node<T> {

        public Node(T data) {
            this.data = data;
            NextNode = null;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return NextNode;
        }

        public void setNext(Node<T> NextNode) {
            this.NextNode = NextNode;
        }

        private Node<T> NextNode;
        private T data;
    }
}
