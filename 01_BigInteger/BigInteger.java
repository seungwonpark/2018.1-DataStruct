import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.StringBuilder;
public class BigInteger{
	public static final String QUIT_COMMAND = "quit";
	public static final String MSG_INVALID_INPUT = "입력이 잘못되었습니다.";
	private boolean isPositive;
	private int[] num; // reversed.

	// reverses string
	public static String myreverse(String str){
		StringBuilder temp = new StringBuilder();
		temp.append(str);
		return temp.reverse().toString();
	}

	// without sign. always positive
	public BigInteger(int[] num){
		this.isPositive = true;
		this.num = num.clone();
	}

	// construct with sign
	// num is already reversed.
	public BigInteger(boolean isPositive, int[] num){
		this.isPositive = isPositive;
		this.num = num.clone();
	}

	// construct with sign, string.
	// s is already reversed.
	public BigInteger(boolean isPositive, String s){
		this.isPositive = isPositive;
		num = new int[210];
		int l = s.length();
		for(int i=0; i<l; i++){
			char c = s.charAt(i);
			if(c < '0' || c > '9'){
				throw new IllegalArgumentException();
			}
			num[i] = (c - '0');
		}
	}
	
	// mulitplies -1 to BigInteger object
	public BigInteger negative(){
		return new BigInteger(!isPositive, num);
	}

	// compare two BigInteger prior to substract one with another
	public static enum Ordering { Less, Greater, Equal };
	public Ordering cmp(BigInteger right){
		for(int i=this.num.length-1; i>=0; i--){
			if(this.num[i] == right.num[i]) { continue; }
			else if(this.num[i] < right.num[i]) { return Ordering.Less; }
			else if(this.num[i] > right.num[i]) { return Ordering.Greater; }
		}
		return Ordering.Equal;
	}

	public BigInteger addition(BigInteger right){
		if(!this.isPositive && !right.isPositive){ // -1 + -2
			BigInteger a = new BigInteger(this.num);
			BigInteger b = new BigInteger(right.num);
			return a.addition(b).negative(); // -(1+2)
		}
		else if(!this.isPositive){ // -1 + +2
			BigInteger temp = new BigInteger(this.num);
			return right.substract(temp); // 2 - 1
		}
		else if(!right.isPositive){ // +1 + -2
			BigInteger temp = new BigInteger(right.num);
			return this.substract(temp); // 1 - 2
		}
		else{ // +1 + +2
			int carry = 0;
			int[] ans = new int[210];
			for(int i=0; i<210; i++){
				int temp = this.num[i] + right.num[i] + carry;
				carry = 0;
				if(temp > 9){
					carry = 1;
					temp -= 10;
				}
				ans[i] = temp;
			}
			return new BigInteger(true, ans);
		}
	}
	public BigInteger substract(BigInteger right){
		if(this.isPositive && !right.isPositive){ // +1 - -2
			BigInteger temp = new BigInteger(right.num);
			return this.addition(temp); // 1 + 2
		}
		else if(!this.isPositive && right.isPositive){ // -1 - +2
			BigInteger a = new BigInteger(this.num);
			BigInteger b = new BigInteger(right.num);
			return a.addition(b).negative(); // -(1+2)
		}
		else if(!this.isPositive && !right.isPositive){ // -1 - -2
			BigInteger temp = new BigInteger(right.num);
			return temp.substract(this);
		}
		else{ // +1 - +2
			Ordering cmp = this.cmp(right);
			if(cmp == Ordering.Less){ // 12 - 34
				return right.substract(this).negative();
			}
			else{ // 112 - 34
				int carry = 0;
				int[] ans = new int[210];
				for(int i=0; i<210; i++){
					int temp = this.num[i] - right.num[i] + carry;
					carry = 0;
					if(temp < 0){
						temp += 10;
						carry = -1;
					}
					ans[i] = temp;
				}
				return new BigInteger(true, ans);
			}
		}
	}
	public BigInteger multiply(BigInteger right){
		if(this.isPositive ^ right.isPositive){ // -1 * 2 or 1 * -2
			BigInteger a = new BigInteger(this.num);
			BigInteger b = new BigInteger(right.num);
			return a.multiply(b).negative();
		}
		int[] ans = new int[210];
		for(int i=0; i<100; i++){
			for(int j=0; j<100; j++){
				ans[i+j] += this.num[i] * right.num[j];
			}
		}
		int carry = 0;
		for(int i=0; i<210; i++){
			int temp = ans[i] + carry;
			carry = temp / 10;
			temp = temp % 10;
			ans[i] = temp;
		}
		return new BigInteger(true, ans);
	}

	@Override
	public String toString(){
		StringBuffer temp = new StringBuffer();
		int x = num.length - 1;
		while(num[x] == 0){
			x -= 1;
		}
		for(int i=0; i<=x; i++){
			temp.append(num[i]);
		}
		if(!isPositive){
			temp.append("-");
		}
		return temp.toString();
	}

	static BigInteger evaluate(String input) throws IllegalArgumentException{
		// input = input.replaceAll("\\s+", ""); // remove all spaces
		// since "1234 + 56 78" is invalid, we should handle spaces in regex.
		Pattern pattern = Pattern.compile("\\s*([+-]?)\\s*(\\d+)\\s*([+\\-*])\\s*([+-]?)\\s*(\\d+)\\s*");
		// "\\s*": match spaces
		Matcher m = pattern.matcher(input);
		try{
			m.find();
		}
		catch (IllegalStateException e){ // no match found
			throw new IllegalArgumentException();
		}
		if(m.group(1).length() > 1 || m.group(3).length() != 1 || m.group(4).length() > 1){
			// in case of invalid sign/operator
			throw new IllegalArgumentException();
		}

		String a_sign = m.group(1); // sign of a
		String a_num = m.group(2); // a
		String operation = m.group(3); // operator
		String b_sign = m.group(4); // sign of b
		String b_num = m.group(5); // b

		// reverse integer to perform calculations, switched back at last
		a_num = myreverse(a_num);
		b_num = myreverse(b_num);

		boolean a_isP, b_isP;
		switch(a_sign){
			case "+":
			case "":
				a_isP = true; break; // "+1++2" = "1+2"
			case "-":
				a_isP = false; break;
			default:
				throw new IllegalArgumentException();
		}
		switch(b_sign){
			case "+":
			case "":
				b_isP = true; break;
			case "-":
				b_isP = false; break;
			default:
				throw new IllegalArgumentException();
		}
		BigInteger a = new BigInteger(a_isP, a_num);
		BigInteger b = new BigInteger(b_isP, b_num);

		switch(operation){
			case "+":
				return a.addition(b);
			case "-":
				return a.substract(b);
			case "*":
				return a.multiply(b);
			default:
				throw new IllegalArgumentException();
		}
	}
	public static void main(String[] args) throws Exception{
		try (InputStreamReader isr = new InputStreamReader(System.in)){
			try (BufferedReader reader = new BufferedReader(isr)){
				boolean done = false;
				while(!done){
					String input = reader.readLine();
					try{
						done = processInput(input);
					}
					catch (IllegalArgumentException e){
						System.err.println(MSG_INVALID_INPUT);
					}
				}
			}
		}
	}
	static boolean processInput(String input) throws IllegalArgumentException{
		boolean quit = isQuitCmd(input);
		if(quit){
			return true;
		}
		else{
			BigInteger result = evaluate(input);
			System.out.println(myreverse(result.toString()));
			return false;
		}
	}
	static boolean isQuitCmd(String input){
		return input.equalsIgnoreCase(QUIT_COMMAND);
	}
}