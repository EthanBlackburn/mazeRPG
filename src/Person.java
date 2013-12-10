import java.lang.Math.*;
import java.util.ArrayList;

public abstract class Person {
	
	//a person will always have a location
	private Location loc;
	private Vertex vert;
	private Path path;
	protected int maxHealth;
	private int health;
	private boolean isAlive;
	
	protected Person(Path p, Vertex v) {
		vert = new Vertex(v);
		loc = new Location(v.getX(), v.getY());
		maxHealth = 100;
		health = 100;
		isAlive = true;
		path = p;
	}
	
	//get the current health
	public int getHealth() {
		return health;
	}
	
	//set the current health to h
	public void setHealth(int h) {
		if (h > maxHealth){
			health = maxHealth;
		}
		else {
			health = h;
		}
	}
	
	//increment the current health
	public void incrementHealth(int dh) {
		if (health + dh > maxHealth) {
			health = maxHealth;
		}
		else {
			health += dh;
		}
	}
	
	
	//get the person's current closest vertex
	public Vertex getVertex() {
		return vert;
	}
	
	//get the person's Location
	public Location getLocation() {
		return loc;
	}
	
	//get the person's x coordinate
	public Double getX() {
		return loc.getX();
	}
	
	//get the person's y coordinate
	public Double getY() {
		return loc.getY();
	}
	
	//move the person
	public void move(double dx, double dy) {
		if(!isValidMove(dx,dy)){
			return;
		}
		loc.increment(dx, dy);
		//change vert if the person moves closer to either the forward or backward Vertices than the current vert
		if(loc.dist(vert.getLocation()) > loc.dist(path.forward(vert).getLocation())) {
			vert = path.forward(vert);
		}
		else if(loc.dist(vert.getLocation()) > loc.dist(path.backward(vert).getLocation())) {
			vert = path.backward(vert);
		}
	}
	
	//check the validity of a move in the path
	public boolean isValidMove(double dx, double dy) {
		//temp is the potentional next location
		Location temp = new Location(loc);
		temp.increment(dx,dy);
		//temp2 is a place holder for the location of the forward and backward vertex;
		Location forwardLoc = path.forward(vert).getLocation();
		Location backwardLoc = path.backward(vert).getLocation();
		if(loc.dist(forwardLoc) > temp.dist(forwardLoc)) {
			return true;
		}
		else if(loc.dist(backwardLoc) > temp.dist(backwardLoc)) {
			return true;
		}
		else {
			return false;
		}
	}
}
