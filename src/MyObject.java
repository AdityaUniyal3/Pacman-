
public class MyObject implements MyObjectADT{
	//my instance variable for this class
	int id;
	int width;
	int height;
	String type;
	BinarySearchTree myTree;
	Location position;
	
	//initializes BST where the pixels of the object will be stored
	public MyObject (int id, int width, int height, String type, Location pos) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.position = pos;	
		myTree = new BinarySearchTree();
	}
	
	//sets the type of this MyObject to the specified value
	public void setType(String type){
		this.type = type;
	}
	
	//returns the width of the enclosing rectangle for this MyObject
	public int getWidth(){
		return this.width;
	}
	
	//returns the height of the enclosing rectangle for this MyObject
	public int getHeight(){
		return this.height;
	}
	
	//returns the type of this MyObject
	public int getId(){
		return this.id;
	}
	
	//returns the id of this MyObject
	public String getType(){
		return this.type;
	}
	
	//returns the locus of this MyObject
	public Location getLocus(){
		return this.position;
	}
	
	//changes the locus of this MyObject to the specified value
	public void setLocus(Location value){
		this.position = value;
	}
	
	//inserts pix into the binary search tree associated with this MyObject. Throws a DuplicatedKeyException if an error occurs when inserting the Pel object pix into the tree
	public void addPel(Pel pix) throws DuplicatedKeyException {
		myTree.put(myTree.getRoot(),pix);
	}
	
	//helper method which returns true if this object has a pixel in location p or returns false otherwise.
	private boolean findPel(Location p) {
		Pel temp = myTree.get(myTree.getRoot(), p);
		if(temp == null) {
			return false;
		}
		return true;
	}
	
	//returns true if this MyObject intersects the one specified in the parameter, returns false otherwise
	public boolean intersects(MyObject otherObject) {
		//instance variables for this method storing the x and y values for this and other object
		Pel smallest = null;
		int otherX = otherObject.getLocus().getx();
		int otherY = otherObject.getLocus().gety();
		int thisX = getLocus().getx();
		int thisY = getLocus().gety();
		
		try {
			smallest = otherObject.myTree.smallest(myTree.getRoot());
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//go through the entire tree ,from smallest to largest
		while(myTree.successor(myTree.getRoot(), smallest.getLocus()) != null) {
			int x,y;
			x = smallest.getLocus().getx()+thisX-otherX;
			y = smallest.getLocus().gety()+thisY-otherY;
			
			if(otherObject.findPel(new Location(x,y))) return true;
			
			smallest = myTree.successor(myTree.getRoot(), smallest.getLocus());
		}
		
		return false;
	}
	
}
