import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// makes comparable class "OperatorType"
// to compare priority of operators
// and determine whether the operator is left/right associative
class OperatorType implements Comparable<OperatorType>{
	private char op;
	private int priority;
	private boolean isLeftAssociative;

	// constructor
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

	// to parse equation, we must first determine whether the token is Long type.
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

				ArrayList<String> postfix = converter(input);
				long answer = evaluation(postfix);
				// convert/evalaute at first, and then flush outputs
				// to print "ERROR" first if error occurs
				for(int i=0; i<postfix.size(); i++){
					System.out.print(postfix.get(i));
					if(i != postfix.size() - 1){ // if not last
						System.out.print(" ");
					}
				}
				System.out.println("");
				System.out.print(answer);
				System.out.println("");
			}
			catch (Exception e){
				System.out.println("ERROR");
			}
		}
	}

	// evaluates answer from postfix expression
	private static long evaluation(ArrayList<String> postfix) throws Exception{
		Stack<Long> st = new Stack<Long>();
		for(int i=0; i<postfix.size(); i++){
			String now = postfix.get(i);
			if(isLong(now)){
				st.push(Long.parseLong(now));
			}
			else{
				if(now.charAt(0) == '~'){
					// process separtely since the argument is single
					long numLeft = st.pop();
					st.push((-1) * numLeft);
				}
				else{
					long numRight = st.pop();
					long numLeft = st.pop();
					switch(now.charAt(0)){
						case '+':
							st.push(numLeft + numRight);
							break;
						case '-':
							st.push(numLeft - numRight);
							break;
						case '*':
							st.push(numLeft * numRight);
							break;
						case '/':
							if(numRight == 0){
								throw new Exception("division by zero");
							}
							st.push(numLeft / numRight);
							break;
						case '%':
							if(numRight == 0){
								throw new Exception("division by zero");
							}
							st.push(numLeft % numRight);
							break;
						case '^':
							if(numLeft == 0 && numRight < 0){
								throw new Exception("division by zero");
							}
							st.push((long) Math.pow((double) numLeft, (double) numRight));
							break;
						default:
							throw new Exception();
					}
				}
			}
		}
		long result = st.pop();
		if(!st.empty()){
			throw new Exception("cannot finish calculation");
		}
		return result;
	}

	private static ArrayList<String> converter(String input) throws Exception{
		input = input.replaceAll("\\s+", ""); // remove all spaces

		// catch the invalid form: (), (-), (--), (---), ...
		Pattern invalidpattern = Pattern.compile("\\(\\-*\\)");
		Matcher m_temp = invalidpattern.matcher(input);
		if(m_temp.find()){ // if invalid form of bracket (e.g. (-), (---)) is found
			throw new Exception("invalid");
		}

		Pattern pattern = Pattern.compile("[\\+\\-\\*\\/\\%\\^\\(\\)]|\\d+");
		Matcher m = pattern.matcher(input);

		boolean isPrevLong = false; // to determine unary/binary '-' operator.
		Stack<OperatorType> opst = new Stack<OperatorType>(); // 'op'erator 'st'ack

		ArrayList<String> ret = new ArrayList<String>();

		// makes use of shunting-yard algorithm.
		// https://en.wikipedia.org/wiki/Shunting-yard_algorithm
		while(m.find()){
			String now = m.group();
			if(isLong(now)){
				// if the token is a number
				ret.add(now);
				isPrevLong = true;
			}
			else if(now.charAt(0) == '('){
				opst.push(new OperatorType("("));
				isPrevLong = false;
			}
			else if(now.charAt(0) == ')'){
				boolean foundMatchingBracket = false; // check if bracket matches
				while(!opst.empty()){
					if(opst.peek().getOp() == '('){
						opst.pop();
						foundMatchingBracket = true;
						break;
					}
					ret.add(Character.toString(opst.pop().getOp()));
				}
				if(foundMatchingBracket == false){
					throw new Exception("Unmatching bracket(s) found");
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
						if(isPrevLong == false){
							throw new Exception();
						}
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
						ret.add(Character.toString(opst.pop().getOp()));
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
			ret.add(Character.toString(opst.pop().getOp()));
		}
		return ret;
	}
}
