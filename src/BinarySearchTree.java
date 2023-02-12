public class BinarySearchTree implements BinarySearchTreeADT {
//my instance variable for this class
private BNode root;
    
    //creates a tree whose root is a leaf node
	public BinarySearchTree() {
		root = new BNode();
	}

	//removes the data item with the given key, if the key is stored in the tree; throws an InexistentKeyException otherwise
	public void remove(BNode r, Location key) throws InexistentKeyException{
		
		//finding the right node
		BNode myNode = findNode(r,key);
		
		//if key is not in tree, throw exception
		if (myNode.isLeaf()) {
			throw new InexistentKeyException("Key is not in the tree!");
		}
		
		else {
			
			//if one of the children is a leaf
			if(myNode.leftChild().isLeaf() || myNode.rightChild().isLeaf()) {
				
				//if the left child is a leaf
				if(myNode.leftChild().isLeaf()) {
					BNode right = myNode.rightChild();
					BNode parent = myNode.parent();
					
					if(parent == null) {
						this.root = right;
						right.setRightChild(null);
						
					}
					
					else {
						if(parent.rightChild().equals(myNode)) {
							parent.setRightChild(right);
							right.setParent(parent);
						}
						else {
							parent.setLeftChild(right);
							right.setParent(parent);
						}
					}
				}
				
				//if the right child is a leaf
				else {
					BNode left = myNode.leftChild();
					BNode parent = myNode.parent();
					
					if(parent == null) {
						this.root = left;
						left.setRightChild(null);
						
					}
					else {
						if(parent.rightChild().equals(myNode)) {
							parent.setRightChild(left);
							left.setParent(parent);
						}
						else {
							parent.setLeftChild(left);
							left.setParent(parent);
						}
					}
				}
				
			}
			//else if the node is internal, find the smallest of its right sub tree and replace it
			else {
				BNode small = smallestNode(myNode.rightChild());
				myNode.setContent(small.getData());
				BNode sParent = small.parent();
				
				if(sParent.leftChild().equals(small)) {
					sParent.setLeftChild(small.rightChild());
				}
				else {
					sParent.setRightChild(small.rightChild());
				}
				small.rightChild().setParent(sParent);
			}
		}		
	}
    
	//returns the Pel object with the smallest key larger than the given one, returns null if the given key has no successor
	public Pel successor(BNode r, Location key) {
		//if r is a leaf return null
		if(r.isLeaf()) {
			return null;
		}
		//else find the node that is smaller than current node
		else {
			BNode myNode = findNode(r,key);
			if(!myNode.isLeaf() && !myNode.rightChild().isLeaf()) {
				return smallestNode(myNode.rightChild()).getData();
			}
			//else go through parents
			else {
				BNode parent = myNode.parent();
				while(parent != null && parent.rightChild() == myNode) {
					myNode = parent;
					parent = parent.parent();
				}
				if(parent != null) {
					return parent.getData();
				}	
				return null;	
			}
		}
	}
    
	//returns the Pel object with the largest key smaller than the given one, returns null if the given key has no predecessor
	public Pel predecessor(BNode r, Location key) {
		//if r is a leaf return null
		if(r.isLeaf()){
			return null;
		}
		//else find the node that is smaller than current node
		else{
		  BNode myNode = findNode(r,key);
		  if (!myNode.isLeaf() && !myNode.leftChild().isLeaf()){
			return largestNode(myNode.leftChild()).getData();
		}
		  //else go through parents
		  else{
			  BNode pNode = myNode.parent();
			  while ( pNode!= null && pNode.leftChild()==(myNode)){
				  myNode = pNode;  
				  pNode = pNode.parent();
			  }
			  if(pNode == null){
				  
				  return null;
			  }
			  else{
			     return pNode.getData();
			  }
		   }
		}
	}
	
	//Returns the Pel object storing the given key, if the key is stored in the tree; returns null otherwise
	public Pel get(BNode r, Location key) {
		return findNode(r,key).getData();
	}

	//Inserts newData in the tree if no data item with the same key is already there
	public void put(BNode r, Pel data) throws DuplicatedKeyException{
		//finding position where pixel should be put
		BNode node = findNode(r, data.getLocus());
		
		//if the node is not null, throw exception
		if(!node.isLeaf()) throw new DuplicatedKeyException("Duplicate key detected!");
		
		//else store it in the tree
		else {
			node.setContent(data);
			BNode leftC = new BNode();
			BNode rightC = new BNode();
			node.setLeftChild(leftC);
			node.setRightChild(rightC);
			leftC.setParent(node);
			rightC.setParent(node);
		}
	}
	
	//returns the Pel object in the tree with the smallest key
	public Pel smallest(BNode r) throws EmptyTreeException{
		//if root is a leaf return null
		if (this.getRoot().isLeaf()) {
			throw new EmptyTreeException("Empty tree detected!");
		}
		//else left-most node's parent pel
		else {
			BNode n = r;
			while (!n.isLeaf()) {
				n = n.leftChild();
			}
			return n.parent().getData();
		}
	}

	//returns the Pel object in the tree with the largest key
	public Pel largest(BNode r) throws EmptyTreeException{
		//if root is a leaf return null
		if (this.getRoot().isLeaf()) {
			throw new EmptyTreeException("Empty tree detected!");
		}
		//else right-most node's parent pel
		else {
			BNode n = r;
			while (!n.isLeaf()) {
				n = n.rightChild();
			}
			return n.parent().getData();
		}
	}
	
	//returns the root of the binary search tree
	public BNode getRoot() {
		return root;
	}
	
	//my private helper method to find and return the parent of the smallest node in the tree 
	private BNode smallestNode(BNode r) {
		BNode node = r;
		    //while node is not a leaf return the left child of node
			while (!node.isLeaf()){
				node = node.leftChild();
			}
			//once left-most node found return its parent
			return node.parent();	
	}
	
	//my private helper method to find and return the parent of the smallest node in the tree
	private BNode largestNode(BNode r) {
		BNode node = r;
		    //while node is not a leaf return the right child of node
			while (!node.isLeaf()){
				node = node.rightChild();
			}
			//once right-most node found return its parent
			return node.parent();
	}
	
	//my private helper method to find and return the node containing a specified key
    private BNode findNode(BNode r, Location key) {
		
    	//if r is a leaf, return r 
		if (r.isLeaf()) return r;
		
		//else if location is equal return r, if greater than this node go left, otherwise go right
		else {		
			int node_cmp = r.getData().getLocus().compareTo(key);

			if(node_cmp == 0) return r;
			
			else if(node_cmp == 1) return findNode(r.leftChild(),key);
			
			else return findNode(r.rightChild(),key);
		}
	}  
}
