public class TreeNodeTest{
	public static void main(String[] args){
		TreeNode x = new TreeNode(2);
		System.out.println(x.getItem());
		x.insertItem(x, 3);
		x.insertItem(x, 1);
		TreeNode y = x.getLeft();
		System.out.println(y.getItem());
	}
}