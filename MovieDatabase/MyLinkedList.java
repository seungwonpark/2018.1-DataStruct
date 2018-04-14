import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T extends Comparable<T>> implements ListInterface<T>{
	Node<T> head; // dummy head
	int numItems;

	// constructor
	public MyLinkedList(){
		head = new Node<T>(null);
		numItems = 0;
	}

	public Iterator<T> iterator(){
		return new MyLinkedListIterator<T>(this);
	}
	// methods to access instance varaibles
	public boolean isEmpty(){
		return numItems == 0;
	}
	public int size(){
		return numItems;
	}

	// methods to modify instance varaibles
	public void append(T item){
		Node<T> now = head;
		while(now.getNext() != null){
			now = now.getNext();
		}
		now.insertNext(item);
		numItems += 1;
	}

	public T first(){
		return head.getNext().getItem();
	}

	public void removeAll(){
		head.setNext(null);
		// this leads to garbage collection
	}

	public void insert(T item){
		// maintain being sorted.
		// insert only if such item does not exist.
		Node<T> now = head;
		while(now.hasNext()){
			T nextItem = now.getNext().getItem();
			if(nextItem.compareTo(item) > 0){
				break;
			}
			now = now.getNext();
		}
		boolean isAlreadyExists = false;
		if(now.equals(head) == false){ // if now is head, there's nothing to compare
			if(now.getItem().equals(item)){ // when duplication is confirmed
				isAlreadyExists = true;
			}
		}
		if(isAlreadyExists == false){
			now.insertNext(item);
			numItems += 1;
		}
	}
	public void delete(T item){
		Node<T> now = head;
		boolean isFound = false;
		while(now.hasNext()){
			if(now.getNext().getItem().equals(item)){
				isFound = true;
				now.removeNext();
				numItems -= 1;
				break;
			}
			now = now.getNext();
		}
	}
}
class MyLinkedListIterator<T extends Comparable<T>> implements Iterator<T>{
	private MyLinkedList<T> list;
	private Node<T> curr;
	private Node<T> prev;

	public MyLinkedListIterator(MyLinkedList<T> list) {
		this.list = list;
		this.curr = list.head;
		this.prev = null;
	}

	@Override
	public boolean hasNext() {
		return curr.getNext() != null;
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();

		prev = curr;
		curr = curr.getNext();

		return curr.getItem();
	}

	@Override
	public void remove() {
		if (prev == null)
			throw new IllegalStateException("next() should be called first");
		if (curr == null)
			throw new NoSuchElementException();
		prev.removeNext();
		list.numItems -= 1;
		curr = prev;
		prev = null;
	}
}