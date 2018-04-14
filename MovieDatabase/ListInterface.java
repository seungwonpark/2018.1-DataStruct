public interface ListInterface<T> extends Iterable<T>{
	public boolean isEmpty();
	public int size();
	public void append(T item);
	public T first();
	public void removeAll();
	public void insert(T item); // maintain being sorted.
	public void delete(T item);
}