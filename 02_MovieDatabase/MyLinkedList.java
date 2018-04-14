import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T extends Comparable<T>> implements ListInterface<T> {
	// dummy head
	Node<T> head;
	int numItems;

	public MyLinkedList() {
		head = new Node<T>(null);
	}
    public final Iterator<T> iterator() {
    	return new MyLinkedListIterator<T>(this);
    }

	@Override
	public boolean isEmpty() {
		return head.getNext() == null;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public T first() {
		return head.getNext().getItem();
	}

	@Override
	public void add(T item) {
		Node<T> last = head;
		while (last.getNext() != null) {
			last = last.getNext();
		}
		last.insertNext(item);
		numItems += 1;
	}

	@Override
	public void removeAll() {
		head.setNext(null);
	}

	@Override
	public void insert(T item){
		Node<T> now = head;
		while(now.hasNext()){
			T nextItem = now.getNext().getItem();

			// proceed while nextItem < item
			if(nextItem.compareTo(item) > 0){
				break;
			}
			now = now.getNext();
		}
		
		// only if item doesn't exist in DB
		boolean alreadyExists = false;
		if(!now.equals(head)){
			if(now.getItem().equals(item)){
				alreadyExists = true;
			}
		}
		if(!alreadyExists){
			now.insertNext(item);
			numItems += 1;
		}
	}

	@Override
	public void delete(T item){
		Node<T> now = head;
		boolean foundItem = false;
		while(now.hasNext()){
			T nextItem = now.getNext().getItem();
			if(nextItem.equals(item)){
				foundItem = true;
				now.removeNext();
				numItems -= 1;
				break;
			}
			now = now.getNext();
		}
		// surpress NoSuchElementException
		// if(!foundItem){
		// 	throw new NoSuchElementException(); 
		// }
	}
}

class MyLinkedListIterator<T extends Comparable<T>> implements Iterator<T> {
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