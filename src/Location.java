

public class Location{
	//coordinate location
	private int x;
	private int y;
	
	
	
	//instantiate a new location from coordinates
	public Location(int x1, int y1) {
		x = x1;
		y = y1;
	}
	
	//instantiate a new location from an existing location
	public Location(Location loc) {
		x = loc.getX();
		y = loc.getY();
	}
	
	//get the x coordinate
	public int getX() {
		return x;
	}
	
	//get the y coordinate
	public int getY() {
		return y;
	}
	
	//change the values of x and y coordinate
	public void setLocation(int x1, int y1) {
		x = x1;
		y = y1;
	}
	
	
	//if incrementing is necessary
	protected void increment(double dx, double dy) {
		x += dx;
		y += dy;
	}
	
	public double dist(Location loc1) {
		return Math.sqrt(Math.pow((loc1.getX() - this.getX()),2) + Math.pow((loc1.getY() - this.getY()),2));
	}
}
