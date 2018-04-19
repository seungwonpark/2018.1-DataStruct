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

	public int getMin(TreeNode tNode) throws Exception{
		if(tNode == null){
			throw new Exception();
		}
		if(tNode.getLeft() == null){
			return tNode.getItem();
		}
		else{
			return getMin(tNode.getLeft());
		}
	}
	public TreeNode insertItem(TreeNode tNode, int newItem){
		if(tNode == null){
			tNode = new TreeNode(newItem);
		}
		else if(newItem < tNode.getItem()){
			tNode.setLeft(insertItem(tNode.getLeft(), newItem));
		}
		else{
			tNode.setRight(insertItem(tNode.getRight(), newItem));
		}
		return tNode;
	}
	public TreeNode retrieveItem(TreeNode tNode, int searchKey){
		if(tNode == null) return null;
		else{
			int now = tNode.getItem();
			if(searchKey == now) return tNode;
			else if(searchKey < now){
				return retrieveItem(tNode.getLeft(), searchKey);
			}
			else{
				return retrieveItem(tNode.getRight(), searchKey);
			}
		}
	}
	public TreeNode deleteItem(TreeNode tNode, int searchKey) throws Exception{
		if(tNode == null) return null;
		else{
			int now = tNode.getItem();
			if(searchKey == now){
				tNode = deleteNode(tNode);
			}
			else if(searchKey < now){
				tNode.setLeft(deleteItem(tNode.getLeft(), searchKey));
			}
			else{
				tNode.setRight(deleteItem(tNode.getRight(), searchKey));
			}
		}
		return tNode;
	}
	public TreeNode deleteNode(TreeNode tNode) throws Exception{
		if(tNode.getLeft() == null && tNode.getRight() == null){
			return null;
		}
		else if(tNode.getLeft() == null){
			return tNode.getRight();
		}
		else if(tNode.getRight() == null){
			return tNode.getLeft();
		}
		else{
			tNode.setItem(getMin(tNode.getRight()));
			tNode.setRight(deleteMin(tNode.getRight()));
			return tNode;
		}
	}
	public TreeNode deleteMin(TreeNode tNode){
		if(tNode.getLeft() == null){
			return tNode.getRight();
		}
		else{
			tNode.setLeft(deleteMin(tNode.getLeft()));
			return tNode;
		}
	}
}







