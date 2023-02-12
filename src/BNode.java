public class BNode {
//instance variables for this class
Pel value;
BNode left;
BNode right;
BNode parent;

    //constructor for the class. Stores the Pel value in the node and sets left child, right child, and parent to the specified values
	public BNode (Pel value, BNode left, BNode right, BNode parent) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	//constructor for the class that initializes a leaf node. The data, children and parent attributes are set to null.
	public BNode() {
		this.value = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	//returns the parent of this node
	public BNode parent() {
		return this.parent;
	}
	
	//sets the parent of this node to the specified value
	public void setParent(BNode newParent) {
		this.parent = newParent;
	}
	
	//sets the left child of this node to the specified value
	public void setLeftChild(BNode p) {
		this.left = p;
	}
	
	//sets the right child of this node to the specified value
	public void setRightChild(BNode p) {
		this.right = p;
	}
	
	//stores the given Pel object in this node
	public void setContent (Pel value) {
		this.value = value;
	}
	
	//returns true if this node is a leaf; returns false otherwise
	public boolean isLeaf() {
		if(value== null && left == null && right == null) {
			return true;
		}
		return false;
	}
	
	//returns the Pel object stored in this node
	public Pel getData() {
		return value;
	}
	
	//returns the left child of this node
	public BNode leftChild() {
		return left;
	}
	
	//returns the right child of this node
	public BNode rightChild() {
		return right;
	}
}
