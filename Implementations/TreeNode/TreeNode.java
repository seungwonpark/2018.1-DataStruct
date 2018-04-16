import java.util.*;

public class TreeNode{
	private Object item;
	private TreeNode leftChild;
	private TreeNode rightChild;
	public TreeNode(Object newItem){
		this.item = newItem;
		this.leftChild = this.rightChild = null;
	}
	public TreeNode(Object newItem, TreeNode left, TreeNode right){
		this.item = newItem;
		this.leftChild = left;
		this.rightChild = right;
	}
	public Object getItem(){
		return item;
	}
	public void setItem(Object newItem){
		this.item = newItem;
	}
	public TreeNode getLeft(){
		return leftChild;
	}
	public TreeNode getRight(){
		return rightChild;
	}
	public void setLeft(TreeNode left){
		this.leftChild = left;
	}
	public void setRight(TreeNode right){
		this.rightChild = right;
	}

	public Object getMin(TreeNode tNode){
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
	public TreeNode insertItem(TreeNode tNode, Comparable newItem){
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
	public TreeNode retrieveItem(TreeNode tNode, Comparable searchKey){
		if(tNode == null) return null;
		else{
			Object now = tNode.getItem();
			if(searchKey == now) return tNode;
			else if(searchKey < now){
				return retrieveItem(tNode.getLeft(), searchKey);
			}
			else{
				return retrieveItem(tNode.getRight(), searchKey);
			}
		}
		return tNode;
	}
	public TreeNode deleteItem(TreeNode tNode, Comparable searchKey){
		if(tNode == null) return null;
		else{
			Object now = tNode.getItem();
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
	public TreeNode deleteNode(TreeNode tNode){
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







