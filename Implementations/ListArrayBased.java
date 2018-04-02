// implement class
class ListArrayBased implements ListInterface{
	private final int MAX_LIST = 50;
	private Object items[];
	private int numItems;
	public ListArrayBased(){
		items = new Object[MAX_LIST+1];
		numItems = 0;
	}
	public boolean isEmpty(){
		return (numItems == 0);
	}
	public int size(){
		return numItems;
	}
	public void removeAll(){
		items = new Object[MAX_LIST+1];
		numItems = 0;
	}
	public void add(int index, Object item)
		throws ListIndexOutOfBoundsException{
		if(numItems > MAX_LIST){
			throw new ListIndexOutOfBoundsException("addition error");
		}
		if(index >= 1 && index <= numItems+1){
			for(int pos = numItems; pos >= index; pos--){
				items[pos+1] = items[pos];
			}
			items[index] = item;
			numItems++;
		} else{
			throw new ListIndexOutOfBoundsException("addition error");
		}
	}
	public Object get(int index)
		throws ListIndexOutOfBoundsException{
		if(index >= 1 && index <= numItems){
			return items[index];
		} else{
			throw new ListIndexOutOfBoundsException("get error");
		}
	}
	public void remove(int index)
		throws ListIndexOutOfBoundsException{
		if(index >= 1 && index <= numItems){
			for(int pos = index+1; pos <= size(); pos++){
				items[pos+1] = items[pos];
			}
			numItems--;
		}
		else{
			throw new ListIndexOutOfBoundsException("remove error");
		}
	}
}