public class Pel {
//my instance variables for this class
Location loc;
int color;
    
   //constructor which initializes the new Pel with the specified coordinates and color
	public Pel(Location p, int color) {
		loc = p;
		this.color = color;
	}
	
	//returns the Location of this Pel
	public Location getLocus() {
		return loc;
	}
	
	//returns the color of this Pel object
	public int getColor() {
	    return color;
	}
}
