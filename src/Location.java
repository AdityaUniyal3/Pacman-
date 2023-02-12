public class Location {
//instance variables for this class
int x;
int y;
	
    //constructor that initializes this Location object with the specified coordinates
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//returns the x coordinate of this Location
	public int getx() {
		return x;
	}
	
	//returns the y coordinate of this Location
	public int gety() {
		return y;
	}
	
	//compares two objects of type location and 
	public int compareTo(Location p){
		if (this.gety() > p.gety() || this.gety() == p.gety() && this.getx() > p.getx()) {
			return 1;
		}
		
		if (this.getx() == p.getx() && this.gety() == p.gety()) {
			return 0;
		}
		
		if (this.gety() < p.gety() || this.gety() == p.gety() && this.getx() < p.getx()) {
			return -1;
		}
		
		return -2;
	}
}
