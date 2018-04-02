import java.util.*;
class List{
	public static void main(String[] args){
		ListArrayBased test = new ListArrayBased();
		try{
			test.add(1, 3);
			test.add(2, 5);
			System.out.println(test.get(2));
			test.remove(1);
			System.out.println(test.get(1));
			System.out.println(test.isEmpty());
			System.out.println(test.size());
			test.removeAll();
			System.out.println(test.isEmpty());
			System.out.println(test.size());
		}
		catch (ListIndexOutOfBoundsException e){
			System.out.println("eh");
			throw new IllegalArgumentException();
		}
	}


	// in order to write other class here, it needs to be 'public static'.
	// however, writing class at other file is recommended.
	/*****************************************/
	// public static class ListIndexOutOfBoundsException extends Exception{
	// 	private String command;
	// 	public ListIndexOutOfBoundsException(String command){
	// 		super(String.format("input command: %s", command));
	// 		this.command = command;
	// 	}
	// 	public String getCommand(){
	// 		return command;
	// 	}
	// }
	// public static class ListArrayBased implements ListInterface{
	// 	private final int MAX_LIST = 50;
	// 	private Object items[];
	// 	private int numItems;
	// 	public ListArrayBased(){
	// 		items = new Object[MAX_LIST+1];
	// 		numItems = 0;
	// 	}
	// 	public boolean isEmpty(){
	// 		return (numItems == 0);
	// 	}
	// 	public int size(){
	// 		return numItems;
	// 	}
	// 	public void removeAll(){
	// 		items = new Object[MAX_LIST+1];
	// 		numItems = 0;
	// 	}
	// 	public void add(int index, Object item)
	// 		throws ListIndexOutOfBoundsException{
	// 		if(numItems > MAX_LIST){
	// 			throw new ListIndexOutOfBoundsException("addition error");
	// 		}
	// 		if(index >= 1 && index <= numItems+1){
	// 			for(int pos = numItems; pos >= index; pos--){
	// 				items[pos+1] = items[pos];
	// 			}
	// 			items[index] = item;
	// 			numItems++;
	// 		} else{
	// 			throw new ListIndexOutOfBoundsException("addition error");
	// 		}
	// 	}
	// 	public Object get(int index)
	// 		throws ListIndexOutOfBoundsException{
	// 		if(index >= 1 && index <= numItems){
	// 			return items[index];
	// 		} else{
	// 			throw new ListIndexOutOfBoundsException("get error");
	// 		}
	// 	}
	// 	public void remove(int index)
	// 		throws ListIndexOutOfBoundsException{
	// 		if(index >= 1 && index <= numItems){
	// 			for(int pos = index+1; pos <= size(); pos++){
	// 				items[pos+1] = items[pos];
	// 			}
	// 			numItems--;
	// 		}
	// 		else{
	// 			throw new ListIndexOutOfBoundsException("remove error");
	// 		}
	// 	}
	// }
	/*****************************************/
}
