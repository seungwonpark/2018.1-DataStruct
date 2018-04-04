	// public boolean isEmpty();
	// public void push(Object newItem);
	// public Object pop();
	// public void popAll();
	// public Object peek();
public class StackArrayBased implements StackInterface{
	final int MAX_STACK = 50;
	private Object items[];
	private int top; // index of top element
	public StackArrayBased(){
		items = new Object[MAX_STACK];
		top = -1;
	}
	public boolean isEmpty(){
		return top < 0;
	}
	public int size(){
		return (top + 1);
	}
	private boolean isFull(){
		return top == MAX_STACK - 1;
	}
	public void push(Object newItem)
		throws StackFullException{
		if(!isFull()){
			items[++top] = newItem;
		}
		else{
			throw new StackFullException("stack is full");
		}
	}
	public Object pop()
		throws StackEmptyException{
		if(!isEmpty()){
			return items[top--];
		}
		else{
			throw new StackEmptyException("stack is empty - nothing to pop");
		}
	}
	public void popAll(){
		items = new Object[MAX_STACK];
		top = -1;
	}
	public Object peek()
		throws StackEmptyException{
		if(top >= 0){
			return items[top];
		}
		else{
			throw new StackEmptyException("stack is empty - nothing to peek");
		}
	}
}