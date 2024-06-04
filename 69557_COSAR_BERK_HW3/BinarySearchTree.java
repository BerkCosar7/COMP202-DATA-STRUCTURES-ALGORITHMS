
public class BinarySearchTree
{
    Node root;

    // Node structure containing the subtrees
	static class Node
	{
	
		// Your code here
		Node left=null;
		Node right=null;
	    int item;

	};

    // Constructor
    public BinarySearchTree()
    {
        // Your code here
        this.root=null;
       
    }

    // Insert new item into the binary tree
    public void insert(int data)
    {
        // Your code here
        root=searchAndInsert(root,data); //actual job is done by helper searchAndInsert method. I think the insert method must take root as a parameter.
    }
    
    public Node searchAndInsert(Node root, int data) {
    	if(root==null) {
    		root=new Node();
    		root.item=data;
    		return root;
    	}
    	
    	if(root.item>data) {
    		root.left=searchAndInsert(root.left, data);
    	}else if(root.item<data) {
    		root.right=searchAndInsert(root.right,data);
    	}
    	
    	return root;
    }

    // Check if the tree is balanced or not
    public boolean isBalanced()
    {
        // Your code here
    	return balanceHelper(root);
    	
    }
    
    public boolean balanceHelper(Node root) {
    	if(root==null) {
    		return true;
    	}
    	
    	if(Math.abs(heightCalculator(root.left)-heightCalculator(root.right))<=1 && balanceHelper(root.left) && balanceHelper(root.right)){
    		return true;
    	}
    	return false;
    }
    
    public int heightCalculator(Node root) {
    	if(root==null) {
    		return 0;
    	}else {
    		return 1+Math.max(heightCalculator(root.left),heightCalculator(root.right));
    	}
    }
	
	// Remove an item from the tree
	public void remove(int item)
	{
		// Your code here
		root=searchAndDelete(root,item);
	}
	
	public Node searchAndDelete( Node root , int data) {
		if(root==null) {
			return root;
		}
		
		if(root.item>data) {
			root.left=searchAndDelete(root.left,data);
		}else if(root.item<data) {
			root.right=searchAndDelete(root.right,data);
		}else {
			
			if(root.right==null) {
				return root.left;
			}else if(root.left==null) {
				return root.right;
			}
			root.item=max(root.left); //actually we can also look for minimum at right subtree but I choose this one.
			root.left=searchAndDelete(root.left,root.item);
		}return root;
		}
	
	public int max(Node root) {  
		int maximum=root.item;
		while(root.right!=null) {
			maximum=root.right.item;
			root=root.right;
		}
		return maximum;
	}
	
	// Compare two trees. Return true if both trees are same
	public boolean compareTo(BinarySearchTree tree)  
	{
		// Your code here
		return comparator(root,tree.root);
	}
	
	public boolean comparator(Node root, Node root2) {
		if(nodeCount(root) != nodeCount(root2)) {
			return false;
		}
		if(root==null && root2==null) {
			return true;
		}
  return((root.item== root2.item) && comparator(root.left,root2.left) && comparator(root.right,root2.right));
	}
	
	public int nodeCount(Node root) {
		if(root==null) {
			return 0;
		}
		
		return 1+nodeCount(root.left)+nodeCount(root.right);
	}
	
	// Given function to print the tree
	public void printInOrder(Node node)
	{
		if (node != null)
		{
			printInOrder(node.left);
			System.out.print(node.item + " ");
			printInOrder(node.right);
		}
    }
}
