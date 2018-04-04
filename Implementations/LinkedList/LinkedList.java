import java.util.*;
class LinkedList{
	public static void main(String[] args){
		ListReferenceBased test = new ListReferenceBased();
		try{
			test.add(1,3);
			System.out.println(test.size());
			System.out.println(test.isEmpty());
			test.add(2,5);
			test.add(3,7);
			for(int i=1; i<=test.size(); i++){
				System.out.println(test.get(i));
			}
			test.remove(2);
			for(int i=1; i<=test.size(); i++){
				System.out.println(test.get(i));
			}
			test.removeAll();
			System.out.println(test.size());
			System.out.println(test.isEmpty());
		}
		catch (ListIndexOutOfBoundsException e){
			throw new IllegalArgumentException();
		}
	}
}