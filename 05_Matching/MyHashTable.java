import java.util.ArrayList;
public class MyHashTable<V>{
	final int mod = 100;
	ArrayList<AVLTree<String, V>> slots;
	MyHashTable(){
		slots = new ArrayList<AVLTree<String, V>>();
		for(int i=0; i<100; i++){
			slots.add(null);
		}
	}

	public static int hash(String x){
		int sum = 0;
		for(int i=0; i<x.length(); i++){
			sum += x.charAt(i);
		}
		return sum % mod;
	}

	void insert(String key, V value){
		int h = hash(key);
		AVLTree<String, V> now = slots.get(hash);
		if(now == null){
			// insert new AVLTree if not exist
			AVLTree<String, V> temp = new AVLTree<String, V>();
			temp.insert(key, value);
			slots.set(hash, temp);
		}
		else{
			// insert new element to existing AVLTree
			now.insert(key, value);
		}
	}
	V get(String query){
		int h = hash(query);
		AVLTree<String, V> now = slots.get(hash);
		if(now == null) return null;
		else{
			return now.get(query);
		}
	}
}