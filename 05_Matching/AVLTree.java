public class AVLTree<K extends Comparable<K>, V>{
	private TreeNode<K, V> root;
	public AVLTree(){
		root = null;
	}
	public void insert(K key, V value){
		TreeNode<K, V> x = new TreeNode<>(key, value);
		TreeNode<K, V> now = root;
		while(true){
			if(now == null){
				now = x;
				break;
			}
			boolean alreadyExists = false;
			switch(key.compareTo(now.getKey())){
				case -1:
					now = now.getLeft();
					break;
				case 1:
					now = now.getRight();
					break;
				case 0:
					alreadyExists = true;
					break;
			}
			if(alreadyExists) break;
		}
	}
	public V get(K query){
		TreeNode<K, V> now = root;
		while(true){
			// must ensure that this does not lead to infinite loop
			if(now == null) return null;
			switch(query.compareTo(now.getKey())){
				case -1:
					now = now.getLeft();
					break;
				case 1:
					now = now.getRight();
					break;
				case 0:
					return now.getValue();
			}
		}
	}
}

class TreeNode<K extends Comparable<K>, V>{
	private TreeNode<K, V> left, right, parent;
	private K key;
	private V value;
	public TreeNode(K key, V value){
		this.key = key;
		this.value = value;
	}
	public K getKey(){
		return this.key;
	}
	public V getValue(){
		return this.value;
	}
	public TreeNode<K, V> getLeft(){
		return this.left;
	}
	public TreeNode<K, V> getRight(){
		return this.right;
	}

	public int height(TreeNode<K, V> x){
		if(x == null) return 0;
		return Math.max(height(x.left), height(x.right)) + 1;
	}

	public TreeNode<K, V> rebalance(){
		// returns the root node of tree.
		int diff = height(this.right) - height(this.left);
		TreeNode<K, V> n;
		if(diff == -2){
			n = height(this.left.left) >= height(this.left.right) ?
				n = this.rotateRight() : this.rotateLeftRight();
		}
		else if(diff == 2){
			n = height(this.right.right) >= height(this.right.left) ?
				n = this.rotateLeft() : this.rotateRightLeft();
		}
		else{
			n = this;
		}
		return this.parent != null ?
			this.parent.rebalance() : n;
	}
	// refer to comment in the bottom of this code.
	// all methods below return the root node of tree.
	public TreeNode<K, V> rotateLeft(){
		assert(this.right != null);
		TreeNode<K, V> p = this.parent;
		TreeNode<K, V> target = this.right;
		TreeNode<K, V> t2 = target.left;

		target.parent = p;
		if(p != null){
			if(p.left == this) 	p.left 	= target;
			else 				p.right = target;
		}
		target.left = this;
		this.parent = target;
		this.right = t2;
		if(t2 != null){
			t2.parent = this;
		}
		return target;
	}
	public TreeNode<K, V> rotateRight(){
		assert(this.left != null);
		TreeNode<K, V> p = this.parent;
		TreeNode<K, V> target = this.left;
		TreeNode<K, V> t2 = target.right;

		target.parent = p;;

		if(p != null){
			if(p.left == this) 	p.left 	= target;
			else 				p.right = target;
		}
		target.right = this;
		this.parent = target;
		this.left = t2;
		if(t2 != null){
			t2.parent = this;
		}
		return target;
	}
	public TreeNode<K, V> rotateLeftRight(){
		this.left.rotateLeft();
		return this.rotateRight();
	}
	public TreeNode<K, V> rotateRightLeft(){
		this.right.rotateRight();
		return this.rotateLeft();
	}
}

/*
rotateLeft()
	before:
	........p
	......./.(or \)
	....this
	..../..\
	...t1..target
	......../..\
	.......t2..t3
	after:
	........p
	......./.(or \)
	....target
	...../...\
	...this..t3
	.../..\
	..t1..t2

rotateRight()
	exactly opposite of rotateLeft()
*/