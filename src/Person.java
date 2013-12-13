
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public abstract class Person{
	
	//a person will always have a location
	protected Location loc;
	protected Vertex vert;
	protected Path path;
	protected int maxHealth;
	private int health;
	private boolean isAlive;
	protected int attack;
	private BufferedImage image;
	private String Direction;
	
	protected Person(Path p, Vertex v) {
		Direction = "down";
		vert = new Vertex(v);
		loc = new Location(v.getX(), v.getY());
		maxHealth = 100;
		health = 100;
		isAlive = true;
		path = p;
	}
	public void setDirection(String s){
		Direction = s;
	}
	
	public String getDirection(){
		return Direction;
	}
	
	public void setImage(BufferedImage im) {
		image = im;
	}
	
	//return the image of the character
	public BufferedImage getImage() {
		if(image == null) return null;
		return image;
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
	
	public int getAttack(){
		return attack;
	}
	
	public void setAttack(int a){
		attack = a;
	}
	
	public void Attack(String s){
		URL url;
		if(s =="up"){
			url = getClass().getResource("/resources/attacks/blast_up.png");
		}
		else if(s =="down"){
			url = getClass().getResource("/resources/attacks/blast_down.png");
		}
		else if(s =="left"){
			url = getClass().getResource("/resources/attacks/blast_left.png");
		}
		else{
			url = getClass().getResource("/resources/attacks/blast_right.png");
		}
		
		
		try {                
	         image = ImageIO.read(url);
	    } catch (IOException ex) {
	           System.out.println("couldnt load blast image");
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
	public int getX() {
		return (int)loc.getX();
	}
	
	
	//get the person's y coordinate
	public int getY() {
		return (int)loc.getY();
	}
	
	
	//move the person
	public void move(double dx, double dy) {
		if(!isValidMove(dx,dy)){
			return;
		}
		loc.increment(dx, dy);
		double tempx = loc.getX();
		double tempy = loc.getY();
		for(int i = 0; i<vert.connections.size(); i++){
			if(vert.connections.get(i).getX() == tempx & vert.connections.get(i).getY() == tempy) {
				int deltax = vert.getX() - vert.connections.get(i).getX();
				int deltay = vert.getX() - vert.connections.get(i).getX();
				if(deltax == 1){
					Direction = "right";
				}
				else if(deltax == -1){
					Direction = "left";
				}
				else if(deltay == 1){
					Direction = "down";
				}
				else if(deltax == -1){
					Direction = "up";
				}
				vert = vert.connections.get(i);
				
				return;
			}
		}
	}
	
	public void moveTo(Vertex v){
		if(isValidMove(v.getX(),v.getY())){
			loc.setLocation(v.getX(), v.getY());
		}
		
	}
	
	//check the validity of a move in the path
	public boolean isValidMove(double dx, double dy) {
		double tempx = loc.getX()+dx;
		double tempy = loc.getY()+dy;
		double temp2x = loc.getX() +2*dx;
		double temp2y = loc.getY() + 2*dy;
		for(int i = 0; i<vert.connections.size(); i++){
			double potx = vert.connections.get(i).getX();
			double poty = vert.connections.get(i).getY();
			if((potx == tempx & poty == tempy) | (potx == temp2x & poty == temp2y)) {
				return true;
			}
		}
		if(vert.getX() == loc.getX() + dx & vert.getY() == loc.getY() + dy) {
			return true;
		}
		return false;

	}
}
