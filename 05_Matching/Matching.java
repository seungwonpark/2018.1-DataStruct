import java.io.*;
import java.util.*;

public class Matching{
	static MyHashTable<LinkedList<MyPair>> db;

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		db = new MyHashTable<LinkedList<MyPair>>();

		while (true){
			try{
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;
				command(input);
			}
			catch (IOException e){
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input) throws Exception{
		String arg = input.substring(2);
		Command cmd;
		if(input.startsWith("< ")){
			cmd = new ReadFile(arg);
		}
		else if(input.startsWith("@ ")){
			cmd = new PrintSlot(arg);
		}
		else if(input.startsWith("? ")){
			cmd = new SearchPattern(arg);
		}
		else{
			throw new Exception("invalid command.");
		}
		cmd.execute(db);
	}
}
