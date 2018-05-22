public class AVLTree<K extends Comparable<K>, V>{
	private class Node{
		private K key;
		private V value;
		private Node parent;
		private Node left;
		private Node right;
		private Node(K key, V value){
			this.key = key;
			this.value = value;
		}
		Node root = null;
		int height(Node x){
			if(x == null) return 0;
			return Math.max(height(x.left), height(x.right)) + 1;
		}

		void insert(K key, V value){
			Node x = new Node(key, value);
			Node now = root;
			while(1){
				if(now == null){
					now = x;
					break;
				}
				boolean alreadyExists = false;
				switch(key.compareTo(now.key)){
					case -1:
						now = now.left;
						break;
					case 0:
						alreadyExists = true;
						break;
					case 1:
						now = now.right;
						break;
				}
				if(alreadyExists) break;
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

		*/
		private void rotateLeft(){
			assert(this.right != null);

			Node p = this.parent;
			Node target = this.right;
			Node t2 = target.left;
			target.parent = p;
			if(p != null){
				if(p.left == this)  p.left  = target;
				else 				p.right = target;
			}
			target.left = this;
			this.parent = target;
			this.right = t2;
			if(t2 != null){
				t2.parent = this;
			}
		}
		private void rotateRight(){
			assert(this.left != null);

			Node p = this.parent;
			Node target = this.left;
			Node t2 = target.right;
			target.parent = p;
			if(p != null){
				if(p.right == this) p.right = target;
				else 				p.left  = target;
			}
			target.right = this;
			this.parent = target;
			this.left = t2;
			if(t2 != null){
				t2.parent = this;
			}
		}
		private void rotateLeftRight(){
			this.left.rotateLeft();
			this.rotateRight();
		}
		private void rotateRightLeft(){
			this.right.rotateRight();
			this.rotateLeft();
		}
		private void balancing(){
			int diff = height(this.right) - height(this.left);
			if(diff == -2){
				if(height(this.left.left) >= height(this.left.right)){
					this.rotateRight();
				}
				else{
					this.rotateLeftRight();
				}
			}
			else if(diff == 2){
				if(height(this.right.right) >= height(this.right.left)){
					this.rotateLeft();
				}
				else{
					this.rotateRightLeft();
				}
			}
			if(this.parent != null){
				this.balancing();
			}
		}

		// preorder traversal
        @Override
        public String toString(){
        	String ret = key.toString();
        	if(left != null) ret += ' '+left.toString();
        	if(right != null) ret += ' '+right.toString();
        	return ret;
        }
	}

	V get(K query){
		Node temp = root;
		while(1){
			if(temp == null) return null;
			switch(query.compareTo(temp.key)){
				case -1:
					temp = temp.left;
					break;
				case 0:
					return temp.value;
					break;
				case 1:
					temp = temp.right;
					break;
			}
		}
	}
}