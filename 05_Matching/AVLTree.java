public class AVLTree<K extends Comparable<K>, V>{
	private TreeNode<K, V> root;
	public AVLTree(){
		root = null;
	}

	public void insert(K key, V value){
		TreeNode<K, V> x = new TreeNode<>(key, value);
		if(root == null){
			root = x;
			return;
		}
		TreeNode<K, V> now = root;
		while(true){
			int cmp = key.compareTo(now.getKey());
			assert(cmp != 0);

			TreeNode<K, V> p = now; // temporarily stores parent.
			if(cmp < 0) now = now.getLeft();
			else 		now = now.getRight();

			// if it reaches leaf node, then insert & rebalance.
			if(now == null){
				if(cmp < 0) p.setLeft(x);
				else 		p.setRight(x);
				x.setParent(p);
				root = p.rebalance();
				return;
			}
		}
	}
	public V get(K query){
		TreeNode<K, V> now = root;
		while(true){
			// must ensure that this does not lead to infinite loop
			if(now == null) return null;
			int cmp = query.compareTo(now.getKey());
			if(cmp < 0) now = now.getLeft();
			else if(cmp > 0) now = now.getRight();
			else{
				return now.getValue();
			}
		}
	}
	@Override
	public String toString(){
		return root.toString();
	}
}

class TreeNode<K extends Comparable<K>, V>{
	private TreeNode<K, V> left, right, parent;
	private K key;
	private V value;
	public TreeNode(K key, V value){
		this.left = this.right = this.parent = null;
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
	public TreeNode<K, V> getParent(){
		return this.parent;
	}
	public void setLeft(TreeNode<K, V> x){
		this.left = x;
	}
	public void setRight(TreeNode<K, V> x){
		this.right = x;
	}
	public void setParent(TreeNode<K, V> x){
		this.parent = x;
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

		target.parent = p;

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

	// pre-order traversal of AVLTree
	@Override
	public String toString(){
		String ret = key.toString();
		if(this.left != null) 	ret += ' ' + left.	toString();
		if(this.right != null) 	ret += ' ' + right.	toString();
		return ret;
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
