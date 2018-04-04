public class Node{
	private Object item;
	private Node next;
	public Node(Object newItem){
		item = newItem;
		next = null;
	}
	public Node(Object newItem, Node nextNode){
		item = newItem;
		next = nextNode;
	}
	public Object getItem(){
		return item;
	}
	// setItem, setNext;
	public Node getNext(){
		return next;
	}
	public void setNext(Node next){
		this.next = next;
	}
}