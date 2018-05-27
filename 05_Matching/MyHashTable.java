import java.util.ArrayList;
public class MyHashTable<V>{
	static final int TABLE_SIZE = 100;
	ArrayList<AVLTree<String, V>> slots;
	public MyHashTable(){
		slots = new ArrayList<AVLTree<String, V>>();
		for(int i=0; i<TABLE_SIZE; i++){
			slots.add(null);
		}
	}

	public static int hash(String x){
		int sum = 0;
		for(int i=0; i<x.length(); i++){
			sum += x.charAt(i);
		}
		return sum % TABLE_SIZE;
	}

	void insert(String key, V value){
		// insert (key, value) to h-th slots.
		int h = hash(key);
		AVLTree<String, V> now = slots.get(h);
		if(now == null){
			// insert new AVLTree if not exist
			AVLTree<String, V> temp = new AVLTree<String, V>();
			temp.insert(key, value);
			slots.set(h, temp);
		}
		else{
			// insert new element to existing AVLTree
			now.insert(key, value);
		}
	}
	V get(String query){
		int h = hash(query);
		AVLTree<String, V> now = slots.get(h);
		if(now == null) return null;
		else{
			return now.get(query);
		}
	}
}
