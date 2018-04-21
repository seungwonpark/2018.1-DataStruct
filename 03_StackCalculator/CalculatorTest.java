import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class OperatorType implements Comparable<OperatorType>{
	private char op;
	private int priority;
	private boolean isLeftAssociative;
	public OperatorType(String x) throws Exception{
		this.op = x.charAt(0);
		switch(x.charAt(0)){
			case '(':
			case ')':
				break;
			case '^':
				this.priority = -1;
				this.isLeftAssociative = false;
				break;
			case '~':
				this.priority = -2;
				this.isLeftAssociative = false;
				break;
			case '*': case '/': case '%':
				this.priority = -3;
				this.isLeftAssociative = true;
				break;
			case '+': case '-':
				this.priority = -4;
				this.isLeftAssociative = true;
				break;
			default:
				throw new Exception("asdf");
		}
	}
	public char getOp(){
		return this.op;
	}
	public boolean isLeftAssociative(){
		return this.isLeftAssociative;
	}
	@Override
	public int compareTo(OperatorType other){
		return new Integer(this.priority).compareTo(new Integer(other.priority));
	}
}

public class CalculatorTest{
	public static boolean isLong(String s){
		// modification from https://stackoverflow.com/a/5439547
		try{
			Long.parseLong(s); // modified: Integer -> Long
		}
		catch(NumberFormatException e){
			return false;
		}
		catch(NullPointerException e){
			return false;
		}
		return true;
	}
	public static void main(String args[]){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true){
			try{
				String input = br.readLine();
				if (input.compareTo("q") == 0)
					break;

				converter(input);
				System.out.println("");
				// System.out.print(postfix);
				// Long answer = evaluation(postfix);
				// System.out.print(answer);
			}
			catch (Exception e){
				System.out.print("ERROR" + e.toString());
			}
		}
	}
	private static void converter(String input) throws Exception{
		input = input.replaceAll("\\s+", ""); // remove all spaces
		Pattern pattern = Pattern.compile("[\\+\\-\\*\\/\\%\\^\\(\\)]|\\d+");
		Matcher m = pattern.matcher(input);

		boolean isPrevLong = false; // to determine unary/binary '-' operator.
		Stack<OperatorType> opst = new Stack<OperatorType>(); // 'op'erator 'st'ack

		// makes use of shunting-yard algorithm.
		while(m.find()){
			String now = m.group();
			if(isLong(now)){
				// if the token is a number
				System.out.print(now);
				isPrevLong = true;
			}
			else if(now.charAt(0) == '('){
				opst.push(new OperatorType("("));
			}
			else if(now.charAt(0) == ')'){
				boolean foundMatchingBracket = false;
				while(!opst.empty()){
					if(opst.peek().getOp() == '('){
						opst.pop();
						foundMatchingBracket = true;
						break;
					}
					System.out.print(opst.pop().getOp());
				}
				if(foundMatchingBracket == false){
					throw new Exception();
				}
			}
			else{
				OperatorType temp;
				switch(now.charAt(0)){
					// store operator which will be pushed to opst.
					case '-':
						if(isPrevLong == false){
							// unary minus operator
							temp = new OperatorType("~");
						}
						else{
							temp = new OperatorType("-");
						}
						break;
					case '+': case '*': case '/': case '%': case '^':
						temp = new OperatorType(now);
						break;
					default:
						throw new Exception();
				}
				while(!opst.empty()){
					OperatorType peeked = opst.peek();
					if(
						(
							peeked.compareTo(temp) > 0 || 
							(
								peeked.compareTo(temp) == 0 &&
								peeked.isLeftAssociative()
							)
						)
						&& peeked.getOp() != '('
					){
						System.out.print(opst.pop().getOp());
					}
					else{
						break;
					}
				}
				opst.push(temp);
				isPrevLong = false;
			}
		}
		while(!opst.empty()){
			System.out.print(opst.pop().getOp());
		}
	}
}
