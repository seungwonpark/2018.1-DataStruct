import java.util.*;
class Stack{
	public static void main(String[] args){
		StackArrayBased test = new StackArrayBased();
		try{
			test.push(123);
			test.push(456);
			System.out.println(test.pop());
			System.out.println(test.isEmpty());
			System.out.println(test.size());
			test.pop();
			System.out.println(test.isEmpty());
			System.out.println(test.size());
		}
		catch (StackEmptyException e){
			throw new IllegalArgumentException();
		}
		catch (StackFullException e){
			throw new IllegalArgumentException();
		}
	}
}