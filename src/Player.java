
public class Player {
	//Player has a location
	private Location playerLoc;
	
	//player should have an angle with respect to the board
	private int direction;
	
	public Player(double x, double y, int A) {
		playerLoc = new Location(x,y);
		if(A>=0 & A < 4){
			direction = A;
		}
	}
	
	//increment the location
	public void move(double dx, double dy) {
		playerLoc.increment(dx, dy);
	}
	
	
	//increment the angle
	public void incrementAngle(int dA){
		int temp = direction + dA;
		if(temp >= 4) {
			direction = temp - direction;
		}
		else if (temp < 0) {
			direction = 4 + temp;
		}
	}
	
	public double getX() {
		return playerLoc.getX();
	}
	
	public double getY() {
		return playerLoc.getY();
	}
}
