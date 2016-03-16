package cardgame.adt;

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
        else if (lastNode == null)
        {
            firstNode.setNext(newNode);
            newNode.setPrevious(firstNode);
            lastNode = newNode;

            numberOfEntries++;
            return;
        }
        else {
            newNode.setPrevious(lastNode);
            lastNode.setNext(newNode);
            lastNode = newNode;
            numberOfEntries++;
        }
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

            //Special case when empty.
            if (isEmpty()) {
                firstNode = newNode;
                return isSuccessful;
            }

            //Special case when adding to the front.
            else if (newPosition == 0)
            {
                newNode.setNext(firstNode);
                firstNode.setPrevious(newNode);
                firstNode = newNode;

                numberOfEntries++;
                return isSuccessful;
            }

            //Case when adding in between entries in the list.
            else if (newPosition > 0 &&  newPosition < (numberOfEntries - 1)) {
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
            //Case when adding entries at the end of the list.
            else if (newPosition == numberOfEntries) {

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

        //Checks if the given position goes out of the boundaries.
        if(givenPosition >= numberOfEntries || givenPosition < 0)
        {
            return null;
        }

        //Case for 0 as the given position.
        else if(givenPosition == 0)
        {
            T data = firstNode.getData();
            firstNode = firstNode.getNext();
            firstNode.setPrevious(null);

            numberOfEntries--;
            return data;
        }

        //Case for the last position if that was given.
        else if(givenPosition == numberOfEntries - 1)
        {
            T data = lastNode.getData();

            lastNode = lastNode.getPrevious();
            lastNode.setNext(null);

            numberOfEntries--;
            return data;
        }

        //General in between case.
        else {
            Node<T> currentNode = firstNode.getNodeAt(givenPosition);
            T data = currentNode.getData();

            Node previousNode = currentNode.getPrevious();
            Node nextNode = currentNode.getNext();

            previousNode.setNext(nextNode);
            nextNode.setPrevious(previousNode);

            numberOfEntries--;
            return data;
        }
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

        Node<T> currentNode = firstNode;
        Node<T> newNode = new Node<>(newEntry);

        //Checks for bogus given position.
        if(givenPosition >= numberOfEntries || givenPosition < 0)
        {
            throw new NullPointerException("That location doesn't exist");
        }

        //Special case for replacing at 0.
        else if(givenPosition == 0)
        {
            if(firstNode.getNext() != null)
            {
                newNode.setNext(firstNode.getNext());
            }

            firstNode = newNode;
            return true;
        }

        //Special case for replacing at end.
        else if(givenPosition == numberOfEntries - 1)
        {
            Node previousNode = lastNode.getPrevious();
            newNode.setPrevious(previousNode);
            previousNode.setNext(newNode);

            lastNode = newNode;
            return true;
        }

        //Case for in between the entries in the list.
        else {
            currentNode = firstNode.getNodeAt(givenPosition);

            Node previousNode = currentNode.getPrevious();
            Node nextNode = currentNode.getNext();

            newNode.setNext(nextNode);
            newNode.setPrevious(previousNode);

            previousNode.setNext(newNode);
            nextNode.setPrevious(newNode);

            return true;
        }
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