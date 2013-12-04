
public class Player {
	//Player has a location
	private Location playerLoc;
	
	//player should have an angle with respect to the board
	private int angle;
	
	public Player(double x, double y, int A) {
		playerLoc = new Location(x,y);
		if(A>=0 & A < 360){
			angle = A;
		}
	}
	
	//increment the location
	public void incrementLoc(double dx, double dy) {
		playerLoc.increment(dx, dy);
	}
	
	
	//increment the angle
	public void incrementAngle(int dA){
		int temp = angle + dA;
		if(temp >= 360) {
			angle = temp - angle;
		}
		else if (temp < 0) {
			angle = 360 + temp;
		}
	}
	
	public double getX() {
		return playerLoc.getX();
	}
	
	public double getY() {
		return playerLoc.getY();
	}
	
	public int getAngle() {
		return angle;
	}
}
