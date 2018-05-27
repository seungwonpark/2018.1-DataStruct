import java.io.*;
import java.util.*;
public interface Command{
	void execute(MyHashTable<LinkedList<MyPair>> db) throws Exception;
}

class ReadFile implements Command{
	private String arg;
	static final int HASH_LEN = 6;
	public ReadFile(String arg){
		this.arg = arg;
	}

	@Override
	public void execute(MyHashTable<LinkedList<MyPair>> db) throws Exception{
		FileReader fr = new FileReader(arg);
		BufferedReader br = new BufferedReader(fr);
		String dataline;
		for(int i=1;; i++){
			dataline = br.readLine();
			if(dataline == null) break;
			int l = dataline.length();
			for(int j=0; j<=l-HASH_LEN; j++){
				String query = dataline.substring(j, j+HASH_LEN);
				MyPair value = new MyPair(i, j+1);
				LinkedList<MyPair> temp = db.get(query);
				if(temp == null){
					LinkedList<MyPair> n = new LinkedList<MyPair>();
					n.add(value);
					db.insert(query, n);
				}
				else{
					temp.add(value);
				}
			}
		}
	}
}

class PrintSlot implements Command{
	private int arg;
	public PrintSlot(String arg){
		this.arg = Integer.parseInt(arg);
	}

	@Override
	public void execute(MyHashTable<LinkedList<MyPair>> db) throws Exception{
		AVLTree<String, LinkedList<MyPair>> temp;
		temp = db.slots.get(arg);
		if(temp == null){
			System.out.println("EMPTY");
		}
		else{
			System.out.println(temp.toString());
		}
	}
}

class SearchPattern implements Command{
	private String arg;
	public SearchPattern(String arg){
		this.arg = arg;
	}
	@Override
	public void execute(MyHashTable<LinkedList<MyPair>> db) throws Exception{
		// asdfasdf
		System.err.println("to be implemented");
	}
}
