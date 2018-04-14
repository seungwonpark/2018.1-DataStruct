public class Node<T>{
	private T item;
	private Node<T> next;

	// constructor
	public Node(T item){
		this.item = item;
		this.next = null;
	}
	public Node(T item, Node<T> next){
		this.item = item;
		this.next = next;
	}

	// methods to access instance variables
	public T getItem(){
		return item;
	}
	public Node<T> getNext(){
		return next;
	}
	public boolean hasNext(){
		return next != null;
	}

	// methods to modify instance varaibles
	public void setItem(T item){
		this.item = item;
	}
	public void setNext(Node<T> next){
		this.next = next;
	}

	public void insertNext(T item){
		Node<T> newNode = new Node<T>(item);
		newNode.next = this.next;
		this.next = newNode;
	}

	public void removeNext(){
		this.next = this.next.next;
	}
}