package cardgame.adt;

import cardgame.adt.ListInterface;

/**
 * Created by lawrencew on 3/2/2016.
 */
public class Pile<T> implements ListInterface<T> {

    private Node<T> head=null;
    private Node<T> tail=null;
    private int numberOfEntries=0;

    public Pile() {

    }

    public void add(T newEntry) {
        Node<T> newNode = new Node<T>(newEntry);
        if(head==null)
        {
            head=newNode;
            numberOfEntries++;
            return;
        }
        if(tail==null)
        {
            head.setNext(newNode);
            newNode.setLast(head);
            tail=newNode;
            numberOfEntries++;
            return;
        }

        newNode.setLast(tail);
        tail.setNext(newNode);
        tail = newNode;
        numberOfEntries++;
    }

    public boolean add(int newPosition, T newEntry) {
        boolean headCreated=false;
        if(head==null)
        {
            head = new Node(null);
            numberOfEntries++;
            headCreated=true;
        }
        Node<T> currentNode=head;
        Node<T> newNode = new Node<T>(newEntry);
        if(newPosition==0)
        {
            newNode.setNext(head);
            head.setLast(newNode);
            head = newNode;
            numberOfEntries++;
            if (headCreated)
            {
                head.setNext(null);
            }
            return true;
        }
        if(newPosition>=numberOfEntries)
        {
            int spot = newPosition-numberOfEntries;
            for(int x=0;x<=spot;x++)
            {
                if(x==spot)
                {

                    tail.getLast().setNext(newNode);
                    newNode.setLast(tail);
                    tail=newNode;
                    return true;
                }
                else
                {
                    Node<T> fillerNode = new Node<T>(null);
                    fillerNode.setLast(tail);
                    tail.setNext(fillerNode);
                    tail=fillerNode;
                    numberOfEntries++;
                }
            }
        }
        for(int x=0;x<=newPosition;x++)
        {
            if(x==newPosition)
            {
                newNode.setNext(currentNode);
                newNode.setLast(currentNode.getLast());
                currentNode.getLast().setNext(newNode);
                numberOfEntries++;
                return true;
            }
            currentNode=currentNode.getNext();
        }
        return false;
    }

    @Override
    public T remove(int givenPosition) {
        if(givenPosition>numberOfEntries)
        {
            return null;
        }
        if(givenPosition==0)
        {
            Node<T> temp= head;
            head=head.getNext();
            head.setLast(null);
            numberOfEntries--;
            return temp.getData();
        }

        Node<T> currentNode = head;

        for(int x=0;x<=givenPosition;x++)
        {
            if(x==givenPosition)
            {
                currentNode.getLast().setNext(currentNode.getNext());
                currentNode.getNext().setLast(currentNode.getLast());
                numberOfEntries--;
                return currentNode.getData();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    @Override
    public void clear() {
        if(head!=null)
        {
            head=null;
        }
    }

    public boolean replace(int givenPosition, T newEntry) throws
            NullPointerException{
        if(givenPosition>numberOfEntries)
        {
            throw new NullPointerException("That location doesn't exist");
        }
        Node<T> currentNode=head;
        Node<T> newNode = new Node<T>(newEntry);
        if(givenPosition==1)
        {
            if(head.getNext()!=null)
            {
                newNode.setNext(head.getNext());
            }
            head = newNode;
            return true;
        }
        if(givenPosition==numberOfEntries)
        {
            newNode.setLast(tail.getLast());
            tail.getLast().setNext(newNode);
            tail=newNode;
            return true;
        }

        for(int x=1;x<=givenPosition;x++)
        {
            if(x==givenPosition)
            {
                newNode.setNext(currentNode.getNext());
                newNode.setLast(currentNode.getLast());
                currentNode.getLast().setNext(newNode);
                currentNode.getNext().setLast(newNode);
                return true;
            }
            currentNode=currentNode.getNext();
        }
        return false;

    }

    @Override
    public T getEntry(int givenPosition) {
        if(givenPosition>numberOfEntries)
        {
            return null;
        }

        Node<T> currentNode = head;

        for(int x=0;x<=givenPosition;x++)
        {
            if(x==givenPosition)
            {
                return currentNode.getData();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public boolean contains(T anEntry) {

        Node<T> nextNode=head;
        while(nextNode!=null){
            if(nextNode.getData()==anEntry){
                return true;
            }
            nextNode=nextNode.getNext();
        }
        return false;

    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries==0;
    }

    @Override
    public T[] toArray() {
        Node<T> nextNode=head;
        Object[] outputArray = new Object[numberOfEntries];
        int index=0;
        while(nextNode!=null&&index<numberOfEntries){
            outputArray[index]=nextNode.getData();
            nextNode=nextNode.getNext();
            index++;
        }

        return (T[])outputArray;
    }

    private class Node<T> {

        public Node(T data) {
            this.data = data;
            NextNode = null;
        }

        public T getData() {
            return data;
        }

        public Node<T> getLast()
        {
            return lastNode;
        }
        public void setLast(Node<T> lastNode)
        {
            this.lastNode=lastNode;
        }
        public Node<T> getNext() {
            return NextNode;
        }

        public void setNext(Node<T> NextNode) {
            this.NextNode = NextNode;
        }

        private Node<T> NextNode,lastNode;
        private T data;
    }
}