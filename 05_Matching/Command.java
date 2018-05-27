import java.io.*;
import java.util.*;
public interface Command{
	static final int HASH_LEN = 6;
	void execute(MyHashTable<LinkedList<MyPair>> db) throws Exception;
}

class ReadFile implements Command{
	private String arg;
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
		int l = arg.length();
		String first_query = arg.substring(0, HASH_LEN);
		LinkedList<MyPair> candidates = db.get(first_query);
		if(candidates == null){
			System.out.println("(0, 0)");
			return;
		}
		// to avoid java.util.ConcurrentModificationException
		LinkedList<MyPair> elected = new LinkedList<MyPair>(candidates);
		for(int i=l; i>HASH_LEN; i-=HASH_LEN){
			String query = arg.substring(i-HASH_LEN, i);
			for(MyPair x : candidates){
				boolean exists = false;
				LinkedList<MyPair> test = db.get(query);
				if(test != null){
					for(MyPair y : test){
						if(x.first == y.first && x.second + i - HASH_LEN == y.second){
							exists = true;
							break;
						}
					}
				}
				if(!exists){
					elected.remove(x);
				}
			}
		}

		StringBuilder temp = new StringBuilder();
		for(MyPair x : elected){
			temp.append(x.toString());
			temp.append(' ');
		}
		System.out.println(temp.toString().trim());
	}
}
