public interface StackInterface{
	public boolean isEmpty();
	public int size();
	public void push(Object newItem)
		throws StackFullException;
	public Object pop()
		throws StackEmptyException;
	public void popAll();
	public Object peek()
		throws StackEmptyException;
}