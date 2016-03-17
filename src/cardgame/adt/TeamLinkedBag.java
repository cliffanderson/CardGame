package cardgame.adt;

/**
 * 
 * @author Tom Plano, Will Lawrence, Cliff Anderson, Artur Janowiec
 *
 * @param <T>
 */
public class TeamLinkedBag<T>{
	
	private Node<T> head;
	
	private int size;

	public TeamLinkedBag(int initial_size){
		this.size = initial_size;
	}

	public TeamLinkedBag(){
		this.size = 0;
	}
	/**
	 * @param newEntry Object of Type T to be added to the bag
	 * @return True
	 */
	public boolean add(T newEntry){
		Node<T> newNode = new Node(newEntry);
		newNode.setNext(head);
		head=newNode;
		this.size++;
		return true;
	}
	/**
	 * Remove all objects from the bag
	 */
	public void clear(){
		if(this.head != null)
		{
			this.head.setNext(null);
		}
	}
	/**
	 * @param entry Object of type T thats is the desired search result
	 * @return True if bag contains at lease ONE instance of entry is present: False otherwise
	 */
	public boolean contains(T entry){
		Node<T> nextNode=this.head;
		while(nextNode!=null){
			if(nextNode.getData()==entry){
				return true;
			}
			nextNode=nextNode.getNext();
		}
		return false;
	}
	/**
	 * @return Current size of bag
	 */
	public int getCurrentSize(){
		return this.size;
	}
	/**
	 * @param entry Object of type T thats is the desired search result
	 * @return Number of copies of entry currently in the bag
	 */
	public int getFrequency(T entry){
		Node<T> nextNode=this.head;
		int entryCount=0;
		while(nextNode!=null){
			if(nextNode.getData()==entry){
				entryCount++;
			}
			nextNode=nextNode.getNext();
		}
		return entryCount;
	}
	/**
	 * @return True if bag is empty: False otherwise
	 */
	public boolean isEmpty(){
		return this.getCurrentSize() == 0;
	}
	
	public T remove(){
		if(head == null){
			return null;
		}
		
		if(head.getNext() == null){
			T result = this.head.getData();
			this.head = null;
			this.size--;
			return result;
		}
		
		//regular behavior
		T result = this.head.getData();
		this.head = this.head.getNext();
		this.size--;
		return result;		
	}
	/**
	 * @param entry Object of type T thats is to be removed
	 * @return True if there was a successful removal for entry; False otherwise
	 */
	public boolean remove(T entry){
		Node<T> nextNode=this.head;
		Node<T> priorNode=null;
	//first node behavior
		if (nextNode.getData()==entry){
			head=nextNode.getNext();
			this.size--;
			return true;
		}
		priorNode=nextNode;
		nextNode=nextNode.getNext();
		
	//2ndi nth node behavior
		while(nextNode!=null){
			if(nextNode.getData()==entry){
				priorNode.setNext(nextNode.getNext());
				this.size--;
				return true;
			}
			priorNode=nextNode;
			nextNode=nextNode.getNext();
		}
		return false;
	}

	public T peak(int index) {
		Node currentNode = this.head;
		for(int i = 0; i < index - 1; i++)
		{
			currentNode = currentNode.getNext();
		}

		return (T)currentNode.getData();
	}
	/**
	 * @return array object containing all objects from the bag. Bag is emptied 
	 */
	public Object[] toArray(){
		Node<T> nextNode=head;
		Object[] outputArray = new Object[this.size];
		int index=0;
		while(nextNode!=null){
			outputArray[index]=nextNode.getData();
			nextNode=nextNode.getNext();
			index++;
		}
		return outputArray;
	}

}

class Node<T>{
	
	//build new node with specified data with a "next" pointer pointing to null
	public Node(T data){
		this.data = data;
		NextNode=null;
	}
	
	public T getData(){
		return data;
	}
	
	public Node<T> getNext(){
		return NextNode;
	}
	
	public void setNext(Node<T> NextNode){
		this.NextNode=NextNode;
	}
	
private Node<T> NextNode;
private T data;
}