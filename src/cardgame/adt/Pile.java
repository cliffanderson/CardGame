package cardgame.adt;

/**
 * Created by lawrencew on 3/2/2016.
 */
public class Pile<T> implements ListInterface<T> {

    private Node<T> head;
    private Node<T> tail;
    private int numberOfEntries;

    public Pile() {
        head = null;
        tail = new Node<>(null);
        numberOfEntries = 0;
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

    /***/
    /*
    public boolean add(int newPosition, T newEntry) {
        boolean headCreated = false;

        if(head == null)
        {
            head = new Node(null);
            numberOfEntries++;
            headCreated = true;
        }

        Node<T> currentNode = head;
        Node<T> newNode = new Node<T>(newEntry);

        if(newPosition == 0)
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

        if(newPosition >= numberOfEntries)
        {
            int spot = newPosition - numberOfEntries;

            for(int x = 0; x <= spot; x++)
            {
                if(x == spot)
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

        for(int x = 0; x <= newPosition; x++)
        {
            if(x == newPosition)
            {
                newNode.setNext(currentNode);
                newNode.setLast(currentNode.getLast()); //Problem here.
                currentNode.getLast().setNext(newNode);
                currentNode.setLast(newNode); //Bug was here, but I fixed it.
                numberOfEntries++;
                return true;
            }
            currentNode=currentNode.getNext();
        }
        return false;
    } */


    public boolean add(int newPosition, T newEntry) {

        boolean isSuccessful = true;

        if (newPosition >= 0 && newPosition <= numberOfEntries) {

            Node<T> currentNode = head;
            Node<T> newNode = new Node<>(newEntry);

            if (head == null) {
                head = new Node<>(newEntry);
                head.setNext(tail);
                tail.setLast(head);

                numberOfEntries++;
                return isSuccessful;
            }
            else if (newPosition == 0) {
                newNode.setNext(head);
                head.setLast(newNode);
                head = newNode;

                numberOfEntries++;
                return isSuccessful;
            } else if (newPosition == numberOfEntries) {
                tail.getLast().setNext(newNode);
                tail = newNode;

                numberOfEntries++;
                return isSuccessful;
            }
            //Bug reminder, you can't use methods of a null instance.

            for (int i = 0; i <= newPosition; i++) {

                if (i == newPosition) {

                }
                currentNode = currentNode.getNext();
            }
        }

        return  isSuccessful;
    }

    /** Removes the entry at the given position.
     * @param givenPosition the position of the entry to be removed
     * @return the object of type T that was removed.
     * */
    public T remove(int givenPosition) {
        if(givenPosition >= numberOfEntries)
        {
            return null;
        }

        if(givenPosition==0)
        {
            head = head.getNext();
            head.setLast(null);
            numberOfEntries--;
            return head.getData();
        }
        else if(givenPosition == numberOfEntries - 1)
        {
            T data = tail.getData();
            tail = tail.getLast();
            tail.setNext(null);
            return data;
        }

        Node<T> currentNode = head;

        for(int x = 0; x <= givenPosition; x++)
        {
            if(x == givenPosition)
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

    /** Clears the list of all entries. */
    public void clear() {
        if(head!=null)
        {
            head=null;
        }
    }

    /** Replaces the entry at the given position with the new entry.
     * @param givenPosition the position of the entry that will be replaced.
     * @param newEntry the new entry to replace the existing entry at the specified position.
     * @return true if the entry at the given position is replaced successfully.
     * */
    public boolean replace(int givenPosition, T newEntry) throws NullPointerException {

        if(givenPosition > numberOfEntries)
        {
            throw new NullPointerException("That location doesn't exist");
        }

        Node<T> currentNode = head;
        Node<T> newNode = new Node<T>(newEntry);

        if(givenPosition == 1)
        {
            if(head.getNext()!=null)
            {
                newNode.setNext(head.getNext());
            }
            head = newNode;
            return true;
        }

        if(givenPosition == numberOfEntries)
        {
            newNode.setLast(tail.getLast());
            tail.getLast().setNext(newNode);
            tail = newNode;
            return true;
        }

        for(int x = 1; x <= givenPosition; x++)
        {
            if(x == givenPosition)
            {
                newNode.setNext(currentNode.getNext());
                newNode.setLast(currentNode.getLast());
                currentNode.getLast().setNext(newNode);
                currentNode.getNext().setLast(newNode);
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    /** Gets the entry at the specified position.
     * @param givenPosition the position to get the entry at.
     * @return the entry of type T.
     * */
    public T getEntry(int givenPosition) {
        if(givenPosition > numberOfEntries)
        {
            return null;
        }

        Node<T> currentNode = head;

        for(int x=0; x <= givenPosition; x++)
        {
            if(x==givenPosition)
            {
                return currentNode.getData();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    /** Checks if the list contains the entry.
     * @param anEntry the entry to be checked.
     * @return true if the object is contained in the list.
     * */
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

    /** Gets the length of the list.
     * @return the length.
     * */
    public int getLength() {
        return numberOfEntries;
    }

    /** Checks if the list is empty.
     * @return true if it is empty.
     * */
    public boolean isEmpty() {
        return numberOfEntries==0;
    }

    /** Returns the list as an array of type T.
     * @return the array of type T.
     * */
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
            newNode = null;
            lastNode = null;
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
            this.lastNode = lastNode;
        }
        public Node<T> getNext()
        {
            return newNode;
        }

        public void setNext(Node<T> NextNode)
        {
            this.newNode = NextNode;
        }

        private Node<T> newNode,lastNode;
        private T data;
    }
}