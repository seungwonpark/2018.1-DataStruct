import java.util.*;

public class TreeNode{
	private int item;
	private TreeNode leftChild;
	private TreeNode rightChild;

	// constructors
	public TreeNode(int newItem){
		this.item = newItem;
		this.leftChild = this.rightChild = null;
	}
	public TreeNode(int newItem, TreeNode left, TreeNode right){
		this.item = newItem;
		this.leftChild = left;
		this.rightChild = right;
	}

	// public methods
	public int getItem(){
		return item;
	}
	public TreeNode getLeft(){
		return leftChild;
	}
	public TreeNode getRight(){
		return rightChild;
	}

	// private methods
	private void setItem(int newItem){
		this.item = newItem;
	}
	private void setLeft(TreeNode left){
		this.leftChild = left;
	}
	private void setRight(TreeNode right){
		this.rightChild = right;
	}

	public int getMin() throws Exception{
		if(this == null){
			throw new Exception();
		}
		if(this.getLeft() == null){
			return this.getItem();
		}
		else{
			return getMin(this.getLeft());
		}
	}
	public TreeNode insertItem(int newItem){
		if(this == null){
			throw new Exception();
		}
		else if(newItem < this.getItem()){
			this.setLeft(insertItem(this.getLeft(), newItem));
		}
		else{
			this.setRight(insertItem(this.getRight(), newItem));
		}
		return this;
	}
	public TreeNode retrieveItem(int searchKey){
		if(this == null) return null;
		else{
			if(searchKey == item) return this;
			else if(searchKey < item){
				return retrieveItem(this.getLeft(), searchKey);
			}
			else{
				return retrieveItem(this.getRight(), searchKey);
			}
		}
	}
	public TreeNode deleteItem(int searchKey) throws Exception{
		if(this == null) return null;
		else{
			if(searchKey == item){
				this = deleteNode(this);
			}
			else if(searchKey < item){
				this.setLeft(deleteItem(this.getLeft(), searchKey));
			}
			else{
				this.setRight(deleteItem(this.getRight(), searchKey));
			}
		}
		return this;
	}
	public TreeNode deleteNode() throws Exception{
		if(this.getLeft() == null && this.getRight() == null){
			return null;
		}
		else if(this.getLeft() == null){
			return this.getRight();
		}
		else if(this.getRight() == null){
			return this.getLeft();
		}
		else{
			this.setItem(getMin(this.getRight()));
			this.setRight(deleteMin(this.getRight()));
			return this;
		}
	}
	public TreeNode deleteMin(){
		if(this.getLeft() == null){
			return this.getRight();
		}
		else{
			this.setLeft(deleteMin(this.getLeft()));
			return this;
		}
	}
}







