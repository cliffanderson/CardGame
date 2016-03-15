package cardgame.adt;

/**
 * Created by lawrencew on 3/2/2016.
 */
public class Pile<T> implements ListInterface<T> {

    private Node<T> firstNode;
    private Node<T> lastNode;
    private int numberOfEntries;

    /** Sets up the pile.
     * */
    public Pile() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }

    /** Adds a new entry to the end of the list.
     * @param newEntry the entry to be added.
     * */
    public void add(T newEntry) {

        Node<T> newNode = new Node<>(newEntry);

        if (firstNode == null)
        {
            firstNode = newNode;

            numberOfEntries++;
            return;
        }

        if (lastNode == null)
        {
            firstNode.setNext(newNode);
            newNode.setPrevious(firstNode);
            lastNode = newNode;

            numberOfEntries++;
            return;
        }

        newNode.setPrevious(lastNode);
        lastNode.setNext(newNode);
        lastNode = newNode;
        numberOfEntries++;
    }

    /** Adds new entry at the specified position.
     * @param newEntry the entry to be added.
     * @param newPosition the position of the new entry.
     * */
    public boolean add(int newPosition, T newEntry) {

        boolean isSuccessful = true;

        if (newPosition >= 0 && newPosition <= numberOfEntries) {

            Node<T> currentNode = firstNode;
            Node<T> newNode = new Node<>(newEntry);

            if (isEmpty()) {
                firstNode = newNode;
            }
            if (newPosition == 0)
            {
                newNode.setNext(firstNode);
                firstNode.setPrevious(newNode);
                firstNode = newNode;

                numberOfEntries++;
                return isSuccessful;
            }
            if (newPosition > 0 &&  newPosition < (numberOfEntries - 1)) {
                currentNode = firstNode.getNodeAt(newPosition);

                Node previousNode = currentNode.getPrevious();
                Node nextNode = currentNode.getNext();

                previousNode.setNext(newNode);
                newNode.setPrevious(previousNode);
                newNode.setNext(currentNode);
                currentNode.setPrevious(newNode);

                numberOfEntries++;
                return isSuccessful;
            }
            if (newPosition == numberOfEntries) {

                Node previousNode = firstNode.getNodeAt(newPosition - 1);
                previousNode.setNext(newNode);
                lastNode = newNode;

                numberOfEntries++;
                return isSuccessful;
            }
        }

        return  !isSuccessful;
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
            firstNode = firstNode.getNext();
            firstNode.setPrevious(null);
            numberOfEntries--;
            return firstNode.getData();
        }
        else if(givenPosition == numberOfEntries - 1)
        {
            T data = lastNode.getData();
            lastNode = lastNode.getPrevious();
            lastNode.setNext(null);
            return data;
        }

        Node<T> currentNode = firstNode;

        for(int x = 0; x <= givenPosition; x++)
        {
            if(x == givenPosition)
            {
                currentNode.getPrevious().setNext(currentNode.getNext());
                currentNode.getNext().setPrevious(currentNode.getPrevious());
                numberOfEntries--;
                return currentNode.getData();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    /** Clears the list of all entries. */
    public void clear() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
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

        Node<T> currentNode = firstNode;
        Node<T> newNode = new Node<T>(newEntry);

        if(givenPosition == 1)
        {
            if(firstNode.getNext()!=null)
            {
                newNode.setNext(firstNode.getNext());
            }
            firstNode = newNode;
            return true;
        }

        if(givenPosition == numberOfEntries)
        {
            newNode.setPrevious(lastNode.getPrevious());
            lastNode.getPrevious().setNext(newNode);
            lastNode = newNode;
            return true;
        }

        for(int x = 1; x <= givenPosition; x++)
        {
            if(x == givenPosition)
            {
                newNode.setNext(currentNode.getNext());
                newNode.setPrevious(currentNode.getPrevious());
                currentNode.getPrevious().setNext(newNode);
                currentNode.getNext().setPrevious(newNode);
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

        Node<T> theNode = firstNode.getNodeAt(givenPosition);

        if (theNode == null) {
            return null;
        } else {
            return theNode.getData();
        }
    }

    /** Checks if the list contains the entry.
     * @param anEntry the entry to be checked.
     * @return true if the object is contained in the list.
     * */
    public boolean contains(T anEntry) {
        Node<T> nextNode= firstNode;
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
        return numberOfEntries == 0;
    }

    /** Returns the list as an array of type T.
     * @return the array of type T.
     * */
    public T[] toArray() {
        Node<T> nextNode= firstNode;
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

        private Node<T> nextNode, previousNode;
        private T data;

        private Node(T data) {
            this.data = data;
            nextNode = null;
            previousNode = null;
        }

        private T getData() {
            return data;
        }

        private Node<T> getPrevious() {
            return previousNode;
        }

        private void setPrevious(Node<T> lastNode) {
            this.previousNode = lastNode;
        }

        private Node<T> getNext() {
            return nextNode;
        }

        private void setNext(Node<T> NextNode) {
            this.nextNode = NextNode;
        }

        private Node getNodeAt(int givenPosition) {
            Node currentNode = firstNode;

            for (int i = 0; i < givenPosition; i++) {
                currentNode = currentNode.getNext();
            }

            return currentNode;
        }
    }
}