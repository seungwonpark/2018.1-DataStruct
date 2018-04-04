public class ListReferenceBased implements ListInterface{
	private Node head;
	private int numItems;
	public ListReferenceBased(){
		numItems = 0;
		head = null;
	}
	public boolean isEmpty(){
		return numItems == 0;
	}
	public int size(){
		return numItems;
	}
	private Node find(int index){
		Node curr = head; // 1st element
		for(int i=1; i<index; i++){
			curr = curr.getNext();
		}
		return curr;
	}
	public Object get(int index)
		throws ListIndexOutOfBoundsException{
		if(index >= 1 && index <= numItems){
			Node curr = find(index);
			return curr.getItem();
		}
		else{
			throw new ListIndexOutOfBoundsException("index out of range");
		}
	}
	public void remove(int index)
		throws ListIndexOutOfBoundsException{
		if(index >= 1 && index <= numItems){
			if(index == 1) head = head.getNext();
			else{
				Node prev = find(index - 1);
				Node curr = prev.getNext();
				prev.setNext(curr.getNext());
			}
			numItems -= 1;
		}
		else{
			throw new ListIndexOutOfBoundsException("index out of range");
		}
	}
	public void add(int index, Object item)
		throws ListIndexOutOfBoundsException{
		if(index >= 1 && index <= numItems + 1){
			if(index == 1){
				Node newNode = new Node(item, head);
				head = newNode;
			}
			else{
				Node prev = find(index - 1);
				Node newNode = new Node(item, prev.getNext());
				prev.setNext(newNode);
			}
			numItems += 1;
		}
		else{
			throw new ListIndexOutOfBoundsException("index out of range");
		}
	}
	public void removeAll(){
		numItems = 0;
		head = null;
	}
}